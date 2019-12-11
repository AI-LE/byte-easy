package com.mbyte.easy.admin.Util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.mbyte.easy.admin.entity.BlogAnswers;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author 魏皓
 */
@Component
public class BlogQAUtil {

    public  String SerachQuestionUrlPrefix="https://m.weibo.cn/api/container/getIndex?containerid=100103type%3D58%26q%3D";  //设置搜索问答时的URL前缀
    public  String SerachQuestionUrlsuffix="%26t%3D0&page_type=searchall";
    public  String toStringEntity="";
    //public  String path="G:\\CreateDoc.docx";

    @Value("${file.upload.local.path}")
    private  String Prefix;

    public  String  GetContent(String question, Boolean choice, LocalDateTime beginTime,LocalDateTime endTime) throws IOException, InterruptedException {
        DateFormat tempTime = DateFormat.getDateTimeInstance();
        String t1 = tempTime.format(new Date()).replace(" ", "").replace(":", "").replace("-", "");
        String path=Prefix+question+t1+".docx";
        String keyWord = SerachQuestionUrlPrefix+URLEncoder.encode(question)+SerachQuestionUrlsuffix;//将问题关键字作为参数放置在url中可得到所搜包含关键字的问题的JSON
        ExportWord2.writeTOC(path,"关键字："+question+"\n");
        List<BlogAnswers> test=null;
        int index=1;
        int patient=3;
        while(index<30)
        {
            try {
                String entity = getHttpEntity(keyWord + "&page=" + index);
                System.out.println("第"+index+"页的内容:----"+entity);
                int cardsIndex = 0;
                if (index <= 1) cardsIndex = 1;
                else cardsIndex = 0;
                test = JsonAnalasis(entity, cardsIndex, question, choice,beginTime,endTime,path);
            }catch (NullPointerException e)
            {
                //System.out.println("空指针导致的异常");
                patient--;
                if(patient<=0)
                {
                    index++;
                    patient=3;
                }
                Thread.sleep(20000);
                if(index<=1)
                {
                    Thread.sleep(30000);
                }
                continue;

            }
            catch (IndexOutOfBoundsException e)
            {

                //System.out.println("越界导致的异常");
                patient--;
                if(patient<=0)
                {
                    index++;
                    patient=3;
                }
                Thread.sleep(20000);
                if(index<=1)
                {
                    Thread.sleep(30000);
                }
                continue;
            }
            index++;
            Thread.sleep(1000);
        }
        return path;
    }



    //发出http请求获取响应体的内容
    public  String getHttpEntity(String url)
    {
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(globalConfig).build();
        String TostringEntity=null;
        try{
            //2.创建一个httpGET对象
            HttpGet httpGet=new HttpGet(url);
            httpGet.addHeader("MWeibo-Pwa: 1","1");
            httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36 QIHU 360SE");
            httpGet.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
            httpGet.addHeader(new BasicHeader("Cookie","SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9W5M6oJ4ZH3W6_qMv8a6Bq6Q5JpX5K-hUgL.Fozp1hnceKBReKM2dJLoIE-LxKnLBK2L1h5LxKnL1h.LBozLxKqLBo-LB-2LxKqLB.eLB-iN; SCF=Angm47ghi9pH-LV7JS_qimo3HqMwaZt4vHo89UY686RrgHGs--Q1AFTxfcMQG2n9cLvNPK169apqhbZPgjvH448.; SUHB=0A0IC92Mh_d6rL; SUB=_2AkMrswSTdcPxrAFQkPATzW_qbYpH-jyYZm1lAn7oJhMyPRh77kkFqSdutBF-XFoM6ZuGwGafS-4Wu7XVLTNYAjpH; _T_WM=23295577348; WEIBOCN_FROM=1110005030; M_WEIBOCN_PARAMS=luicode%3D10000011%26lfid%3D231583%26uicode%3D20000174; MLOGIN=1"));//携带对应cookie
            CloseableHttpResponse resp=client.execute(httpGet);
            try {
                // 4. 获取响应体
                HttpEntity entity = resp.getEntity();

                // 5. 打印响应状态
                //System.out.println(resp.getStatusLine());

                // 6. 打印响应长度和响应内容
                if (null != entity) {
                    //System.out.println("Response content length = " + entity.getContentLength());
                    TostringEntity= EntityUtils.toString(entity);
                }
            } finally {
                // 7. 无论请求成功与否都要关闭resp
                resp.close();
            }
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        }catch (ParseException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 8. 最终要关闭连接，释放资源
            try{
                client.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            return TostringEntity;
        }
    }



    //解析包含关键字的所有的问题的Json的函数
    public  List<BlogAnswers> JsonAnalasis(String Json,int cardsIndex,String question,Boolean choice,LocalDateTime beginTime,LocalDateTime endTime,String path) throws IOException, IndexOutOfBoundsException, InterruptedException {
        //String path=Prefix+question+".docx";
        List<BlogAnswers> blogAnswers = new ArrayList<BlogAnswers>();
        //try {
        JSONArray cards = JSONObject.parseObject(Json).getJSONObject("data").getJSONArray("cards");
        JSONArray cardgroup = cards.getJSONObject(cardsIndex).getJSONArray("card_group");//此时已获取到含有关键字的问题组。
        int index = 0;
        int patient=2;
        JSONObject card = null;
        while (index < cardgroup.size() && cardgroup.getJSONObject(index) != null)//遍历问题数组并取出内容，当数组遍历完即结束
        {
            BlogAnswers blogAnswer = new BlogAnswers();//初始化一个问题对象
            card = cardgroup.getJSONObject(index);//取到具体的问题
            String title_sub = card.getString("title_sub");//取到问题标题
            System.out.println(title_sub);
            String Answers = card.getString("scheme");//取到问题对应回答的url（但是是PC端，所以需要取出其中的containerid，放在手机端的url中）
            String contanerid = Answers.substring(19, 41);//取到contanerid
            String mobileUrl = "https://m.weibo.cn/api/container/getIndex?luicode[]=10000011&luicode[]=10000011&lfid[]=100103type%3D58%26q%3D" + URLEncoder.encode(question) + "&t=0&lfid[]=100103type=58&q=" + URLEncoder.encode(question) + "%26t%3D0&lfid[]=100103type%3D58%26q%3D%E5%9E%83%E5%9C%BE%E5%88%86%E7%B1%BB%26t%3D0&jumpfrom=weibocom&containerid=" + contanerid;//拼接成具体问题的所有回答的手机端url，通过此url获取接下来的内容
            String entity = getHttpEntity(mobileUrl);
            String time=null;
            LocalDateTime  time1=null;
            try {
                time = JSONObject.parseObject(entity).getJSONObject("data").getJSONArray("cards").getJSONObject(0).getJSONArray("card_group").getJSONObject(1).getString("update_time");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                time1 = LocalDateTime.parse(time, df);
                System.out.println(time);
            }catch (IndexOutOfBoundsException e)
            {
                patient--;
                if(patient<=0)
                {
                    index++;
                    patient=2;
                }
                Thread.sleep(3000);
                continue;
            }
            catch (NullPointerException e)
            {
                patient--;
                if(patient<=0)
                {
                    index++;
                    patient=2;
                }
                Thread.sleep(3000);
                continue;
            }
            if(time1.isBefore(beginTime)||time1.isAfter(endTime))
            {
                index++;
                continue;
            }
            ExportWord2.writeTOC3(path, "问题："+title_sub);
            blogAnswer.setTitle(title_sub);//问题标题放置到问题对象的title中
            //这一步继续访问回答的url获取问题对应的所有回答(注意：这里要判断传过来的参数：是否要爬取回答)
            if (choice) {
                //
                String async_api = null;
                try {
                    async_api = JSONObject.parseObject(entity).getJSONObject("data").getJSONArray("cards").getJSONObject(1).getString("async_api");//取到相关信息，前边拼接上 https://m.weibo.cn可再次获取JSON
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("没有async_api");
                }
                if (async_api == null) {
                    index++;
                    continue;
                }
                //System.err.println("    问题的所有回答的url：https://m.weibo.cn" + async_api);
                blogAnswer.setAnswers(addAnswer("https://m.weibo.cn" + async_api, contanerid,question,path));

            }
            index++;
            blogAnswers.add(blogAnswer);
        }
        return blogAnswers;
    }


    //通过解析url返回的JSON数据，返回所有回答
    public  List<String> addAnswer(String url,String contanerid,String question,String path) throws IOException {
        //String path=Prefix+question+".docx";
        String nxetPage="https://m.weibo.cn/api/container/getIndex?containerid="+contanerid+"&luicode=10000011&lfid="+contanerid+"&page=";
        List<String> temp=new ArrayList<String>();
        //通过回答的url获取响应体解析json获取回答
        String ansEntity=getHttpEntity(url);
        String json=ansEntity.substring(1,ansEntity.length()-1);//去掉首尾获取json
        try {
            JSONArray answers = JSONObject.parseObject(json).getJSONObject("data").getJSONArray("card_group");
            int count=1;//显示在每个回答前边的序号
            int index = 0;
            int page = 2;
            while (index < answers.size() && answers.getJSONObject(index) != null) {
                Thread.sleep(500);
                String text = null;
                try {
                    text = "       " + answers.getJSONObject(index).getJSONObject("mblog").getString("text").replaceAll("</?[^>]+>", "");
                    System.out.println(text);
                    ExportWord2.writeTOC2(path, "("+(count++)+")"+text,12);
                    //爬取评论
                    String BlogId=answers.getJSONObject(index).getJSONObject("mblog").getString("mid");
                    Comment(path,BlogId);
                    temp.add(text);
                } catch (NullPointerException e) {
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                index++;
            }
            //后几页

            int patient=2;
            while (true) {
                try {
                    String nextPageEntity = getHttpEntity(nxetPage + page);
                    JSONArray cards = JSONObject.parseObject(nextPageEntity).getJSONObject("data").getJSONArray("cards");
                    int index2 = 0;
                    if (cards.getJSONObject(0).getJSONObject("mblog") == null) break;
                    while (index2 < cards.size() && cards.getJSONObject(index2).getJSONObject("mblog") != null) {
                        String text = "       " + cards.getJSONObject(index2).getJSONObject("mblog").getString("text").replaceAll("</?[^>]+>", "");
                        System.out.println(text);
                        ExportWord2.writeTOC2(path, "(" + (count++) + ")" + text, 12);
                        temp.add(text);
                        index2++;
                    }
                }catch (NullPointerException e){
//                    patient--;
//                    if(patient<=0)
//                    {
//                        page++;
//                        patient=2;
//                    }
//                    Thread.sleep(1000*patient);
//                    continue;
                    break;
                }
                catch (IndexOutOfBoundsException e){
//                    patient--;
//                    if(patient<=0)
//                    {
//                        page++;
//                        patient=2;
//                    }
//                    Thread.sleep(1000*patient);
//                    continue;
                    break;
                }
                page++;
            }
        }
        finally {
            return temp;
        }
    }

    public  void Comment(String path,String id) throws IOException {
        String url="https://m.weibo.cn/comments/hotflow?id="+id+"&mid="+id+"&max_id_type=0";
        String CommentContent=getHttpEntity(url);//获取所有评论
        JSONObject json=JSONObject.parseObject(CommentContent);//开始转换响应体为json，然后取出每个评论
        if(json.getJSONObject("data")!=null) {
            JSONObject data = json.getJSONObject("data");
            JSONArray dataArray = data.getJSONArray("data");
            for(int i=0;i<dataArray.size();i++)
            {
                String commentUser=dataArray.getJSONObject(i).getJSONObject("user").getString("screen_name");
                String commentText=dataArray.getJSONObject(i).getString("text").replaceAll("</?[^>]+>","");
                ExportWord2.writeTOC2(path,"\n评论："+commentUser+"： "+commentText,10);
            }
        }
        else{return;}
    }



}
