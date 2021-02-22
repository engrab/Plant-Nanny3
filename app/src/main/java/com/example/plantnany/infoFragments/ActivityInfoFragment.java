package com.example.plantnany.infoFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;


public class ActivityInfoFragment extends Fragment {


    ButtonClick buttonClick;
    Context mContext;

    public ActivityInfoFragment(Context context) {
        // Required empty public constructor
        mContext = context;
        buttonClick = new ButtonClick(mContext);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_activity_info, container, false);
        inflate.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick.setOnsoundOnButtonClick();
                StartActivity.viewPager.setCurrentItem(1);
            }
        });
        return inflate;
    }
}