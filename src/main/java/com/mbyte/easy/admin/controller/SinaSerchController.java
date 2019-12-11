package com.mbyte.easy.admin.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.Util.ExportWord;
import com.mbyte.easy.admin.entity.SinaHistory;
import com.mbyte.easy.admin.entity.SinaSerch;
import com.mbyte.easy.admin.service.ISinaHistoryService;
import com.mbyte.easy.admin.service.ISinaSerchService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author 张虎
 * @since 2019-07-12
 */
@Controller
@RequestMapping("/admin/sinaSerch")
public class SinaSerchController extends BaseController  {

    private String prefix = "admin/sinaSerch/";

    @Autowired
    private ISinaSerchService sinaSerchService;

    @Autowired
    private ISinaHistoryService sinaHistoryService;

    //用来存放搜索词
    public static List<String> serchList = new ArrayList<>();

    //默认下载路径
    @Value("${file.upload.local.path}")
    private String localPath ;

    /**
     * 递归进行微博搜索
     * @param serchKeyWord 搜索输入内容
     * @throws IOException
     */
    public  static void sinaSerchCrawler(String serchKeyWord)throws IOException {
        //获取搜索联想的url
        String sinaSerch = serchKeyWord.replace(" ","");
        String url = "https://s.weibo.com/ajax/topsuggest.php?key=";
        url += sinaSerch;
        HttpClient client = HttpClients.createDefault();
        //创建httpget实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpget实例
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Host", "s.weibo.com");
        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

        try {
            // 添加时间间隔 1s  解决禁止访问问题。
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //执行 get请求
        HttpResponse httpResponse = client.execute(httpGet);
        //获取网页内容
        HttpEntity entity =  httpResponse.getEntity();
        //返回请求的实体
        String html = EntityUtils.toString(entity, "UTF-8");
        //关闭请求资源
        httpClient.close();

        //解析返回的数据
        Document document = Jsoup.parse(html);
        /**
         * 获取搜索联想内容并转化为json数组的格式
         */
        String arrayBefore = document.text().split("rys\":")[1];
        String jsonArrayBefore = arrayBefore.split(",\"wei")[0];
        JSONArray jsonArray = JSONArray.parseArray(jsonArrayBefore);
        /**
         * 递归进行联想内容的搜索查询爬取
         */
        for(int i = 0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if(!serchList.contains(jsonObject.get("key").toString())){
                serchList.add(jsonObject.get("key").toString());
                sinaSerchCrawler(jsonObject.get("key").toString());
            }
        }
    }

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param sinaSerch
     * @return
     */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String creatTimeSpace, SinaSerch sinaSerch) {
        Page<SinaSerch> page = new Page<SinaSerch>(pageNo, pageSize);
        QueryWrapper<SinaSerch> queryWrapper = new QueryWrapper<SinaSerch>();

        if(sinaSerch.getSerchName() != null  && !"".equals(sinaSerch.getSerchName() + "")) {
            queryWrapper = queryWrapper.like("serchName",sinaSerch.getSerchName());
        }
        queryWrapper = queryWrapper.orderByDesc("creatTime");
        IPage<SinaSerch> pageInfo = sinaSerchService.page(page, queryWrapper);
        model.addAttribute("creatTimeSpace", creatTimeSpace);
        model.addAttribute("searchInfo", sinaSerch);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"sinaSerch-list";
    }

    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
     * 添加
     * @param sinaSerch
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(SinaSerch sinaSerch)throws IOException{
        sinaSerch.setCreatTime(LocalDateTime.now());
        return toAjax(sinaSerchService.save(sinaSerch));
    }

    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("sinaSerch",sinaSerchService.getById(id));
        return prefix+"edit";
    }

    /**
     * 查看
     * @param sinaSerch
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(SinaSerch sinaSerch){
        return toAjax(sinaSerchService.updateById(sinaSerch));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(sinaSerchService.removeById(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(sinaSerchService.removeByIds(ids));
    }

    /**
     *  爬取并导出微博搜索关键字
     * @param id 选中 id
     * @throws IOException
     */
    @PostMapping("export")
    @ResponseBody
    public void crawlerSinaSerch(@RequestParam("id")  Long id)throws IOException{

        SinaSerch  sinaSerch = sinaSerchService.getById(id);
        sinaSerchCrawler(sinaSerch.getSerchName());

        SinaHistory sinaHistory = new SinaHistory();
        sinaHistory.setUuid(id);
        sinaHistory.setKeyword(sinaSerch.getSerchName());
        sinaHistory.setSerchKeyword("");
        sinaHistory.setCrawlerTime(LocalDateTime.now());

        String path = "";
        path += localPath;
        path += sinaSerch.getSerchName();
        String time = sinaSerch.getCreatTime().toString();
        path += time.split("T")[0];
        path += ".doc";
        sinaHistory.setPath(path);

        sinaSerch.setContent("");

        String word = "";
        word += "搜索关键字：";
        word += sinaSerch.getSerchName();
        word += "\n";
        word += "联想关键字：\n";
        for(String serch:serchList){
            word += serch;
            word += "\n";
            sinaHistory.setSerchKeyword(sinaHistory.getSerchKeyword() + "," + serch);
            sinaSerch.setContent(sinaSerch.getContent() + serch);
        }

        sinaHistoryService.save(sinaHistory);
        sinaSerchService.updateById(sinaSerch);
        ExportWord exportWord = new ExportWord();
        exportWord.creatDoc(path,word);
        serchList.clear();
    }
    @ResponseBody
    @GetMapping(value = "download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {

        //得到爬取导出的word的路径
        SinaSerch sinaSerch = sinaSerchService.getById(id);
        //下载文件地址
        String path = "";
        path += localPath;
        path += sinaSerch.getSerchName();
        String time = sinaSerch.getCreatTime().toString();
        path += time.split("T")[0];
        path += ".doc";
        //下载文件名
        String name = "";
        name += sinaSerch.getSerchName();
        name += time.split("T")[0];
        name += ".doc";
        //设置编码
        String downloadFileName = new String(name.getBytes("UTF-8"),"iso-8859-1");
        //打开word
        File file=new File(path);
        //请求头部
        HttpHeaders headers = new HttpHeaders();
        //下载请求
        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }


}

