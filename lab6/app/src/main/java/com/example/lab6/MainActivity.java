package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button optionMenuButton, popupMenuButton, contextMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionMenuButton = findViewById(R.id.optionMenuButton);
        popupMenuButton = findViewById(R.id.popupMenuButton);
        contextMenuButton = findViewById(R.id.contextMenuButton);

        optionMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptionMenuActivity.class));
            }
        });

        popupMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopupMenuActivity.class));
            }
        });

        contextMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContextMenuActivity.class));
            }
        });
    }
}
