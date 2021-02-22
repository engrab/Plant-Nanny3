package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.example.plantnany.R;
import com.example.plantnany.database.DataEntity;
import com.example.plantnany.database.DateConverter;
import com.example.plantnany.databinding.ActivitySplashScreenBinding;
import com.example.plantnany.services.MusicService;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.viewmodels.FragmentViewModel;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Date;

public class SplashScreenActivity extends AppCompatActivity {


    private static final String TAG = "SplashScreenActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActivitySplashScreenBinding binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        playMusic();


    }

    private void playMusic() {


        if (SharedPreferencesManager.getInstance(this).getMusicPlay()) {

            startService(new Intent(this, MusicService.class));
        } else {
            Log.d(TAG, "playMusic: false");
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        boolean isFirstTime = SharedPreferencesManager.getInstance(SplashScreenActivity.this).getIsFirstTime();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isFirstTime) {

                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                } else {

                    startActivity(new Intent(SplashScreenActivity.this, StartActivity.class));
                }
                finish();
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

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}