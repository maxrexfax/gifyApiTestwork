package com.example.gifslistapitestwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String IMAGE_MESSAGE = "com.example.IMG_MESSAGE";

    private ApiHttpGetter _apiHttpGetter;
    RecyclerView _recyclerView;
    private String _completeUrl = "https://api.giphy.com/v1/gifs/search?api_key=YGHnKKBGSydS6nSt6WAoUcICWwmgCfvL&q=&limit=25&offset=0&rating=g&lang=en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _recyclerView = findViewById(R.id.element_recycler_view);
        _apiHttpGetter = new ApiHttpGetter(this);
        _apiHttpGetter.getElements();
    }

    public void showData(List<GifElement> listOfElements) {
        RecyclerView recyclerView = findViewById(R.id.element_recycler_view);
        GifElementAdapter gifElementAdapter = new GifElementAdapter(this, listOfElements);
        recyclerView.setAdapter(gifElementAdapter);
    }

    public void showOneImage(View view) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(IMAGE_MESSAGE, view.getTag().toString());
        startActivity(intent);
    }
}