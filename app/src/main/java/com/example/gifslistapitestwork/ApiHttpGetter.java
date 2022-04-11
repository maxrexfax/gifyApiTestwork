package com.example.gifslistapitestwork;

import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.HttpsURLConnection;

public class ApiHttpGetter {
        //Handler handler = MainActivity.handler;
        private String _baseUrl = "https://android-kotlin-fun-mars-server.appspot.com";
        //private String _baseUrl = "https://api.giphy.com/v1/gifs/search?";
        //private String _baseUrl = "http://tests.loc/";
        //private String _reqParamsUrl = "api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=&limit=25&offset=0&rating=g&lang=en";
        private String _completeUrl = "https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=deadpool&limit=25&offset=0&rating=g&lang=en";
        public void getElements() {
        getDataFromUrl(_completeUrl);
        Log.d("TAG1", "LINE 29 getElements");

    }



    private void getDataFromUrl(String demoIdUrl) {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    HttpURLConnection urlConnection = null;
                    URL url = null;
                    try {
                        url = new URL(demoIdUrl);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000 /* milliseconds */);
                    urlConnection.setConnectTimeout(15000 /* milliseconds */);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();

                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder sb = new StringBuilder();

                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    String jsonString = sb.toString();
                    Log.d("TAG1","JSON: " + jsonString);
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Log.d("TAG1","jsonArray.length(): " + jsonArray.length());
                    //Log.d("TAG1","jsonArray.toString(): " + jsonArray.toString());
                    GifElement[] gifElements = new GifElement[jsonArray.length()];
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject tmpObj = jsonArray.getJSONObject(i);
                        JSONObject tmpImage = tmpObj.getJSONObject("original");
                        gifElements[i] = new GifElement(
                                tmpObj.getString("type"),
                                tmpObj.getString("id"),
                                tmpObj.getString("url"),
                                tmpObj.getString("slug"),
                                tmpObj.getString("bitly_gif_url"),
                                tmpObj.getString("bitly_url"),
                                tmpObj.getString("embed_url"),
                                tmpObj.getString("username"),
                                tmpObj.getString("source"),
                                tmpObj.getString("title"),
                                tmpObj.getString("rating"),
                                tmpObj.getString("content_url"),
                                tmpObj.getString("source_tld"),
                                tmpObj.getString("source_post_url"),
                                tmpObj.getBoolean("is_sticker"),
                                tmpObj.getString("import_datetime"),
                                tmpObj.getString("trending_datetime"),
                                "original",
                                tmpImage.getString("height"),
                                tmpImage.getString("width"),
                                tmpImage.getString("size"));


                        Log.d("TAG1","tmpObj.getString(\"type\"): " + tmpObj.getString("type"));
                        Log.d("TAG1","tmpObj.getString(\"id\"): " + tmpObj.getString("id"));
                    }
                }catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
        Log.d("TAG1", "LINE 73 getDataFromUrl");
    }
}
