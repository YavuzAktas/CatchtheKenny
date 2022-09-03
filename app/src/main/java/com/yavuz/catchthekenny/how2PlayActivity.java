package com.yavuz.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class how2PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how2_play);
    }

    public void menu(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(intent);
    }


    public void play(View view) {
        Intent intent = new Intent(getApplicationContext(), gameActivity.class);
        finish();
        startActivity(intent);
    }
}