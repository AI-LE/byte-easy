package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Baidu;
import com.mbyte.easy.admin.entity.BdRecords;
import com.mbyte.easy.admin.service.IBdRecordsService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping("/admin/bdRecords")
public class BdRecordsController extends BaseController  {

    private String prefix = "admin/bdRecords/";

    @Autowired
    private IBdRecordsService bdRecordsService;


    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param bdRecords
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, BdRecords bdRecords) {
        Page<BdRecords> page = new Page<BdRecords>(pageNo, pageSize);
        QueryWrapper<BdRecords> queryWrapper = new QueryWrapper<BdRecords>();
        //获得当前用户,查询当前用户的信息
        if(Utility.getCurrentUser().getUsername() != null  ) {
            queryWrapper = queryWrapper.like("username", Utility.getCurrentUser().getUsername());//传入当前用户
        }

        if(bdRecords.getCreatetime() != null  && !"".equals(bdRecords.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",bdRecords.getCreatetime());
         }


        if(bdRecords.getKeyword() != null  && !"".equals(bdRecords.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",bdRecords.getKeyword());

         }
        queryWrapper = queryWrapper.orderByDesc("createtime");
        IPage<BdRecords> pageInfo = bdRecordsService.page(page, queryWrapper);


        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", bdRecords);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"bdRecords-list";
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
    * @param bdRecords
    * @return
    */
    @RequestMapping("add")
    @ResponseBody
    public AjaxResult add(BdRecords bdRecords){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);

        String loginUserName = Utility.getCurrentUsername();
        bdRecords.setUsername(loginUserName);
        bdRecords.setCreatetime(timechange);
//        AjaxResult ajaxResult =AjaxResult.success("查询成功");
//        ajaxResult.put("data","ssss");
//        ajaxResult.put("data","ssss");

        return toAjax(bdRecordsService.save(bdRecords));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("bdRecords",bdRecordsService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param bdRecords
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(BdRecords bdRecords){
//
//        LocalDateTime time = LocalDateTime.now();
//        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
//        String localTime = df.format(time);
//        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
//        bdRecords.setCreatetime(timechange);
        return toAjax(bdRecordsService.updateById(bdRecords));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
//        QueryWrapper<Baidu> baiduQueryWrapper = new QueryWrapper<Baidu>();
//        baiduQueryWrapper = baiduQueryWrapper.eq("id", )

        return toAjax(bdRecordsService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(bdRecordsService.removeByIds(ids));
    }

}

