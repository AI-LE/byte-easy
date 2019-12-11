package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TBlogger;
import com.mbyte.easy.admin.service.ITBloggerService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author 吴天豪
* @since 2019-03-11
*/
@Controller
@RequestMapping("/admin/tBlogger")
public class TBloggerController extends BaseController  {

    private String prefix = "admin/tBlogger/";

    @Autowired
    private ITBloggerService tBloggerService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param tBlogger
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, String timestartSpace, String timeendSpace, TBlogger tBlogger) {
        Page<TBlogger> page = new Page<TBlogger>(pageNo, pageSize);
        QueryWrapper<TBlogger> queryWrapper = new QueryWrapper<TBlogger>();

        if(tBlogger.getBloggername() != null  && !"".equals(tBlogger.getBloggername() + "")) {
            queryWrapper = queryWrapper.like("bloggername",tBlogger.getBloggername());
         }


        if(tBlogger.getUid() != null  && !"".equals(tBlogger.getUid() + "")) {
            queryWrapper = queryWrapper.like("uid",tBlogger.getUid());
         }


        if(tBlogger.getContainerid() != null  && !"".equals(tBlogger.getContainerid() + "")) {
            queryWrapper = queryWrapper.like("containerid",tBlogger.getContainerid());
         }


        if(tBlogger.getCreatetime() != null  && !"".equals(tBlogger.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",tBlogger.getCreatetime());
         }


        if(tBlogger.getWordmax() != null  && !"".equals(tBlogger.getWordmax() + "")) {
            queryWrapper = queryWrapper.like("wordmax",tBlogger.getWordmax());
         }


        if(tBlogger.getWordmin() != null  && !"".equals(tBlogger.getWordmin() + "")) {
            queryWrapper = queryWrapper.like("wordmin",tBlogger.getWordmin());
         }


        if(tBlogger.getTimestart() != null  && !"".equals(tBlogger.getTimestart() + "")) {
            queryWrapper = queryWrapper.like("timestart",tBlogger.getTimestart());
         }


        if(tBlogger.getTimeend() != null  && !"".equals(tBlogger.getTimeend() + "")) {
            queryWrapper = queryWrapper.like("timeend",tBlogger.getTimeend());
         }


        if(Utility.getCurrentUser().getUsername() != null ) {
            queryWrapper = queryWrapper.like("username",Utility.getCurrentUser().getUsername());
         }


        if(tBlogger.getCommitchioce() != null  && !"".equals(tBlogger.getCommitchioce() + "")) {
            queryWrapper = queryWrapper.like("commitchioce",tBlogger.getCommitchioce());
         }


        if(tBlogger.getPointersum() != null  && !"".equals(tBlogger.getPointersum() + "")) {
            queryWrapper = queryWrapper.like("pointersum",tBlogger.getPointersum());
         }


        if(tBlogger.getJudge() != null  && !"".equals(tBlogger.getJudge() + "")) {
            queryWrapper = queryWrapper.like("judge",tBlogger.getJudge());
         }
        queryWrapper = queryWrapper.orderByDesc("createtime");
        IPage<TBlogger> pageInfo = tBloggerService.page(page, queryWrapper);

        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("timestartSpace", timestartSpace);
        model.addAttribute("timeendSpace", timeendSpace);
        model.addAttribute("searchInfo", tBlogger);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBlogger-list";
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
    * @param tBlogger
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TBlogger tBlogger){

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);

        String loginUserName = Utility.getCurrentUsername();
        tBlogger.setUsername(loginUserName);
        tBlogger.setCreatetime(timechange);

        return toAjax(tBloggerService.save(tBlogger));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBlogger",tBloggerService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param tBlogger
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBlogger tBlogger){
//        LocalDateTime time = LocalDateTime.now();
//        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
//        String localTime = df.format(time);
//        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
//        tBlogger.setCreatetime(timechange);
        return toAjax(tBloggerService.updateById(tBlogger));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerService.removeByIds(ids));
    }

}

