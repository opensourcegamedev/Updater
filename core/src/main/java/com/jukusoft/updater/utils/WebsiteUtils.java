package com.jukusoft.updater.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Justin on 23.04.2017.
 */
public class WebsiteUtils {

    public static String getWebsiteContent (String url) throws IOException {
        URL uri= new URL(url);
        URLConnection ec = uri.openConnection();
        ec.setConnectTimeout(3000);
        BufferedReader in = new BufferedReader(new InputStreamReader(
                ec.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            a.append(inputLine);
        in.close();

        return a.toString();
    }

}
