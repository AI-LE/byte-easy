package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Baidukeyword;
import com.mbyte.easy.admin.service.IBaidukeywordService;
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
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 * @author 艾乐
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/admin/baidukeyword")
public class BaidukeywordController extends BaseController  {

    private String prefix = "admin/baidukeyword/";

    @Autowired
    private IBaidukeywordService baidukeywordService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param baidukeyword
     * @return
     */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Baidukeyword baidukeyword) {
        Page<Baidukeyword> page = new Page<Baidukeyword>(pageNo, pageSize);
        QueryWrapper<Baidukeyword> queryWrapper = new QueryWrapper<Baidukeyword>();
        if(baidukeyword.getKeyword() != null  && !"".equals(baidukeyword.getKeyword() + "")) {
            queryWrapper = queryWrapper.like("keyword",baidukeyword.getKeyword());
        }
        queryWrapper = queryWrapper.orderByDesc("id");
        IPage<Baidukeyword> pageInfo = baidukeywordService.page(page, queryWrapper);
        model.addAttribute("searchInfo", baidukeyword);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"baidukeyword-list";
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
     * @param baidukeyword
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Baidukeyword baidukeyword){
        LocalDateTime time = LocalDateTime.now();
        String loginUserName = Utility.getCurrentUsername();
        baidukeyword.setUsername(loginUserName);
        baidukeyword.setCreatetime(time);
        return toAjax(baidukeywordService.save(baidukeyword));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("baidukeyword",baidukeywordService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param baidukeyword
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Baidukeyword baidukeyword){
        return toAjax(baidukeywordService.updateById(baidukeyword));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(baidukeywordService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(baidukeywordService.removeByIds(ids));
    }

}

