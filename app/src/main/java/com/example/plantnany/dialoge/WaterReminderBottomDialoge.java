package com.example.plantnany.dialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantnany.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class WaterReminderBottomDialoge extends BottomSheetDialogFragment {




    public WaterReminderBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_water_reminder_sheet, container, false);




        return view;
    }



}
