package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.BdRecords;
import com.mbyte.easy.admin.entity.ZhihuRecords;
import com.mbyte.easy.admin.service.IZhihuRecordsService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@RequestMapping("/admin/zhihuRecords")
public class ZhihuRecordsController extends BaseController  {

    private String prefix = "admin/zhihuRecords/";

    @Autowired
    private IZhihuRecordsService zhihuRecordsService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param zhihuRecords
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, ZhihuRecords zhihuRecords) {
        Page<ZhihuRecords> page = new Page<ZhihuRecords>(pageNo, pageSize);
        QueryWrapper<ZhihuRecords> queryWrapper = new QueryWrapper<ZhihuRecords>();

        if(zhihuRecords.getCreatetime() != null  && !"".equals(zhihuRecords.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",zhihuRecords.getCreatetime());
        }


        if(zhihuRecords.getKeyword() != null  && !"".equals(zhihuRecords.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",zhihuRecords.getKeyword());
        }


            queryWrapper = queryWrapper.like("username", Utility.getCurrentUser().getUsername());//传入当前用户

        queryWrapper = queryWrapper.orderByDesc("createtime");
        IPage<ZhihuRecords> pageInfo = zhihuRecordsService.page(page, queryWrapper);

        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", zhihuRecords);
        model.addAttribute("pageInfo",  new PageInfo(pageInfo));
        return prefix+"zhihuRecords-list";
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
    * @param zhihuRecords
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(ZhihuRecords zhihuRecords){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);

        String loginUserName = Utility.getCurrentUsername();
        zhihuRecords.setUsername(loginUserName);
        zhihuRecords.setCreatetime(timechange);

        return toAjax(zhihuRecordsService.save(zhihuRecords));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("zhihuRecords",zhihuRecordsService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param zhihuRecords
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(ZhihuRecords zhihuRecords){

//        LocalDateTime time = LocalDateTime.now();
//        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
//        String localTime = df.format(time);
//        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
//        zhihuRecords.setCreatetime(timechange);
        return toAjax(zhihuRecordsService.updateById(zhihuRecords));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(zhihuRecordsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(zhihuRecordsService.removeByIds(ids));
    }

}

