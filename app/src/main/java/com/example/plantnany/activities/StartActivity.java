package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.plantnany.R;
import com.example.plantnany.adapters.InfoStatePagerAdapter;
import com.example.plantnany.infoFragments.ActivityInfoFragment;
import com.example.plantnany.infoFragments.ReminderInfoFragment;
import com.example.plantnany.infoFragments.WaterInfoFragment;
import com.example.plantnany.infoFragments.WeightInfoFragment;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity implements WeightInfoFragment.WeightListener,
        WaterInfoFragment.TargetWaterListener {

    public static ViewPager viewPager;
    private InfoStatePagerAdapter adapter;
    private List<Fragment> list;
    public static final String CALCULATE_GOAL_KEY = "Calculate_goal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_start);

        viewPager = findViewById(R.id.info_view_pager);

        list = new ArrayList<>();
        list.add(new ActivityInfoFragment());
        list.add(new ReminderInfoFragment());
        list.add(new WeightInfoFragment());
        list.add(new WaterInfoFragment());

        adapter = new InfoStatePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void weight(double weight) {
        SharedPreferencesManager.getInstance(this).setWeight(weight);
    }

    @Override
    public void targetWater(double water) {
        SharedPreferencesManager.getInstance(this).setTargetWater(water);
    }
}