package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Baidu;
import com.mbyte.easy.admin.service.IBaiduService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Cache;

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
@RequestMapping("/admin/baidu")
public class BaiduController extends BaseController  {

    private String prefix = "admin/baidu/";

    @Autowired
    private IBaiduService baiduService;
    
    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param baidu
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Baidu baidu) {
        Page<Baidu> page = new Page<Baidu>(pageNo, pageSize);
        QueryWrapper<Baidu> queryWrapper = new QueryWrapper<Baidu>();

        if(baidu.getTitle() != null  && !"".equals(baidu.getTitle() + "")) {
            queryWrapper = queryWrapper.like("title",baidu.getTitle());
         }


        if(baidu.getCreattime() != null  && !"".equals(baidu.getCreattime() + "")) {
            queryWrapper = queryWrapper.like("creattime",baidu.getCreattime());
         }


        if(baidu.getKeyword() != null  && !"".equals(baidu.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",baidu.getKeyword());
         }


        if(baidu.getUsername() != null  && !"".equals(baidu.getUsername() + "")) {
            queryWrapper = queryWrapper.like("username",baidu.getUsername());
         }

        IPage<Baidu> pageInfo = baiduService.page(page, queryWrapper);
        model.addAttribute("searchInfo", baidu);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"baidu-list";
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
    * @param baidu
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Baidu baidu){
        return toAjax(baiduService.save(baidu));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("baidu",baiduService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param baidu
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Baidu baidu){
        return toAjax(baiduService.updateById(baidu));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(baiduService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(baiduService.removeByIds(ids));
    }

}

