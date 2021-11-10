package com.example.assignment2_skeletonproject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingManager {

    public String getJsonFromUrl(String urlString){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream inputStream = connection.getInputStream();
                int len = 0;
                byte[] bs = new byte[1024];
                while ((len = inputStream.read(bs)) != -1) {
                    baos.write(bs, 0, len);
                    baos.flush();
                }
                return baos.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }
}
