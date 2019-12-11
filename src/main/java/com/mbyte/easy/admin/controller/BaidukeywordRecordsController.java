package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.BaidukeywordRecords;
import com.mbyte.easy.admin.service.IBaidukeywordRecordsService;
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
 * @author 艾乐
 * @since 2019-03-11
 */
@Controller
@RequestMapping("/admin/baidukeywordRecords")
public class BaidukeywordRecordsController extends BaseController  {

    private String prefix = "admin/baidukeywordRecords/";

    @Autowired
    private IBaidukeywordRecordsService baidukeywordRecordsService;

    private Integer keywordid;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param baidukeywordRecords
     * @return
     */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, BaidukeywordRecords baidukeywordRecords) {
        Page<BaidukeywordRecords> page = new Page<BaidukeywordRecords>(pageNo, pageSize);
        //便于分页，当重新选中页数时，前台没有传keywordid过来，所以用之前保存的全局变量，
        //当前台传了keywordid时，说明查看的记录keywordid已经变化，所以需要更新
        if(baidukeywordRecords.getKeywordid() != null){
            keywordid = baidukeywordRecords.getKeywordid();
        }
        QueryWrapper<BaidukeywordRecords> queryWrapper = new QueryWrapper<BaidukeywordRecords>();
        queryWrapper = queryWrapper.eq("keywordid",keywordid).orderByDesc("id");
        IPage<BaidukeywordRecords> pageInfo = baidukeywordRecordsService.page(page, queryWrapper);
        model.addAttribute("searchInfo", baidukeywordRecords);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"baidukeywordRecords-list";
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(baidukeywordRecordsService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(baidukeywordRecordsService.removeByIds(ids));
    }

}