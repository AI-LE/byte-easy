package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Zhihu;
import com.mbyte.easy.admin.service.IZhihuService;
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
@RequestMapping("/admin/zhihu")
public class ZhihuController extends BaseController  {

    private String prefix = "admin/zhihu/";

    @Autowired
    private IZhihuService zhihuService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param zhihu
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Zhihu zhihu) {
        Page<Zhihu> page = new Page<Zhihu>(pageNo, pageSize);
        QueryWrapper<Zhihu> queryWrapper = new QueryWrapper<Zhihu>();

        if(zhihu.getTitle() != null  && !"".equals(zhihu.getTitle() + "")) {
            queryWrapper = queryWrapper.like("title",zhihu.getTitle());
         }


        if(zhihu.getCreatetime() != null  && !"".equals(zhihu.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",zhihu.getCreatetime());
         }


        if(zhihu.getKeyword() != null  && !"".equals(zhihu.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",zhihu.getKeyword());
         }


        if(zhihu.getUsername() != null  && !"".equals(zhihu.getUsername() + "")) {
            queryWrapper = queryWrapper.like("username",zhihu.getUsername());
         }

        IPage<Zhihu> pageInfo = zhihuService.page(page, queryWrapper);
        model.addAttribute("searchInfo", zhihu);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"zhihu-list";
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
    * @param zhihu
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Zhihu zhihu){
        return toAjax(zhihuService.save(zhihu));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("zhihu",zhihuService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param zhihu
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Zhihu zhihu){
        return toAjax(zhihuService.updateById(zhihu));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(zhihuService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(zhihuService.removeByIds(ids));
    }

}

