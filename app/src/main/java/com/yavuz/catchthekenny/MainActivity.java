package com.yavuz.catchthekenny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void play(View view){

        mHandler.postDelayed(mLaunchTask,700);
    }

    public void stop(View view){

        finishAffinity();
        System.exit(0);
    }

    public void how(View view){

        Intent intent = new Intent(getApplicationContext(), how2PlayActivity.class);
        finish();
        startActivity(intent);
    }

    public void github(View view) {
        Uri uri = Uri.parse("https://github.com/YavuzAktas");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void linkedin(View view) {
        Uri uri = Uri.parse("https://www.linkedin.com/in/yavuznaktas/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            Intent i = new Intent(getApplicationContext(),gameActivity.class);
            finish();
            startActivity(i);
        }
    };
}