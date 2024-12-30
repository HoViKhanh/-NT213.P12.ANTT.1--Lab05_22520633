package com.example.lab2_bai5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Dish> foodList;

    public DishAdapter(Context context, int layout, List<Bai4_Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.ivThumbnail = convertView.findViewById(R.id.iv_thumbnail);
            holder.tvDishName = convertView.findViewById(R.id.tv_dish_name);
            holder.ivPromotion = convertView.findViewById(R.id.iv_promotion);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Dish food = foodList.get(position);

        // Hiển thị hình ảnh
        holder.ivThumbnail.setImageResource(food.getThumbnail());

        // Hiển thị tên món ăn
        holder.tvDishName.setText(food.getName());

        // Hiển thị icon star nếu có khuyến mãi
        if (food.isPromotion()) {
            holder.ivPromotion.setVisibility(View.VISIBLE);
        } else {
            holder.ivPromotion.setVisibility(View.GONE);
        }

        return convertView;
    }

    private class ViewHolder {
        ImageView ivThumbnail;
        TextView tvDishName;
        ImageView ivPromotion;
    }
}
