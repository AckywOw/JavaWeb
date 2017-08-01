package com.mingxxx.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Dom4jUtils {

    // 获取Document对象
    public static Document getDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(file);
    }

    // 回写
    public static void domToFile(Document document, File file)
            throws IOException {
//		 OutputFormat format=OutputFormat.createCompactFormat();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        // XMLWriter writer = new XMLWriter(new OutputStreamWriter(
        // new FileOutputStream("D:/books.xml"), "utf-8"),format);

        XMLWriter writer = new XMLWriter(new FileOutputStream(file),
                format);

        // FileWriter流底层使用的是系统编码 windows xp底层编码gbk

        writer.write(document);
        writer.close();
    }
}
