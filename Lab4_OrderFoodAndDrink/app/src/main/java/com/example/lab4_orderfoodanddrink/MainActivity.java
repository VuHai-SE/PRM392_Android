package com.example.lab4_orderfoodanddrink;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnChonThucAn, btnChonDoUong, btnThoat;
    TextView tvResult;
    private static final int REQUEST_CODE_FOOD = 1;
    private static final int REQUEST_CODE_DRINK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChonThucAn = findViewById(R.id.btnChonThucAn);
        btnChonDoUong = findViewById(R.id.btnChonDoUong);
        btnThoat = findViewById(R.id.btnThoat);
        tvResult = findViewById(R.id.tvResult);

        btnChonThucAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivityForResult(intent, REQUEST_CODE_FOOD);
            }
        });

        btnChonDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DRINK);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
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
                tvResult.setText(selectedFood + " - " + tvResult.getText().toString().split(" - ")[1]);
            } else if (requestCode == REQUEST_CODE_DRINK) {
                String selectedDrink = data.getStringExtra("selectedDrink");
                tvResult.setText(tvResult.getText().toString().split(" - ")[0] + " - " + selectedDrink);
            }
        }
    }
}