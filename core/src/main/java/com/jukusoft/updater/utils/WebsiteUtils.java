package com.jukusoft.updater.utils;

import java.io.*;
import java.net.HttpURLConnection;
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

    public static void downloadFile (String fileURL, String outputFile) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream in = connection.getInputStream();
        FileOutputStream out = new FileOutputStream(outputFile);

        copy(in, out, 1024);

        out.close();
    }

    public static void copy (InputStream input, OutputStream output, int bufferSize) throws IOException {
        byte[] buf = new byte[bufferSize];
        int n = input.read(buf);
        while (n >= 0) {
            output.write(buf, 0, n);
            n = input.read(buf);
        }
        output.flush();
    }

}
