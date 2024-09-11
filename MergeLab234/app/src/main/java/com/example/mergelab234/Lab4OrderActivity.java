package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Lab4OrderActivity extends AppCompatActivity {

    Button btnChonThucAn, btnChonDoUong, btnReturn;
    TextView tvResultFood, tvResultDrink, tvTong;
    private static final int REQUEST_CODE_FOOD = 1;
    private static final int REQUEST_CODE_DRINK = 2;
    int totalPrice = 0;
    int currentFoodPrice = 0;
    int currentDrinkPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4_order);

        btnChonThucAn = findViewById(R.id.btnChonThucAn);
        btnChonDoUong = findViewById(R.id.btnChonDoUong);
        tvResultFood = findViewById(R.id.tvResultFood);
        tvResultDrink = findViewById(R.id.tvResultDrink);
        tvTong = findViewById(R.id.tvTong);
        btnReturn = findViewById(R.id.btnReturn);

        btnChonThucAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab4OrderActivity.this, FoodActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOOD);
            }
        });

        btnChonDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab4OrderActivity.this, DrinkActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DRINK);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_FOOD) {
                String selectedFood = data.getStringExtra("selectedFood");
                int foodPrice = data.getIntExtra("foodPrice", 0);
                totalPrice -= currentFoodPrice; // Loại bỏ giá món ăn hiện tại khỏi tổng
                currentFoodPrice = foodPrice; // Cập nhật giá món ăn mới
                totalPrice += currentFoodPrice; // Thêm giá món ăn mới vào tổng
                tvResultFood.setText("Món ăn: " + selectedFood + " (" + foodPrice + " đ)");
            } else if (requestCode == REQUEST_CODE_DRINK) {
                String selectedDrink = data.getStringExtra("selectedDrink");
                int drinkPrice = data.getIntExtra("drinkPrice", 0);
                totalPrice -= currentDrinkPrice; // Loại bỏ giá đồ uống hiện tại khỏi tổng
                currentDrinkPrice = drinkPrice; // Cập nhật giá đồ uống mới
                totalPrice += currentDrinkPrice; // Thêm giá đồ uống mới vào tổng
                tvResultDrink.setText("Đồ uống: " + selectedDrink + " (" + drinkPrice + " đ)");
            }

            tvTong.setText("Tổng tiền: " + totalPrice + " đ");
        }
    }
}
