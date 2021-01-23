package com.example.plantnany.infoFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;


public class ReminderInfoFragment extends Fragment {



    public ReminderInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_reminder_info, container, false);

        inflate.findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.viewPager.setCurrentItem(2);
            }
        });
        inflate.findViewById(R.id.btn_remind_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.viewPager.setCurrentItem(2);
            }
        });
        return inflate;
    }
}