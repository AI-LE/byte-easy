package com.mbyte.easy.admin.Util;



import com.mbyte.easy.admin.entity.RecordsSum;
import com.mbyte.easy.admin.entity.TRecordssum;
import com.mbyte.easy.admin.entity.WechatContent;
import com.mbyte.easy.admin.service.IWechatService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WechatClient {
    /**
     * 爬取搜狗微信文章标题
     * @author 张松哲
     */
    @Autowired
    private IWechatService wechatService;

    @Value("${file.upload.local.path}")
    public String uploadLocalPath;

    public static WechatContent wechatContent = new WechatContent();

    public static TRecordssum tRecordssum = new TRecordssum();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    String formatStr =formatter.format(new Date());

    public void titleClient(String keyword, String path){
        String lujing = uploadLocalPath + path + formatStr +".doc";
        String keyword1 = keyword + formatStr + ".doc";
        wechatContent.setPath(lujing);
        wechatContent.setKeyword(keyword1);
        String title = "";
        long num = 0;
        for(int i = 1; i <= 10; i++) {
            Document document = null;
            boolean flag = true;
            while (flag) {
                String url = "https://weixin.sogou.com/weixin?query=" + keyword + "&type=2&ie=utf8&page=" + i;
                try {
                    //创建CloseableHttpClient实例
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    //创建client实例
                    HttpClient client = HttpClients.createDefault();
                    //创建httpget实例
                    HttpGet httpGet = new HttpGet(url);
                    httpGet.addHeader("Host", "weixin.sogou.com");
                    httpGet.addHeader("Cache-Control", "max-age=0");
                    httpGet.addHeader("Upgrade-Insecure-Requests", "1");
                    httpGet.addHeader("Connection", "keep-alive");
                    httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
                    httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
                    httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
                    httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
                    //httpGet.addHeader("Cookie","SUID=E235E16F3921940A000000005D2300E9; SUV=1562575170482502; ABTEST=0|1562575084|v1; weixinIndexVisited=1; IPLOC=CN1306; sct=10; SNUID=29FF2AA4CACF46803B1AF7A8CBEEB2CB; JSESSIONID=aaaSIvnGkN9PF6_okisTw");//携带对应cookie

                    try {
                        // 添加时间间隔 5s  解决禁止访问问题。
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //执行 get请求
                    HttpResponse httpResponse = client.execute(httpGet);
                    //获取网页内容
                    HttpEntity entity = httpResponse.getEntity();
                    //返回请求的实体
                    String html = EntityUtils.toString(entity, "UTF-8");
                    httpClient.close();
                    //解析整个url页面
                    document = Jsoup.parse(html);
                    flag = false;
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println(document);
            //寻找特定属性值的属性
            Elements divlist = document.getElementsByAttributeValue("class", "txt-box");
            num +=divlist.size();
            //遍历属性里的
            for (Element element : divlist) {
                //获取a标签
                Elements a = element.getElementsByTag("a");
                //得到第一个a标签的文本,即获取标题
                String text = a.get(0).text();
                text += "\n";
                title += "文章标题："+text;
            }
            System.out.println(title);

            ExportWord exportWord = new ExportWord();
            exportWord.creatDoc(lujing, title);
        }
        tRecordssum.setRecords(num);
    }

    /**
     * 爬取搜狗微信文章标题和作者、公众号、时间、文章内容
     * @author 张松哲
     */
    public void titleMessageClient(String keyword, String path) throws IOException, InvalidFormatException {
        List<String> listImgUrl= new ArrayList<>();
        //word保存路径
        String path1 = uploadLocalPath + path + formatStr + ".doc";
        String keyword1 = keyword + formatStr + ".doc";
        wechatContent.setPath(path1);
        wechatContent.setKeyword(keyword1);
        //创建空白文档
        XWPFDocument doc= new XWPFDocument();
        //创建输出流
        FileOutputStream out = new FileOutputStream(
                new File(path1));
        //创建段落
        XWPFParagraph firstParagraph = doc.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("1");
        doc.write(out);
        out.close();
        long num = 0;
        for(int i = 1; i <= 10; i++) {
            Document document = null;
            boolean flag = true;
            while(flag) {
                String URL = "https://weixin.sogou.com/weixin?query=" + keyword + "&type=2&ie=utf8&page=" + i;
                try {
                    //创建client实例
                    HttpClient client = HttpClients.createDefault();
                    //创建httpget实例
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    //创建httpget实例
                    HttpGet httpGet = new HttpGet(URL);
                    httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
                    httpGet.addHeader("Host", "weixin.sogou.com");
                    httpGet.addHeader("Cache-Control: max-age", "0");
                    httpGet.addHeader("Upgrade-Insecure-Requests", "1");
                    httpGet.addHeader("Connection", "keep-alive");
                    //httpGet.addHeader("Keep-Alive", "timeout=0");
                    httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
                    httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
                    httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

                    try {
                        // 添加时间间隔 2s  解决禁止访问问题。
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //执行 get请求
                    HttpResponse httpResponse = client.execute(httpGet);
                    //获取网页内容
                    HttpEntity entity = httpResponse.getEntity();
                    //返回请求的实体
                    String html = EntityUtils.toString(entity, "UTF-8");
                    //关闭请求资源
                    httpClient.close();

                    document = Jsoup.parse(html);
                    flag = false;
                } catch (IOException e) {
                    System.out.println("正在重新发送请求");
//                    e.printStackTrace();
                } catch (ParseException e) {
                    System.out.println("正在重新发送请求");
//                    e.printStackTrace();
                }
            }
            //System.out.println(document);

            Elements elements = document.getElementsByAttributeValue("class", "txt-box");
            //String title = "";
            List<String> urlList = new ArrayList<>();
            for (Element element : elements) {
                Elements a = element.getElementsByTag("a");
                String url2 = a.attr("data-share");
                //.replace("http","https")
                urlList.add(url2);
            }

            for(String url : urlList) {
                Document document1 = null;
                boolean flag1 = true;
                while (flag1) {
                    //System.err.println(url);
                    try {
                        //创建httpClient实例
                        HttpClient client1 = HttpClients.createDefault();
                        //创建httpget实例
                        CloseableHttpClient messageHttpClient = HttpClients.createDefault();
                        //创建httpget实例
                        HttpGet httpGet1 = new HttpGet(url);
                        httpGet1.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
                        try {
                            // 添加时间间隔 1s  解决禁止访问问题。
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //执行 get请求
                        HttpResponse httpResponse1 = client1.execute(httpGet1);
                        HttpEntity entity1 = httpResponse1.getEntity();  //获取网页内容
                        String html1 = EntityUtils.toString(entity1, "UTF-8");
                        //System.out.println(html1);
                        messageHttpClient.close();

                        document1 = Jsoup.parse(html1);
                        flag1 = false;
                        //System.out.println(document1);
                    } catch (IOException e) {
                        System.out.println("正在重新发送请求");
//                            e.printStackTrace();
                    } catch (ParseException e) {
                        System.out.println("正在重新发送请求");
//                            e.printStackTrace();
                    }
                }


                FileOutputStream out1 = new FileOutputStream(new File(path1));
                XWPFParagraph firstParagraph1 = doc.createParagraph();
                XWPFRun run1 = firstParagraph1.createRun();
                /**
                 * 获取文章标题
                 */
                Elements elements3 = document1.select("#activity-name");
                String tit = "文章标题："+elements3.text();
                System.out.println(tit);
                run1.setText(tit);
                doc.write(out1);
                out1.close();


                FileOutputStream out2 = new FileOutputStream(new File(path1));
                XWPFParagraph firstParagraph2 = doc.createParagraph();
                XWPFRun run2 = firstParagraph2.createRun();
                /**
                 * 获取文章公众号
                 */
                Elements elements5 = document1.select("#js_name");
                String g = elements5.text();
                String gz = "文章公众号："+g;
                System.out.println(gz);
                run2.setText(gz);
                doc.write(out2);
                out2.close();


                FileOutputStream out3 = new FileOutputStream(new File(path1));
                XWPFParagraph firstParagraph3 = doc.createParagraph();
                XWPFRun run3 = firstParagraph3.createRun();
                /**
                 * 获取文章作者
                 */
                Elements elements4 = document1.select("#js_author_name");
                String na = elements4.text();
                if(elements4.size() == 0){
                    na = g;
                }
                String nam = "文章作者："+na;
                System.out.println(nam);
                run3.setText(nam);
                doc.write(out3);
                out3.close();

                FileOutputStream out4 = new FileOutputStream(new File(path1));
                XWPFParagraph firstParagraph4 = doc.createParagraph();
                XWPFRun run4 = firstParagraph4.createRun();
                /**
                 * 获取文章内容
                 */
                Elements elements2 = document1.select("#js_content");
                String con = elements2.text();
                System.out.println(con);
                run.setFontSize(14);
                run4.setText(con);
                doc.write(out4);
                out4.close();
                /**
                 * 获取文章中图片data-src
                 */

                Elements elements1 = document1.select("#js_content");
                for(Element element : elements1){
                    Elements img = element.getElementsByTag("img");
                    //System.out.println("img长度"+img.size());
                    for(Element img1 : img){
                        String datasrc = img1.attr("data-src");
                        if( datasrc.length() != 0 ){
                            listImgUrl.add(datasrc);
                        }else {
                            String src = img1.attr("src");
                            listImgUrl.add(src);
                        }
                    }
                }
                for(String imgUrl : listImgUrl) {
                    //图片以时间命名
                    System.out.println(imgUrl);
                    String houzhui = "";
                    if (imgUrl.contains("png")) {
                        houzhui = ".png";
                    } else if (imgUrl.contains("jpg")) {
                        houzhui = ".jpg";
                    }else if(imgUrl.contains("jpeg")){
                        houzhui = ".jpeg";
                    } else if (imgUrl.contains("gif")) {
                        houzhui = ".gif";
                    } else if (imgUrl.contains("wmf")) {
                        houzhui = ".wmf";
                    } else if (imgUrl.contains("emf")) {
                        houzhui = ".emf";
                    } else if (imgUrl.contains("dib")) {
                        houzhui = ".dib";
                    } else if (imgUrl.contains("pict")) {
                        houzhui = ".pict";
                    } else if (imgUrl.contains("tiff")) {
                        houzhui = ".tiff";
                    } else if (imgUrl.contains("eps")) {
                        houzhui = ".eps";
                    } else if (imgUrl.contains("bmp")) {
                        houzhui = ".bmp";
                    } else if (imgUrl.contains("wpg")) {
                        houzhui = ".wpg";
                    }else if(imgUrl.contains("webp")){
                        houzhui = ".webp";
                    }else{
                        houzhui = ".jpg";
                    }
                    //imgUrl.substring(imgUrl.lastIndexOf("=") + 1
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    String formatStr =formatter.format(new Date());
                    String reWechatPicture = uploadLocalPath + "images/" + formatStr  + houzhui;
                    System.out.println(reWechatPicture);
                    //如果没有images文件夹不存在，则创建文件夹
                    if(!new File(uploadLocalPath + "images/") .exists()){
                        new File(uploadLocalPath + "images/") .mkdir();
                    }
                    //System.out.println(reBlogPicture);
                    //下载图片到本地
                    downloadPicture(imgUrl, reWechatPicture);
                    FileInputStream in = new FileInputStream(reWechatPicture);
                    //XWPFDocument doc2= new XWPFDocument(in);
                    FileOutputStream out5 = new FileOutputStream(new File(path1));
                    XWPFParagraph p = doc.createParagraph();
                    XWPFRun r = p.createRun();
                    // 设置图片默认类型
                    int format = XWPFDocument.PICTURE_TYPE_JPEG;
                    // 判断图片类型
                    if (reWechatPicture.endsWith(".emf")) {
                        format = XWPFDocument.PICTURE_TYPE_EMF;
                    } else if (reWechatPicture.endsWith(".wmf")) {
                        format = XWPFDocument.PICTURE_TYPE_WMF;
                    } else if (reWechatPicture.endsWith(".pict")) {
                        format = XWPFDocument.PICTURE_TYPE_PICT;
                    } else if (reWechatPicture.endsWith(".jpeg") || reWechatPicture.endsWith(".jpg")) {
                        format = XWPFDocument.PICTURE_TYPE_JPEG;
                    } else if (reWechatPicture.endsWith(".png")) {
                        format = XWPFDocument.PICTURE_TYPE_PNG;
                    } else if (reWechatPicture.endsWith(".dib")) {
                        format = XWPFDocument.PICTURE_TYPE_DIB;
                    } else if (reWechatPicture.endsWith(".gif")) {
                        format = XWPFDocument.PICTURE_TYPE_GIF;
                    } else if (reWechatPicture.endsWith(".tiff")) {
                        format = XWPFDocument.PICTURE_TYPE_TIFF;
                    } else if (reWechatPicture.endsWith(".eps")) {
                        format = XWPFDocument.PICTURE_TYPE_EPS;
                    } else if (reWechatPicture.endsWith(".bmp")) {
                        format = XWPFDocument.PICTURE_TYPE_BMP;
                    } else if (reWechatPicture.endsWith(".wpg")) {
                        format = XWPFDocument.PICTURE_TYPE_WPG;
                    }
                    r.addPicture(in, format, reWechatPicture, Units.toEMU(150), Units.toEMU(150));
                    doc.write(out5);
                    out5.close();
                }
                listImgUrl.clear();
            }
        }
        tRecordssum.setRecords(num);
    }

    /**
     * 通过图片URL从网络获取图片下载到本地
     */
    public static void downloadPicture(String urlSource,String filePath)
    {
        URL url = null;
        boolean flag = true;
        while (flag) {
            try {
                url = new URL(urlSource);
                DataInputStream dataInputStream = new DataInputStream(url.openStream());
                FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;

                while ((length = dataInputStream.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                fileOutputStream.write(output.toByteArray());
                dataInputStream.close();
                fileOutputStream.close();
                flag = false;
            } catch (MalformedURLException e) {
                System.out.println("正在重新下载图片");
//            e.printStackTrace();
            } catch (IOException e) {
                System.out.println("正在重新下载图片");
//            e.printStackTrace();
            }
        }
    }

}

