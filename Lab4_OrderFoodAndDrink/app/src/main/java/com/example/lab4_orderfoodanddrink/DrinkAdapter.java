package com.example.lab4_orderfoodanddrink;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Drink> drinkList;

    public DrinkAdapter(Context context, int layout, ArrayList<Drink> drinkList) {
        this.context = context;
        this.layout = layout;
        this.drinkList = drinkList;
    }

    @Override
    public int getCount() {
        return drinkList.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imgDrink;
        TextView txtName, txtDescription;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgDrink = convertView.findViewById(R.id.imgDrink);
            holder.txtName = convertView.findViewById(R.id.txtDrinkName);
            holder.txtDescription = convertView.findViewById(R.id.txtDrinkDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Drink drink = (Drink) getItem(position);
        holder.imgDrink.setImageResource(drink.getImageResourceId());
        holder.txtName.setText(drink.getName());
        holder.txtDescription.setText(drink.getDescription());

        return convertView;
    }
}
