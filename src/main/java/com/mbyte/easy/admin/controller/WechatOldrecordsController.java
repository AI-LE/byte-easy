package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mbyte.easy.admin.entity.Wechat;
import com.mbyte.easy.admin.entity.WechatOldrecords;
import com.mbyte.easy.admin.service.IWechatOldrecordsService;
import com.mbyte.easy.admin.service.IWechatService;
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
 * @author 张松哲
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/admin/wechatOldrecords")
public class WechatOldrecordsController extends BaseController  {

    private String prefix = "admin/wechatOldrecords/";

    @Autowired
    private IWechatOldrecordsService iWechatOldrecordsService;

    @Autowired
    private IWechatService iWechatService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("rec/{id}")
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace
            , @PathVariable("id")Long id) {
        WechatOldrecords wechatOldrecords=new WechatOldrecords();
        Page<WechatOldrecords> page = new Page<WechatOldrecords>(pageNo, pageSize);
        QueryWrapper<WechatOldrecords> queryWrapper = new QueryWrapper<WechatOldrecords>();
//
//        if(zhihuOldrecords.getCreatetime() != null  && !"".equals(zhihuOldrecords.getCreatetime() + "")) {
//            queryWrapper = queryWrapper.like("createtime",zhihuOldrecords.getCreatetime());
//         }
        if(wechatOldrecords.getKeyword() != null && !"".equals(wechatOldrecords.getKeyword())) {
            queryWrapper = queryWrapper.like("keyword", wechatOldrecords.getKeyword());
        }
        //wechatOldrecords.setWechatid(id);
        if(id != null && !"".equals(id)){
            queryWrapper = queryWrapper.like("wechatid",id);
        }
        /*long wechatid = iWechatOldrecordsService.getById(id).getWechatid();*/
        /* queryWrapper = queryWrapper.like("wechatid",wechatOldrecords.getWechatid());*/

//        if(Utility.getCurrentUser().getUsername() != null) {
//            queryWrapper = queryWrapper.like("username",  Utility.getCurrentUser().getUsername());
//        }
        queryWrapper = queryWrapper.orderByDesc("createtime");
        if(wechatOldrecords.getStatus() != null && !"".equals(wechatOldrecords.getStatus())) {
            queryWrapper = queryWrapper.like("status", wechatOldrecords.getStatus());
        }
        IPage<WechatOldrecords> pageInfo = iWechatOldrecordsService.page(page, queryWrapper);

        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", wechatOldrecords);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"wechatOldrecords-list";
    }


    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, WechatOldrecords wechatOldrecords) {
        Page<WechatOldrecords> page = new Page<WechatOldrecords>(pageNo, pageSize);
        QueryWrapper<WechatOldrecords> queryWrapper = new QueryWrapper<WechatOldrecords>();

        if(wechatOldrecords.getKeyword() != null  && !"".equals(wechatOldrecords.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",wechatOldrecords.getKeyword());
        }


        if(wechatOldrecords.getCreatetime() != null  && !"".equals(wechatOldrecords.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",wechatOldrecords.getCreatetime());
        }


        if(wechatOldrecords.getStatus() != null  && !"".equals(wechatOldrecords.getStatus() + "")) {
            queryWrapper = queryWrapper.like("status",wechatOldrecords.getStatus());
        }

        IPage<WechatOldrecords> pageInfo = iWechatOldrecordsService.page(page, queryWrapper);
        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("searchInfo", wechatOldrecords);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"wechatOldrecords-list";
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
     * @param wechatOldrecords
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(WechatOldrecords wechatOldrecords){
        return toAjax(iWechatOldrecordsService.save(wechatOldrecords));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("wechatOldrecords",iWechatOldrecordsService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param wechatOldrecords
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(WechatOldrecords wechatOldrecords){
        return toAjax(iWechatOldrecordsService.updateById(wechatOldrecords));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(iWechatOldrecordsService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(iWechatOldrecordsService.removeByIds(ids));
    }

}

