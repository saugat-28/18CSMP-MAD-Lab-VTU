package com.madlab.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.madlab.myapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;
    private String username, password;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialising Binding and Setting Content View
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        Bundle bundle = getIntent().getBundleExtra("credentials");
        username = bundle.getString("username");
        password = bundle.getString("password");

        // setting OnClick Listener
        loginBinding.loginButton.setOnClickListener(v -> {
            validateLogin();
        });
    }

    private void validateLogin() {
        String loginUsername = loginBinding.loginUsernameEt.getText().toString();
        String loginPassword = loginBinding.loginPasswordEt.getText().toString();

        if (loginUsername.equals(username) && loginPassword.equals(password)) {
            Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_LONG).show();
        } else {
            count++;
            if (count == 3) {
                loginBinding.loginButton.setEnabled(false);
                Toast.makeText(this, "Failed Login Attempts: " + count, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }
}