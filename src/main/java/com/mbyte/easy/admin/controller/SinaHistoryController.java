package com.mbyte.easy.admin.controller;


import com.mbyte.easy.admin.Util.ExportWord;
import com.mbyte.easy.admin.entity.SinaHistory;
import com.mbyte.easy.admin.service.ISinaHistoryService;
import com.mbyte.easy.admin.service.ISinaSerchService;
import com.mbyte.easy.common.web.AjaxResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.mbyte.easy.common.controller.BaseController;
import com.mbyte.easy.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张虎
 * @since 2019-07-15
 */
@Controller
@RequestMapping("/admin/sinaHistory")
public class SinaHistoryController extends BaseController {

    @Autowired
    private ISinaHistoryService sinaHistoryService;

    @Autowired
    private ISinaSerchService sinaSerchService;

    @Value("${file.upload.local.path}")
    private String localPath ;

    private String prefix = "admin/sinaHistory/";

    /**
     * 查询列表
     *
     * @param model
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("rec/{id}")
    public String index(Model model,@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                        @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize
            , @PathVariable("id")Long id) {
        Page<SinaHistory> page = new Page<SinaHistory>(pageNo, pageSize);
        QueryWrapper<SinaHistory> queryWrapper = new QueryWrapper<SinaHistory>();

        if(id != null && !"".equals(id)){
            queryWrapper = queryWrapper.eq("uuid",id);
        }
        queryWrapper = queryWrapper.orderByDesc("crawlerTime");
        IPage<SinaHistory> pageInfo = sinaHistoryService.page(page, queryWrapper);
        model.addAttribute("pageInfo", new PageInfo(pageInfo));

        return prefix+"sinaHistory-list";
    }
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        return toAjax(sinaHistoryService.removeById(id));
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("deleteAll")
    @ResponseBody
    public AjaxResult deleteAll(@RequestBody List<Long> ids){
        return toAjax(sinaHistoryService.removeByIds(ids));
    }



    @RequestMapping("download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) throws IOException {

        //得到爬取导出的word的路径
        String path = sinaHistoryService.getById(id).getPath();
        //设置文件下载名字
        String name = path.replace(localPath,"");
        //设置编码
        String downloadFileName = new String(name.getBytes("UTF-8"),"iso-8859-1");
        //打开word
        File file=new File(path);
        //请求头部
        HttpHeaders headers = new HttpHeaders();
        //下载请求
        headers.setContentDispositionFormData("attachment", downloadFileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
    }
}
