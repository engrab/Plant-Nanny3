package com.example.plantnany.bottomdialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantnany.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class DailyGoalBottomDialoge extends BottomSheetDialogFragment implements View.OnClickListener {

    LinearLayout mBodyWeight, mHowCalGoal;
    SeekBar mSeekBar;
    TextView mActivityLevel;

    public DailyGoalBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_daily_goal_sheet, container, false);
        mBodyWeight = view.findViewById(R.id.ll_body_weight);
        mActivityLevel = view.findViewById(R.id.tv_activity_level);
        mSeekBar = view.findViewById(R.id.seekBar);
        mHowCalGoal = view.findViewById(R.id.ll_how_calc_goal);
        mHowCalGoal.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress == 0) {
                    mActivityLevel.setText("Sedentary: Almost no exercise");
                }
                if (progress == 1) {
                    mActivityLevel.setText("Moderately Active: Exercise 2 - 5 hours a week");
                }
                if (progress == 2) {
                    mActivityLevel.setText("Vigorously Active: Exercise 5 - 7 hours a week");
                }
                if (progress == 3) {
                    mActivityLevel.setText("Extremely Active: Exercise more than 7 hour a week");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mBodyWeight.setOnClickListener(this);

        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        return view;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_body_weight:

                BodyWeightBottomDialoge bodyWeightBottomDialoge = new BodyWeightBottomDialoge();
                bodyWeightBottomDialoge.show(getChildFragmentManager(), "bodyweight");
                break;

            case R.id.ll_how_calc_goal:

                HowCalcGoalBottomDialoge howCalcGoalBottomDialoge = new HowCalcGoalBottomDialoge();
                howCalcGoalBottomDialoge.show(getChildFragmentManager(), "howcalgoal");
                break;
        }
    }
}
