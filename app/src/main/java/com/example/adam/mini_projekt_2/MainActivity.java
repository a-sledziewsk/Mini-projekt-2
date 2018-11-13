package com.example.adam.mini_projekt_2;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToListClick(View view){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
    public void exitClick(View view){
        finish();
        System.exit(0);
    }


    }

