package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLab2, btnLab3, btnLab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLab2 = findViewById(R.id.btnLab2);
        btnLab3 = findViewById(R.id.btnLab3);
        btnLab4 = findViewById(R.id.btnLab4);

        btnLab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lab2MenuActivity.class);
                startActivity(intent);
            }
        });

        btnLab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lab3MenuActivity.class);
                startActivity(intent);
            }
        });

        btnLab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Lab4MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
