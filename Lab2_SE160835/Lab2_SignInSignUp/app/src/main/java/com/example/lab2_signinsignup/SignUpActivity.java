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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    // Views
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private TextView tvAlreadyAccount;
    private Button btnSignUp;

    // Notify
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Reference from Layout
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        tvAlreadyAccount = findViewById(R.id.tvAlreadyAccount);
        btnSignUp = findViewById(R.id.btnSignUp);

        // Register event
        tvAlreadyAccount.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(edtUsername.getText().toString())) {
            edtUsername.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            edtPassword.setError(REQUIRE);
            return false;
        }

        if (TextUtils.isEmpty(edtConfirmPassword.getText().toString())) {
            edtConfirmPassword.setError(REQUIRE);
            return false;
        }

        if (!TextUtils.equals(edtPassword.getText().toString(), edtConfirmPassword.getText().toString())) {
            Toast.makeText(this, "Password are not match", Toast.LENGTH_LONG).show();
            return false;
        }

        // Valid
        return true;
    }

    private void signUp() {
        // Invalid
        if (!checkInput()) {
            return;
        }

        //start main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();//close current activity
    }

    private void signInForm() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSignUp){
            signUp();
        } else if (id == R.id.tvAlreadyAccount) {
            signInForm();
        }
    }
}
