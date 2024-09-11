package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Lab3MenuActivity extends AppCompatActivity {

    Button btnListView, btnCustomListView1, btnCustomListView2, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab3_menu);

        btnListView = findViewById(R.id.btnListView);
        btnCustomListView1 = findViewById(R.id.btnCustomListView1);
        btnCustomListView2 = findViewById(R.id.btnCustomListView2);
        btnReturn = findViewById(R.id.btnReturn);

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab3MenuActivity.this, Lab3ListViewActivity.class);
                startActivity(intent);
            }
        });

        btnCustomListView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab3MenuActivity.this, Lab3FruitActivity.class);
                startActivity(intent);
            }
        });

        btnCustomListView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab3MenuActivity.this, Lab3FootballActivity.class);
                startActivity(intent);
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
