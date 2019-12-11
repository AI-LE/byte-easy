package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TBloggerArticle;
import com.mbyte.easy.admin.service.ITBloggerArticleService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/tBloggerArticle")
public class TBloggerArticleController extends BaseController {
    private String prefix="admin/tBloggerArticle/";

    @Autowired
    private ITBloggerArticleService tBloggerArticleService;

    /**
     * 查询列表
     */
    @RequestMapping
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, TBloggerArticle tBloggerArticle){
        Page<TBloggerArticle> page=new Page<TBloggerArticle>(pageNo,pageSize);
        QueryWrapper<TBloggerArticle> queryWrapper=new QueryWrapper<TBloggerArticle>();
        if(tBloggerArticle.getKeywords() != null && !"".equals(tBloggerArticle.getKeywords()+"")){
            queryWrapper=queryWrapper.like("keywords",tBloggerArticle.getKeywords());
        }
        /*if(!"".equals(tBloggerArticle.getHas_export()+"")){
            queryWrapper=queryWrapper.like("hasExport",tBloggerArticle.getHas_export());
        }*/
        queryWrapper=queryWrapper.orderByDesc("createtime");
        IPage<TBloggerArticle> pageInfo=tBloggerArticleService.page(page,queryWrapper);
        model.addAttribute("searchInfo",tBloggerArticle);
        model.addAttribute("pageInfo",new PageInfo(pageInfo));
        return prefix+"tBloggerArticle-list";
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
    public AjaxResult add(TBloggerArticle tBloggerArticle){
        LocalDateTime time=LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        tBloggerArticle.setCreatetime(timechange);
        return toAjax(tBloggerArticleService.save(tBloggerArticle));
    }
    /**
     * 编辑跳转页面
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBloggerArticle",tBloggerArticleService.getById(id));
        return prefix+"edit";
    }
    /**
     * 编辑
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBloggerArticle tBloggerArticle){
        return  toAjax(tBloggerArticleService.updateById(tBloggerArticle));
    }
    /**
     * 删除
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerArticleService.removeById(id));
    }
    /**
     * 批量删除
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerArticleService.removeByIds(ids));
    }

}
