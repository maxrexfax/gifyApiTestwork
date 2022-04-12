package com.example.gifslistapitestwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<GifElement> _listOfElements = null;
    private ApiHttpGetter _apiHttpGetter;
    ProgressDialog _pd;
    RecyclerView _recyclerView;
    private String _completeUrl = "https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=&limit=25&offset=0&rating=g&lang=en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _recyclerView = findViewById(R.id.element_recycler_view);
        _apiHttpGetter = new ApiHttpGetter(_listOfElements, this, _recyclerView, this);
        _apiHttpGetter.getElements();
    }

//    private void getDataAndFillList() {
//        _listOfElements.clear();
//        //Log.d("TAG1", "public void getDataAndFillList worked");
//        for(int i = 0; i < 10; i++) {
//            _listOfElements.add(new GifElement("Element N" + (i+1), "http://domain.com/?i=" + i));
//        }
//       // Log.d("TAG1", "getDataAndFillList _listOfElements.size():" + _listOfElements.size());
//        Toast.makeText(this, "List filled successfully", Toast.LENGTH_SHORT).show();
//    }

    public void showData(ArrayList<GifElement> listOfElements) {
        RecyclerView recyclerView = findViewById(R.id.element_recycler_view);
        GifElementAdapter gifElementAdapter = new GifElementAdapter(this, listOfElements);
        recyclerView.setAdapter(gifElementAdapter);
    }

    private void getDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.giphy.com/v1/gifs/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GifElementApi gifElementsApi = retrofit.create(GifElementApi.class);

        Call<List<Data>> gifElements = gifElementsApi.getDataElements();

        gifElements.enqueue(new Callback <List<Data>>() {

            @Override
            public void onResponse(Call<List<Data>> call, Response<List<Data>> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG1","84 response " + response.body().size());
                } else {
                    Log.d("TAG1","87 response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Data>> call, Throwable t) {
                Log.d("TAG1","93 failure " + t);
            }
        });
    }

    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}