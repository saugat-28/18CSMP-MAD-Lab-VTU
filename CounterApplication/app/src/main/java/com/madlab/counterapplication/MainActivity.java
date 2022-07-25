package com.madlab.counterapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.madlab.counterapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    int counter = 0;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.startButton.setOnClickListener(v -> {
                counter = 0;
                isRunning = true;
                new Counter().start();
                mainBinding.startButton.setEnabled(false);
                mainBinding.stopButton.setEnabled(true);

        });

        mainBinding.stopButton.setOnClickListener(v -> {
                isRunning = false;
                mainBinding.startButton.setEnabled(true);
                mainBinding.stopButton.setEnabled(false);
        });
    }


    class Counter extends Thread {
        public void run() {
            while (isRunning) {
                counter++;
                runOnUiThread(() -> mainBinding.counterText.setText(String.valueOf(counter)));
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// If handler is to be used, replace runOnUiThread with the following commented code.
/*
 new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
       @Override
       public void run() {
           mainBinding.counterText.setText(String.valueOf(counter));
       }
 }, 0);
 */
