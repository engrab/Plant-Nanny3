package com.example.plantnany.infoFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;


public class ActivityInfoFragment extends Fragment {


    public ActivityInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_activity_info, container, false);
        inflate.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StartActivity.viewPager.setCurrentItem(1);
            }
        });
        return inflate;
    }
}