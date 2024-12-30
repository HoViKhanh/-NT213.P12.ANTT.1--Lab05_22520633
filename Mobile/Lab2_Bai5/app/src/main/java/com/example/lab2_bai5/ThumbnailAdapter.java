package com.example.lab2_bai5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbnailAdapter extends BaseAdapter {
    private Context context;
    private int[] thumbnails;
    private String[] thumbnailNames;

    public ThumbnailAdapter(Context context, int[] thumbnails, String[] thumbnailNames) {
        this.context = context;
        this.thumbnails = thumbnails;
        this.thumbnailNames = thumbnailNames;
    }

    @Override
    public int getCount() {
        return thumbnails.length;
    }

    @Override
    public Object getItem(int position) {
        return thumbnails[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        }

        ImageView ivThumbnail = convertView.findViewById(R.id.iv_thumbnail);
        TextView tvThumbnailName = convertView.findViewById(R.id.tv_thumbnail_name);

        ivThumbnail.setImageResource(thumbnails[position]);
        tvThumbnailName.setText(thumbnailNames[position]);

        return convertView;
    }
}
