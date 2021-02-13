package com.example.plantnany.bottomdialoge;


import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class WaterReminderBottomDialoge extends BottomSheetDialogFragment {

    SwitchCompat mReminder;
    TextView reminderDesc, reminderStatment;
    ImageView cutomReminder;
    private NotificationStatusListener notificationStatusListener;
    private NotificationFrequencyListener notificationFrequencyListener;
    RadioGroup mRadioGroupDefaultTime;
    RadioButton radTwoHour, radFourHour, radSixHour;
    LinearLayout customTime;
    int hour, mint;

    public WaterReminderBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_water_reminder_sheet, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mReminder = view.findViewById(R.id.sc_reminder);
        reminderDesc = view.findViewById(R.id.tv_reminder_desc);
        reminderStatment = view.findViewById(R.id.tv_reminder_statement);
        mRadioGroupDefaultTime = view.findViewById(R.id.rad_times);
        customTime = view.findViewById(R.id.ll_custome);



        radTwoHour = view.findViewById(R.id.rad_two_hour);
        radFourHour = view.findViewById(R.id.rad_four_hour);
        radSixHour = view.findViewById(R.id.rad_six_hour);

        int notificationFrequency = SharedPreferencesManager.getInstance(getActivity()).getNotificationFrequency();
        if (notificationFrequency == 120) {
            radTwoHour.setChecked(true);
            radFourHour.setChecked(false);
            radSixHour.setChecked(false);
        } else if (notificationFrequency == 240) {
            radTwoHour.setChecked(false);
            radFourHour.setChecked(true);
            radSixHour.setChecked(false);
        } else if (notificationFrequency == 360) {
            radTwoHour.setChecked(false);
            radFourHour.setChecked(false);
            radSixHour.setChecked(true);
        }

        customTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomReminderBottomDialoge customReminderBottomDialoge = new CustomReminderBottomDialoge();
                customReminderBottomDialoge.show(getChildFragmentManager(), "customreminder");
            }

        });

        mRadioGroupDefaultTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (group.getId()) {

                    case R.id.rad_two_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(120);
//                        notificationFrequencyListener.notificationFrequency(120);

                        break;
                    case R.id.rad_four_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(240);

//                        notificationFrequencyListener.notificationFrequency(240);
                        break;
                    case R.id.rad_six_hour:
                        SharedPreferencesManager.getInstance(getActivity()).setNotificationFrequency(360);

//                        notificationFrequencyListener.notificationFrequency(360);
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


        boolean notificationStatus = SharedPreferencesManager.getInstance(getActivity()).getNotificationStatus();
        mReminder.setChecked(notificationStatus);

        if (notificationStatus) {
            view.findViewById(R.id.rad_times).setVisibility(View.VISIBLE);
            view.findViewById(R.id.ll_custome).setVisibility(View.VISIBLE);
            reminderDesc.setText("Set custom reminders for specific times throughtout the day.");
            reminderStatment.setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.rad_times).setVisibility(View.INVISIBLE);
            view.findViewById(R.id.ll_custome).setVisibility(View.INVISIBLE);
            reminderDesc.setText("Plant Apps will send you notifications to remind you to drink water!");
            reminderStatment.setVisibility(View.INVISIBLE);
        }

        mReminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                notificationStatusListener.notificationStatus(isChecked);

                if (isChecked) {
                    view.findViewById(R.id.rad_times).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.VISIBLE);
                    reminderDesc.setText("Set custom reminders for specific times throughtout the day.");
                    reminderStatment.setVisibility(View.VISIBLE);
                } else {
                    view.findViewById(R.id.rad_times).setVisibility(View.INVISIBLE);
                    view.findViewById(R.id.ll_custome).setVisibility(View.INVISIBLE);
                    reminderDesc.setText("Plant Apps will send you notifications to remind you to drink water!");
                    reminderStatment.setVisibility(View.INVISIBLE);
                }

            }
        });
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
