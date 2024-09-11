package com.example.mergelab234;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Lab3FruitActivity extends AppCompatActivity {
    ListView listViewTraiCay;
    EditText editTextTen, editTextMoTa;
    Button btnAdd, btnUpdate, btnDelete, btnReturn;
    ArrayList<TraiCay> traiCayArrayList;
    TraiCayAdapter traiCayAdapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_cuslv_fruit);

        listViewTraiCay = findViewById(R.id.listViewTraiCay);
        editTextTen = findViewById(R.id.editTextTen);
        editTextMoTa = findViewById(R.id.editTextMoTa);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);
        traiCayArrayList = new ArrayList<>();

        // Thêm dữ liệu mẫu vào danh sách
        traiCayArrayList.add(new TraiCay("Dragon Fruit", "This is a dragon fruit.", R.drawable.dragon_fruit));
        traiCayArrayList.add(new TraiCay("Durian", "This is a durian.", R.drawable.durian));
        traiCayArrayList.add(new TraiCay("Rambutan", "This is a rambutan.", R.drawable.rambutan));
        traiCayArrayList.add(new TraiCay("Star Apple", "This is a star apple.", R.drawable.star_apple));

        traiCayAdapter = new TraiCayAdapter(this, R.layout.dong_trai_cay, traiCayArrayList);
        listViewTraiCay.setAdapter(traiCayAdapter);

        // Xử lý sự kiện nút Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editTextTen.getText().toString().trim();
                String moTa = editTextMoTa.getText().toString().trim();
                if (!ten.isEmpty() && !moTa.isEmpty()) {
                    if (!isDuplicate(ten)) {
                        traiCayArrayList.add(new TraiCay(ten, moTa, R.drawable.dragon_fruit)); // Sử dụng hình ảnh mặc định
                        traiCayAdapter.notifyDataSetChanged();
                        editTextTen.setText("");
                        editTextMoTa.setText("");
                    } else {
                        Toast.makeText(Lab3FruitActivity.this, "Tên trái cây đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3FruitActivity.this, "Vui lòng nhập tên và mô tả", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = editTextTen.getText().toString().trim();
                String moTa = editTextMoTa.getText().toString().trim();
                if (selectedIndex != -1 && !ten.isEmpty() && !moTa.isEmpty()) {
                    if (!isDuplicate(ten) || traiCayArrayList.get(selectedIndex).getTen().equals(ten)) {
                        TraiCay traiCay = traiCayArrayList.get(selectedIndex);
                        traiCay.setTen(ten);
                        traiCay.setMoTa(moTa);
                        traiCayAdapter.notifyDataSetChanged();
                        editTextTen.setText("");
                        editTextMoTa.setText("");
                        selectedIndex = -1;
                    } else {
                        Toast.makeText(Lab3FruitActivity.this, "Tên trái cây đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3FruitActivity.this, "Vui lòng chọn trái cây để cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện khi bấm vào một dòng trong ListView
        listViewTraiCay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                TraiCay traiCay = traiCayArrayList.get(position);
                editTextTen.setText(traiCay.getTen());
                editTextMoTa.setText(traiCay.getMoTa());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != -1) {
                    traiCayArrayList.remove(selectedIndex);
                    traiCayAdapter.notifyDataSetChanged();
                    editTextTen.setText("");
                    editTextMoTa.setText("");
                    selectedIndex = -1;
                } else {
                    Toast.makeText(Lab3FruitActivity.this, "Vui lòng chọn trái cây để xóa", Toast.LENGTH_SHORT).show();
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

    // Phương thức kiểm tra trùng lặp tên
    private boolean isDuplicate(String ten) {
        for (TraiCay traiCay : traiCayArrayList) {
            if (traiCay.getTen().equals(ten)) {
                return true;
            }
        }
        return false;
    }
}