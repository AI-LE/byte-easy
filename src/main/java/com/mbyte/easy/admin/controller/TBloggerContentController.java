package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TBloggerContent;
import com.mbyte.easy.admin.service.ITBloggerContentService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
* <p>
* 前端控制器
* </p>
* @author 吴天豪
* @since 2019-03-11
*/
@Controller
@RequestMapping("/admin/tBloggerContent")
public class TBloggerContentController extends BaseController  {

    private String prefix = "admin/tBloggerContent/";

    @Autowired
    private ITBloggerContentService tBloggerContentService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param tBloggerContent
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, TBloggerContent tBloggerContent) {
        Page<TBloggerContent> page = new Page<TBloggerContent>(pageNo, pageSize);
        QueryWrapper<TBloggerContent> queryWrapper = new QueryWrapper<TBloggerContent>();

        if(tBloggerContent.getContent() != null  && !"".equals(tBloggerContent.getContent() + "")) {
            queryWrapper = queryWrapper.like("content",tBloggerContent.getContent());
         }


        if(tBloggerContent.getBloggerid() != null  && !"".equals(tBloggerContent.getBloggerid() + "")) {
            queryWrapper = queryWrapper.like("bloggerid",tBloggerContent.getBloggerid());
         }


        if(tBloggerContent.getContentrealy() != null  && !"".equals(tBloggerContent.getContentrealy() + "")) {
            queryWrapper = queryWrapper.like("contentrealy",tBloggerContent.getContentrealy());
         }


        if(tBloggerContent.getOnlyid() != null  && !"".equals(tBloggerContent.getOnlyid() + "")) {
            queryWrapper = queryWrapper.like("onlyid",tBloggerContent.getOnlyid());
         }


        if(tBloggerContent.getContentid() != null  && !"".equals(tBloggerContent.getContentid() + "")) {
            queryWrapper = queryWrapper.like("contentid",tBloggerContent.getContentid());
         }


        if(tBloggerContent.getCreatetime() != null  && !"".equals(tBloggerContent.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",tBloggerContent.getCreatetime());
         }

        IPage<TBloggerContent> pageInfo = tBloggerContentService.page(page, queryWrapper);
        model.addAttribute("searchInfo", tBloggerContent);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBloggerContent-list";
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
    * @param tBloggerContent
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TBloggerContent tBloggerContent){
        return toAjax(tBloggerContentService.save(tBloggerContent));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBloggerContent",tBloggerContentService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param tBloggerContent
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBloggerContent tBloggerContent){
        return toAjax(tBloggerContentService.updateById(tBloggerContent));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerContentService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerContentService.removeByIds(ids));
    }

}

