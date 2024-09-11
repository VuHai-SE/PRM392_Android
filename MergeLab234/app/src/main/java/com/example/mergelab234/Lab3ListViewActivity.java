package com.example.mergelab234;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Lab3ListViewActivity extends AppCompatActivity {

    ListView listView;
    EditText editText;
    Button btnAdd, btnUpdate, btnReturn, btnDelete;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_listview);

        listView = findViewById(R.id.ListViewMonHoc);
        editText = findViewById(R.id.editTextMonHoc);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnReturn = findViewById(R.id.btnReturn);
        btnDelete = findViewById(R.id.btnDelete);

        list = new ArrayList<>();
        list.add("Android");
        list.add("PHP");
        list.add("iOS");
        list.add("Unity");
        list.add("ASP.net");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHoc = editText.getText().toString().trim();
                if (!monHoc.isEmpty()) {
                    if (!list.contains(monHoc)) {
                        list.add(monHoc);
                        adapter.notifyDataSetChanged();
                        editText.setText("");
                    } else {
                        Toast.makeText(Lab3ListViewActivity.this, "Môn học đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3ListViewActivity.this, "Vui lòng nhập môn học", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monHoc = editText.getText().toString().trim();
                if (!monHoc.isEmpty() && selectedIndex != -1) {
                    if (!list.contains(monHoc)) {
                        list.set(selectedIndex, monHoc);
                        adapter.notifyDataSetChanged();
                        editText.setText("");
                        selectedIndex = -1;
                    } else {
                        Toast.makeText(Lab3ListViewActivity.this, "Môn học đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3ListViewActivity.this, "Vui lòng chọn môn học để cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                editText.setText(list.get(position));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != -1) {
                    list.remove(selectedIndex);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    selectedIndex = -1;
                } else {
                    Toast.makeText(Lab3ListViewActivity.this, "Vui lòng chọn môn học để xóa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}