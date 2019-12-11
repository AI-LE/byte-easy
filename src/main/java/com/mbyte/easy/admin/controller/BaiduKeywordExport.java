package com.mbyte.easy.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.admin.entity.BaidukeywordRecords;
import com.mbyte.easy.admin.entity.KeywordIndex;
import com.mbyte.easy.admin.entity.TRecordssum;
import com.mbyte.easy.admin.service.IBaidukeywordRecordsService;
import com.mbyte.easy.admin.service.IKeywordIndexService;
import com.mbyte.easy.admin.service.ITRecordssumService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.util.Utility;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author 艾乐
 * 导出百度关键字及其联想词
 * 多例，可并发抓取
 */
@Controller
@Scope(value = "prototype")
@RequestMapping("/bdkeyExport")
public class BaiduKeywordExport extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaiduKeywordExport.class);

    //关键字联想值连接
    private String keywordStr = "https://www.baidu.com/sugrec?pre=1&p=3&ie=utf-8&json=1&prod=pc&from=pc_web&wd=";

    //关键字的百度指数获取
    private String indexStr = "http://rank.chinaz.com/ajaxsync.aspx?at=index&kw=";


    @Autowired
    private IBaidukeywordRecordsService iBaidukeywordRecordsService;

    @Autowired
    private IKeywordIndexService iKeywordIndexService;

    @Autowired
    private ITRecordssumService itRecordssumService;

    private Integer number = 0; //插入数据库时的序号

    private int index = 0;  //记录遍历数据库查询记录时的序号

    //给某个关键字的抓取添加唯一标识，这样可以并发同时抓取，同过该字段区分
    private String uuid;

    //当连接超时时，将URL放进去接着请求，最多2次，2次后还请求超时，就放弃该URL
    private int deepin = 0;

    @Value("${file.upload.local.path}")
    private String fileLocalPath;

    /**
     * 百度关键字及索引抓取的入口
     * @param keyword
     * @throws UnsupportedEncodingException
     * @throws InterruptedException
     */
    @GetMapping("catchBaiduKeyword/{keyword}/{keywordid}")
    @ResponseBody
    public String catchBaiduKeyword(@PathVariable("keyword") String keyword, @PathVariable("keywordid") Integer keywordid) throws IOException, InterruptedException {
        //每次抓取都得记录入库
        BaidukeywordRecords baidukeywordRecords;
        baidukeywordRecords = new BaidukeywordRecords();
        baidukeywordRecords.setKeyword(keyword);
        baidukeywordRecords.setCatchtime(LocalDateTime.now());
        baidukeywordRecords.setUsername(Utility.getCurrentUsername());
        baidukeywordRecords.setKeywordid(keywordid);
        baidukeywordRecords.setUuid(UUID.randomUUID().toString().replaceAll("-", "").substring(0,11));
        uuid = baidukeywordRecords.getUuid();
        JSONObject indexObj = getJSON(indexStr + URLEncoder.encode(keyword.replace("'","").replace("\\",""),"utf-8"));
        Integer bdindex;
        try{
            bdindex = Integer.parseInt(indexObj.getString("index"));
        }
        catch (Exception e){
            bdindex = 0;    //当格式转换异常时，说明百度指数为0
        }
        KeywordIndex keywordIndex = new KeywordIndex();
        keywordIndex.setData(keyword);
        keywordIndex.setBdindex(bdindex);
        keywordIndex.setNumber(number++);
        keywordIndex.setUuid(uuid);
        logger.info("关键字:" + keyword + "-----百度指数：" + bdindex);
        iKeywordIndexService.save(keywordIndex);
        while (index < iKeywordIndexService.selectTotalNumber(uuid) && iKeywordIndexService.selectTotalNumber(uuid) < 10000){
            QueryWrapper<KeywordIndex> queryWrapper = new QueryWrapper<KeywordIndex>();
            queryWrapper.eq("number",index++).eq("uuid",uuid);//按序号从数据库取出数据，全局变量index防止取到相同的数据，且会小于数据库数据总量
            getKeywordFile(iKeywordIndexService.getOne(queryWrapper).getData());
        }
        downloadAllClassmate(baidukeywordRecords); //导出excel表
        iBaidukeywordRecordsService.save(baidukeywordRecords);
        return baidukeywordRecords.getKeyword() + baidukeywordRecords.getUuid() ;
    }

    /**
     *  传入关键字，开始抓取
     * @param keyword
     * @throws UnsupportedEncodingException
     */
    public void getKeywordFile(String keyword) throws UnsupportedEncodingException, InterruptedException {
        keyword = URLEncoder.encode(keyword,"utf-8").replace("'","").replace("\\","");
        String url = keywordStr + keyword;
        JSONObject jsonObj = getJSON(url);  //获得含关键字json值
        JSONArray array =jsonObj.getJSONArray("g");
        if(array != null){
            for(int i = 0;i < array.size();i++){
                JSONObject data = array.getJSONObject(i);
                //获得含百度指数的json值
                QueryWrapper<KeywordIndex> queryWrapper = new QueryWrapper<KeywordIndex>();
                queryWrapper.eq("data",data.getString("q")).eq("uuid",uuid);
                if(iKeywordIndexService.getOne(queryWrapper) == null){
                    JSONObject indexObj = getJSON( indexStr + URLEncoder.encode(data.getString("q").replace("'","").replace("\\",""),"utf-8"));
                    Integer bdindex;
                    try{//如果转换为指数时异常，则说明该指数只能是0
                        bdindex = Integer.parseInt(indexObj.getString("index"));
                    }
                    catch (Exception e){
                        bdindex = 0;
                    }
                    KeywordIndex keywordIndex = new KeywordIndex();
                    keywordIndex.setData(data.getString("q"));
                    keywordIndex.setBdindex(bdindex);
                    keywordIndex.setNumber(number);//给每条数据安排序号，从数据库取出数据加入抓取函数时，需要按照序号取出，防止取到相同的，或者空的
                    keywordIndex.setUuid(uuid);
                        logger.info("关键字:" + data.getString("q") + "-----百度指数：" + bdindex);
                    iKeywordIndexService.save(keywordIndex);
                    number++;
                    Thread.sleep(5000);//防止封ip
                }
            }

        }
    }

    /**
     *  通过url请求网页，获取返回的值
     * @param url
     * @return  返回值的json形式
     */
    public JSONObject getJSON(String url){
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            if(deepin++ > 0){
                deepin = 0;//这次请求结束，下一次还是从0开始
                return JSON.parseObject("{}");
            }
            logger.info("正在重新请求！");
            return getJSON(url);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                logger.info("closing failed!");
            }
        }
        // 如果是获取百度指数的返回值，形式不是标准的json，得做一些操作
        if(result.contains("({")){
            int temp = result.indexOf("(");
            result = result.substring(temp + 1,result.length() - 1);
        }
        //如果没有爬取到网页，则result是  "" 空字符串，得转一下格式，防止空指针异常
        if(result == ""){
            result = "{}";
        }
        //得到的json数据
        //解析,
        try{
            return JSON.parseObject(result);
        }
        catch (Exception e){
            return JSON.parseObject("{}");
        }
    }

    /**
     * 导出excel为表
     * @throws IOException
     */
    public void downloadAllClassmate(BaidukeywordRecords baidukeywordRecords) throws IOException {
        TRecordssum tRecordssum = new TRecordssum();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("百度关键字");
        String keyword = baidukeywordRecords.getKeyword();
        String downloadUUID = baidukeywordRecords.getUuid();
        String fileName = keyword + downloadUUID + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        String[] headers = {"关键字", "百度指数"};
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //在表中存放查询到的数据放入对应的列
        boolean falg = true;
        for(int page = 0;falg;page++){
            List<KeywordIndex> keywordIndexs = iKeywordIndexService.selectAll(page,downloadUUID);
            if(keywordIndexs.size() != 0){
                for (KeywordIndex keywordIndex : keywordIndexs) {
                    HSSFRow row1 = sheet.createRow(rowNum);
                    row1.createCell(0).setCellValue(keywordIndex.getData());
                    row1.createCell(1).setCellValue(keywordIndex.getBdindex());
                    rowNum++;
                }
            }
            else {
                falg = false;   //数据库数据以取出完毕，结束循环
            }
        }
        long num = (int)iKeywordIndexService.selectTotalNumber(downloadUUID);
        tRecordssum.setRecords(num);
        tRecordssum.setCreatetime(LocalDateTime.now());
        tRecordssum.setType("百度关键字");
        itRecordssumService.save(tRecordssum);
        //将本次抓取查询记录清空，因为导出后数据就不再需要
        iKeywordIndexService.remove(new QueryWrapper<KeywordIndex>().eq("uuid",downloadUUID));
        FileOutputStream output=new FileOutputStream(fileLocalPath + fileName);
        workbook.write(output);//写入磁盘
        output.close();
    }

    @RequestMapping("downloadExcel/{fileName}")
    public ResponseEntity<byte[]> downloadExcel(@PathVariable("fileName") String fileName) throws IOException {
        fileName += ".xls";
        InputStream in=new FileInputStream(new File(fileLocalPath + fileName));//将该文件加入到输入流之中
        byte[] body = new byte[in.available()];// 返回下一次对此输入流调用的方法可以不受阻塞地从此输入流读取（或跳过）的估计剩余字节数
        in.read(body);//读入到输入流里面
        in.close();
        fileName = fileName.substring(0,fileName.length() - 15) + ".xls"; //将不必要的标识后缀去掉
        fileName=new String(fileName.getBytes("UTF-8"),"ISO-8859-1");//防止中文乱码
        HttpHeaders headers=new HttpHeaders();//设置响应头
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        HttpStatus statusCode = HttpStatus.OK;//设置响应吗
        ResponseEntity<byte[]> response=new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

}
