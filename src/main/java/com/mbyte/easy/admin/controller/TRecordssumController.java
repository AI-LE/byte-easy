package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TRecordssum;
import com.mbyte.easy.admin.service.ITRecordssumService;
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
@RequestMapping("/admin/tRecordssum")
public class TRecordssumController extends BaseController  {

    private String prefix = "admin/tRecordssum/";

    @Autowired
    private ITRecordssumService tRecordssumService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param tRecordssum
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, TRecordssum tRecordssum) {
        Page<TRecordssum> page = new Page<TRecordssum>(pageNo, pageSize);
        QueryWrapper<TRecordssum> queryWrapper = new QueryWrapper<TRecordssum>();

        if(tRecordssum.getRecords() != null  && !"".equals(tRecordssum.getRecords() + "")) {
            queryWrapper = queryWrapper.like("records",tRecordssum.getRecords());
         }


        if(tRecordssum.getCreatetime() != null  && !"".equals(tRecordssum.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",tRecordssum.getCreatetime());
         }


        if(tRecordssum.getType() != null  && !"".equals(tRecordssum.getType() + "")) {
            queryWrapper = queryWrapper.like("type",tRecordssum.getType());
         }

        IPage<TRecordssum> pageInfo = tRecordssumService.page(page, queryWrapper);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", tRecordssum);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tRecordssum-list";
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
    * @param tRecordssum
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TRecordssum tRecordssum){
        return toAjax(tRecordssumService.save(tRecordssum));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tRecordssum",tRecordssumService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param tRecordssum
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TRecordssum tRecordssum){
        return toAjax(tRecordssumService.updateById(tRecordssum));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tRecordssumService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tRecordssumService.removeByIds(ids));
    }

}

