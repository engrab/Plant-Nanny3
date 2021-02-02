package com.example.plantnany.dialoge;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.adapters.BodyWeightAdapter;
import com.example.plantnany.adapters.CustomTimeAdapter;
import com.example.plantnany.model.TimeModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CustomReminderBottomDialoge extends BottomSheetDialogFragment {

    ImageView addReminder;
    RecyclerView recyclerViewTime;
    List<TimeModel> mList;
    int hour;
    int mint;
    boolean isEnable;



    public CustomReminderBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_custom_reminder_sheet, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addReminder = view.findViewById(R.id.iv_add_reminder);
        recyclerViewTime = view.findViewById(R.id.recycler_view_time);
        addReminder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                final int[] hour = {mcurrentTime.get(Calendar.HOUR_OF_DAY)};
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hour[0] = hourOfDay;
                        mint = minute;

                    }

                }, hour[0], minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        timeList();
        setAdapterInRecycler();
    }

    private void setAdapterInRecycler() {
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTime.setAdapter(new CustomTimeAdapter(getActivity(), mList));
    }

    private void timeList() {
        mList.add(new TimeModel(hour, mint, true));
    }
}
