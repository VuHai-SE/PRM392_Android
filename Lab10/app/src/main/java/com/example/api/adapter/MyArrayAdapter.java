package com.example.api.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.api.R;
import com.example.api.model.Trainee;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Trainee> {
    Activity context;
    int idLayout;
    ArrayList<Trainee> myList;

    public MyArrayAdapter(Activity context, int idLayout, ArrayList<Trainee> myList) {
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlacter = context.getLayoutInflater();
        convertView = myFlacter.inflate(idLayout, null);
        Trainee myTrainee = myList.get(position);

        TextView name = convertView.findViewById(R.id.textViewName);
        name.setText(myTrainee.getName());

        TextView email = convertView.findViewById(R.id.textViewEmail);
        email.setText(myTrainee.getEmail());

        TextView phone = convertView.findViewById(R.id.textViewPhone);
        phone.setText(myTrainee.getPhone());

        TextView gender = convertView.findViewById(R.id.textViewGender);
        gender.setText(myTrainee.getGender());

        return convertView;
    }
}
