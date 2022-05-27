package com.madlab.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.madlab.myapplication.databinding.ActivitySignUpBinding;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding signUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialising Binding and Setting Content View
        signUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(signUpBinding.getRoot());

        // setting OnClick Listener
        signUpBinding.signupButton.setOnClickListener(v -> {
            signUp();
        });
    }

    private void signUp() {
        String username = signUpBinding.usernameEt.getText().toString();
        String password = signUpBinding.passwordEt.getText().toString();

        if (validatePassword(password)) {
            Bundle bundle = new Bundle();
            bundle.putString("username", username);
            bundle.putString("password", password);

            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("credentials", bundle);

            startActivity(intent);

        } else {
            Toast.makeText(this, "Invalid Password", Toast.LENGTH_LONG).show();
        }
    }

    private boolean validatePassword(String password) {
        String regularExpression = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!])[A-Za-z\\d@$!]{8,}$";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}