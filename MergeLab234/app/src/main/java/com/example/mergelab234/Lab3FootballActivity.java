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

public class Lab3FootballActivity extends AppCompatActivity {

    ListView listViewFootballLegends;
    EditText editTextName, editTextDetails;
    Button btnAdd, btnUpdate, btnReturn, btnDelete;
    ArrayList<FootballLegend> footballLegendsList;
    FootballLegendAdapter footballLegendAdapter;
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_cuslv_football);

        listViewFootballLegends = findViewById(R.id.listViewFootballLegends);
        editTextName = findViewById(R.id.editTextName);
        editTextDetails = findViewById(R.id.editTextDetails);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnReturn = findViewById(R.id.btnReturn);
        footballLegendsList = new ArrayList<>();

        // Thêm dữ liệu cầu thủ vào danh sách
        footballLegendsList.add(new FootballLegend("Cristiano Ronaldo", "February 5, 1985 (age 36)", R.drawable.cristiano_ronaldo, R.drawable.bo_dao_nha));
        footballLegendsList.add(new FootballLegend("David Beckham", "May 2, 1975 (age 46)", R.drawable.david_beckham, R.drawable.anh));
        footballLegendsList.add(new FootballLegend("Kaka", "April 22, 1982 (age 39)", R.drawable.kaka, R.drawable.brazil));
        footballLegendsList.add(new FootballLegend("Lionel Messi", "June 24, 1987 (age 34)", R.drawable.messi, R.drawable.argentina));
        footballLegendsList.add(new FootballLegend("Ronaldinho", "March 21, 1980 (age 41)", R.drawable.ronaldinho, R.drawable.brazil));
        footballLegendsList.add(new FootballLegend("Thierry Henry", "August 17, 1977 (age 44)", R.drawable.thierry_henry, R.drawable.france));
        footballLegendsList.add(new FootballLegend("Zinedine Zidane", "June 23, 1972 (age 49)", R.drawable.zinedine_zidane, R.drawable.france));

        footballLegendAdapter = new FootballLegendAdapter(this, R.layout.item_football_legend, footballLegendsList);
        listViewFootballLegends.setAdapter(footballLegendAdapter);

        // Xử lý sự kiện nút Add
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String details = editTextDetails.getText().toString().trim();
                if (!name.isEmpty() && !details.isEmpty()) {
                    if (!isDuplicate(name)) {
                        footballLegendsList.add(new FootballLegend(name, details, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background)); // Sử dụng hình ảnh mặc định
                        footballLegendAdapter.notifyDataSetChanged();
                        editTextName.setText("");
                        editTextDetails.setText("");
                    } else {
                        Toast.makeText(Lab3FootballActivity.this, "Tên cầu thủ đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3FootballActivity.this, "Vui lòng nhập tên và chi tiết", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện nút Update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString().trim();
                String details = editTextDetails.getText().toString().trim();
                if (selectedIndex != -1 && !name.isEmpty() && !details.isEmpty()) {
                    if (!isDuplicate(name) || footballLegendsList.get(selectedIndex).getName().equals(name)) {
                        FootballLegend legend = footballLegendsList.get(selectedIndex);
                        legend.setName(name);
                        legend.setDetails(details);
                        footballLegendAdapter.notifyDataSetChanged();
                        editTextName.setText("");
                        editTextDetails.setText("");
                        selectedIndex = -1;
                    } else {
                        Toast.makeText(Lab3FootballActivity.this, "Tên cầu thủ đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Lab3FootballActivity.this, "Vui lòng chọn cầu thủ để cập nhật", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Xử lý sự kiện khi bấm vào một dòng trong ListView
        listViewFootballLegends.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                FootballLegend legend = footballLegendsList.get(position);
                editTextName.setText(legend.getName());
                editTextDetails.setText(legend.getDetails());
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != -1) {
                    footballLegendsList.remove(selectedIndex);
                    footballLegendAdapter.notifyDataSetChanged();
                    editTextName.setText("");
                    editTextDetails.setText("");
                    selectedIndex = -1;
                } else {
                    Toast.makeText(Lab3FootballActivity.this, "Vui lòng chọn cầu thủ để xóa", Toast.LENGTH_SHORT).show();
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
    private boolean isDuplicate(String name) {
        for (FootballLegend legend : footballLegendsList) {
            if (legend.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
