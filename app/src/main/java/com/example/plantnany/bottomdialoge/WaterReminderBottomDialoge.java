package com.example.plantnany.bottomdialoge;


import android.content.Context;
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
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WaterReminderBottomDialoge extends BottomSheetDialogFragment {

    SwitchCompat mReminder;
    TextView reminderDesc;
    ImageView cutomReminder;
    private NotificationStatusListener notificationStatusListener;
    private NotificationFrequencyListener notificationFrequencyListener;
    RadioGroup mRadioGroupDefaultTime;
    RadioButton radTwoHour, radFourHour, radSixHour;

    public WaterReminderBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_water_reminder_sheet, container, false);

        mReminder = view.findViewById(R.id.sc_reminder);
        reminderDesc = view.findViewById(R.id.tv_reminder_desc);
        mRadioGroupDefaultTime = view.findViewById(R.id.rad_times);

        radTwoHour = view.findViewById(R.id.rad_two_hour);
        radFourHour = view.findViewById(R.id.rad_four_hour);
        radSixHour = view.findViewById(R.id.rad_six_hour);

        int notificationFrequency = SharedPreferencesManager.getInstance(getActivity()).getNotificationFrequency();
        if (notificationFrequency==120){
            radTwoHour.setChecked(true);
        }else if (notificationFrequency == 240){
            radFourHour.setChecked(true);
        }else if (notificationFrequency == 360){
            radSixHour.setChecked(true);
        }

        mRadioGroupDefaultTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (group.getId()) {

                    case R.id.rad_two_hour:
                        notificationFrequencyListener.notificationFrequency(120);

                        break;
                    case R.id.rad_four_hour:
                        notificationFrequencyListener.notificationFrequency(240);
                        break;
                    case R.id.rad_six_hour:
                        notificationFrequencyListener.notificationFrequency(360);
                        break;
                }
            }
        });
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
                notificationStatusListener.notificationStatus(isChecked);

                if (isChecked) {
                    view.findViewById(R.id.rad_times).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.rad_times).setVisibility(View.GONE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.GONE);
                    reminderDesc.setText("Plant Apps will send you notifications to remind you to drink water!");
                }

            }
        });


        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NotificationStatusListener) {
            notificationStatusListener = (NotificationStatusListener) context;
        }
        if (context instanceof NotificationFrequencyListener) {
            notificationFrequencyListener = (NotificationFrequencyListener) context;
        }
    }

    public interface NotificationStatusListener {
        void notificationStatus(boolean bool);
    }

    public interface NotificationFrequencyListener {
        void notificationFrequency(int frequency);
    }


}
