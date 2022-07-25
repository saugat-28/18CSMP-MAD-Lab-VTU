package com.example.wallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.example.wallpapers.databinding.ActivityMainBinding;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    boolean running;
    int[] imageArray = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        running = false;

        mainBinding.changeButton.setOnClickListener(v -> {
            if(!running){
                changeWallpaper();
            }
            running = !running;
        });
    }

    private void changeWallpaper(){
        new Timer().schedule(new WallpaperTimerTask(), 0, 1000);
    }

    class WallpaperTimerTask extends TimerTask {
        @Override
        public void run() {
            try {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
                Random random = new Random();

                wallpaperManager.setBitmap(BitmapFactory.decodeResource(getResources(), imageArray[random.nextInt(5)]));
            } catch (Exception e){
               e.printStackTrace();
            }
        }
    }

}