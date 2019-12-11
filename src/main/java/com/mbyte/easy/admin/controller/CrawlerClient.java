package com.mbyte.easy.admin.controller;


import com.mbyte.easy.admin.Util.Request;
import com.mbyte.easy.admin.entity.P;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by rongyaowen on 2018/10/4.
 */
@Controller
@RequestMapping(value = "test")
public class CrawlerClient {

    /**
     * 第一个爬虫程序，获取源码，注意需要带上User_Agetn
     */
//    @RequestMapping(value = "ok")
//    public void Test(HttpResponse response, HttpRequest request){
//        return "test";
//    }
    @Test
   // @RequestMapping
    public void crawlerClient_01() {
        //  String url = "https://www.douban.com";
        String url = "https://www.zhihu.com/api/v4/search_v3?t=general&q=%E6%95%99%E5%B8%88&correction=1&offset=20&limit=20&lc_idx=23&show_all_topics=0&search_hash_id=d9f7c0b41027082f72f78a8df1706d87&vertical_info=1%2C1%2C1%2C1%2C0%2C1%2C0%2C0%2C0%2C1";
        //这个是JSON爬虫
        Map<String, Object> headerParams = new HashMap<>();
        headerParams.put(P.REQUEST.USER_AGENT, P.USER_AGENT);
        Map<String, Object> resMap = Request.get(url, headerParams);
        System.out.println("==========" + resMap.get(P.REQUEST.RES_BODY) + "=================================");
        String a = resMap.get(P.REQUEST.RES_BODY).toString();
            //汉字提取
        int index=a.indexOf("question\",\"name");
        int end=a.indexOf("url", index+1);
        String result=a.substring(index,end);
        String reg = "[^\u4e00-\u9fa5]";
        String b  = result.replaceAll(reg, "");
        System.out.println("全是汉字=============="+ b +"==============");

       // json切割
        int cout = 1;
        String[] value = resMap.get(P.REQUEST.RES_BODY).toString().split(",");
        for (int i = 1; i < value.length-1; i++) {
          //  int index1=value[i].indexOf("name");
       //     System.out.println("=============="+index1+"===========已经统计:"+cout);
          //  int end1=value[i].indexOf("？", index1+1);

//            if(end1>0){
//                String result1=a.substring(index1,end1);
//                result1= result1.replaceAll(reg, "");
//                System.out.println("全是汉字+++++++++++++=============="+ result1 +"==============");
//            }
            cout++;
             System.out.println("===========已经统计:"+cout);
             System.out.println(value[i]+" ");
        }


        //根据第二个点的位置，截取 字符串。得到结果 result
//        String result=a.substring(index,end);
//
//        System.out.println("=========="+result+"==============");
//        String reg = "[^\u4e00-\u9fa5]";
//        result = result.replaceAll(reg, "");
//        //输出结果
//        System.out.println(result+"?");

//        /**
//         * 这个是非=jsoup爬虫
//         */
//        try{
//            Document doc = null;
//            doc = Jsoup.connect(url).userAgent(P.REQUEST.USER_AGENT).timeout(5000).get();
//            Elements listDiv = doc.getElementsByAttributeValue("class", "witkey-name fl text-overflow");
//            //	System.out.println("标题1111：" + listDiv.html());
//            for (Element element : listDiv) {
//                Elements texts = element.getElementsByTag("a");
//                System.out.println("标题：" + texts.html());
//                for (Element text : texts) {
//                    String ptext = text.attr("href");
//                    System.out.println("地址：" + ptext);
//                }
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        return  resMap.get(P.REQUEST.RES_BODY).toString();
    }


//    @Test
//    public void get() throws Exception {
//        System.out.println(P.COOKIE);
//        String url = "https://www.douban.com";
//        String resStr = Request.get(url);
//        System.out.println(resStr);
//    }
//
//    @Test
//    public void post() throws Exception {
////        String formDataStr = "source=index_nav&form_email=572491392%40qq.com&form_password=douban753159&captcha-solution=school&captcha-id=qIXn45inOJui2weLg5TC2hss%3Aen";
//        String authCodeUrl = "https://www.douban.com/j/misc/captcha";
//        String resStr = Request.get(authCodeUrl);
//        System.out.println(resStr);
//        ReturnMessage returnMessage = GsonUtil.fromJson(resStr, ReturnMessage.class);
//        System.out.println("验证码链接：" + "https:" + returnMessage.getUrl());
//        System.out.println("token:" + returnMessage.getToken());
//
//        String formEmail = "572491392@qq.com";
//        String fromPassword = "douban753159";
//        String captchaSolution = "";
//        String captchaId = returnMessage.getToken();
//
//        String formDataStr = "source=index_nav" +
//                "&form_email=" + formEmail +
//                "&form_password=" + fromPassword +
//                "&captcha-solution=" + captchaSolution +
//                "&captcha-id=" + captchaId;
//
//        System.out.println(formDataStr);
//
//    }
//
//    @Test
//    public void login() {
//        String authCode = "language";
//        String captchaId  = "vodyh1phQLB1CBbcpWOv3ANW:en";
//
//
//        String formDataStr = "source=index_nav" +
//                "&form_email=572491392@qq.com" +
//                "&form_password=douban753159" +
//                "&captcha-solution="+authCode +
//                "&captcha-id="+captchaId;
//        System.out.println(formDataStr);
//        String url = "https://www.douban.com/accounts/login";
//        Map<String,Object> resMap = Request.post(url, formDataStr);
//        System.out.println(resMap.get("cookie"));
//        System.out.println(P.COOKIE +  ";" +resMap.get("cookie"));
//
//
//    }
}
