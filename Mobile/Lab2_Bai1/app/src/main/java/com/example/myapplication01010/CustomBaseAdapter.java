package com.example.myapplication01010;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;


public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String array[];
    LayoutInflater inflater;
    public CustomBaseAdapter(Context ctx, String [] arr) {
        this.context = ctx;
        this.array = arr;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return array.length;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_listview, null);
        TextView txtView = (TextView) convertView.findViewById(R.id.textView);
        txtView.setText(array[position]);
        return convertView;
    }
}
