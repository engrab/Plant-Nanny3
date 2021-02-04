package com.example.plantnany.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.plantnany.ButtonCick;
import com.example.plantnany.R;
import com.example.plantnany.bottomdialoge.WaterReminderBottomDialoge;
import com.example.plantnany.fragments.AllPlantsFragment;
import com.example.plantnany.fragments.GraphFragment;
import com.example.plantnany.fragments.HomeFragment;
import com.example.plantnany.fragments.PotsFragment;
import com.example.plantnany.fragments.SettingsFragment;
import com.example.plantnany.helper.AlarmHelper;
import com.example.plantnany.services.MusicService;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , SettingsFragment.MusicPlayingListener, SettingsFragment.SoundClickListener,
        WaterReminderBottomDialoge.NotificationStatusListener, WaterReminderBottomDialoge.NotificationFrequencyListener {

    private static final String TAG = "MainActivity";
    ButtonCick buttonCick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setNotification();
        buttonCick = new ButtonCick(this);


        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void setNotification() {
        if (SharedPreferencesManager.getInstance(this).getNotificationStatus()){

            AlarmHelper alarm = new AlarmHelper();
            if (!alarm.checkAlarm(this)) {

                alarm.setAlarm(this, SharedPreferencesManager.getInstance(this).getNotificationFrequency());
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (SharedPreferencesManager.getInstance(this).getMusicPlay()) {
            startService(new Intent(this, MusicService.class));
        } else {
            stopService(new Intent(this, MusicService.class));
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                buttonCick.setOnsoundOnButtonClick();
                fragment = new HomeFragment(MainActivity.this);
                break;

            case R.id.navigation_pots:
                buttonCick.setOnsoundOnButtonClick();
                fragment = new PotsFragment();
                break;

            case R.id.navigation_all_plants:
                buttonCick.setOnsoundOnButtonClick();
                fragment = new AllPlantsFragment();
                break;

            case R.id.navigation_setting:
                buttonCick.setOnsoundOnButtonClick();
                fragment = new SettingsFragment();
                break;
            case R.id.navigation_graph:
                buttonCick.setOnsoundOnButtonClick();
                fragment = new GraphFragment();
                break;
            default:
                fragment = new HomeFragment(MainActivity.this);
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
        stopService(new Intent(this, MusicService.class));
        super.onStop();
    }


    @Override
    public void musicPlaying(boolean bool) {

        SharedPreferencesManager.getInstance(this).setIsMusicPlay(bool);
        if (SharedPreferencesManager.getInstance(this).getMusicPlay()) {
            startService(new Intent(this, MusicService.class));
        } else {
            stopService(new Intent(this, MusicService.class));
        }
    }

    @Override
    public void soundClick(boolean bool) {
        SharedPreferencesManager.getInstance(this).setButtonClickSound(bool);
    }

    @Override
    public void notificationStatus(boolean bool) {
        SharedPreferencesManager.getInstance(this).setNotificationStatus(bool);
        setNotification();
    }

    @Override
    public void notificationFrequency(int frequency) {
        SharedPreferencesManager.getInstance(this).setNotificationFrequency(frequency);
        setNotification();
    }
}