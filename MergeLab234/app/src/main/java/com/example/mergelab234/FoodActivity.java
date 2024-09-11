package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    ListView lvFood;
    Button btnDatMon;
    ArrayList<Food> foodList;
    FoodAdapter foodAdapter;
    int selectedFoodIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        lvFood = findViewById(R.id.lvFood);
        btnDatMon = findViewById(R.id.btnDatMon);

        foodList = new ArrayList<>();
        foodList.add(new Food("Bún đậu", "Món ngon dân dã", R.drawable.bun_dau, 35000));
        foodList.add(new Food("Gà rán", "Món ăn phổ biến", R.drawable.fried_chicken, 45000));
        foodList.add(new Food("Hamburger", "Món ăn nhanh", R.drawable.hamburgur, 50000));
        foodList.add(new Food("Thịt kho tàu", "Món ăn truyền thống", R.drawable.kho_tau, 40000));
        foodList.add(new Food("Spagetti", "Món Ý nổi tiếng", R.drawable.spagetti, 55000));

        foodAdapter = new FoodAdapter(this, R.layout.item_food, foodList);
        lvFood.setAdapter(foodAdapter);

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFoodIndex = position;
                Toast.makeText(FoodActivity.this, "Bạn đã chọn: " + foodList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btnDatMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFoodIndex != -1) {
                    Food selectedFood = foodList.get(selectedFoodIndex);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("selectedFood", selectedFood.getName());
                    resultIntent.putExtra("foodPrice", selectedFood.getPrice());
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(FoodActivity.this, "Vui lòng chọn món ăn", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
