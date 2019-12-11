package com.mbyte.easy.admin.Util;
import java.awt.Color;

import java.io.*;

import com.lowagie.text.Document;

import com.lowagie.text.DocumentException;

import com.lowagie.text.Font;

import com.lowagie.text.PageSize;

import com.lowagie.text.Paragraph;

import com.lowagie.text.rtf.RtfWriter2;
import org.apache.http.HttpResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 吴天豪
 * word导出工具类
 */

public class ExportWord {
    public ExportWord() {

    }

    /**
     */
    public  void creatDoc(String path,String text) {

//设置纸张的大小
        Document document = new Document(PageSize.A4);
        try {
//创建word文档
            RtfWriter2.getInstance(document,new FileOutputStream(path));
            //打开文档
            document.open();
            //创建段落
            Paragraph p = new Paragraph(text,new Font(Font.NORMAL, 10, Font.NORMAL, new Color(0, 0, 0)) );
            //设置段落为居中对齐
            p.setAlignment(Paragraph.ALIGN_LEFT);
            //写入段落
            document.add(p);
            //关流
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param resp
     * @param name         文件真实名字
     * @param downloadName 文件下载时名字
     */
    public static void download(HttpServletResponse resp, String name, String downloadName) {
        String fileName = null;
        try {
            fileName = new String(downloadName.getBytes("GBK"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ///home/tomcat/apache-tomcat-9.0.1/files
        String realPath = "D:" + File.separator + "apache-tomcat-8.5.15" + File.separator + "files";
//        String realPath=File.separator+"home"+File.separator+"tomcat"+File.separator+"apache-tomcat-9.0.1"+File.separator+"files";
        String path = realPath + File.separator + name;
        File file = new File(path);
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}