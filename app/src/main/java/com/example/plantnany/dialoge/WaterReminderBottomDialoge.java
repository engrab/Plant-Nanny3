package com.example.plantnany.dialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.plantnany.R;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WaterReminderBottomDialoge extends BottomSheetDialogFragment {

    SwitchCompat mReminder;
    TextView reminderDesc;
    ImageView cutomReminder;


    public WaterReminderBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_water_reminder_sheet, container, false);

        mReminder = view.findViewById(R.id.sc_reminder);
        reminderDesc = view.findViewById(R.id.tv_reminder_desc);
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cutomReminder = view.findViewById(R.id.iv_custom_reminder);
        cutomReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomReminderBottomDialoge customReminderBottomDialoge = new CustomReminderBottomDialoge();
                customReminderBottomDialoge.show(getChildFragmentManager(), "customreminder");
            }
        });
        mReminder.setChecked(SharedPreferencesManager.getInstance(getActivity()).getNotificationStatus());

        mReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferencesManager.getInstance(getActivity()).setNotificationStatus(true);
                    view.findViewById(R.id.rad_times).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.rad_times).setVisibility(View.GONE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.GONE);
                    reminderDesc.setText("Plannt Apps will send you notifications to remind you to drink water!");
                }
                switch (buttonView.getId()) {

                    case R.id.rad_two_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(120);
                        break;
                    case R.id.rad_four_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(240);
                        break;
                    case R.id.rad_six_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(630);
                        break;
                }
            }
        });


        return view;
    }


}
