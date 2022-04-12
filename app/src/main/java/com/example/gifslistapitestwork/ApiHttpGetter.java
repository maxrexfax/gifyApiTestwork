package com.example.gifslistapitestwork;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.HttpsURLConnection;

public class ApiHttpGetter {
    private ArrayList<GifElement> _listOfElements;
    private Context _context;
    private MainActivity _activity;
    private RecyclerView _recyclerView;
    //Handler handler = MainActivity.handler;
    private String _baseUrl = "https://android-kotlin-fun-mars-server.appspot.com";
    //private String _baseUrl = "https://api.giphy.com/v1/gifs/search?";
    //private String _baseUrl = "http://tests.loc/";
    //private String _reqParamsUrl = "api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=&limit=25&offset=0&rating=g&lang=en";
    private String _completeUrl = "https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=deadpool&limit=25&offset=0&rating=g&lang=en";

    public ApiHttpGetter(ArrayList<GifElement> listOfElements, Context context, RecyclerView recyclerView, MainActivity activity) {
        this._listOfElements  = new ArrayList<>();
        this._context = context;
        this._activity = activity;
        this._recyclerView = recyclerView;
    }

    public void getElements() {
        Runnable r = new Runnable()
        {
            @Override
            public void run()
            {
                try {
                    HttpURLConnection urlConnection = null;
                    URL url = null;
                    try {
                        url = new URL(_completeUrl);
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
                    JSONObject jsonObject = new JSONObject(jsonString);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject tmpObj = jsonArray.getJSONObject(i);
                        JSONObject tmpImages = tmpObj.getJSONObject("images");
                        JSONObject tmpImage = tmpImages.getJSONObject("original");

                        _listOfElements.add( new GifElement(
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
                                tmpObj.getInt("is_sticker"),
                                tmpObj.getString("import_datetime"),
                                tmpObj.getString("trending_datetime"),
                                "original",
                                tmpImage.getString("height"),
                                tmpImage.getString("width"),
                                tmpImage.getString("size"),
                                tmpImage.getString("url")
                                 ));
                    }
                    _activity.runOnUiThread(new Runnable()
                    {
                        public void run()
                        {
                            _activity.showData(_listOfElements);
                        }
                    });

                }catch (Exception ex) {
                    Log.d("TAG1", "Class ApiHttpGetter, Exception message:" + ex.getMessage());
                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}
