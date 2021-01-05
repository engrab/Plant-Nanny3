package com.example.plantnany.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.plantnany.R;
import com.example.plantnany.adapters.InfoStatePagerAdapter;
import com.example.plantnany.infoFragments.ActivityInfoFragment;
import com.example.plantnany.infoFragments.ReminderInfoFragment;
import com.example.plantnany.infoFragments.WaterInfoFragment;
import com.example.plantnany.infoFragments.WeightInfoFragment;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private InfoStatePagerAdapter adapter;
    private List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        viewPager = findViewById(R.id.info_view_pager);
        list = new ArrayList<>();
        list.add(new WaterInfoFragment());
        list.add(new ReminderInfoFragment());
        list.add(new WeightInfoFragment());
        list.add(new ActivityInfoFragment());

        adapter = new InfoStatePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }
}