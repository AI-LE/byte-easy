package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.Util.ExportWord;
import com.mbyte.easy.admin.Util.GetArticle;
import com.mbyte.easy.admin.entity.TBloggerArticleOldRecords;
import com.mbyte.easy.admin.entity.TRecordssum;
import com.mbyte.easy.admin.service.ITBloggerArticleOldRecordsService;
import com.mbyte.easy.admin.service.ITBloggerArticleService;
import com.mbyte.easy.admin.service.ITRecordssumService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/*import static com.mbyte.easy.admin.Util.WechatClient.tRecordssum;
import static com.mbyte.easy.admin.Util.WechatClient.wechatContent;*/

@Controller
@RequestMapping("/admin/tBloggerArticleOldRecords")
public class TBloggerArticleOldRecordsController extends BaseController {
    private String prefix="admin/tBloggerArticleOldRecords/";
    public TBloggerArticleOldRecords tBloggerArticleOldRecords=new TBloggerArticleOldRecords();
    @Value("${file.upload.local.path}")
    public String uploadLocalPath;

    @Autowired
    private ITBloggerArticleOldRecordsService tBloggerArticleOldRecordsService;
    @Autowired
    private ITBloggerArticleService tBloggerArticleService;
    @Autowired
    private ITRecordssumService itRecordssumService;

    /**
     * 查询列表
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, TBloggerArticleOldRecords tBloggerArticleOldRecords){
        Page<TBloggerArticleOldRecords> page=new Page<TBloggerArticleOldRecords>(pageNo,pageSize);
        QueryWrapper<TBloggerArticleOldRecords> queryWrapper=new QueryWrapper<TBloggerArticleOldRecords>();
        if(tBloggerArticleOldRecords.getKeywords() != null && !"".equals(tBloggerArticleOldRecords.getKeywords()+"")){
            queryWrapper=queryWrapper.like("keywords",tBloggerArticleOldRecords.getKeywords());
        }
        /*if(!"".equals(tBloggerArticle.getHas_export()+"")){
            queryWrapper=queryWrapper.like("hasExport",tBloggerArticle.getHas_export());
        }*/
        queryWrapper=queryWrapper.orderByDesc("createtime");
        IPage<TBloggerArticleOldRecords> pageInfo=tBloggerArticleOldRecordsService.page(page,queryWrapper);
        model.addAttribute("searchInfo",tBloggerArticleOldRecords);
        model.addAttribute("pageInfo",new PageInfo(pageInfo));
        return prefix+"tBloggerArticleOldRecords-list";
    }
    /**
     * 添加跳转页面
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }
    /**
     * 添加
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TBloggerArticleOldRecords tBloggerArticleOldRecords){
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        tBloggerArticleOldRecords.setCreatetime(timechange);
        return toAjax(tBloggerArticleOldRecordsService.save(tBloggerArticleOldRecords));
    }
    /**
     * 编辑跳转页面
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBloggerArticle",tBloggerArticleOldRecordsService.getById(id));
        return prefix+"edit";
    }
    /**
     * 编辑
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBloggerArticleOldRecords tBloggerArticleOldRecords){
        return  toAjax(tBloggerArticleOldRecordsService.updateById(tBloggerArticleOldRecords));
    }
    /**
     * 删除
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerArticleOldRecordsService.removeById(id));
    }
    /**
     * 批量删除
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerArticleOldRecordsService.removeByIds(ids));
    }

    /**
     * 下载
     */
    @RequestMapping("downloadToWord")
    public void downloadToWord(Model model, HttpServletResponse response, HttpServletRequest request) throws Exception{
        TRecordssum tRecordssum = new TRecordssum();
        //存入数据库
        long id = Long.parseLong(request.getParameter("id"));
        String keywords=tBloggerArticleService.getById(id).getKeywords();
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        tBloggerArticleOldRecords.setKid(id);
        tBloggerArticleOldRecords.setKeywords(keywords);
        tBloggerArticleOldRecords.setCreatetime(timechange);
        Date date=new Date();
        long timeJudge=date.getTime();
        tBloggerArticleOldRecords.setTimejudge(timeJudge);
        GetArticle testXL=new GetArticle();
        Integer articlePage=1;//需要爬取页数
        String filePaath="";
        Document doc =null;
        boolean flag=true;
        int t=0;
        while (flag && t<10) {
            try {
                t++;
                try {
                    // 添加时间间隔 1s  解决 418问题。
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doc = Jsoup.connect("https://s.weibo.com/article?q=" + keywords + "&Refer=weibo_article" + "&page=" + articlePage)
                        .timeout(5000)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                        .get();
                flag=false;
            }catch (IOException e){
                System.out.println("网络不给力，正在重新加载.......");
            }
        }
        String articleInfos=doc.toString();
        testXL.getArticlInfo(articleInfos,articlePage);
        System.out.println(testXL.num);
        filePaath= uploadLocalPath+keywords+"_"+timeJudge+".doc";
        /*System.out.println(testXL.getResult());*/
        /*String str=testXL.deleteHtmlTage(testXL.getResult());*/
        ExportWord exportWord = new ExportWord();
        exportWord.creatDoc(filePaath,testXL.getResult());
        tBloggerArticleOldRecordsService.save(tBloggerArticleOldRecords);
        QueryWrapper<TBloggerArticleOldRecords> queryWrapper=new QueryWrapper<TBloggerArticleOldRecords>();
        queryWrapper=queryWrapper.eq("timejudge",timeJudge);
        long articleID=tBloggerArticleOldRecordsService.getOne(queryWrapper).getId();
        response.getWriter().write(String.valueOf(articleID));
        tRecordssum.setRecords(testXL.num);
        tRecordssum.setType("微博文章");
        tRecordssum.setCreatetime(LocalDateTime.now());
        itRecordssumService.save(tRecordssum);
    }

    /**
     * 获取爬取的历史纪录
     */
    @GetMapping("oldrecords/{id}")
    public String getRecords(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                             @PathVariable("id")Long id){
        Page<TBloggerArticleOldRecords> page=new Page<TBloggerArticleOldRecords>(pageNo,pageSize);
        QueryWrapper<TBloggerArticleOldRecords> queryWrapper = new QueryWrapper<TBloggerArticleOldRecords>();
        queryWrapper=queryWrapper.eq("kid",id);
        queryWrapper=queryWrapper.orderByDesc("createtime");
        IPage<TBloggerArticleOldRecords> pageInfo=tBloggerArticleOldRecordsService.page(page,queryWrapper);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBloggerArticleOldRecords-list";
    }

    @RequestMapping("/OldExportWord")
    public void OldExportWord(Model model, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        // String filename = request.getParameter("filename");
        try{
            QueryWrapper<TBloggerArticleOldRecords> queryCWrapper1 = new QueryWrapper<TBloggerArticleOldRecords>();
            queryCWrapper1 = queryCWrapper1.eq("id", id);
            String keywords =tBloggerArticleOldRecordsService.getOne(queryCWrapper1).getKeywords();
            long timeJudge =tBloggerArticleOldRecordsService.getOne(queryCWrapper1).getTimejudge();
            String path=uploadLocalPath+keywords+"_"+timeJudge+".doc";
            response.getWriter().write(path);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
    @ResponseBody
    @GetMapping(value = "download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id")Long id) throws IOException {
        //得到爬取导出的word的路径
        TBloggerArticleOldRecords tBloggerArticleOldRecords=tBloggerArticleOldRecordsService.getById(id);
        String path =uploadLocalPath+tBloggerArticleOldRecords.getKeywords()+"_"+tBloggerArticleOldRecords.getTimejudge()+".doc" ;
        String name = tBloggerArticleOldRecords.getKeywords()+"_"+tBloggerArticleOldRecords.getTimejudge()+".doc";
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
