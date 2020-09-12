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
    //导出到word
    public static void exportWord(String html,HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception {
        try {
                //word内容
                String content="<html>";//拼接注意加上<html>
                content += "</html>";
                byte b[] = content.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                POIFSFileSystem poifs = new POIFSFileSystem();
                DirectoryEntry directory = poifs.getRoot();
                response.reset();
                response.setHeader("Content-Disposition",
                        "attachment;filename=" +
                                new String( (fileName + ".doc").getBytes(),
                                        "iso-8859-1"));
                response.setContentType("application/msword");
                OutputStream ostream = response.getOutputStream();
                //输出到本地文件的话，new一个文件流
                //FileOutputStream ostream = new FileOutputStream(path+ fileName);
                poifs.writeFilesystem(ostream);
                bais.close();
                ostream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 添加水印
     */
    public static void addWaterMark(String filePath,String markContent) throws IOException {
//        InputStream inputStream = new FileInputStream(new File(filePath));
        XWPFDocument doc= new XWPFDocument();

        // the body content
        XWPFParagraph paragraph = doc.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("The Body:");

        // create header-footer
        XWPFHeaderFooterPolicy headerFooterPolicy = doc.getHeaderFooterPolicy();
        if (headerFooterPolicy == null) headerFooterPolicy = doc.createHeaderFooterPolicy();

        // create default Watermark - fill color black and not rotated
        headerFooterPolicy.createWatermark(markContent);

        // get the default header
        // Note: createWatermark also sets FIRST and EVEN headers
        // but this code does not updating those other headers
        XWPFHeader header = headerFooterPolicy.getHeader(XWPFHeaderFooterPolicy.DEFAULT);
        paragraph = header.getParagraphArray(0);

        // get com.microsoft.schemas.vml.CTShape where fill color and rotation is set
        org.apache.xmlbeans.XmlObject[] xmlobjects = paragraph.getCTP().getRArray(0).getPictArray(0).selectChildren(
                new javax.xml.namespace.QName("urn:schemas-microsoft-com:vml", "shape"));

        if (xmlobjects.length > 0) {
            com.microsoft.schemas.vml.CTShape ctshape = (com.microsoft.schemas.vml.CTShape)xmlobjects[0];
            // set fill color
            ctshape.setFillcolor("#d8d8d8");
            // set rotation
            ctshape.setStyle(ctshape.getStyle() + ";rotation:315");
            //System.out.println(ctshape);
        }
        doc.write(new FileOutputStream(filePath));
        doc.close();
    }

    public static void main(String[] args) throws IOException {
        addWaterMark("D://waterMark.docx","这是一个说因");
    }

}
