package com.example.plantnany.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.plantnany.R;
import com.example.plantnany.fragments.AllPlantsFragment;
import com.example.plantnany.fragments.GraphFragment;
import com.example.plantnany.fragments.HomeFragment;
import com.example.plantnany.fragments.PotsFragment;
import com.example.plantnany.fragments.SettingsFragment;
import com.example.plantnany.helper.AlarmHelper;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static com.example.plantnany.activities.SplashScreenActivity.KEY_MUSIC;
import static com.example.plantnany.activities.SplashScreenActivity.PLAY_MUSIC;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , SettingsFragment.MusicPlayingListener {

    private static final String TAG = "MainActivity";

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNotification();

        SharedPreferences sharedPreferences = getSharedPreferences(PLAY_MUSIC, MODE_PRIVATE);
        playMusic(sharedPreferences.getBoolean(KEY_MUSIC, true));


        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void setNotification() {
        boolean notificationStatus = SharedPreferencesManager.getInstance(this).getNotificationStatus();
        AlarmHelper alarm = new AlarmHelper();
        if (!alarm.checkAlarm(this) && notificationStatus) {

            alarm.setAlarm(this, SharedPreferencesManager.getInstance(this).getNotificationFrequency());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void playMusic(boolean bool) {


        if (bool) {
            Log.d(TAG, "playMusic: " + bool);
            mediaPlayer = MediaPlayer.create(this, R.raw.sound1);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else {
            Log.d(TAG, "playMusic: " + bool);
            if (mediaPlayer != null) {

                mediaPlayer.stop();
            } else {
                Log.d(TAG, "playMusic: stopd");
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_pots:
                fragment = new PotsFragment();
                break;

            case R.id.navigation_all_plants:
                fragment = new AllPlantsFragment();
                break;

            case R.id.navigation_setting:
                fragment = new SettingsFragment();
                break;
            case R.id.navigation_graph:
                fragment = new GraphFragment();
                break;
            default:
                fragment = new HomeFragment();
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        mediaPlayer.stop();
        super.onStop();

    }

    @Override
    public void musicPlaying(boolean bool) {

        SharedPreferences.Editor editor = getSharedPreferences(PLAY_MUSIC, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_MUSIC, bool);
        editor.apply();
        playMusic(bool);
    }
}