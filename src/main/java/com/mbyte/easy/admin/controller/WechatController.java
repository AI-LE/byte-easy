package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Wechat;
import com.mbyte.easy.admin.service.IWechatService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
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
@RequestMapping("/admin/wechat")
public class WechatController extends BaseController  {

    private String prefix = "admin/wechat/";

    @Autowired
    private IWechatService wechatService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param wechat
     * @return
     */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Wechat wechat) {
        Page<Wechat> page = new Page<Wechat>(pageNo, pageSize);
        QueryWrapper<Wechat> queryWrapper = new QueryWrapper<Wechat>();

        if(wechat.getKeyword() != null  && !"".equals(wechat.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",wechat.getKeyword());
        }

        queryWrapper = queryWrapper.orderByDesc("createtime");
        IPage<Wechat> pageInfo = wechatService.page(page, queryWrapper);
        model.addAttribute("searchInfo", wechat);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"wechat-list";
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
     * @param wechat
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Wechat wechat){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        wechat.setCreatetime(timechange);
        return toAjax(wechatService.save(wechat));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("wechat",wechatService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param wechat
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Wechat wechat){
        wechat.setCreatetime(wechat.getCreatetime());
        return toAjax(wechatService.updateById(wechat));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(wechatService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(wechatService.removeByIds(ids));
    }

}

