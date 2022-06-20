package com.madlab.texttospeech;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.madlab.texttospeech.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        TextToSpeech textToSpeech = new TextToSpeech(getBaseContext(), status -> {
            if (status != TextToSpeech.ERROR) {
                Toast.makeText(getBaseContext(),
                        "Success",
                        Toast.LENGTH_LONG).show();
            }
        });

        mainBinding.speakButton.setOnClickListener(v -> {
            String text = mainBinding.inputText.getText().toString();
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        });
    }
}