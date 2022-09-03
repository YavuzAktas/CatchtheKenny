package com.yavuz.catchthekenny;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class gameActivity extends AppCompatActivity {

    TextView scoreText;
    TextView bestScoreText;
    TextView timeText;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Button button;
    Button button2;

    SharedPreferences sharedPreferences;
    Handler handler;
    Runnable runnable;
    int userScore, lastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreText = findViewById(R.id.scoreText);
        bestScoreText = findViewById(R.id.bestScoreText);
        timeText = findViewById(R.id.timeText);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        sharedPreferences = this.getSharedPreferences("com.yavuz.catchthekenny", Context.MODE_PRIVATE);
        imageArray =new ImageView[]{imageView, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9};

        userScore = 0;
        lastScore = sharedPreferences.getInt("userScore", 0);
        bestScoreText.setText("Last Score : " + lastScore);
        Toast.makeText(gameActivity.this,"Good luck ;)", Toast.LENGTH_LONG).show();
        hideImage();


        new CountDownTimer(10000, 1000){

            @Override
            public void onTick(long l) {
                timeText.setText("Time : " + l/1000);
                button.setEnabled(false);
                button2.setEnabled(false);
            }

            @Override
            public void onFinish() {

                button.setEnabled(true);
                button2.setEnabled(true);
                timeText.setText("Time's Up!");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                imageView5.setVisibility(View.VISIBLE);
                imageView5.setEnabled(false);

                AlertDialog.Builder alert = new AlertDialog.Builder(gameActivity.this);
                alert.setTitle("Game is over!");
                alert.setMessage("Your score : " + userScore);
                alert.setCancelable(false);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alert.show();

            }

        }.start();

    }

    public void score(View view){

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click_sound);
        mediaPlayer.start();
        userScore++;
        scoreText.setText("Score : " + userScore);
        sharedPreferences.edit().putInt("userScore", userScore).apply();
    }

    public void hideImage(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,600);
            }
        };
        handler.post(runnable);
    }

    public void menu(View view){

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("userScore", lastScore);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void restart(View view){

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {

                Toast.makeText(gameActivity.this, "The game starts after " + l/1000 + " seconds", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFinish() {

                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }

        }.start();

    }

}