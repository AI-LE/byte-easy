package com.mbyte.easy.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImgUtil {
    /**
     * 图片的工具类
     * @param htmlCode
     * @return
     */
    public static List<String> getImageSrc(String htmlCode) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(htmlCode);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);

            // src=https://sms.reyo.cn:443/temp/screenshot/zY9Ur-KcyY6-2fVB1-1FSH4.png
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
            imageSrcList.add(src);

        }
        return imageSrcList;
    }


    /**
     *     链接url下载图片
     */

    public static void downloadPicture(String urlList,String path) {
        URL url = null;
        try {
            // 添加时间间隔 2s  解决下载图片失效问题。
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            url = new URL(urlList);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());

            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件名，时间戳
     */
    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }



    /**
     * @文件工具类，用来导出文档图片+话题
     * @Description: 将网页内容导出为word
     * @param @param file
     * @param @throws DocumentException
     * @param @throws IOException 设定文件
     * @return void 返回类型
     * @throws
     */
    public static String exportDoc(List<String> urlimg, String titleFiele) throws DocumentException, IOException {
        // 设置纸张大小
        Document document = new Document(PageSize.A4);
        // 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
        // ByteArrayOutputStream baos = new ByteArrayOutputStream();
        File file = new File(FileUtil.uploadLocalPath + titleFiele + Utility.getCurrentUser().getUsername() + "_微博.doc");
        RtfWriter2.getInstance(document, new FileOutputStream(file));
        document.open();
        // 设置中文字体
        BaseFont bfChinese = BaseFont.createFont(BaseFont.HELVETICA,
                BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
        // 标题字体风格
        Font titleFont = new Font(bfChinese, 12, Font.BOLD);
        // // 正文字体风格
        Font contextFont = new Font(bfChinese, 10, Font.NORMAL);
        Paragraph title = new Paragraph(titleFiele);

        // 设置标题格式对齐方式
        title.setAlignment(Element.ALIGN_CENTER);
//         title.setFont(titleFont);
//        document.add(title);

        /**
         * 文字
         */
        String reg = "[^\u4e00-\u9fa5]";
        String urlreg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
        for (int i = 0;i<urlimg.size();i++){
            String judgept = urlimg.get(i);
            Pattern pattern = Pattern.compile(urlreg);
            Matcher matcher = pattern.matcher(judgept);
       //     String lay = judgept.replaceAll(reg,"");
            if((matcher.find()) ){
                try{
                    Image img = Image.getInstance(judgept);
                    img.setAlignment(Image.LEFT);// 设置图片显示位置
                    System.out.println("img" + img);
                    document.add(img);
                    Paragraph contextTransversal = new Paragraph("\n");
                    document.add(contextTransversal);
                }
                catch (DocumentException e){
                    e.printStackTrace();
                }
                //System.out.println(img);
            }
            else {
                Paragraph context = new Paragraph("\n"+judgept);
                context.setAlignment(Element.ALIGN_LEFT);
                // context.setFont(contextFont);
                // 离上一段落（标题）空的行数
                context.setSpacingBefore(5);
                // 设置第一行空的列数
                context.setFirstLineIndent(20);
                document.add(context);
            }
        }
       // Paragraph context = new Paragraph(contextString);
        // 正文格式左对齐
//        context.setAlignment(Element.ALIGN_LEFT);
//        // context.setFont(contextFont);
//        // 离上一段落（标题）空的行数
//        context.setSpacingBefore(5);
//        // 设置第一行空的列数
//        context.setFirstLineIndent(20);
//        document.add(context);
//        for(int i = 0; i <urlimg.size();i++){
//            Image img = Image.getInstance(urlimg.get(i));
//            img.setAbsolutePosition(0, 0);
//            img.setAlignment(Image.LEFT);// 设置图片显示位置
//            document.add(img);
//        }
        document.close();
        return "";
    }
}
