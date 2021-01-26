package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.plantnany.R;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class SplashScreenActivity extends AppCompatActivity {


    public static final String PLAY_MUSIC = "play_music";
    public static final String KEY_MUSIC = "key_music";
    private static final String TAG = "SplashScreenActivity";
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        setContentView(R.layout.activity_splash_screen);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        playMusic();


    }

    private void playMusic() {


        SharedPreferences sharedPreferences = getSharedPreferences(PLAY_MUSIC, MODE_PRIVATE);
        boolean isFirstTime = sharedPreferences.getBoolean(KEY_MUSIC, true);

        if (isFirstTime) {

            mediaPlayer = MediaPlayer.create(this, R.raw.sound1);
            mediaPlayer.start();
        } else {
            Log.d(TAG, "playMusic: false");
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE);
        boolean intro = sharedPreferences.getBoolean("intro", false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (intro) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(SplashScreenActivity.this, StartActivity.class));
                }
            }
        }, 7000);
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}