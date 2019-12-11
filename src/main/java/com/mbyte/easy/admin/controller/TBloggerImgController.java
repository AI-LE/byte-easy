package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TBloggerImg;
import com.mbyte.easy.admin.service.ITBloggerImgService;
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
@RequestMapping("/admin/tBloggerImg")
public class TBloggerImgController extends BaseController  {

    private String prefix = "admin/tBloggerImg/";

    @Autowired
    private ITBloggerImgService tBloggerImgService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param tBloggerImg
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, TBloggerImg tBloggerImg) {
        Page<TBloggerImg> page = new Page<TBloggerImg>(pageNo, pageSize);
        QueryWrapper<TBloggerImg> queryWrapper = new QueryWrapper<TBloggerImg>();

        if(tBloggerImg.getImgsourceurl() != null  && !"".equals(tBloggerImg.getImgsourceurl() + "")) {
            queryWrapper = queryWrapper.like("imgsourceurl",tBloggerImg.getImgsourceurl());
         }


        if(tBloggerImg.getImglocalurl() != null  && !"".equals(tBloggerImg.getImglocalurl() + "")) {
            queryWrapper = queryWrapper.like("imglocalurl",tBloggerImg.getImglocalurl());
         }


        if(tBloggerImg.getContentid() != null  && !"".equals(tBloggerImg.getContentid() + "")) {
            queryWrapper = queryWrapper.like("contentid",tBloggerImg.getContentid());
         }

        IPage<TBloggerImg> pageInfo = tBloggerImgService.page(page, queryWrapper);
        model.addAttribute("searchInfo", tBloggerImg);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBloggerImg-list";
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
    * @param tBloggerImg
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TBloggerImg tBloggerImg){
        return toAjax(tBloggerImgService.save(tBloggerImg));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBloggerImg",tBloggerImgService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param tBloggerImg
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBloggerImg tBloggerImg){
        return toAjax(tBloggerImgService.updateById(tBloggerImg));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerImgService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerImgService.removeByIds(ids));
    }

}

