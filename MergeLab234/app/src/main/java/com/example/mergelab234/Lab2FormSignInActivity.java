package com.example.mergelab234;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Lab2FormSignInActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSignIn, btnReturn;
    TextView tvNotAccountYet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab2_form_sign_in);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnReturn = findViewById(R.id.btnReturn);
        tvNotAccountYet = findViewById(R.id.tvNotAccountYet);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Lab2FormSignInActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Assuming you have saved the user data in a previous activity
                    Intent intent = getIntent();
                    String registeredUsername = intent.getStringExtra("username");
                    String registeredPassword = intent.getStringExtra("password");

                    if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                        Intent mainIntent = new Intent(Lab2FormSignInActivity.this, Lab2MenuActivity.class);
                        startActivity(mainIntent);
                    } else {
                        Toast.makeText(Lab2FormSignInActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvNotAccountYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lab2FormSignInActivity.this, Lab2FormSignUpActivity.class);
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
