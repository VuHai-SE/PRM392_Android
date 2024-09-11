package com.example.lab2_signinsignup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    //Views
    private EditText edtUsername;
    private EditText edtPassword;
    private TextView tvNotAccountYet;
    private Button btnSignIn;

    //Notify
    private final String REQUIRE = "Require";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Reference from Layout
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        tvNotAccountYet = findViewById(R.id.tvNotAccountYet);
        btnSignIn = findViewById(R.id.btnSignIn);

        //Register event
        tvNotAccountYet.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private boolean checkInput(){
        //Username
        if(TextUtils.isEmpty(edtUsername.getText().toString())){
            edtUsername.setError(REQUIRE);
            return false;
        }

        //Password
        if (TextUtils.isEmpty(edtPassword.getText().toString())){
            edtPassword.setError(REQUIRE);
            return false;
        }

        //Valid
        return true;
    }

    private void signIn(){
        //Invalid
        if (!checkInput()){
            return;
        }

        //Start MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); //close current activity
    }

    private void signUpForm(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnSignIn) {
            signIn();
        } else if (id == R.id.tvNotAccountYet) {
            signUpForm();
        }
    }
}
