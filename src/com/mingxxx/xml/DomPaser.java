package com.mingxxx.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Iterator;

public class DomPaser {

    @Test
    public void test() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("web/WEB-INF/schema/books.xml"));
        Element root = document.getRootElement();
        for (Iterator it = root.elementIterator(); it.hasNext(); ) {
            Element element = (Element) it.next();
            String text = element.getName();
            System.out.println(text);
        }
        System.out.println(document.toString());
    }
}
