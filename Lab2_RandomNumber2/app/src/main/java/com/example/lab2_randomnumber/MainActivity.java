package com.example.lab2_randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText editTextMin;
    private EditText editTextMax;
    private TextView textViewResult;
    private Button buttonGenerate;
    private LinearLayout linearLayoutHistory;
    private ScrollView scrollViewHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextMin = findViewById(R.id.editTextMin);
        editTextMax = findViewById(R.id.editTextMax);
        textViewResult = findViewById(R.id.textViewResult);
        buttonGenerate = findViewById(R.id.buttonGenerate);
        linearLayoutHistory = findViewById(R.id.linearLayoutHistory);
        scrollViewHistory = findViewById(R.id.scrollViewHistory);

        buttonGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();
            }
        });
    }

    private void generateRandomNumber() {
        String minString = editTextMin.getText().toString();
        String maxString = editTextMax.getText().toString();

        if (!minString.isEmpty() && !maxString.isEmpty()) {
            int min = Integer.parseInt(minString);
            int max = Integer.parseInt(maxString);

            if (min <= max) {
                Random random = new Random();
                int randomNumber = random.nextInt((max - min) + 1) + min;
                textViewResult.setText("Result: " + randomNumber);
                addNumberToHistory(randomNumber);
            } else {
                textViewResult.setText("Min should be less than or equal to Max");
            }
        } else {
            textViewResult.setText("Please enter both Min and Max values");
        }
    }

    private void addNumberToHistory(int number) {
        // Lấy thời gian hiện tại
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.getDefault());
        String currentTime = sdf.format(new Date());

        // Create new textview
        TextView textView = new TextView(this);
        String historyText = number + " (Generated at: " + currentTime + ")";
        textView.setText(historyText);
        textView.setTextSize(16);
        textView.setPadding(8, 8, 8, 8);

        // Add new TextView
        linearLayoutHistory.addView(textView);

        // Scrol to the latest number
        scrollViewHistory.post(new Runnable() {
            @Override
            public void run() {
                scrollViewHistory.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}