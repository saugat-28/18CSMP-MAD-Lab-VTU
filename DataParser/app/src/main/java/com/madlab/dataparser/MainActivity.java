package com.madlab.dataparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.madlab.dataparser.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Intent viewIntent = new Intent(this, ViewDataActivity.class);

        mainBinding.parseJson.setOnClickListener(v-> {
            viewIntent.putExtra("mode", 1);
            startActivity(viewIntent);
        });

        mainBinding.parseXml.setOnClickListener(v -> {
            viewIntent.putExtra("mode", 2);
            startActivity(viewIntent);
        });
    }
}