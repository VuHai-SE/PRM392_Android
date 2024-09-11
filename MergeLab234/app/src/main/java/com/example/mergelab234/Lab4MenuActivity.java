package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Lab4MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab4_menu);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Button btn1 = findViewById(R.id.btn1);
//        btn1.setOnClickListener(v -> {
//            Intent intent = new Intent(Lab4MenuActivity.this, Lab4_1.class);
//            startActivity(intent);
//        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(Lab4MenuActivity.this, Lab4OrderActivity.class);
            startActivity(intent);
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(v -> {
            Intent intent = new Intent(Lab4MenuActivity.this, Lab4ClickShowActivity.class);
            startActivity(intent);
        });

//
//        btnReturn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }
}
