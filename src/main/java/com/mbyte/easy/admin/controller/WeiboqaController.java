package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.Util.BlogQAUtil;
import com.mbyte.easy.admin.entity.BlogAnswers;
import com.mbyte.easy.admin.entity.Weiboqa;
import com.mbyte.easy.admin.entity.Weiboqahistory;
import com.mbyte.easy.admin.service.IWeiboqaService;
import com.mbyte.easy.admin.service.IWeiboqahistoryService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author 魏皓
 * @since 2019-07-10
 */
@Controller
@RequestMapping("/admin/weiboqa")
public class WeiboqaController extends BaseController  {

    private String prefix = "admin/weiboqa/";

    @Autowired
    private IWeiboqaService weiboqaService;

    @Autowired
    private IWeiboqahistoryService weiboqahistoryService;

    @Autowired
    private BlogQAUtil blogQAUtil;

    @Value("${file.upload.local.path}")
    private  String Prefix;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param weiboqa
     * @return
     */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, Weiboqa weiboqa) {
        Page<Weiboqa> page = new Page<Weiboqa>(pageNo, pageSize);
        QueryWrapper<Weiboqa> queryWrapper = new QueryWrapper<Weiboqa>();

        if(weiboqa.getQuestion() != null  && !"".equals(weiboqa.getQuestion() + "")) {
            queryWrapper = queryWrapper.like("question",weiboqa.getQuestion());
        }

        if(weiboqa.getCreatetime() != null  && !"".equals(weiboqa.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",weiboqa.getCreatetime());
        }

        IPage<Weiboqa> pageInfo = weiboqaService.page(page, queryWrapper);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", weiboqa);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"weiboqa-list";
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
     * @param weiboqa
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Weiboqa weiboqa){
        return toAjax(weiboqaService.save(weiboqa));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("weiboqa",weiboqaService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param weiboqa
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Weiboqa weiboqa){
        return toAjax(weiboqaService.updateById(weiboqa));
    }


    /**
     *
     */
    @ResponseBody
    @GetMapping("download")
    public void download(@RequestParam("id")Integer id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(id);
        Weiboqa entity=weiboqaService.getById(id);
        String keyword=entity.getQuestion();
        Boolean choice=entity.getContent();//判断爬取问题还是问题和内容，false代表只爬问题，true代表爬问题和内容
        String timeBetween=entity.getCreatetime();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime  bedginTime=null;
        LocalDateTime  endTime=null;
        try {
            bedginTime = LocalDateTime.parse(timeBetween.split("~")[0].trim(), df);
            endTime = LocalDateTime.parse(timeBetween.split("~")[1].trim(), df);
        }catch (DateTimeParseException e)
        {
            bedginTime=LocalDateTime.parse("2000-01-01 00:00:00",df);
            endTime=LocalDateTime.now();
        }
        String tempPath= blogQAUtil.GetContent(keyword,choice,bedginTime,endTime);
        Weiboqahistory weiboqahistory=new Weiboqahistory();
        weiboqahistory.setCreattime(LocalDateTime.now());
        weiboqahistory.setFilename(entity.getQuestion());
        weiboqahistory.setStatus(entity.getContent());
        weiboqahistory.setPath(tempPath);
        weiboqahistoryService.save(weiboqahistory);
        entity.setPath(tempPath);
        weiboqaService.updateById(entity);
    }



    @ResponseBody
    @GetMapping("reallyDownload")
    public ResponseEntity<byte[]> reallyDownload(@RequestParam("id")Integer id) throws Exception
    {
        String filePath=weiboqaService.getById(id).getPath();
        File file=new File(filePath);
        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filePath.getBytes("UTF-8"),"iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName.substring(7));

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);

    }




    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(weiboqaService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(weiboqaService.removeByIds(ids));
    }

}

