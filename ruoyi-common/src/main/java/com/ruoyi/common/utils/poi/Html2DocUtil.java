package com.ruoyi.common.utils.poi;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName Html2DocUtil
 * @Description TODO
 * @Author yangHui
 * @Date 2020/9/10 19:27
 * @Version 1.0
 */
public class Html2DocUtil {
    Logger log = LoggerFactory.getLogger(Html2DocUtil.class);

    //导出到word
    public void exportWord(String html,HttpServletRequest request, HttpServletResponse response,String fileName) throws Exception {
        try {
                //word内容
                String content="<html>";//拼接注意加上<html>
                content += "</html>";
                byte b[] = content.getBytes();
                ByteArrayInputStream bais = new ByteArrayInputStream(b);
                POIFSFileSystem poifs = new POIFSFileSystem();
                DirectoryEntry directory = poifs.getRoot();
//                DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
                //输出文件
                fileName = "导出知识";
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

}
