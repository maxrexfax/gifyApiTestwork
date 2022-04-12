package com.example.gifslistapitestwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageActivity extends AppCompatActivity {
    public static final String IMAGE_MESSAGE = "com.example.IMG_MESSAGE";

    ImageView _imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        _imageView = findViewById(R.id.image_view_big);
        Intent intent = getIntent();
        URL url = null;
        try {
            url = new URL(intent.getStringExtra(MainActivity.IMAGE_MESSAGE));
            setImageInImageView(url);
        } catch (Exception e) {
            Log.d("TAG1", "CATCH" + e.getMessage() );
        }
        // Capture the layout's TextView and set the string as its text

    }

    public void setImageInImageView(URL urlIn) {
        Runnable r = new Runnable()
        {
            Bitmap bitmap = null;
            @Override
            public void run()
            {
                try {
                    bitmap = BitmapFactory.decodeStream(urlIn.openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        _imageView.setImageBitmap(bitmap);
                    }
                });
            }
        };

        Thread t = new Thread(r);
        t.start();
    }
}