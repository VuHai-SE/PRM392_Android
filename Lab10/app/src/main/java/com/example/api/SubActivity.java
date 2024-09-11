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

public class SubActivity extends AppCompatActivity {
    TraineeService traineeService;
    EditText name, email, phone, gender;
    long id;
    Button btnUpdate, btnDelete, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        gender = findViewById(R.id.gender);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBack);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Trainee trainee = (Trainee) bundle.getSerializable("trainee");
            if (trainee != null) {
                id = trainee.getId();
                name.setText(trainee.getName());
                email.setText(trainee.getEmail());
                phone.setText(trainee.getPhone());
                gender.setText(trainee.getGender());
            }
        }

        traineeService = TraineeRepository.getTraineeService();
        btnUpdate.setOnClickListener(v -> update());
        btnDelete.setOnClickListener(v -> delete());
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void update() {
        String nameStr = name.getText().toString();
        String emailStr = email.getText().toString();
        String phoneStr = phone.getText().toString();
        String genderStr = gender.getText().toString();

        Trainee trainee = new Trainee(nameStr,emailStr,phoneStr, genderStr);
        try {
            Call<Trainee> call = traineeService.updateTrainees(id, trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(SubActivity.this, "Sửa thành công!", Toast.LENGTH_LONG).show();
                        Intent resultIntent = new Intent();
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(SubActivity.this, "Sửa thất bại!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }

    private void delete() {
        try {
            Call<Trainee> call = traineeService.deleteTrainees(id);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(SubActivity.this, "Xóa thành công!", Toast.LENGTH_LONG).show();
                        Intent resultIntent = new Intent();
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(SubActivity.this, "Xóa thất bại!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}