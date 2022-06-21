package com.madlab.dialerapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.madlab.dialerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.clearText.setOnClickListener(this);
        mainBinding.buttonOne.setOnClickListener(this);
        mainBinding.buttonTwo.setOnClickListener(this);
        mainBinding.buttonThree.setOnClickListener(this);
        mainBinding.buttonFour.setOnClickListener(this);
        mainBinding.buttonFive.setOnClickListener(this);
        mainBinding.buttonSix.setOnClickListener(this);
        mainBinding.buttonSeven.setOnClickListener(this);
        mainBinding.buttonEight.setOnClickListener(this);
        mainBinding.buttonNine.setOnClickListener(this);
        mainBinding.buttonStar.setOnClickListener(this);
        mainBinding.buttonZero.setOnClickListener(this);
        mainBinding.buttonHash.setOnClickListener(this);
        mainBinding.callButton.setOnClickListener(this);
        mainBinding.saveButton.setOnClickListener(this);

        mainBinding.phoneNumber.setText("");
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mainBinding.buttonOne)) {
            mainBinding.phoneNumber.append("1");
        } else if (v.equals(mainBinding.buttonTwo)) {
            mainBinding.phoneNumber.append("2");
        } else if (v.equals(mainBinding.buttonThree)) {
            mainBinding.phoneNumber.append("3");
        } else if (v.equals(mainBinding.buttonFour)) {
            mainBinding.phoneNumber.append("4");
        } else if (v.equals(mainBinding.buttonFive)) {
            mainBinding.phoneNumber.append("5");
        } else if (v.equals(mainBinding.buttonSix)) {
            mainBinding.phoneNumber.append("6");
        } else if (v.equals(mainBinding.buttonSeven)) {
            mainBinding.phoneNumber.append("7");
        } else if (v.equals(mainBinding.buttonEight)) {
            mainBinding.phoneNumber.append("8");
        } else if (v.equals(mainBinding.buttonNine)) {
            mainBinding.phoneNumber.append("9");
        } else if (v.equals(mainBinding.buttonZero)) {
            mainBinding.phoneNumber.append("0");
        } else if (v.equals(mainBinding.buttonStar)) {
            mainBinding.phoneNumber.append("*");
        } else if (v.equals(mainBinding.buttonHash)) {
            mainBinding.phoneNumber.append("#");
        } else if (v.equals(mainBinding.saveButton)) {
            Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
            contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, "Unknown");
            contactIntent.putExtra(ContactsContract.Intents.Insert.PHONE, mainBinding.phoneNumber.getText().toString());
            startActivity(contactIntent);
        } else if (v.equals(mainBinding.callButton)) {
            String data = mainBinding.phoneNumber.getText().toString();
            Intent it = new Intent(Intent.ACTION_CALL);
            it.setData(Uri.parse("tel:" + data));
            startActivity(it);
        } else if (v.equals(mainBinding.clearText)) {
            String data = mainBinding.phoneNumber.getText().toString();
            if (data.length() > 0) {
                mainBinding.phoneNumber.setText(data.substring(0, data.length() - 1));
            } else {
                mainBinding.phoneNumber.setText("");
            }
        }
    }
}