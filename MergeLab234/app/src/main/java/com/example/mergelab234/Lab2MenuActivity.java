package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Lab2MenuActivity extends AppCompatActivity {

    Button btnRandom, btnR2S, btnForm, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_menu);

        btnRandom = findViewById(R.id.btnRandom);
        btnR2S = findViewById(R.id.btnR2S);
        btnForm = findViewById(R.id.btnForm);
        btnReturn = findViewById(R.id.btnReturn);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab2MenuActivity.this, Lab2RandomActivity.class);
                startActivity(intent);
            }
        });

        btnR2S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab2MenuActivity.this, Lab2R2SActivity.class);
                startActivity(intent);
            }
        });

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab2MenuActivity.this, Lab2FormSignInActivity.class);
                startActivity(intent);
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab2MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
