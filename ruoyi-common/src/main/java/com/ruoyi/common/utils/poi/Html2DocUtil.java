package com.ruoyi.common.utils.poi;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @ClassName Html2DocUtil
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/10 19:27
 * @Version 1.0
 */
public class Html2DocUtil {

    /**
     * 导出文档
     * @param response
     * @param content
     */
    public static void exportWord( HttpServletResponse response, String content)  {
        try {
            byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
            //生成word
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("exportWord", bais);
            //输出文件
            response.setCharacterEncoding("utf-8");
            //设置word格式
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename=exportWord.docx");
            OutputStream ostream = response.getOutputStream();
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("");
            //异常处理
        }
    }

    /**
     * 保存文档到本地
     * @param filePath
     * @param content
     */
    public static void saveWord(String filePath,String content) {
        try {
            byte b[] = content.getBytes("utf-8");  //这里是必须要设置编码的，不然导出中文就会乱码。
            ByteArrayInputStream bais = new ByteArrayInputStream(b);//将字节数组包装到流中
            //生成word
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument("exportWord", bais);
            OutputStream ostream = new FileOutputStream(filePath);
            poifs.writeFilesystem(ostream);
            bais.close();
            ostream.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("生成文档失败");
            //异常处理
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            String html = "\n" +
                    "\n" +
                    "\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\">\n" +
                    "<meta name=\"ProgId\" content=\"Word.Document\">\n" +
                    "<meta name=\"Generator\" content=\"Microsoft Word 15\">\n" +
                    "<meta name=\"Originator\" content=\"Microsoft Word 15\">\n" +
                    "<link rel=\"File-List\" href=\"伤情鉴定书新格式2.files/filelist.xml\">\n" +
                    "<link rel=\"Edit-Time-Data\" href=\"伤情鉴定书新格式2.files/editdata.mso\">\n" +
                    "<title>公刑﹝2016﹞    号</title>\n" +
                    "<link rel=\"themeData\" href=\"伤情鉴定书新格式2.files/themedata.thmx\">\n" +
                    "<link rel=\"colorSchemeMapping\" href=\"伤情鉴定书新格式2.files/colorschememapping.xml\">\n" +
                    "<style>\n" +
                    "</style>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<div class=\"WordSection1\" style=\"layout-grid:15.6pt\">\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" align=\"center\" style=\"margin-right:-7.35pt;mso-para-margin-right:\n" +
                    "-.7gd;text-align:center;line-height:200%\"><!--[if !vml]--><span style=\"mso-ignore:vglayout;position:\n" +
                    "absolute;z-index:251657728;left:0px;margin-left:-8px;margin-top:94px;\n" +
                    "width:928px;height:10px\"><img width=\"619\" height=\"7\" src=\"伤情鉴定书新格式2.files/image001.gif\" v:shapes=\"_x0000_s1032\"></span><!--[endif]--><b style=\"mso-bidi-font-weight:normal\"><span style=\"font-size:26.0pt;line-height:\n" +
                    "200%;font-family:宋体;mso-ascii-font-family:&quot;Times New Roman&quot;;letter-spacing:\n" +
                    "8.4pt\">金乡县公安局</span></b><span lang=\"EN-US\" style=\"font-size:14.0pt;mso-bidi-font-size:\n" +
                    "12.0pt;line-height:200%\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "</span><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:86.6pt;mso-char-indent-count:3.92;line-height:200%\"><b style=\"mso-bidi-font-weight:normal\"><span style=\"font-size:22.0pt;mso-bidi-font-size:\n" +
                    "12.0pt;line-height:200%;font-family:宋体;mso-ascii-font-family:&quot;Times New Roman&quot;\">法医学人体损伤程度鉴定书</span></b><b style=\"mso-bidi-font-weight:normal\"><span lang=\"EN-US\" style=\"font-size:22.0pt;\n" +
                    "mso-bidi-font-size:12.0pt;line-height:200%\"><o:p></o:p></span></b></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" align=\"right\" style=\"text-align:right;line-height:25.0pt;\n" +
                    "mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;font-family:仿宋_GB2312;\n" +
                    "mso-ascii-font-family:&quot;Times New Roman&quot;;letter-spacing:-1.0pt\">（</span><span style=\"font-size:15.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">金<span style=\"letter-spacing:-1.0pt\">）公（</span>刑<span style=\"letter-spacing:-1.0pt\">）鉴（</span>伤检<span style=\"letter-spacing:-1.0pt\">）</span></span><span lang=\"EN-US\" style=\"font-size:\n" +
                    "15.0pt;mso-fareast-font-family:仿宋_GB2312;letter-spacing:-1.0pt\"> [20</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\">17<span style=\"letter-spacing:-1.0pt\">]</span> 0614</span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;;letter-spacing:\n" +
                    "-1.0pt\">号</span><b style=\"mso-bidi-font-weight:normal\"><span lang=\"EN-US\" style=\"font-size:22.0pt;mso-font-kerning:0pt\"><o:p></o:p></span></b></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" align=\"center\" style=\"margin-right:-25.3pt;mso-para-margin-right:\n" +
                    "-2.41gd;text-align:center;line-height:25.0pt;mso-line-height-rule:exactly\"><span lang=\"EN-US\" style=\"font-size:16.0pt;mso-fareast-font-family:仿宋_GB2312;\n" +
                    "letter-spacing:-1.0pt\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "</span></span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:黑体;mso-ascii-font-family:&quot;Times New Roman&quot;;mso-hansi-font-family:\n" +
                    "&quot;Times New Roman&quot;\">一、绪论</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "mso-fareast-font-family:黑体\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（一）委托单位：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（二）送检人：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（三）受理日期：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\">20<span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">年</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">月</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">日</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（四）案情摘要：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（五）检验对象：8989898</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（六）送检材料</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（七）鉴定要求：以现有材料对</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\">xxx</span><span style=\"font-size:15.0pt;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">身体所受损伤程度进行鉴定，并出具鉴定文书。</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（八）检验日期：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\">20<span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">年</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">月</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">日</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:29.4pt;mso-char-indent-count:1.96;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（九）检验地点：金乡县人民医院法医门诊</span><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:黑体;mso-ascii-font-family:&quot;Times New Roman&quot;;mso-hansi-font-family:\n" +
                    "&quot;Times New Roman&quot;\">二、检验</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "mso-fareast-font-family:黑体\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:29.4pt;mso-char-indent-count:1.96;line-height:150%\"><span style=\"font-size:15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">根据</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "line-height:150%;mso-fareast-font-family:仿宋_GB2312\">2013</span><span style=\"font-size:15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">年</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:\n" +
                    "150%;mso-fareast-font-family:仿宋_GB2312\">8</span><span style=\"font-size:15.0pt;\n" +
                    "line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">月</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\">30</span><span style=\"font-size:15.0pt;line-height:150%;font-family:\n" +
                    "仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">日最高人民法院、最高人民检察院、公安部、国家安全部、司法部联合发布的《人体损伤程度鉴定标准》和《法医临床检验规范》（</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\">SF/Z JD0103003-2011</span><span style=\"font-size:15.0pt;line-height:\n" +
                    "150%;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">）进行检验：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-top:0cm;margin-right:-17.85pt;margin-bottom:\n" +
                    "0cm;margin-left:29.4pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;\n" +
                    "mso-para-margin-right:-1.7gd;mso-para-margin-bottom:0cm;mso-para-margin-left:\n" +
                    "29.4pt;mso-para-margin-bottom:.0001pt;line-height:150%\"><span style=\"font-size:\n" +
                    "15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（一）病历摘要</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-top:0cm;margin-right:-17.85pt;margin-bottom:\n" +
                    "0cm;margin-left:29.4pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;\n" +
                    "mso-para-margin-right:-1.7gd;mso-para-margin-bottom:0cm;mso-para-margin-left:\n" +
                    "29.4pt;mso-para-margin-bottom:.0001pt;line-height:150%\"><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:仿宋_GB2312\">1</span><span style=\"font-size:15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">、</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:\n" +
                    "150%;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-top:0cm;margin-right:-17.85pt;margin-bottom:\n" +
                    "0cm;margin-left:29.4pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;\n" +
                    "mso-para-margin-right:-1.7gd;mso-para-margin-bottom:0cm;mso-para-margin-left:\n" +
                    "29.4pt;mso-para-margin-bottom:.0001pt;line-height:150%\"><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:仿宋_GB2312\">2</span><span style=\"font-size:15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">、</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:\n" +
                    "150%;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-top:0cm;margin-right:-17.85pt;margin-bottom:\n" +
                    "0cm;margin-left:29.4pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;\n" +
                    "mso-para-margin-right:-1.7gd;mso-para-margin-bottom:0cm;mso-para-margin-left:\n" +
                    "29.4pt;mso-para-margin-bottom:.0001pt;line-height:150%\"><span style=\"font-size:\n" +
                    "15.0pt;line-height:150%;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">（二）检验所见</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:150%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:黑体;mso-ascii-font-family:&quot;Times New Roman&quot;;mso-hansi-font-family:\n" +
                    "&quot;Times New Roman&quot;\">三、论证</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "mso-fareast-font-family:黑体\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span style=\"font-size:15.0pt;\n" +
                    "font-family:黑体;mso-ascii-font-family:&quot;Times New Roman&quot;;mso-hansi-font-family:\n" +
                    "&quot;Times New Roman&quot;\">四、鉴定意见</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "mso-fareast-font-family:黑体\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"text-indent:30.0pt;mso-char-indent-count:2.0;\n" +
                    "line-height:24.0pt;mso-line-height-rule:exactly\"><span lang=\"EN-US\" style=\"font-size:15.0pt;mso-fareast-font-family:仿宋_GB2312\"><o:p>&nbsp;</o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:240.0pt;mso-char-indent-count:16.0;line-height:200%\"><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:200%\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312\">鉴定人：<span lang=\"EN-US\"><o:p></o:p></span></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:270.0pt;mso-char-indent-count:18.0;line-height:200%\"><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312\">金乡县公安局法医师<span lang=\"EN-US\"><o:p></o:p></span></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:270.0pt;mso-char-indent-count:18.0;line-height:200%\"><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312\">金乡县公安局法医师<span lang=\"EN-US\"><o:p></o:p></span></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:269.4pt;mso-char-indent-count:17.96;line-height:200%\"><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">授权签字人：</span><span lang=\"EN-US\" style=\"font-size:15.0pt;\n" +
                    "line-height:200%;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "text-indent:270.0pt;mso-char-indent-count:18.0;line-height:200%\"><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312\">金乡县公安局法医师<span lang=\"EN-US\"><o:p></o:p></span></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-right:-17.85pt;mso-para-margin-right:-1.7gd;\n" +
                    "line-height:200%\"><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:200%;\n" +
                    "font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "</span></span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:200%;\n" +
                    "mso-fareast-font-family:仿宋_GB2312\">20<span style=\"mso-spacerun:yes\">&nbsp;&nbsp; </span></span><span style=\"font-size:\n" +
                    "15.0pt;line-height:200%;font-family:仿宋_GB2312;mso-ascii-font-family:&quot;Times New Roman&quot;\">年</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:200%;mso-fareast-font-family:\n" +
                    "仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp; </span></span><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312;mso-ascii-font-family:\n" +
                    "&quot;Times New Roman&quot;\">月</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:\n" +
                    "200%;mso-fareast-font-family:仿宋_GB2312\"><span style=\"mso-spacerun:yes\">&nbsp;\n" +
                    "</span></span><span style=\"font-size:15.0pt;line-height:200%;font-family:仿宋_GB2312;\n" +
                    "mso-ascii-font-family:&quot;Times New Roman&quot;\">日</span><span lang=\"EN-US\" style=\"font-size:15.0pt;line-height:200%;mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" align=\"center\" style=\"margin-right:-7.35pt;mso-para-margin-right:\n" +
                    "-.7gd;text-align:center;line-height:17.0pt;mso-line-height-rule:exactly\"><span lang=\"EN-US\" style=\"font-size:14.0pt;mso-bidi-font-size:12.0pt\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "</span><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><o:p></o:p></span></p>\n" +
                    "\n" +
                    "<p class=\"MsoNormal\" style=\"margin-top:0cm;margin-right:-16.4pt;margin-bottom:\n" +
                    "0cm;margin-left:-8.9pt;margin-bottom:.0001pt;mso-para-margin-top:0cm;\n" +
                    "mso-para-margin-right:-1.56gd;mso-para-margin-bottom:0cm;mso-para-margin-left:\n" +
                    "-.85gd;mso-para-margin-bottom:.0001pt;text-indent:220.5pt;mso-char-indent-count:\n" +
                    "21.0;line-height:17.0pt;mso-line-height-rule:exactly;layout-grid-mode:char;\n" +
                    "mso-layout-grid-align:none\"><span lang=\"EN-US\" style=\"mso-bidi-font-size:10.5pt\"><span style=\"mso-spacerun:yes\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                    "</span></span><span lang=\"EN-US\" style=\"font-size:16.0pt;mso-bidi-font-size:12.0pt;\n" +
                    "mso-fareast-font-family:仿宋_GB2312\"><o:p></o:p></span></p>\n" +
                    "\n" +
                    "</div>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n";
            Html2DocUtil.saveWord("D://3333333.docx",html);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
