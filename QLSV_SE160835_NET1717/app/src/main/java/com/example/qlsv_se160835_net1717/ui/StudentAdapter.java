package com.example.qlsv_se160835_net1717.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.qlsv_se160835_net1717.R;
import com.example.qlsv_se160835_net1717.database.DatabaseHelper;
import com.example.qlsv_se160835_net1717.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    private Context context;
    private int resource;
    private List<Student> students;
    private StudentAdapterListener listener;
    private DatabaseHelper dbHelper;

    public interface StudentAdapterListener {
        void onEditClicked(Student student);
        void onDeleteClicked(Student student);
    }

    public StudentAdapter(Context context, int resource, List<Student> students, StudentAdapterListener listener) {
        super(context, resource, students);
        this.context = context;
        this.resource = resource;
        this.students = students;
        this.listener = listener;
        this.dbHelper = new DatabaseHelper(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, parent, false);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewEmail = view.findViewById(R.id.textViewEmail);
        TextView textViewAddress = view.findViewById(R.id.textViewAddress);
        TextView textViewDate = view.findViewById(R.id.textViewDate);
        TextView textViewGender = view.findViewById(R.id.textViewGender);
        TextView textViewMajor = view.findViewById(R.id.textViewMajor);
        Button buttonEdit = view.findViewById(R.id.buttonEdit);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        final Student student = students.get(position);

        textViewName.setText(student.getName());
        textViewEmail.setText(student.getEmail());
        textViewAddress.setText(student.getAddress());
        textViewDate.setText(student.getDate());
        textViewGender.setText(student.getGender());
        textViewMajor.setText(dbHelper.getMajorName(student.getIdMajor()));

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClicked(student);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClicked(student);
            }
        });

        return view;
    }
}
