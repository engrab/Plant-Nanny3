package com.example.plantnany.infoFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;


public class ReminderInfoFragment extends Fragment {

    ButtonClick buttonClick;
    Context mContext;

    public ReminderInfoFragment(Context context) {
        // Required empty public constructor
        mContext = context;
        buttonClick = new ButtonClick(mContext);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_reminder_info, container, false);

        inflate.findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick.setOnsoundOnButtonClick();
                StartActivity.viewPager.setCurrentItem(2);
            }
        });
        inflate.findViewById(R.id.btn_remind_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClick.setOnsoundOnButtonClick();
                StartActivity.viewPager.setCurrentItem(2);

            }
        });
        return inflate;
    }
}