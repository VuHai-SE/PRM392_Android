package com.example.mergelab234;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Lab4ClickShowActivity extends Activity {

    Button btnString, btnNumber, btnObject, btnArray, btnBundle, btnReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab4_clickshow);
        Log.i("Lab4_3", "onCreate");

        btnString = findViewById(R.id.btnString);
        btnNumber = findViewById(R.id.btnNumber);
        btnObject = findViewById(R.id.btnObj);
        btnArray = findViewById(R.id.btnArray);
        btnBundle = findViewById(R.id.btnBundle);
        btnReturn = findViewById(R.id.btnReturn);

        btnString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringValue = "Vũ Văn Hải";
                Intent stringIntent = new Intent(Lab4ClickShowActivity.this, SecondActivity.class);
                stringIntent.putExtra("STRING_KEY", stringValue);
                startActivity(stringIntent);
            }
        });

        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = 15;
                Intent numberIntent = new Intent(Lab4ClickShowActivity.this, SecondActivity.class);
                numberIntent.putExtra("NUMBER_KEY", number);
                startActivity(numberIntent);
            }
        });

        btnObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentObject myObject = new StudentObject("Vũ Văn Hải", 22, "NET1713");
                Intent objectIntent = new Intent(Lab4ClickShowActivity.this, SecondActivity.class);
                objectIntent.putExtra("OBJECT_KEY", myObject);
                startActivity(objectIntent);
            }
        });

        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> fruits = new ArrayList<>();
                fruits.add("Legend");
                fruits.add("Fruit");
                fruits.add("Flag");
                Intent arrayIntent = new Intent(Lab4ClickShowActivity.this, SecondActivity.class);
                arrayIntent.putExtra("ARRAY_KEY", fruits);
                startActivity(arrayIntent);
            }
        });

        btnBundle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundleValue = new Bundle();
                bundleValue.putString("BUNDLE_STRING_KEY", "This is a string in a bundle");
                bundleValue.putDouble("BUNDLE_DOUBLE_KEY", 15.05);
                bundleValue.putInt("BUNDLE_INT_KEY", 42);
                bundleValue.putBoolean("BUNDLE_BOOLEAN_KEY", true);
                bundleValue.putStringArray("BUNDLE_STRING_ARRAY_KEY", new String[]{"Legend", "Fruit", "Flag"});
                Intent bundleIntent = new Intent(Lab4ClickShowActivity.this, SecondActivity.class);
                bundleIntent.putExtras(bundleValue);
                startActivity(bundleIntent);
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
