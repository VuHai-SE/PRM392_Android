package com.example.lab4_orderfoodanddrink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Food> foodList;

    public FoodAdapter(Context context, int layout, ArrayList<Food> foodList) {
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

    private class ViewHolder {
        ImageView imgFood;
        TextView txtName, txtDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgFood = convertView.findViewById(R.id.imgFood);
            holder.txtName = convertView.findViewById(R.id.txtFoodName);
            holder.txtDescription = convertView.findViewById(R.id.txtFoodDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food = (Food) getItem(position);
        holder.imgFood.setImageResource(food.getImageResourceId());
        holder.txtName.setText(food.getName());
        holder.txtDescription.setText(food.getDescription());

        return convertView;
    }
}
