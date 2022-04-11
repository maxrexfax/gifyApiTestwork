package com.example.gifslistapitestwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
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
                Uri imageUri = Uri.parse(element.getEmbed_url());
                Bitmap bitmap = null;
                try {
                        bitmap = MediaStore.Images.Media.getBitmap(_context.getContentResolver(), imageUri);
                } catch (IOException e) {
                        e.printStackTrace();
                }
                holder.gifView.setImageBitmap(bitmap);
                holder.titleView.setText(element.getTitle());
               // Log.d("TAG1", "GifElementAdapter onBindViewHolder  element.getTitle():" + element.getTitle());
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
