package com.mingxxx.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.File;

public class DomPaser {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(new File("web/WEB-INF/schema/books.xml"));
        System.out.println(document.toString());
    }
}
