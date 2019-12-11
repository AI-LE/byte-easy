package com.mbyte.easy.admin.Util;




import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

public class ExportWord2 {

    public static void writeTOC(String path,String content) throws IOException, FileNotFoundException {

        //设置关键字标题
        //FileInputStream in =new FileInputStream(new File(path));
        XWPFDocument document= new XWPFDocument();

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(path));


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setStyle("Heading1");
        XWPFRun run = firstParagraph.createRun();
        run.setBold(true);
        run.setFontFamily("宋体");
        run.setFontSize(16);
        run.setText(content);
        //run.setColor("696969");
        //run.setFontSize(18);


        //document.createTOC();
        document.write(out);
        out.close();
    }

    public static void writeTOC2(String path,String content,int fonSize) throws IOException, FileNotFoundException {

        FileInputStream in =new FileInputStream(new File(path));
        XWPFDocument document= new XWPFDocument(in);

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(path));


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setStyle("Heading1");
        XWPFRun run = firstParagraph.createRun();
//        run.setBold(true);
        run.setFontSize(fonSize);
        run.setFontFamily("宋体");
        run.setText(content);
        //run.setColor("696969");
        //run.setFontSize(18);


        //document.createTOC();
        document.write(out);
        out.close();
    }

    public static void writeTOC3(String path,String content) throws IOException, FileNotFoundException {

        FileInputStream in =new FileInputStream(new File(path));
        XWPFDocument document= new XWPFDocument(in);

        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(new File(path));


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        firstParagraph.setStyle("Heading1");
        XWPFRun run = firstParagraph.createRun();
        run.setBold(true);
        run.setFontSize(14);
        run.setFontFamily("宋体");
        run.setText(content);
        //run.addBreak();
        //run.setColor("696969");
        //run.setFontSize(18);


        //document.createTOC();
        document.write(out);
        out.close();
    }
}
