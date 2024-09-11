package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.api.api.TraineeRepository;
import com.example.api.api.TraineeService;
import com.example.api.model.Trainee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TraineeService traineeService;
    EditText name, email, phone, gender;
    Button btnSave, btnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        btnSave = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnDelete);
        btnSave.setOnClickListener(v -> save());
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListuserActivity.class);
                startActivity(intent);
            }
        });
        traineeService = TraineeRepository.getTraineeService();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void save() {
        String nameStr = name.getText().toString();
        String emailStr = email.getText().toString();
        String phoneStr = phone.getText().toString();
        String genderStr = gender.getText().toString();

        Trainee trainee = new Trainee(nameStr,emailStr,phoneStr, genderStr);
        try {
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Lưu thành công!", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Lưu thất bại!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}