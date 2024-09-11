package com.example.mergelab234;

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
        TextView txtFoodName, txtFoodDescription, txtFoodPrice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgFood = convertView.findViewById(R.id.imgFood);
            holder.txtFoodName = convertView.findViewById(R.id.txtFoodName);
            holder.txtFoodDescription = convertView.findViewById(R.id.txtFoodDescription);
            holder.txtFoodPrice = convertView.findViewById(R.id.txtFoodPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food = (Food) getItem(position);
        holder.imgFood.setImageResource(food.getImage());
        holder.txtFoodName.setText(food.getName());
        holder.txtFoodDescription.setText(food.getDescription());
        holder.txtFoodPrice.setText(food.getPrice() + " đ");

        return convertView;
    }
}
