package com.mbyte.easy.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lowagie.text.DocumentException;
import com.mbyte.easy.admin.Util.ExportWord;
import com.mbyte.easy.admin.Util.Request;
import com.mbyte.easy.admin.entity.*;
import com.mbyte.easy.admin.model.Timetest;
import com.mbyte.easy.admin.service.*;
import com.mbyte.easy.security.controller.AdminController;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.ImgUtil;
import com.mbyte.easy.util.Utility;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

/**
 * @author 吴天豪
 * 这个用来爬取微博的
 */
@Controller
@RequestMapping(value = "/weibo/")
public class WeiBoCrawlerController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ITBloggerContentService itBloggerContentService;

    @Autowired
    private ITBloggerService itBloggerService;

    @Autowired
    private ITBloggerPointService itBloggerPointService;

    @Autowired
    private ITBloggerImgService itBloggerImgService;

    @Autowired
    private ITBloggerOldService itBloggerOldService;

    @Autowired
    private ITRecordssumService itRecordssumService;
    /**
     * 微博万能
     */
    @RequestMapping(value = "weibocommit")
    @ResponseBody
    public void crawlerClientcommit(Model model, HttpServletResponse response, HttpServletRequest request) {
//        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //  String url = "https://www.douban.com";
        response.setContentType("text/html;charset=utf-8");
        long currt = 0;
        List<String> sum = new ArrayList<String>();
        //获得前台的id
        String id = request.getParameter("id");
        QueryWrapper<TBlogger> queryCWrapper = new QueryWrapper<TBlogger>();
        queryCWrapper = queryCWrapper.eq("id", id);
        //获得两个需要的值
        TBlogger tBlogger = itBloggerService.getOne(queryCWrapper);
        long uid = tBlogger.getUid();
        LocalDateTime createtimeblog = tBlogger.getCreatetime();
        String bloggername = tBlogger.getBloggername();
        long bloggerid = tBlogger.getId();
        int commitchioce = tBlogger.getCommitchioce();
        //时间的获得
        String timestart = tBlogger.getTimestart().toString();
        String timeend = tBlogger.getTimeend().toString();
        int time_count = 0;//定义输出标题
        //现在的时间戳
        LocalDateTime time = LocalDateTime.now();
        /**
         * 博主内容或者博主图片+内容追加条件存储
         */
        boolean contentOnly = false;
        boolean contentImg = false;
        boolean contentimg = false;
        /**
         * 博主内容请求不到不用存
         */
        boolean contentBlogger = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datatime = new Date();
        long passeDate = datatime.getTime();
        //时间flag
        int timeflag = 0;
        boolean flag = false;
        //博主
        boolean flagblogger = false;
        //字数的限制
        //  System.out.println("timestart" + timestart);
        long wordmax = tBlogger.getWordmax();
//        long wordmin = tBlogger.getWordmin();
        //点赞数
        long hotpoint = 0;
        boolean flaghotpoint = false;
        if (tBlogger.getPointersum()!=null){
            hotpoint = tBlogger.getPointersum();
            flaghotpoint = true;
        }
        int imgflag =tBlogger.getJudge();
        String reg = "[^\u4e00-\u9fa5]";

        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 250000; i++) {
            //清空内容判断
            // 捕获异常
            try {
                String url = "https://m.weibo.cn/api/container/getIndex?type=uid&value=" + uid + "&containerid=107603" + uid+ "&page=" + i;
                //这个是JSON爬虫
                Map<String, Object> headerParams = new HashMap<>();
                headerParams.put(P.REQUEST.USER_AGENT, P.USER_AGENT);//将cookie值也放入请求中
                Map<String, Object> resMap = Request.get(url, headerParams);
                try {
                    // 添加时间间隔 5s  解决 418问题。
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //  System.out.println("==========" + resMap.get(P.REQUEST.RES_BODY) + "=================================");
                if(resMap.get(P.REQUEST.RES_BODY) == null){
                    continue;
                }
                String result = resMap.get(P.REQUEST.RES_BODY).toString();
                /**
                 * json解析
                 */
                JSONObject json = (JSONObject) JSON.parse(result);
                if (json != null) {
                    JSONObject firstCardJson = (JSONObject) json.getJSONObject("data");
                    if (firstCardJson != null) {
                        JSONArray dataNJson = firstCardJson.getJSONArray("cards");
                        if (dataNJson.isEmpty() != true || dataNJson.size() > 0) {
                            for (int u = 0; u < dataNJson.size(); u++) {
                                //   System.out.println("contentBlogger" +contentBlogger);
                                JSONObject firstCardJson1 = (JSONObject) dataNJson.get(u);
                                if (firstCardJson1 != null) {
                                    JSONObject firstmblog = firstCardJson1.getJSONObject("mblog");
                                    if (firstmblog != null) {
                                        TBloggerContent tBloggerContent = new TBloggerContent();
                                        String contentid = firstCardJson1.getJSONObject("mblog").get("id").toString();
                                        String Content = firstCardJson1.getJSONObject("mblog").get("text").toString();
                                        String createtime = firstCardJson1.getJSONObject("mblog").get("created_at").toString();
                                        String retweeted_status = "";//内容的转发文章
                                        String retweeted_statusid = "";
                                        JSONObject jsonObjectretweeted = (JSONObject) firstmblog.get("retweeted_status");
                                        if (jsonObjectretweeted != null) {
                                            retweeted_statusid = jsonObjectretweeted.get("id").toString();
                                            try {
                                                // 添加时间间隔 5s  解决 418问题。
                                                Thread.sleep(5000);
                                            } catch (InterruptedException e) {
                                                logger.error(e.getMessage());
                                                continue;
                                            }
                                            /**
                                             * 请求转发
                                             */
                                            String urlJson = "https://m.weibo.cn/statuses/extend?id=" + retweeted_statusid;
                                            //这个是JSON爬虫
                                            Map<String, Object> headerParamsurl = new HashMap<>();
                                            headerParamsurl.put(P.REQUEST.USER_AGENT, P.USER_AGENT);//将cookie值也放入请求中
                                            Map<String, Object> resMapurl = Request.get(urlJson, headerParamsurl);
                                            //  System.out.println("==========" + resMap.get(P.REQUEST.RES_BODY) + "=================================");
                                            if(resMapurl.get(P.REQUEST.RES_BODY) == null){
                                                continue;
                                            }

                                            String resulturl = resMapurl.get(P.REQUEST.RES_BODY).toString();

                                            /**
                                             * json解析
                                             */
                                            JSONObject jsonContent = (JSONObject) JSON.parse(resulturl);
                                            if (jsonContent != null) {
                                                JSONObject firstCardJsondata = jsonContent.getJSONObject("data");
                                                if (firstCardJsondata != null) {
                                                    String longTextContent = firstCardJsondata.get("longTextContent").toString();
                                                    if (longTextContent != null && longTextContent != "") {
                                                        retweeted_status = longTextContent;
                                                    }
                                                }
                                            }
                                        }

                                        /**
                                         * 这里变动很大，可能要可能不要，text中的图片判断影响了后面的文字+图片内容和文字内容
                                         */
                                        if (imgflag == 0 || imgflag == 1) {
                                            /**
                                             * 尝试获得text中的图片路径
                                             */
                                            TBloggerImg tBloggerImg = new TBloggerImg();
                                            tBloggerImg.setContentid(Long.parseLong(contentid));
                                            if (ImgUtil.getImageSrc(Content) != null && ImgUtil.getImageSrc(Content).size() > 0) {
                                                for (int j = 0; j < ImgUtil.getImageSrc(Content).size(); j++) {
                                                    String imgurlSource = ImgUtil.getImageSrc(Content).get(j);
                                                    if (imgurlSource.indexOf("http") != -1) {
                                                        //有前缀
                                                        System.out.println("Content" + Content);
                                                        contentimg = true;
                                                        if (imgflag == 1) {
                                                            tBloggerImg.setImgsourceurl(imgurlSource);
                                                            String imgUrllocal = FileUtil.uploadLocalPath + ImgUtil.generateSuffix() + ".jpg";
                                                            ImgUtil.downloadPicture(tBloggerImg.getImgsourceurl(), imgUrllocal);
                                                            tBloggerImg.setImglocalurl(imgUrllocal);
                                                            itBloggerImgService.save(tBloggerImg);
                                                        }

                                                    } else {
                                                        contentimg = true;
                                                        //没有前缀
                                                        if (imgflag == 1) {
                                                            tBloggerImg.setImgsourceurl("https:" + imgurlSource);
                                                            String imgUrllocal1 = FileUtil.uploadLocalPath + ImgUtil.generateSuffix() + ".jpg";
                                                            //   System.out.println("==========" + tBloggerImg.getImgsourceurl());
                                                            ImgUtil.downloadPicture(tBloggerImg.getImgsourceurl(), imgUrllocal1);
                                                            tBloggerImg.setImglocalurl(imgUrllocal1);
                                                            itBloggerImgService.save(tBloggerImg);
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        /**
                                         * 图片路径存入，这个是博主的单独图片
                                         */
                                        if (imgflag == 1) {
                                            JSONArray dataImg = firstmblog.getJSONArray("pics");
                                            if (dataImg != null && dataImg.size() > 0) {
                                                for (int p = 0; p < dataImg.size(); p++) {
                                                    JSONObject firstImgJson = (JSONObject) dataImg.get(p);
                                                    //   System.out.println("firstImgJson:" + firstImgJson);
                                                    if (firstImgJson != null) {
                                                        String imgURL = firstImgJson.getString("url");
                                                        TBloggerImg tBloggerImg = new TBloggerImg();
                                                        tBloggerImg.setContentid(Long.parseLong(contentid));
                                                        tBloggerImg.setImgsourceurl(imgURL);
                                                        String imgUrllocal = "";
                                                        if (imgURL.indexOf("gif") == -1) {//防止图片格式是gif类型
                                                            imgUrllocal = FileUtil.uploadLocalPath + ImgUtil.generateSuffix() + ".jpg";
                                                            ImgUtil.downloadPicture(imgURL, imgUrllocal);
                                                            tBloggerImg.setImglocalurl(imgUrllocal);
                                                            itBloggerImgService.save(tBloggerImg);
                                                        } else {
                                                            System.out.println("=====");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        //图片判断
                                        JSONArray dataImgfloow = firstmblog.getJSONArray("pics");
                                        if (dataNJson.isEmpty() != true || dataNJson.size() > 1) {

                                            try {
                                                // 添加时间间隔 5s  解决 418问题。
                                                Thread.sleep(2000);
                                            } catch (InterruptedException e) {
                                                e.printStackTrace();
                                            }
                                            /**
                                             * 请求博主内容
                                             */
                                            String urlJson = "https://m.weibo.cn/statuses/extend?id=" + Long.parseLong(contentid);
                                            //这个是JSON爬虫
                                            Map<String, Object> headerParamsurl = new HashMap<>();
                                            headerParamsurl.put(P.REQUEST.USER_AGENT, P.USER_AGENT);//将cookie值也放入请求中
                                            Map<String, Object> resMapurl = Request.get(urlJson, headerParamsurl);
                                            //  System.out.println("==========" + resMap.get(P.REQUEST.RES_BODY) + "=================================");
                                            if(resMapurl.get(P.REQUEST.RES_BODY) == null){
                                                continue;
                                            }
                                            String resulturl = resMapurl.get(P.REQUEST.RES_BODY).toString();
                                            if(resulturl.indexOf("打开微博客户端") == -1 ){//强硬过滤
                                                /**
                                                 * 判断是哪种类型的存储,是否有照片？
                                                 */
                                                JSONObject jsonContent = (JSONObject) JSON.parse(resulturl);
                                                if (imgflag == 0) {
                                                    if (jsonContent != null && dataImgfloow == null && contentimg == false) {
                                                        JSONObject firstCardJsondata = jsonContent.getJSONObject("data");
                                                        if (firstCardJsondata != null) {
                                                            // System.out.println("firstCardJsondata" + firstCardJsondata);
                                                            String longTextContent = firstCardJsondata.get("longTextContent").toString();
                                                            if (longTextContent != null && longTextContent.equals("") == false && longTextContent.indexOf("微博视频") == -1) {
                                                                tBloggerContent.setContent(longTextContent);
                                                                contentOnly = true; //纯文字
                                                                contentBlogger = true; //博主内容的完整请求到了
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    if (jsonContent != null) {
                                                        JSONObject firstCardJsondata = jsonContent.getJSONObject("data");
                                                        if (firstCardJsondata != null) {
                                                            // System.out.println("firstCardJsondata" + firstCardJsondata);
                                                            String longTextContent = firstCardJsondata.get("longTextContent").toString();
                                                            if (longTextContent != null && longTextContent.equals("") == false && longTextContent.indexOf("微博视频") == -1) {
                                                                tBloggerContent.setContent(longTextContent);
                                                                contentImg = true;
                                                                contentBlogger = true; //博主内容的完整请求到了
                                                            }
                                                        }
                                                    }
                                                }
                                            }

                                            if (contentBlogger = true) {
                                                /**
                                                 * 存博客的内容
                                                 */

                                                tBloggerContent.setContentid(Long.parseLong(contentid));

                                                /**
                                                 * 根据时间
                                                 */
                                                Timetest timetest = new Timetest();
                                                SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
                                                SimpleDateFormat sdfsql = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                //定义特殊时间符号
                                                String hour = "小时";
                                                String son = "刚刚";
                                                String min = "分钟";
                                                String ss = "秒钟";
                                                String yesterday = "昨天";
                                                boolean statushour = createtime.contains(hour);
                                                boolean statusmin = createtime.contains(min);
                                                boolean statusss = createtime.contains(ss);
                                                boolean statuson = createtime.contains(son);
                                                boolean statuyesterday = createtime.contains(yesterday);

                                                /**
                                                 * 特殊的时间期限存在
                                                 */
                                                if (statushour == true || statusmin == true || statusss == true || statuson == true) {
                                                    SimpleDateFormat df = new SimpleDateFormat("MM-dd");//设置日期格式

                                                    tBloggerContent.setCreatetime("2019-" + df.format(new Date()));
                                                } else if (statuyesterday == true) {
                                                    SimpleDateFormat df = new SimpleDateFormat("MM-dd");//设置日期格式
                                                    Date date = new Date();
                                                    date.getTime();
                                                    long lastday = date.getTime() - 24 * 60 * 60 * 1000;
                                                    date.setTime(lastday);
                                                    tBloggerContent.setCreatetime("2019-" + df.format(date));
                                                } else {
                                                    if (createtime.length() < 8) {
                                                        tBloggerContent.setCreatetime("2019-" + createtime);
                                                    } else {
                                                        tBloggerContent.setCreatetime(createtime);
                                                    }
                                                }
                                                tBloggerContent.setBloggerid(bloggerid);

                                                if (retweeted_status != "") {
                                                    String regsdop = "<[^>]+>";
                                                    Pattern p = Pattern.compile(regsdop, Pattern.CASE_INSENSITIVE);
                                                    Matcher m = p.matcher(retweeted_status);
                                                    String sb = m.replaceAll(""); //过滤html标签
                                                    tBloggerContent.setContentrealy(sb);
                                                }
                                                /**
                                                 * 这里进行一个评论请求,
                                                 * @Param commitchioce是否评论，1是 0不要评论
                                                 */
                                                if (commitchioce == 1 && contentBlogger==true && contentid != null && (contentImg == true || contentOnly == true)) {
                                                    try {
                                                        // 添加时间间隔 1s  解决 418问题。
                                                        Thread.sleep(1000);
                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }
                                                    String urlpoint = "https://m.weibo.cn/comments/hotflow?id=" + contentid + "&mid=" + contentid + "&max_id_type=0";
                                                    //这个是JSON爬虫
                                                    Map<String, Object> headerParamcontent = new HashMap<>();
                                                    headerParamcontent.put(P.REQUEST.USER_AGENT, P.USER_AGENT);
                                                    Map<String, Object> resMapreply = Request.get(urlpoint, headerParamcontent);
                                                    // System.out.println("==========" + resMapreply.get(P.REQUEST.RES_BODY) + "=================================");
                                                    String res = resMapreply.get(P.REQUEST.RES_BODY).toString();
                                                    JSONObject jsoncommit = (JSONObject) JSON.parse(res);

                                                    /**
                                                     * 获得第0个,如果评论不为空
                                                     */
                                                    if (jsoncommit != null) {
                                                        if (jsoncommit.getJSONObject("data") != null) {
                                                            JSONObject firstjsoncommit = (JSONObject) jsoncommit.getJSONObject("data");
                                                            if (firstjsoncommit != null && firstjsoncommit.toString() != "") {
                                                                JSONArray fastcheck = firstjsoncommit.getJSONArray("data");
                                                                if (fastcheck.isEmpty() != true || fastcheck.size() > 0) {
                                                                    for (int j = 0; j < fastcheck.size(); j++) {
                                                                        JSONObject arrayJson = (JSONObject) fastcheck.get(j);
                                                                        JSONObject jsonObject = (JSONObject) arrayJson.get("user");
                                                                        String replay = "";
                                                                        if (jsonObject.get("screen_name") != null) {
                                                                            replay = jsonObject.get("screen_name").toString();
                                                                        }
                                                                        String likecount = "";
                                                                        if (arrayJson.get("like_count") != null) {
                                                                            likecount = arrayJson.get("like_count").toString();//这个是获得点赞量
                                                                        }
                                                                        String textCommit = arrayJson.get("text").toString();
                                                                        String regsdop = "<[^>]+>";
                                                                        Pattern p = Pattern.compile(regsdop, Pattern.CASE_INSENSITIVE);
                                                                        Matcher m = p.matcher(textCommit);
                                                                        String sb = m.replaceAll(""); //过滤html标签

                                                                        /**
                                                                         * 存进评论的数据库,根据最小点赞数
                                                                         */
                                                                        if (flaghotpoint == true) {
                                                                            if (hotpoint > -1 && likecount.equals("") == false) {
                                                                                if (hotpoint <= Integer.parseInt(likecount)) {
                                                                                    TBloggerPoint tBloggerPoint = new TBloggerPoint();
                                                                                    tBloggerPoint.setContentid(Long.parseLong(contentid));
                                                                                    tBloggerPoint.setComment(sb.toString());
                                                                                    tBloggerPoint.setPointsum(Long.parseLong(likecount));
                                                                                    tBloggerPoint.setCommitername(replay);
                                                                                    itBloggerPointService.save(tBloggerPoint);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                //只有通过筛选才能够存进去
                                                if (contentOnly == true || contentImg == true) {
                                                    if (imgflag == 0 && contentimg == false) {
                                                        itBloggerContentService.save(tBloggerContent);
                                                    }
                                                    if (imgflag == 1) {
                                                        itBloggerContentService.save(tBloggerContent);
                                                    }

                                                }

                                                //这里进行时间范围的判断
                                                SimpleDateFormat sdfok = new SimpleDateFormat("yyyy-MM-dd");
                                                try {
                                                    Date dateconcontent = sdfok.parse(tBloggerContent.getCreatetime());//获取到的微博时间
                                                    Date dateconstartok = sdfok.parse(timestart);
                                                    Date dateconendok = sdfok.parse(timeend);
                                                    Timetest timetest1 = new Timetest();
                                                    boolean timetest1EffectiveDateok = timetest1.isEffectiveDate(dateconcontent, dateconstartok, dateconendok);
                                                    timeflag++;
                                                    if (timeflag != 1) {
                                                        if (timetest1EffectiveDateok == false && dateconendok.compareTo(dateconcontent) == 1) {
                                                            //  System.out.println("dateconcontent" + dateconcontent + "dateconstartok" + dateconstartok + "dateconendok" +dateconendok);
                                                            List<String> listContent = new ArrayList();
                                                            QueryWrapper<TBloggerContent> queryWrapper = new QueryWrapper<TBloggerContent>();
                                                            queryWrapper = queryWrapper.eq("bloggerid", bloggerid);
                                                            List<TBloggerContent> list1 = itBloggerContentService.list(queryWrapper);
                                                            String regsdop = "<[^>]+>";

                                                            long sum_count = 0;
                                                            for (int g = 0; g < list1.size(); g++) {
                                                                sum_count++;
                                                                TBloggerContent bloggerContent = list1.get(g);
                                                                try {
                                                                    Date datecontent = sdfok.parse(bloggerContent.getCreatetime());//获取到的微博时间
                                                                    Date dateconstart = sdfok.parse(timestart);
                                                                    Date dateconend = sdfok.parse(timeend);
                                                                    Timetest timetesttry = new Timetest();
                                                                    boolean timetest1EffectiveDate = timetesttry.isEffectiveDate(datecontent, dateconstart, dateconend);

                                                                    //符合时间范围内
                                                                    if (timetest1EffectiveDate == true) {
                                                                        Pattern p = Pattern.compile(regsdop, Pattern.CASE_INSENSITIVE);
                                                                        Matcher m = p.matcher(bloggerContent.getContent());
                                                                        String sb = m.replaceAll(""); //过滤html标签

                                                                        /**
                                                                         * 这个是判断字数的
                                                                         */
                                                                        if (imgflag == 0) {
                                                                            if (wordmax >= 0) {
//                                StringBuffer resetStringword = new StringBuffer();
                                                                                String[] word = sb.toString().split("");
                                                                                int length = word.length;
                                                                                if (wordmax < length) {
                                                                                    QueryWrapper<TBloggerImg> bloggerImgQueryWrapper = new QueryWrapper<TBloggerImg>();
                                                                                    bloggerImgQueryWrapper = bloggerImgQueryWrapper.eq("contentid", bloggerContent.getContentid());
                                                                                    List<TBloggerImg> tBloggerImgList = itBloggerImgService.list(bloggerImgQueryWrapper);
                                                                                    if (tBloggerImgList.size() == 0 || tBloggerImgList == null) {
                                                                                        time_count++;//加次数
                                                                                        listContent.add("\n"+time_count + "、" + bloggerContent.getCreatetime() + "\n");
                                                                                        listContent.add(sb + "\n");//内容
                                                                                        flag = true;
                                                                                        //                                                                                /**
                                                                                        //                                                                                 * 转发的
                                                                                        //                                                                                 */
                                                                                        if (bloggerContent.getContentrealy() != null && bloggerContent.getContentrealy() != "" && flag == true) {
                                                                                            listContent.add("\n" + "转发："  + "\n" );
                                                                                            listContent.add(bloggerContent.getContentrealy()  + "\n");//内容
                                                                                        }
                                                                                        /**
                                                                                         * 评论的
                                                                                         */
                                                                                        if (commitchioce == 1 && flag == true) {
                                                                                            //  String commitWord = "评论：";
                                                                                            JTextArea commitWord = new JTextArea();
                                                                                            Font Str = new Font("宋体", Font.BOLD, 50);
                                                                                            commitWord.setText("\n" + "评论：" + "\n");
                                                                                            commitWord.setFont(Str);
                                                                                            listContent.add(commitWord.getText()  + "\n");
                                                                                            QueryWrapper<TBloggerPoint> tBloggerPointQueryWrapper = new QueryWrapper<TBloggerPoint>();
                                                                                            tBloggerPointQueryWrapper = tBloggerPointQueryWrapper.eq("contentid", bloggerContent.getContentid());
                                                                                            List<TBloggerPoint> list2 = itBloggerPointService.list(tBloggerPointQueryWrapper);
                                                                                            int countpoint = 1;
                                                                                            for (int l = 0; l < list2.size(); l++) {
                                                                                                TBloggerPoint tBloggerPoint = list2.get(l);
                                                                                                if (tBloggerPoint != null) {
                                                                                                    listContent.add( tBloggerPoint.getCommitername() + "：" + tBloggerPoint.getComment()  + "\n");
                                                                                                    countpoint++;
                                                                                                }
                                                                                            }
                                                                                        }

                                                                                    }
                                                                                }
                                                                            }
                                                                        } else {
                                                                            QueryWrapper<TBloggerImg> bloggerImgQueryWrapper = new QueryWrapper<TBloggerImg>();
                                                                            bloggerImgQueryWrapper = bloggerImgQueryWrapper.eq("contentid", bloggerContent.getContentid());
                                                                            List<TBloggerImg> tBloggerImgList = itBloggerImgService.list(bloggerImgQueryWrapper);
                                                                            if (tBloggerImgList != null && tBloggerImgList.size() > 0) {
                                                                                if (wordmax >= 0) {
                                                                                    String[] word = sb.toString().split("");
                                                                                    int length = word.length;
                                                                                    if (wordmax < length) {
                                                                                        time_count++;//加次数
                                                                                        listContent.add("\n"+time_count  + "." + bloggerContent.getCreatetime()+ "\n");
                                                                                        listContent.add(sb + "\n");//内容
                                                                                        flag = true;

                                                                                    }
                                                                                }

                                                                                /**
                                                                                 * 图片加载
                                                                                 */
                                                                                for (int j = 0; j < tBloggerImgList.size(); j++) {
                                                                                    //得到它的一个对象数据
                                                                                    TBloggerImg tBloggerImg = tBloggerImgList.get(j);
                                                                                    listContent.add(tBloggerImg.getImglocalurl());
                                                                                }

                                                                                /**
                                                                                 * 转发的
                                                                                 */
                                                                                if (bloggerContent.getContentrealy() != null && bloggerContent.getContentrealy() != "" && flag == true) {
                                                                                    listContent.add("\n"+"转发："+"\n");
                                                                                    listContent.add(bloggerContent.getContentrealy()+"\n");//内容
                                                                                }
                                                                                if (commitchioce == 1 && flag == true) {

                                                                                    /**
                                                                                     * 评论的
                                                                                     */
                                                                                    listContent.add("\n"+"评论："+"\n");
                                                                                    QueryWrapper<TBloggerPoint> tBloggerPointQueryWrapper = new QueryWrapper<TBloggerPoint>();
                                                                                    tBloggerPointQueryWrapper = tBloggerPointQueryWrapper.eq("contentid", bloggerContent.getContentid());
                                                                                    List<TBloggerPoint> list2 = itBloggerPointService.list(tBloggerPointQueryWrapper);
                                                                                    int countpoint = 1;
                                                                                    for (int l = 0; l < list2.size(); l++) {
                                                                                        TBloggerPoint tBloggerPoint = list2.get(l);
                                                                                        if (tBloggerPoint != null) {
                                                                                            listContent.add(tBloggerPoint.getCommitername() + "：" + tBloggerPoint.getComment()+"\n");
                                                                                            countpoint++;
                                                                                        }
                                                                                    }
                                                                                    //  TBloggerPoint tBloggerPoint = itBloggerPointService.getOne(tBloggerPointQueryWrapper);
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                    if (g == list1.size() - 1) {
                                                                        /**
                                                                         *记录总数
                                                                         */
                                                                        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                                                                        String localTime = df.format(time);
                                                                        LocalDateTime timenow = LocalDateTime.parse(localTime, df);
                                                                        TRecordssum tRecordssum = new TRecordssum();
                                                                        tRecordssum.setRecords(sum_count);
                                                                        tRecordssum.setCreatetime(timenow);
                                                                        tRecordssum.setType("微博");
                                                                        itRecordssumService.save(tRecordssum);
                                                                    }
                                                                } catch (ParseException e) {
                                                                    logger.error(e.getMessage());
                                                                    continue;
                                                                }
                                                            }

                                                            /**
                                                             * 切割字符串
                                                             */
                                                            try {
                                                                /**
                                                                 * 获得当前时间
                                                                 */
                                                                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
                                                                String localTime = df.format(time);
                                                                LocalDateTime timechange = LocalDateTime.parse(localTime, df);
                                                                //这个是时间段的时间转换
                                                                DateTimeFormatter sldt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                                                String  bloggertimeS = timestart.replaceAll("T"," ");
                                                                String  bloggertimeE  = timeend.replaceAll("T"," ");
                                                                String timeform = bloggertimeS + "--" +  bloggertimeE;
                                                                /**
                                                                 * 存入历史记录
                                                                 */
                                                                TBloggerOld tBloggerOld = new TBloggerOld();
                                                                tBloggerOld.setUsername(Utility.getCurrentUser().getUsername());
                                                                tBloggerOld.setCreatetime(timechange);
                                                                tBloggerOld.setBloggername(bloggername);
                                                                tBloggerOld.setUid(uid);
                                                                tBloggerOld.setWordmax(passeDate);
                                                                tBloggerOld.setTimeduring(timeform);

                                                                tBloggerOld.setContainerid(Long.parseLong(id));
                                                                itBloggerOldService.save(tBloggerOld);
                                                                // System.out.println(listContent);
                                                                if (flag == true && imgflag == 1 ) {
                                                                    try {
                                                                        /**
                                                                         * 文字+图片的生成
                                                                         */
                                                                        //     System.out.println(listContent);
                                                                        ImgUtil.exportDoc(listContent, bloggername + passeDate);
                                                                        QueryWrapper<TBloggerContent> queryWrapper1 = new QueryWrapper();
                                                                        queryWrapper1 = queryWrapper1.eq("bloggerid", bloggerid);
                                                                        List<TBloggerContent> tBloggerContents = itBloggerContentService.list(queryWrapper1);

                                                                        /**
                                                                         * 删除图片操作
                                                                         */
                                                                        for (int w = 0; w < tBloggerContents.size(); w++) {
                                                                            TBloggerContent tBloggerContentdelete = tBloggerContents.get(w);
                                                                            QueryWrapper<TBloggerImg> queryWrapperimg = new QueryWrapper();
                                                                            queryWrapperimg = queryWrapperimg.eq("contentid", tBloggerContentdelete.getContentid());
                                                                            itBloggerImgService.remove(queryWrapperimg);
                                                                        }
                                                                        /**
                                                                         * 删除评论操作
                                                                         */
                                                                        if (commitchioce == 1) {
                                                                            for (int w = 0; w < tBloggerContents.size(); w++) {
                                                                                TBloggerContent tBloggerContentdelete = tBloggerContents.get(w);
                                                                                QueryWrapper<TBloggerPoint> queryWrapperPoint = new QueryWrapper();
                                                                                queryWrapperPoint = queryWrapperPoint.eq("contentid", tBloggerContentdelete.getContentid());
                                                                                itBloggerPointService.remove(queryWrapperPoint);
                                                                            }
                                                                        }
                                                                        itBloggerContentService.remove(queryWrapper1);
                                                                        //  System.out.println(bloggername);
                                                                        String datarepsonse = bloggername + passeDate + Utility.getCurrentUser().getUsername();
                                                                        response.getWriter().write(datarepsonse);
                                                                        return;
                                                                    } catch (DocumentException d) {
                                                                        logger.error(d.getMessage());
                                                                        continue;
                                                                    }
                                                                } else {
                                                                    /**
                                                                     * 导出文件，纯文字
                                                                     */

                                                                    String datareplace = listContent.toString().replace(",", "");
                                                                    datareplace = datareplace.replace("[", "");
                                                                    datareplace = datareplace.replace("]", "");
                                                                    //     System.out.println(datareplace);
                                                                    if (datareplace.equals("")) {
                                                                        QueryWrapper<TBloggerContent> tBloggerContentQueryWrapper = new QueryWrapper<TBloggerContent>();
                                                                        tBloggerContentQueryWrapper = tBloggerContentQueryWrapper.eq("bloggerid", bloggerid);
                                                                        List<TBloggerContent> list2 = itBloggerContentService.list(tBloggerContentQueryWrapper);
                                                                        for (int j = 0; j < list2.size(); j++) {
                                                                            TBloggerContent tBloggerContentfas = list2.get(j);
                                                                            QueryWrapper<TBloggerImg> tBloggerImgQueryWrapper = new QueryWrapper<TBloggerImg>();
                                                                            tBloggerImgQueryWrapper = tBloggerImgQueryWrapper.eq("contentid", tBloggerContentfas.getContentid());
                                                                            itBloggerImgService.remove(tBloggerImgQueryWrapper);
                                                                        }
                                                                        itBloggerContentService.remove(tBloggerContentQueryWrapper);
                                                                        String a = "9999";
                                                                        response.getWriter().write(a);
                                                                        return;
                                                                    }
                                                                    ExportWord e = new ExportWord();
                                                                    e.creatDoc(FileUtil.uploadLocalPath + bloggername + passeDate + Utility.getCurrentUser().getUsername() + "_微博.doc", datareplace.toString());
                                                                    QueryWrapper<TBloggerContent> queryWrapper1 = new QueryWrapper();
                                                                    queryWrapper1 = queryWrapper1.eq("bloggerid", bloggerid);

                                                                    /**
                                                                     * 如果评论存在
                                                                     */
                                                                    if (commitchioce == 1) {
                                                                        List<TBloggerContent> tBloggerContents = itBloggerContentService.list(queryWrapper1);
                                                                        for (int w = 0; w < tBloggerContents.size(); w++) {
                                                                            TBloggerContent tBloggerContentcommit = tBloggerContents.get(w);
                                                                            QueryWrapper<TBloggerPoint> queryWrapperPoint = new QueryWrapper();
                                                                            queryWrapperPoint = queryWrapperPoint.eq("contentid", tBloggerContentcommit.getContentid());
                                                                            itBloggerPointService.remove(queryWrapperPoint);
                                                                        }
                                                                    }
                                                                    itBloggerContentService.remove(queryWrapper1);
                                                                    //  System.out.println(bloggername);
                                                                    String datarepsonse = bloggername + passeDate + Utility.getCurrentUser().getUsername();
                                                                    // System.out.println(datarepsonse);
                                                                    response.getWriter().write(datarepsonse);
                                                                    return;
                                                                }
                                                            } catch (IOException e1) {
                                                                logger.error(e1.getMessage());
                                                                continue;
                                                            }
                                                        }
                                                    }
                                                } catch (ParseException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                    }
                                }
                                //清空
                                contentOnly = false;
                                contentImg = false;
                                contentimg = false;
                                contentBlogger = false;
                            }
                        }
                    }
                }

                else{
                    /**
                     * 请求不到数据返回
                     */
                    try{
                        if(flag == true){
                            QueryWrapper<TBloggerContent> queryWrapper1 = new QueryWrapper();
                            queryWrapper1 = queryWrapper1.eq("bloggerid", bloggerid);
                            List<TBloggerContent> tBloggerContents =  itBloggerContentService.list(queryWrapper1);

                            /**
                             * 删除图片操作
                             */
                            for(int w = 0 ;w<tBloggerContents.size();w++){
                                TBloggerContent tBloggerContentdelete = tBloggerContents.get(w);
                                QueryWrapper<TBloggerImg> queryWrapperimg = new QueryWrapper();
                                queryWrapperimg = queryWrapperimg.eq("contentid", tBloggerContentdelete.getContentid());
                                itBloggerImgService.remove(queryWrapperimg);
                            }
                            /**
                             * 删除评论操作
                             */
                            if(commitchioce == 1){
                                for(int w = 0 ;w<tBloggerContents.size();w++){
                                    TBloggerContent tBloggerContentdelete = tBloggerContents.get(w);
                                    QueryWrapper<TBloggerPoint> queryWrapperPoint = new QueryWrapper();
                                    queryWrapperPoint = queryWrapperPoint.eq("contentid", tBloggerContentdelete.getContentid());
                                    itBloggerPointService.remove(queryWrapperPoint);
                                }
                            }
                            itBloggerContentService.remove(queryWrapper1);
                        }
                        else{
                            QueryWrapper<TBloggerContent> tBloggerContentQueryWrapper = new QueryWrapper<TBloggerContent>();
                            tBloggerContentQueryWrapper = tBloggerContentQueryWrapper.eq("bloggerid", bloggerid);
                            List<TBloggerContent> list2 = itBloggerContentService.list(tBloggerContentQueryWrapper);
                            for(int j = 0; j < list2.size();j++){
                                TBloggerContent tBloggerContentfas = list2.get(j);
                                QueryWrapper<TBloggerImg> tBloggerImgQueryWrapper = new QueryWrapper<TBloggerImg>();
                                tBloggerImgQueryWrapper = tBloggerImgQueryWrapper.eq("contentid", tBloggerContentfas.getContentid());
                                itBloggerImgService.remove(tBloggerImgQueryWrapper);
                            }
                            itBloggerContentService.remove(tBloggerContentQueryWrapper);
                        }
                        String msg = "9998";
                        response.getWriter().write(msg);
                        return;
                    }
                    catch (IOException e){
                        logger.error(e.getMessage());
                        continue;
                    }
                }
            }catch (JSONException e){
                //e.printStackTrace();

                logger.error(e.getMessage());
                continue;
            }
        }

    }


    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }



    /**
     * 这个是导出word文档，目标是G盘的word文件夹下的zhihu.doc文档
     * @param model
     * @param response
     * @param request
     */

    @RequestMapping("/ExportWord")
    public void ExportWord(Model model, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        try{
            QueryWrapper<TBloggerOld> queryCWrapper1 = new QueryWrapper<TBloggerOld>();
            queryCWrapper1 = queryCWrapper1.eq("id", id);
            // System.out.println("queryCWrapper" + queryCWrapper1);
            String keyword =itBloggerOldService.getOne(queryCWrapper1).getBloggername();
            long timejudge = itBloggerOldService.getOne(queryCWrapper1).getWordmax();
            response.getWriter().write(keyword + timejudge + Utility.getCurrentUser().getUsername());
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }



}

