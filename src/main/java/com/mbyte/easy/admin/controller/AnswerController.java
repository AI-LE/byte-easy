package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Answer;
import com.mbyte.easy.admin.service.IAnswerService;
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
@RequestMapping("/admin/answer")
public class AnswerController extends BaseController  {

    private String prefix = "admin/answer/";

    @Autowired
    private IAnswerService answerService;

    /**
    * 查询列表
    *
    * @param model
    * @param pageNo
    * @param pageSize
    * @param answer
    * @return
    */
    @RequestMapping
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, Answer answer) {
        Page<Answer> page = new Page<Answer>(pageNo, pageSize);
        QueryWrapper<Answer> queryWrapper = new QueryWrapper<Answer>();

        if(answer.getTitle() != null  && !"".equals(answer.getTitle() + "")) {
            queryWrapper = queryWrapper.like("title",answer.getTitle());
         }


        if(answer.getAnswerone() != null  && !"".equals(answer.getAnswerone() + "")) {
            queryWrapper = queryWrapper.like("answerone",answer.getAnswerone());
         }


        if(answer.getAnswertwo() != null  && !"".equals(answer.getAnswertwo() + "")) {
            queryWrapper = queryWrapper.like("answertwo",answer.getAnswertwo());
         }


        if(answer.getAnswerthree() != null  && !"".equals(answer.getAnswerthree() + "")) {
            queryWrapper = queryWrapper.like("answerthree",answer.getAnswerthree());
         }


        if(answer.getAnswerfour() != null  && !"".equals(answer.getAnswerfour() + "")) {
            queryWrapper = queryWrapper.like("answerfour",answer.getAnswerfour());
         }


        if(answer.getAnswerfive() != null  && !"".equals(answer.getAnswerfive() + "")) {
            queryWrapper = queryWrapper.like("answerfive",answer.getAnswerfive());
         }


        if(answer.getUsername() != null  && !"".equals(answer.getUsername() + "")) {
            queryWrapper = queryWrapper.like("username",answer.getUsername());
         }

        IPage<Answer> pageInfo = answerService.page(page, queryWrapper);
        model.addAttribute("searchInfo", answer);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"answer-list";
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
    * @param answer
    * @return
    */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Answer answer){
        return toAjax(answerService.save(answer));
    }
    /**
    * 添加跳转页面
    * @return
    */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("answer",answerService.getById(id));
        return prefix+"edit";
    }
    /**
    * 添加
    * @param answer
    * @return
    */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Answer answer){
        return toAjax(answerService.updateById(answer));
    }
    /**
    * 删除
    * @param id
    * @return
    */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(answerService.removeById(id));
    }
    /**
    * 批量删除
    * @param ids
    * @return
    */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(answerService.removeByIds(ids));
    }

}

