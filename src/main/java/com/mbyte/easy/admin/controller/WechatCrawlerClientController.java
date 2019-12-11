package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.admin.Util.WechatClient;
import com.mbyte.easy.admin.entity.Wechat;
import com.mbyte.easy.admin.entity.WechatContent;
import com.mbyte.easy.admin.entity.WechatOldrecords;
import com.mbyte.easy.admin.entity.ZhihuRecords;
import com.mbyte.easy.admin.service.IWechatOldrecordsService;
import com.mbyte.easy.admin.service.IWechatService;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.WatchService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.mbyte.easy.admin.Util.WechatClient.wechatContent;

/**
 * @author 张松哲
 * 微博爬取程序
 */


@Controller
@RequestMapping(value = "/wechat/")
public class WechatCrawlerClientController {

    @Autowired
    private IWechatService wechatService;
    @Autowired
    private IWechatOldrecordsService wechatOldrecordsService;

    @Autowired
    private WechatClient wechatClient;

    @Value("${file.upload.local.path}")
    public String uploadLocalPath;

    @ResponseBody
    @RequestMapping(value = "wechatcatch/{id}")
    public void crawlerWechat(@PathVariable("id") Integer id) throws IOException, InvalidFormatException {
        Wechat wechat = wechatService.getById(id);
        String keyword = wechat.getKeyword();
        Integer status = wechat.getStatus();
        if(status == 1){
            wechatClient.titleClient(keyword,keyword);
        }
        if(status == 2){
            wechatClient.titleMessageClient(keyword,keyword);
        }
        WechatOldrecords wechatOldrecords = new WechatOldrecords();
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        wechatOldrecords.setWechatid(id);
        wechatOldrecords.setKeyword(keyword);
        wechatOldrecords.setCreatetime(timechange);
        wechatOldrecords.setStatus(status);
        String path = wechatContent.getPath();
        wechatOldrecords.setPath(path);
        wechatOldrecordsService.save(wechatOldrecords);

    }

    @ResponseBody
    @GetMapping(value = "download")
    public ResponseEntity<byte[]> download() throws IOException {
        //得到爬取导出的word的路径
        String path = wechatContent.getPath();
        String keyword = wechatContent.getKeyword();
        //设置编码
        String downloadFileName = new String(keyword.getBytes("UTF-8"),"iso-8859-1");
        //打开word
        File file=new File(path);
        //请求头部
        HttpHeaders headers = new HttpHeaders();
        //下载请求
        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "download/{id}")
    public ResponseEntity<byte[]> ExportWord(@PathVariable("id")Long id) throws IOException {
        //得到爬取导出的word的路径
        String path= wechatOldrecordsService.getById(id).getPath();
        String keyword =  path.replace(uploadLocalPath,"");
        System.out.println(keyword);
        //设置编码
        String downloadFileName = new String(keyword.getBytes("UTF-8"),"iso-8859-1");
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
