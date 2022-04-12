package com.example.gifslistapitestwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GifElementAdapter extends RecyclerView.Adapter<GifElementAdapter.ViewHolder> {

        Context _context;
        private final LayoutInflater _inflater;
        private final List<GifElement> _gifElements;

        GifElementAdapter(Context context, List<GifElement> gifElements) {
                this._gifElements = gifElements;
                this._inflater = LayoutInflater.from(context);
                this._context = context;
        }

        @Override
        public GifElementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = _inflater.inflate(R.layout.gif_element_view, parent, false);
                return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(GifElementAdapter.ViewHolder holder, int position) {
                GifElement element = _gifElements.get(position);
                //Uri imageUri = Uri.parse(element.getImageInner().getUrl());
                //Uri imageUri = Uri.fromFile(new File(element.getImageInner().getUrl()));
                URL url = null;
                try {
                        url = new URL(element.getImageInner().getUrl());Log.d("TAG1", "LINE 48 TRY: " + url.toString() );
                } catch (MalformedURLException e) {
                        Log.d("TAG1", "LINE 50 CATCH" + e.getMessage() );
                }

                Bitmap bitmap = null;
                Log.d("TAG1", "LINE 54 GifElementAdapter onBindViewHolder  element.getEmbed_url():" + element.getImageInner().getUrl());
                try {
                        //bitmap = MediaStore.Images.Media.getBitmap(_context.getContentResolver(), url);
                        //bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        setImageInImageView(holder, url);
                        Log.d("TAG1", "LINE 58 TRY" );
                } catch (Exception e) {
                        Log.d("TAG1", "LINE 60 CATCH" + e.getMessage() );
                }
                //holder.gifView.setImageBitmap(bitmap);
                holder.titleView.setText(element.getTitle());
               // Log.d("TAG1", "GifElementAdapter onBindViewHolder  element.getTitle():" + element.getTitle());
        }

        public void setImageInImageView(GifElementAdapter.ViewHolder holder, URL urlIn) {
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
                                                holder.gifView.setImageBitmap(bitmap);
                                        }
                                });
                        }
                };

                Thread t = new Thread(r);
                t.start();
        }

        @Override
        public int getItemCount() {
                return _gifElements.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
                final ImageView gifView;
                final TextView titleView;
                ViewHolder(View view){
                        super(view);
                        gifView = view.findViewById(R.id.gif_image_view);
                        titleView = view.findViewById(R.id.title_text_view);
                       // Log.d("TAG1", "GifElementAdapter public static class ViewHolder extends RecyclerView.ViewHolder:");
                }
        }
}
