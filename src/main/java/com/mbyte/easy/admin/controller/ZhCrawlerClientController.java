package com.mbyte.easy.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mbyte.easy.admin.Util.ExportWord;
import com.mbyte.easy.admin.Util.Request;
import com.mbyte.easy.admin.Util.WechatClient;
import com.mbyte.easy.admin.entity.*;
import com.mbyte.easy.admin.service.*;
import com.mbyte.easy.util.FileUtil;
import com.mbyte.easy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 吴天豪
 * 这个是用来爬取知乎的话题的
 * @param
 */
@Controller
@RequestMapping(value = "/zhihu/")
public class ZhCrawlerClientController {
    //    @Test
    @Autowired
    private IZhihuService iZhihuService;

    @Autowired
    private IZhihuOldrecordsService iZhihuOldrecordsService;

    @Autowired
    private IZhihuRecordsService iZhihuRecordsService;

    @Autowired
    private  IAnswerService iAnswerService;

    @Autowired
    private ITRecordssumService itRecordssumService;
    @RequestMapping(value = "zhihucatch")
    public void crawlerClient_01(Model model, HttpServletResponse response, HttpServletRequest request) {
        //生成唯一标识
    //    String uuid =  UUID.randomUUID().toString().replaceAll("-","");
        response.setContentType("text/html;charset=utf-8");
        int page;
        int currt = 0;
        int jump;
        int currtans = 0;
        /**
         * 存入条数历史记录
         */
        LocalDateTime time1 = LocalDateTime.now();
        DateTimeFormatter df1= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTimeever = df1.format(time1);
        LocalDateTime timenow = LocalDateTime.parse(localTimeever,df1);
        //时间戳
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datatime = new Date();
        long passeDate = datatime.getTime();

        String v = "";
        //打印
        List<String> sum = new ArrayList<String>();
        //这个是接受数据库数据的对象，用来存入数据库数据的
        Zhihu zhihu = new Zhihu();
        //存入条总数
        TRecordssum tRecordssum = new TRecordssum();
        /**
         * 过滤标签
         */
        String regsok = "<[^>]+>";
        //获取系统当前时间
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String localTime = df.format(time);
        LocalDateTime timechange = LocalDateTime.parse(localTime,df);
        //获得前台的id
        String id = request.getParameter("id");
        QueryWrapper<ZhihuRecords> queryCWrapper = new QueryWrapper<ZhihuRecords>();
        queryCWrapper = queryCWrapper.eq("id", id);
        String question =iZhihuRecordsService.getOne(queryCWrapper).getKeyword();
        ZhihuRecords zhihuRecords =iZhihuRecordsService.getOne(queryCWrapper);
        int choice = zhihuRecords.getTypechoice();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            page = 20 * i;
            jump = page + 2;
            String reg = "[^\u4e00-\u9fa5]";
            try {
                String url = "https://www.zhihu.com/api/v4/search_v3?t=general&q=" + question + "&correction=1&offset=" + page + "&limit=20&lc_idx=" + jump + "&show_all_topics=0&search_hash_id=6198bd6b0e8dab406f9568dcf95e84e4&vertical_info=1%2C1%2C1%2C1%2C0%2C1%2C0%2C0%2C0%2C1";
                //这个是JSON爬虫
                Map<String, Object> headerParams = new HashMap<>();
                headerParams.put(P.REQUEST.USER_AGENT, P.USER_AGENT);//将cookie值也放入请求中
                 headerParams.put(P.REQUEST.COOKIE, P.COOKIE);//将cookie值也放入请求中
                Map<String, Object> resMap = Request.get(url, headerParams);
            //  System.out.println("==========" + resMap.get(P.REQUEST.RES_BODY) + "=================================");
                String Zhihuword = resMap.get(P.REQUEST.RES_BODY).toString();
                String[] value = resMap.get(P.REQUEST.RES_BODY).toString().split(",");
                String lay = Zhihuword.replaceAll(reg,"");
                //    System.out.println("lay"+lay.equals(""));
                if(lay.equals("")==false) {
                    /**
                     * json获取
                     */
                    JSONObject json = (JSONObject) JSON.parse(Zhihuword);
                    //    System.out.println("lay"+lay.equals(""));
                    if (json != null) {
                        JSONArray firstDataJson = json.getJSONArray("data");
                        if (firstDataJson.isEmpty() != true || firstDataJson.size() > 0) {
                            int answercount = 0;//定义回答个数
                            for (int u = 0; u < firstDataJson.size(); u++) {
                                /**
                                 * 这个是标题的
                                 */
                                JSONObject jsonObject = (JSONObject) firstDataJson.get(u);
                                //获得标题
                                String title = "";
                                JSONObject jsonObjectheight = jsonObject.getJSONObject("highlight");
                                if (jsonObjectheight != null) {
                                    if (jsonObjectheight.get("title") != null) {
                                        title = jsonObject.getJSONObject("highlight").get("title").toString();
                                    }
                                }

                                //获得话题id
                                String titleid = "";
                                JSONObject jsonObject1 = jsonObject.getJSONObject("object");
                                if (jsonObject1 != null) {
                                    JSONObject questionUrl = jsonObject1.getJSONObject("question");
                                    if (questionUrl != null) {
                                        titleid = questionUrl.get("id").toString();
                                    }
                                }
                                // System.out.println(titleid);
                                Pattern patt = Pattern.compile(regsok, Pattern.CASE_INSENSITIVE);
                                Matcher mas = patt.matcher(title);
                                String titleFinal = mas.replaceAll(""); //过滤html标签
                                //    System.out.println(titleFinal);
                                /**
                                 * 知乎的话题置入数据库
                                 */
                                if (title != null && title != "") {
                                    zhihu.setTitle(titleFinal);
                                    zhihu.setKeyword(question);
                                    zhihu.setUsername(Utility.getCurrentUsername());
                                    iZhihuService.save(zhihu);
                                }
                                currt = currt + 1;
                                //这个是导出word文档的数据
                                if (currt == 201) {
                                    /**
                                     * 存入历史记录
                                     */
                                    ZhihuOldrecords zhihuOldrecords = new ZhihuOldrecords();
                                    zhihuOldrecords.setCreatetime(timechange);
                                    zhihuOldrecords.setKeyword(zhihuRecords.getKeyword());
                                    zhihuOldrecords.setUsername(Utility.getCurrentUser().getUsername());
                                    zhihuOldrecords.setUuid(String.valueOf(passeDate));
                                    zhihuOldrecords.setZhihuid(Long.parseLong(id));
                                    iZhihuOldrecordsService.save(zhihuOldrecords);
                                    /**
                                     *生成word文档
                                     */

                                    List<String> zhihudatatest = new ArrayList<String>();//这个链表用来word存储
                                    QueryWrapper<Zhihu> LookZhihudata = new QueryWrapper<Zhihu>();//这个是用来查找标题的
                                    LookZhihudata = LookZhihudata.eq("keyword", question);
                                    List<Zhihu> listodap = iZhihuService.list(LookZhihudata);//这个是用来查找所有标题的
                                    int funSum = 0;
                                    for (int r = 0; r < listodap.size(); r++) {
                                        Zhihu zhihute = new Zhihu();
                                        zhihute.setTitle(listodap.get(r).getTitle());//获得标题
                                        QueryWrapper<Answer> answerqueryWrapper = new QueryWrapper<Answer>();
                                        answerqueryWrapper = answerqueryWrapper.eq("title", zhihute.getTitle());//传入标题这个变量
                                        List<Answer> list2 = iAnswerService.list(answerqueryWrapper);
                                        /**
                                         * choice = 1为存在标题和回答，0为只要标题
                                         */
                                        if (choice == 1) {
                                            for (int g = 0; g < list2.size(); g++) {
                                                Answer answerdata = list2.get(g);
                                                //标题存入链表
                                                int judges = 1;
                                                zhihudatatest.add(funSum + 1 + "、" + zhihute.getTitle() + "\n");
                                                for (int s = 1; s < 6; s++) {
                                                    if (answerdata.getAnswerone() != null && s == 1) {
                                                        zhihudatatest.add("(" + s + "):" + answerdata.getAnswerone() + "\n");
                                                        judges++;
                                                    } else if (answerdata.getAnswertwo() != null && s == 2) {
                                                        zhihudatatest.add("(" + s + "):" + answerdata.getAnswertwo() + "\n");
                                                        judges++;
                                                    } else if (answerdata.getAnswerthree() != null && s == 3) {
                                                        zhihudatatest.add("(" + s + "):" + answerdata.getAnswerthree() + "\n");
                                                        judges++;
                                                    } else if (answerdata.getAnswerfour() != null && s == 4) {
                                                        zhihudatatest.add("(" + s + "):" + answerdata.getAnswerfour() + "\n");
                                                        judges++;
                                                    } else if (answerdata.getAnswerfive() != null && s == 5) {
                                                        zhihudatatest.add("(" + s + "):" + answerdata.getAnswerfive() + "\n");
                                                        judges++;
                                                    }
                                                }
                                                funSum++;
                                            }
                                        } else {
                                            zhihudatatest.add(funSum + 1 + "、" + zhihute.getTitle() + "\n");
                                            funSum++;
                                        }
                                        if (r == listodap.size() - 1) {

                                            //存入历史爬取值
                                            tRecordssum.setCreatetime(timenow);
                                            tRecordssum.setType("知乎");
                                            itRecordssumService.save(tRecordssum);
                                            ExportWord e = new ExportWord();
                                            System.out.println("zhihudatatest.toString()" + zhihudatatest.toString());
                                            String datareplace = zhihudatatest.toString().replace(",", "");
                                            datareplace = datareplace.replace("[", "");
                                            datareplace = datareplace.replace("]", "");
                                            e.creatDoc(FileUtil.uploadLocalPath + question + passeDate + Utility.getCurrentUser().getUsername() + "_知乎.doc", datareplace.toString());
                                            QueryWrapper<Answer> answesuer = new QueryWrapper<Answer>();
                                            answesuer = answesuer.eq("username", Utility.getCurrentUser().getUsername());
                                            iAnswerService.remove(answesuer);
                                            QueryWrapper<Zhihu> zhihuQueryWrapper = new QueryWrapper<Zhihu>();
                                            zhihuQueryWrapper = zhihuQueryWrapper.eq("username", Utility.getCurrentUser().getUsername());
                                            iZhihuService.remove(zhihuQueryWrapper);
                                        }
                                    }
                                    response.getWriter().write(question + passeDate + Utility.getCurrentUser().getUsername());
                                    return;
                                }

                                //如果是需要爬取评论的
                                if (choice == 1 && titleid != null && titleid != "") {
                                    /**
                                     * 尝试请求路径拿前五的答案,
                                     */
                                    String urlanswer = "https://www.zhihu.com/api/v4/questions/" + titleid + "/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled%2Cis_recognized%2Cpaid_info%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B*%5D.topics&offset=&limit=5&sort_by=default&platform=desktop";
                                    Map<String, Object> headerParamsurl = new HashMap<>();
                                    headerParamsurl.put(P.REQUEST.USER_AGENT, P.USER_AGENT);//将cookie值也放入请求中
                                    Map<String, Object> resMapurl = Request.get(urlanswer, headerParamsurl);
                                    //这个是字符串，得到网站的数据字符串
                                    System.out.println("==========" + resMapurl.get(P.REQUEST.RES_BODY) + "=================================");
                                    String answer = "";
                                    answer = resMapurl.get(P.REQUEST.RES_BODY).toString();
                                    JSONObject jsonAnswer = (JSONObject) JSON.parse(answer);
                                    if (jsonAnswer != null) {
                                        JSONArray firstAnswerJson = jsonAnswer.getJSONArray("data");
                                        if (firstAnswerJson.isEmpty() != true || firstAnswerJson.size() > 0) {
                                            Answer answer1 = new Answer();
                                            answercount = 0;
                                            answer1.setTitle(titleFinal);
                                            answer1.setUsername(Utility.getCurrentUser().getUsername());
                                            for (int j = 0; j < firstAnswerJson.size(); j++) {
                                                answercount++;
                                                JSONObject jsonAnswerok = (JSONObject) firstAnswerJson.get(j);
                                                String answerone = "";
                                                // System.out.println(jsonAnswerok);
                                                if (jsonAnswerok != null) {
                                                    answerone = jsonAnswerok.get("content").toString();

                                                    /**
                                                     * 过滤前端标签
                                                     */
                                                    Pattern pattAnswer = Pattern.compile(regsok, Pattern.CASE_INSENSITIVE);
                                                    Matcher masanswer = pattAnswer.matcher(answerone);
                                                    //得到回答
                                                    String AnswerFinal = masanswer.replaceAll(""); //过滤html标签
                                                    System.out.println("AnswerFinal" + AnswerFinal);
                                                    if (j < 5) {
                                                        if (AnswerFinal != null && AnswerFinal != "") {
                                                            if (answercount == 1) {
                                                                answer1.setAnswerone(AnswerFinal);
                                                            } else if (answercount == 2) {
                                                                answer1.setAnswertwo(AnswerFinal);
                                                            } else if (answercount == 3) {
                                                                answer1.setAnswerthree(AnswerFinal);
                                                            } else if (answercount == 4) {
                                                                answer1.setAnswerfour(AnswerFinal);
                                                            } else if (answercount == 5) {
                                                                answer1.setAnswerfive(AnswerFinal);
                                                                iAnswerService.save(answer1);
                                                            }
                                                        } else {
                                                            iAnswerService.save(answer1);
                                                        }
//                                                                   System.out.println(answer1);
                                                    }

                                                }
                                                if (j == firstAnswerJson.size() - 1) {
                                                    headerParamsurl.clear();
                                                    resMapurl.clear();
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                            //  System.out.println("list=====" + list);
                            System.out.println("已统计个数" + currt);
                            tRecordssum.setRecords(Long.parseLong(String.valueOf(currt)));
                        }
                    }

                }
                else {

                    tRecordssum.setCreatetime(timenow);
                    tRecordssum.setType("知乎");
                    itRecordssumService.save(tRecordssum);
                    /**
                     * 存入历史记录
                     */
                    ZhihuOldrecords zhihuOldrecords = new ZhihuOldrecords();
                    zhihuOldrecords.setCreatetime(timechange);
                    zhihuOldrecords.setKeyword(zhihuRecords.getKeyword());
                    zhihuOldrecords.setUsername(Utility.getCurrentUser().getUsername());
                    zhihuOldrecords.setZhihuid(Long.parseLong(id));
                    iZhihuOldrecordsService.save(zhihuOldrecords);
                    List<String> zhihudatatest = new ArrayList<String>();//这个链表用来word存储
                    QueryWrapper<Zhihu> LookZhihudata = new QueryWrapper<Zhihu>();//这个是用来查找标题的
                    LookZhihudata = LookZhihudata.eq("keyword", question);
                    List<Zhihu> listodap = iZhihuService.list(LookZhihudata);//这个是用来查找所有标题的

                    if(choice==1){
                        int titlecout = 0;
                        for (int r = 0;r < listodap.size(); r++){
                            Zhihu zhihute = new Zhihu();
                            zhihute.setTitle(listodap.get(r).getTitle());//获得标题
                            QueryWrapper<Answer> answerqueryWrapper = new QueryWrapper<Answer>();
                            answerqueryWrapper = answerqueryWrapper.eq("title", zhihute.getTitle());//传入标题这个变量
                            List<Answer> list2 = iAnswerService.list(answerqueryWrapper);
                            //判断链表是否有值

                            for(int g = 0; g < list2.size(); g++){
                                Answer answerdata = list2.get(g);
                                if(answerdata.getAnswerfive() != null &&answerdata.getAnswerfour() != null &&answerdata.getUsername() != null){
                                    //标题存入链表
                                    zhihudatatest.add(titlecout+1+"、"+zhihute.getTitle()+"\n");
                                    zhihudatatest.add("(1):"+answerdata.getAnswerone()+"\n");
                                    zhihudatatest.add("(2):"+answerdata.getAnswertwo()+"\n");
                                    zhihudatatest.add("(3):"+answerdata.getAnswerthree()+"\n");
                                    zhihudatatest.add("(4):"+answerdata.getAnswerfour()+"\n");
                                    zhihudatatest.add("(5):"+answerdata.getAnswerfive()+"\n\n");
                                    if(titlecout == 199){
                                        ExportWord e = new ExportWord();
                                        String datareplace = zhihudatatest.toString().replace(",","");
                                        datareplace= datareplace.replace("[","");
                                        datareplace= datareplace.replace("]","");
                                        System.out.println("====" + datareplace);
                                        e.creatDoc(FileUtil.uploadLocalPath + question + passeDate + Utility.getCurrentUser().getUsername() +  "_知乎.doc", datareplace.toString());
                                        /**
                                         * 导出word
                                         */
                                        System.out.println("对不起，没有爬取到数据哦");
                                        QueryWrapper<Answer> answesuer = new QueryWrapper<Answer>();
                                        answesuer = answesuer.eq("username", Utility.getCurrentUser().getUsername());
                                        iAnswerService.remove(answesuer);
                                        String passfan = question + passeDate + Utility.getCurrentUser().getUsername();
                                        response.getWriter().write(passfan);
                                        return;
                                    }
                                    answerdata = new Answer();
                                    titlecout++;
                                }
                            }

                            if(r == listodap.size()-1){
                                ExportWord e = new ExportWord();
                                String datareplace = zhihudatatest.toString().replace(",","");
                                datareplace= datareplace.replace("[","");
                                datareplace= datareplace.replace("]","");
                                  System.out.println(datareplace);
                                e.creatDoc(FileUtil.uploadLocalPath +question+ passeDate+Utility.getCurrentUser().getUsername()+"_知乎.doc", datareplace.toString());
                            }
                        }
                    }
                    else{
                        int titlecoutok = 0;
                        for (int r = 0;r < listodap.size(); r++){
                                Zhihu zhihu1 = new Zhihu();
                                   zhihu1 = listodap.get(r);
                                    //标题存入链表
                                    zhihudatatest.add(titlecoutok+1+"、"+zhihu1.getTitle()+"\n");
                                  //  System.out.println("zhihudatatestzhihudatatest"+zhihudatatest);
                            if(r == listodap.size()-1){
                                ExportWord e = new ExportWord();
                                String datareplace = zhihudatatest.toString().replace(",","");
                                datareplace= datareplace.replace("[","");
                                datareplace= datareplace.replace("]","");
                          System.out.println(datareplace);
                                e.creatDoc(FileUtil.uploadLocalPath +question+ passeDate +Utility.getCurrentUser().getUsername()+"_知乎.doc", datareplace.toString());
                            }
                            titlecoutok++;
                        }
                    }
                    if(choice==1){
                        QueryWrapper<Zhihu> answesuer1 = new QueryWrapper<Zhihu>();
                        QueryWrapper<Answer> answesuer = new QueryWrapper<Answer>();
                        answesuer = answesuer.eq("username", Utility.getCurrentUser().getUsername());
                        answesuer1 = answesuer1.eq("username", Utility.getCurrentUser().getUsername());
                        iAnswerService.remove(answesuer);
                        iZhihuService.remove(answesuer1);
                    }
                    else{
                        QueryWrapper<Zhihu> answesuer1 = new QueryWrapper<Zhihu>();
                        answesuer1 = answesuer1.eq("username", Utility.getCurrentUser().getUsername());
                        iZhihuService.remove(answesuer1);
                    }
                    response.getWriter().write(question + passeDate + Utility.getCurrentUser().getUsername());
                    return;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
            QueryWrapper<ZhihuRecords> queryCWrapper1 = new QueryWrapper<ZhihuRecords>();
            queryCWrapper1 = queryCWrapper1.eq("id", id);
          //  System.out.println("queryCWrapper" + queryCWrapper1);
            String keyword = iZhihuRecordsService.getOne(queryCWrapper1).getKeyword();
            response.getWriter().write(keyword);
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }


    /**
     * 这个是导出word文档，目标是G盘的word文件夹下的zhihu.doc文档
     * @param model
     * @param response
     * @param request
     */

    @RequestMapping("/ZhOldExportWord")
    public void ZhOldExportWord(Model model, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        try{
            QueryWrapper<ZhihuOldrecords> queryCWrapper1 = new QueryWrapper<ZhihuOldrecords>();
            queryCWrapper1 = queryCWrapper1.eq("id", id);
           // System.out.println("queryCWrapper" + queryCWrapper1);
            String keyword = iZhihuOldrecordsService.getOne(queryCWrapper1).getKeyword();
            String uutime = iZhihuOldrecordsService.getOne(queryCWrapper1).getUuid();
            response.getWriter().write(keyword + uutime + Utility.getCurrentUser().getUsername());
        }
        catch (IOException e ){
            e.printStackTrace();
        }
    }



}

