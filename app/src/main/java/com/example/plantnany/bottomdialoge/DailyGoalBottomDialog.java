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
import com.example.plantnany.fragments.SettingsFragment;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.utils.AppUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class DailyGoalBottomDialog extends BottomSheetDialogFragment implements View.OnClickListener,
        BodyWeightBottomDialog.WeightListener {

    LinearLayout mBodyWeight, mHowCalGoal;
    SeekBar mSeekBar;
    TextView mActivityLevel;
    TextView mDailyGoal, mWeight;
    int exerciseTime = 2;
    int totalWeight;
    int targetWater;
    float kg;
    float lb;
    private TargetWaterListener targetWaterListener;


    public DailyGoalBottomDialog(SettingsFragment context) {

        if (context != null) {
            targetWaterListener = context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_daily_goal_sheet, container, false);
        initViews(view);


        mHowCalGoal.setOnClickListener(this);
        mBodyWeight.setOnClickListener(this);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress == 0) {
                    mActivityLevel.setText("Sedentary: Almost no exercise");
                    exerciseTime = 2;
                    updateUI();
                }
                if (progress == 1) {
                    mActivityLevel.setText("Moderately Active: Exercise 2 - 5 hours a week");
                    exerciseTime = 5;
                    updateUI();
                }
                if (progress == 2) {
                    mActivityLevel.setText("Vigorously Active: Exercise 5 - 7 hours a week");
                    exerciseTime = 7;
                    updateUI();
                }
                if (progress == 3) {
                    mActivityLevel.setText("Extremely Active: Exercise more than 7 hour a week");
                    exerciseTime = 10;
                    updateUI();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetWaterListener.targetWater(targetWater);
                dismiss();
            }
        });

        return view;
    }

    private void updateUI() {
        targetWater = AppUtils.calculateIntake(totalWeight, exerciseTime);
        mDailyGoal.setText(String.valueOf(targetWater));
        SharedPreferencesManager.getInstance(getActivity()).setTargetWater(targetWater);
        SharedPreferencesManager.getInstance(getActivity()).setExerciseTime(exerciseTime);
        int weight = SharedPreferencesManager.getInstance(getActivity()).getWeight();
        mWeight.setText(String.valueOf(weight));

    }

    private void initViews(View view) {
        mBodyWeight = view.findViewById(R.id.ll_body_weight);
        mWeight = view.findViewById(R.id.tv_total_weight);
        mDailyGoal = view.findViewById(R.id.tv_daily_target_water);
        mActivityLevel = view.findViewById(R.id.tv_activity_level);
        mSeekBar = view.findViewById(R.id.seekBar);
        mHowCalGoal = view.findViewById(R.id.ll_how_calc_goal);
    }

    @Override
    public void onResume() {
        super.onResume();
        totalWeight = SharedPreferencesManager.getInstance(getActivity()).getWeight();
        targetWater = SharedPreferencesManager.getInstance(getActivity()).getTargetWater();
        updateUI();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDailyGoal.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getTargetWater()));
        mWeight.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getWeight()));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_body_weight:

                BodyWeightBottomDialog bodyWeightBottomDialog = new BodyWeightBottomDialog(DailyGoalBottomDialog.this);
                bodyWeightBottomDialog.show(getChildFragmentManager(), "bodyweight");
                break;

            case R.id.ll_how_calc_goal:

                HowCalcGoalBottomDialoge howCalcGoalBottomDialoge = new HowCalcGoalBottomDialoge();
                howCalcGoalBottomDialoge.show(getChildFragmentManager(), "howcalgoal");
                break;
        }
    }

    @Override
    public void selectedWeight(int weight, int isKg) {

        if (isKg == 1) {
            totalWeight = weight;
            targetWater = AppUtils.calculateIntake(weight, SharedPreferencesManager.getInstance(getActivity()).getExerciseTime());
            SharedPreferencesManager.getInstance(getActivity()).setWeight(weight);
        } else {
            lb = (float) weight;
            kg = (float) (lb * 0.45359237);
            totalWeight = (int) kg;
            targetWater = AppUtils.calculateIntake(Math.round(kg), SharedPreferencesManager.getInstance(getActivity()).getExerciseTime());
            SharedPreferencesManager.getInstance(getActivity()).setWeight(Math.round(kg));
        }
        updateUI();
    }
    public interface TargetWaterListener{
        void targetWater(int targetWater);
    }
}
