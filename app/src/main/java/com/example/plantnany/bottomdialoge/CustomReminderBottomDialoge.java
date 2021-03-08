package com.example.plantnany.bottomdialoge;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
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
import com.example.plantnany.adapters.CustomTimeAdapter;
import com.example.plantnany.model.TimeModel;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.plantnany.sharedpref.SharedPreferencesManager.*;

public class CustomReminderBottomDialoge extends BottomSheetDialogFragment {

    ImageView addReminder;
    RecyclerView recyclerViewTime;
    List<TimeModel> mList;
    int hour = 8;
    int mint = 0;
    TimePickerDialog timePickerDialog;
    CustomTimeAdapter customTimeAdapter;


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
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        addReminder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        hour = hourOfDay;
                        mint = minute;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(0, 0, 0, hour, mint);

//                        time.setText(DateFormat.format("hh:mm aa", calendar));
                        mList.add(new TimeModel(hour, mint));
                        customTimeAdapter.notifyDataSetChanged();
                        getInstance(getActivity()).setSafeReminderTime(mList);


                    }
                }, 12, 0, false);
                timePickerDialog.show();

            }
        });

        timeList();
        setAdapterInRecycler();
    }

    private void setAdapterInRecycler() {
        customTimeAdapter = new CustomTimeAdapter(getActivity(), mList);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerViewTime.setAdapter(customTimeAdapter);
    }

    private void timeList() {
        mList = new ArrayList<>();
        mList = SharedPreferencesManager.getInstance(getActivity()).getSafeReminderTime();
    }
}
