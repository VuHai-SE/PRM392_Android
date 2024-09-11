package com.example.lab2_signinsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnSignIn;
    TextView tvNotAccountYet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        tvNotAccountYet = findViewById(R.id.tvNotAccountYet);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Assuming you have saved the user data in a previous activity
                    Intent intent = getIntent();
                    String registeredUsername = intent.getStringExtra("username");
                    String registeredPassword = intent.getStringExtra("password");

                    if (username.equals(registeredUsername) && password.equals(registeredPassword)) {
                        Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tvNotAccountYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
