package com.example.plantnany.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.plantnany.ButtonClick;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , SettingsFragment.MusicPlayingListener,
        WaterReminderBottomDialoge.NotificationStatusListener, WaterReminderBottomDialoge.NotificationFrequencyListener {

    ButtonClick buttonClick;
    public static BottomNavigationView navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        setNotification();
        buttonClick = new ButtonClick(this);


        //getting bottom navigation view and attaching the listener

        navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            Fragment fragment = new HomeFragment(MainActivity.this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

    private void setNotification() {
        if (SharedPreferencesManager.getInstance(this).getNotificationStatus()) {

            AlarmHelper alarm = new AlarmHelper();
            if (!alarm.checkAlarm(this)) {

                alarm.setAlarm(this, SharedPreferencesManager.getInstance(this).getNotificationFrequency());
            }
        }

    }

    @Override
    protected void onResume() {

        super.onResume();

//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

        if (SharedPreferencesManager.getInstance(this).getMusicPlay()) {
            startService(new Intent(this, MusicService.class));
        } else {
            stopService(new Intent(this, MusicService.class));
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                buttonClick.setOnsoundOnButtonClick();
                fragment = new HomeFragment(this);

                break;

            case R.id.navigation_pots:
                buttonClick.setOnsoundOnButtonClick();
                fragment = new PotsFragment(this);

                break;

            case R.id.navigation_all_plants:
                buttonClick.setOnsoundOnButtonClick();
                fragment = new AllPlantsFragment(this);

                break;

            case R.id.navigation_setting:
                buttonClick.setOnsoundOnButtonClick();
                fragment = new SettingsFragment(this);

                break;
            case R.id.navigation_graph:
                buttonClick.setOnsoundOnButtonClick();
                fragment = new GraphFragment(this);

                break;

        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commit();

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