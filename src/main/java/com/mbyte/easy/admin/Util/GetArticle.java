package com.mbyte.easy.admin.Util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mbyte.easy.admin.entity.TRecordssum;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetArticle {

    private String result="";
    public String getResult() {
        return result;
    }

    public long num = 0;

    public String getArticlInfo(String articleInfos,Integer i) throws IOException {
        result+="---------------第"+i+"页------------------\n";
        Pattern articlePattern=Pattern.compile("(<div class=\"card-wrap\">[\\s\\S]*?</div>)");//懒惰模式
        Matcher articleMatcher=articlePattern.matcher(articleInfos);
        while (articleMatcher.find()){
            Pattern h3Pattern=Pattern.compile("<h3[\\s\\S]*?>[\\s\\S]*?</h3>");
            Matcher h3Matcher=h3Pattern.matcher(articleMatcher.group());
            if(h3Matcher.find()){
                    /*
                    获取文章的标题和路径
                     */
                Pattern titlePattern=Pattern.compile("(title=\"[\\s\\S]*?\")");
                Matcher titleMatcher=titlePattern.matcher(h3Matcher.group());

                if(titleMatcher.find()) {
                    result += "\n文章标题：\n\t";
                    num++;
                    result += titleMatcher.group().substring(7, titleMatcher.group().length()-1);
                    Pattern hrefPattern = Pattern.compile("(<a href=\"[\\s\\S]*?\")");
                    Matcher hrefMatcher = hrefPattern.matcher(h3Matcher.group());
                    if (hrefMatcher.find()){
                        String articleURL = hrefMatcher.group().substring(9, hrefMatcher.group().length()-1);
                            /*
                            通过路径获取内容
                             */
                        String content =getHttpEntity(articleURL);
                        int t=0;
                        while (content == null && t<10) {
                            t++;
                            try {
                                // 添加时间间隔 1s  解决 418问题。
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("网络不给力，正在重新加载.......");
                            content=getHttpEntity(articleURL);
                        }
                        if (content == null) continue;
                        //System.out.println(content);
                        Pattern bodyPattern = Pattern.compile("(<body[\\s\\S]*</body>)");
                        Matcher bodyMatcher = bodyPattern.matcher(content);
                        if (bodyMatcher.find()) {
                            String articleCont=deleteHtmlTage(bodyMatcher.group());
                            if (articleCont.indexOf("您的浏览器不支持JavaScript脚本")>-1) {
                                result+="\n文章内容：\n\t"+"您的浏览器不支持JavaScript脚本,请换个浏览器试试！！！";
                                continue;
                            }
                            /*if (articleCont.indexOf("设为首页我的菜单")>-1 || articleCont.indexOf("切换导航")>-1){
                                result+="\n文章内容：\n\t该页面无法跳转！！！";
                                continue;
                            }*/
                            if (!isChinese(articleCont)) {
                                result+="\n文章内容：\n\t"+"文章来源于其他网站，无法正常编码！！！！";
                            }
                            else if (articleCont == null || " ".equals(articleCont+" ")){
                                Pattern contentPattern=Pattern.compile("\"content\": \"([\\s\\S])*\",[\\s]*\"userinfo\"");
                                Matcher contentMatcher=contentPattern.matcher(bodyMatcher.group());
                                if (contentMatcher.find()){
                                    String cont=contentMatcher.group().split("\",")[0];
                                    if (deleteHtmlTage(cont.substring(12,cont.length())) != null || " ".equals(deleteHtmlTage(cont.substring(12,cont.length()))+" "))
                                    {
                                        result+="\n文章内容：\n\t"+deleteHtmlTage(cont.substring(12,cont.length()))+"\n";
                                        continue;
                                    }
                                }
                                result+="\n文章内容：\n\t"+" 需要登录才能爬取！！！";
                                continue;
                            }
                            else if (articleCont != null || !" ".equals(articleCont+" ")) {
                                result += "\n文章内容:\n\t" + articleCont;
                                continue;
                            }
                            else result+="\n文章内容：\n\t"+"需要登录爬取";
                        }
                        else {
                            result+="\n文章内容：\n\t内容无法抓取，请登录客户端！\n";
                            continue;
                        }
                    }
                }
                //result+="\n文章内容：\n\t内容无法抓取，请登录客户端！\n";
                //System.out.println(num);

            }
        }
        /*System.out.println(result);*/
        return result;
    }
    //发出http请求获取响应体的内容
    public  String getHttpEntity(String url) {
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
    /**
     * 去除标签
     */
    public String deleteHtmlTage(String str) throws FileNotFoundException {
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";// 定义script的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
        String regEx_html = "<[^>]+>";// 定义HTML标签的正则表达式
        String regEx_space = "\\s*|\t|\r\n";//定义空格回车换行符
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(str);
        str=m_script.replaceAll("");

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(str);
        str=m_style.replaceAll("");

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(str);
        str=m_html.replaceAll("");

        Pattern p_space=Pattern.compile(regEx_space,Pattern.CASE_INSENSITIVE);
        Matcher m_space=p_space.matcher(str);
        str=m_space.replaceAll("");

        return str;
    }
    /**
     * 保存到文档
     */
    public boolean writeIntoWord(String filePath,String str){
        boolean isSuccess = true;
        File file=new File(filePath);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                isSuccess=false;
                e.printStackTrace();
            }
        }
        FileWriter fileWriter=null;
        try {
            fileWriter=new FileWriter(file);
            //fileWriter.write(content);
            for(int i=0;i<str.length();i++){
                char con=str.charAt(i);
                fileWriter.append(con);
            }
            fileWriter.flush();
        } catch (IOException e) {
            isSuccess=false;
            e.printStackTrace();
        }
        finally {
            try {
                if(fileWriter!=null) fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return isSuccess;
    }
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    // 完整的判断中文汉字和符号
    public static boolean isChinese(String strName) {
        char[] ch = strName.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }
    public static void main(String []  args) throws Exception{
        GetArticle testXL=new GetArticle();
        Integer articlePage=1;//需要爬取页数
        String filePaath="";
        Document doc= Jsoup.connect("https://s.weibo.com/article?q="+"迪丽热"+"&Refer=weibo_article"+"&page="+articlePage)
                .timeout(5000)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
                .get();
        String articleInfos=doc.toString();
        System.out.println(testXL.getArticlInfo(articleInfos,articlePage));
    }


}
