package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FootballLegendAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<FootballLegend> legendsList;

    public FootballLegendAdapter(Context context, int layout, ArrayList<FootballLegend> legendsList) {
        this.context = context;
        this.layout = layout;
        this.legendsList = legendsList;
    }

    @Override
    public int getCount() {
        return legendsList.size();
    }

    @Override
    public Object getItem(int position) {
        return legendsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imgPlayer, imgCountry;
        TextView txtName, txtDetails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder.imgPlayer = convertView.findViewById(R.id.imgPlayer);
            holder.imgCountry = convertView.findViewById(R.id.imgCountry);
            holder.txtName = convertView.findViewById(R.id.txtPlayerName);
            holder.txtDetails = convertView.findViewById(R.id.txtPlayerDetails);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FootballLegend legend = (FootballLegend) getItem(position);
        holder.imgPlayer.setImageResource(legend.getPlayerImage());
        holder.imgCountry.setImageResource(legend.getCountryImage());
        holder.txtName.setText(legend.getName());
        holder.txtDetails.setText(legend.getDetails());

        return convertView;
    }
}
