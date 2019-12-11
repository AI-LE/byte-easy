package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.TBloggerOld;
import com.mbyte.easy.admin.service.ITBloggerOldService;
import com.mbyte.easy.admin.service.ITBloggerService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import com.mbyte.easy.util.Utility;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/admin/tBloggerOld")
public class TBloggerOldController extends BaseController  {

    private String prefix = "admin/tBloggerOld/";

    @Autowired
    private ITBloggerOldService tBloggerOldService;

    @Autowired
    private ITBloggerService itBloggerService;

    TBloggerOld tBloggerOld = new TBloggerOld();
    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param tBloggerOld
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, String timestartSpace, String timeendSpace, TBloggerOld tBloggerOld) {
        Page<TBloggerOld> page = new Page<TBloggerOld>(pageNo, pageSize);
        QueryWrapper<TBloggerOld> queryWrapper = new QueryWrapper<TBloggerOld>();

        if(tBloggerOld.getBloggername() != null  && !"".equals(tBloggerOld.getBloggername() + "")) {
            queryWrapper = queryWrapper.like("bloggername",tBloggerOld.getBloggername());
         }

        System.out.println(tBloggerOld.getContainerid());
        if(tBloggerOld.getUid() != null  && !"".equals(tBloggerOld.getUid() + "")) {
            queryWrapper = queryWrapper.like("uid",tBloggerOld.getUid());
         }


        if(tBloggerOld.getContainerid() != null  && !"".equals(tBloggerOld.getContainerid() + "")) {
            queryWrapper = queryWrapper.like("containerid",tBloggerOld.getContainerid());
         }


        if(tBloggerOld.getCreatetime() != null  && !"".equals(tBloggerOld.getCreatetime() + "")) {
            queryWrapper = queryWrapper.like("createtime",tBloggerOld.getCreatetime());
         }


        if(tBloggerOld.getWordmax() != null  && !"".equals(tBloggerOld.getWordmax() + "")) {
            queryWrapper = queryWrapper.like("wordmax",tBloggerOld.getWordmax());
         }


        if(tBloggerOld.getWordmin() != null  && !"".equals(tBloggerOld.getWordmin() + "")) {
            queryWrapper = queryWrapper.like("wordmin",tBloggerOld.getWordmin());
         }


        if(tBloggerOld.getTimestart() != null  && !"".equals(tBloggerOld.getTimestart() + "")) {
            queryWrapper = queryWrapper.like("timestart",tBloggerOld.getTimestart());
         }


        if(tBloggerOld.getTimeend() != null  && !"".equals(tBloggerOld.getTimeend() + "")) {
            queryWrapper = queryWrapper.like("timeend",tBloggerOld.getTimeend());
         }


        if(tBloggerOld.getUsername() != null  && !"".equals(tBloggerOld.getUsername() + "")) {
            queryWrapper = queryWrapper.like("username",tBloggerOld.getUsername());
         }


        if(tBloggerOld.getCommitchioce() != null  && !"".equals(tBloggerOld.getCommitchioce() + "")) {
            queryWrapper = queryWrapper.like("commitchioce",tBloggerOld.getCommitchioce());
         }


        if(tBloggerOld.getPointersum() != null  && !"".equals(tBloggerOld.getPointersum() + "")) {
            queryWrapper = queryWrapper.like("pointersum",tBloggerOld.getPointersum());
         }


        if(tBloggerOld.getJudge() != null  && !"".equals(tBloggerOld.getJudge() + "")) {
            queryWrapper = queryWrapper.like("judge",tBloggerOld.getJudge());
         }

        queryWrapper = queryWrapper.orderByDesc("createtime");
        IPage<TBloggerOld> pageInfo = tBloggerOldService.page(page, queryWrapper);

        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("timestartSpace", timestartSpace);
        model.addAttribute("timeendSpace", timeendSpace);
        model.addAttribute("searchInfo", tBloggerOld);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBloggerOld-list";
    }



    @GetMapping("oldmain/{id}")
    public String index(Model model, @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String createtimeSpace, String timestartSpace, String timeendSpace, TBloggerOld tBloggerOld,
                        @PathVariable("id")Long id) {
        Page<TBloggerOld> page = new Page<TBloggerOld>(pageNo, pageSize);
        QueryWrapper<TBloggerOld> queryWrapper = new QueryWrapper<TBloggerOld>();

        if(itBloggerService.getById(id).getBloggername() != null  ) {
            queryWrapper = queryWrapper.like("bloggername",itBloggerService.getById(id).getBloggername());
        }
        queryWrapper = queryWrapper.like("containerid",itBloggerService.getById(id).getId());


        if(tBloggerOld.getUid() != null  && !"".equals(tBloggerOld.getUid() + "")) {
            queryWrapper = queryWrapper.like("uid",tBloggerOld.getUid());
        }

//
        if(Utility.getCurrentUser().getUsername()!=null) {
            queryWrapper = queryWrapper.like("username", Utility.getCurrentUser().getUsername());
        }
//
//
//        if(tBloggerOld.getCommitchioce() != null  && !"".equals(tBloggerOld.getCommitchioce() + "")) {
//            queryWrapper = queryWrapper.like("commitchioce",tBloggerOld.getCommitchioce());
//        }
//        if(tBloggerOld.getPointersum() != null  && !"".equals(tBloggerOld.getPointersum() + "")) {
//            queryWrapper = queryWrapper.like("pointersum",tBloggerOld.getPointersum());
//        }

        if(tBloggerOld.getJudge() != null  && !"".equals(tBloggerOld.getJudge() + "")) {
            queryWrapper = queryWrapper.like("judge",tBloggerOld.getJudge());
        }
        queryWrapper = queryWrapper.orderByDesc("createtime");

        IPage<TBloggerOld> pageInfo = tBloggerOldService.page(page, queryWrapper);


        model.addAttribute("createtimeSpace", createtimeSpace);
        model.addAttribute("timestartSpace", timestartSpace);
        model.addAttribute("timeendSpace", timeendSpace);
        model.addAttribute("searchInfo", tBloggerOld);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"tBloggerOld-list";
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
    * @param tBloggerOld
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(TBloggerOld tBloggerOld){
        return toAjax(tBloggerOldService.save(tBloggerOld));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("tBloggerOld",tBloggerOldService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param tBloggerOld
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(TBloggerOld tBloggerOld){
        return toAjax(tBloggerOldService.updateById(tBloggerOld));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(tBloggerOldService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(tBloggerOldService.removeByIds(ids));
    }

}

