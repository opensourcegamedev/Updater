package com.jukusoft.updater.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;

import java.net.URL;

/**
 * Created by Justin on 23.04.2017.
 */
public class Dom4JUtils {

    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(url);

        return document;
    }

    public static Document createFromString (String content) throws DocumentException {
        return DocumentHelper.parseText(content);
    }

}
