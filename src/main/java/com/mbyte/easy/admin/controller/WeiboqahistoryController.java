package com.mbyte.easy.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mbyte.easy.admin.entity.Weiboqahistory;
import com.mbyte.easy.admin.service.IWeiboqahistoryService;
import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.common.web.AjaxResult;
import com.mbyte.easy.util.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
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
@RequestMapping("/weiboqaHistroy/weiboqahistory/")
public class WeiboqahistoryController extends BaseController  {

    private String prefix = "admin/weiboqahistory/";

    @Autowired
    private IWeiboqahistoryService weiboqahistoryService;

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @param weiboqahistory
     * @return
     */
    @RequestMapping("view")
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,@RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize, String creattimeSpace, Weiboqahistory weiboqahistory) {
        Page<Weiboqahistory> page = new Page<Weiboqahistory>(pageNo, pageSize);
        QueryWrapper<Weiboqahistory> queryWrapper = new QueryWrapper<Weiboqahistory>();

        if(weiboqahistory.getFilename() != null  && !"".equals(weiboqahistory.getFilename() + "")) {
            queryWrapper = queryWrapper.like("filename",weiboqahistory.getFilename());
        }


        if(weiboqahistory.getCreattime() != null  && !"".equals(weiboqahistory.getCreattime() + "")) {
            queryWrapper = queryWrapper.like("creattime",weiboqahistory.getCreattime());
        }


        if(weiboqahistory.getPath() != null  && !"".equals(weiboqahistory.getPath() + "")) {
            queryWrapper = queryWrapper.like("path",weiboqahistory.getPath());
        }

        System.out.println("111111111111111111111"+weiboqahistory.getFilename());
        IPage<Weiboqahistory> pageInfo = weiboqahistoryService.page(page, queryWrapper);
        model.addAttribute("creattimeSpace", creattimeSpace);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));
        return prefix+"weiboqahistory-list";
    }

    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("addBefore")
    public String addBefore(){
        return prefix+"add";
    }


    @ResponseBody
    @GetMapping("download/{id}")
    public ResponseEntity<byte[]> reallyDownload(@PathVariable("id")Long id) throws Exception
    {
        String filePath=weiboqahistoryService.getById(id).getPath();
        File file=new File(filePath);
        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filePath.getBytes("UTF-8"),"iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName.substring(7));

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);

    }
    /**
     * 添加
     * @param weiboqahistory
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public AjaxResult add(Weiboqahistory weiboqahistory){
        return toAjax(weiboqahistoryService.save(weiboqahistory));
    }
    /**
     * 添加跳转页面
     * @return
     */
    @GetMapping("editBefore/{id}")
    public String editBefore(Model model,@PathVariable("id")Long id){
        model.addAttribute("weiboqahistory",weiboqahistoryService.getById(id));
        return prefix+"edit";
    }
    /**
     * 添加
     * @param weiboqahistory
     * @return
     */
    @PostMapping("edit")
    @ResponseBody
    public AjaxResult edit(Weiboqahistory weiboqahistory){
        return toAjax(weiboqahistoryService.updateById(weiboqahistory));
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(weiboqahistoryService.removeById(id));
    }
    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(weiboqahistoryService.removeByIds(ids));
    }

}

