package com.example.qlsv_se160835_net1717.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.qlsv_se160835_net1717.R;

import java.util.List;

public class MajorAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> majors;
    private MajorAdapterListener listener;

    public interface MajorAdapterListener {
        void onEditClicked(String major);
        void onDeleteClicked(String major);
    }

    public MajorAdapter(Context context, int resource, List<String> majors, MajorAdapterListener listener) {
        super(context, resource, majors);
        this.context = context;
        this.majors = majors;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_major, parent, false);

        TextView textViewMajor = view.findViewById(R.id.textViewMajor);
        Button buttonEdit = view.findViewById(R.id.buttonEditMajor);
        Button buttonDelete = view.findViewById(R.id.buttonDeleteMajor);

        final String major = majors.get(position);
        textViewMajor.setText(major);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClicked(major);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(major);
            }
        });

        return view;
    }
}
