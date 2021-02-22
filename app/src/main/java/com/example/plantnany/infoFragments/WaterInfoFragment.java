package com.example.plantnany.infoFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.activities.SplashScreenActivity;
import com.example.plantnany.database.DataEntity;
import com.example.plantnany.database.DateConverter;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.viewmodels.FragmentViewModel;

import java.util.Date;


public class WaterInfoFragment extends Fragment implements View.OnClickListener {


    ImageView sedentary, moderateActive, vigorouslyActive, extemelyActive;
    TextView exerciseDesc;
    Button calculateGoal;
    Context mContext;
    int workHour = 0;
    ButtonClick buttonClick;
    private FragmentViewModel mViewModel;


    public WaterInfoFragment(Context mContext) {
        // Required empty public constructor
        this.mContext = mContext;
        buttonClick = new ButtonClick(mContext);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_water_info, container, false);

        initViewModel();
        exerciseDesc = inflate.findViewById(R.id.tv_exsercise_desc);
        sedentary = inflate.findViewById(R.id.iv_sedentary);
        moderateActive = inflate.findViewById(R.id.iv_moderate_active);
        vigorouslyActive = inflate.findViewById(R.id.iv_vigorously_active);
        extemelyActive = inflate.findViewById(R.id.iv_extremely_active);
        calculateGoal = inflate.findViewById(R.id.btn_calc_goal);

        sedentary.setOnClickListener(this);
        moderateActive.setOnClickListener(this);
        vigorouslyActive.setOnClickListener(this);
        extemelyActive.setOnClickListener(this);
        calculateGoal.setOnClickListener(this);
        return inflate;
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(requireActivity()).get(FragmentViewModel.class);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_sedentary:
                buttonClick.setOnsoundOnButtonClick();
                workHour = 2;
                exerciseDesc.setText("Sedentary: Almost no exercise");
                sedentary.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_moderate_active:
                buttonClick.setOnsoundOnButtonClick();
                workHour = 5;
                exerciseDesc.setText("Moderately Active: Exercise 2 - 5 hours a week");

                moderateActive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_vigorously_active:
                workHour = 7;
                exerciseDesc.setText("Vigorously Active: Exercise 5 - 7 hours a week");

                vigorouslyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_extremely_active:
                buttonClick.setOnsoundOnButtonClick();
                workHour = 10;
                exerciseDesc.setText("Extremely Active: Exercise more than 7 hour a week");

                extemelyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(mContext, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;

            case R.id.btn_calc_goal:
                buttonClick.setOnsoundOnButtonClick();

                if (workHour == 0) {
                    Toast.makeText(mContext, "Please Select any activity", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferencesManager.getInstance(getActivity()).setIsFirstTime(false);
                    int weight = SharedPreferencesManager.getInstance(getActivity()).getWeight();
                    int targetWater = calculateIntake(weight, workHour);
                    SharedPreferencesManager.getInstance(getActivity()).setTargetWater(targetWater);

                    mViewModel.insertData(new DataEntity(DateConverter.dateToString(new Date().getTime()), 0, targetWater,
                            SharedPreferencesManager.getInstance(getActivity()).getLevel(),
                            SharedPreferencesManager.getInstance(getActivity()).getPlantType(),
                            SharedPreferencesManager.getInstance(getActivity()).getIsTargetCompleted(),
                            SharedPreferencesManager.getInstance(getActivity()).getSeeds(),
                            SharedPreferencesManager.getInstance(getActivity()).getClover()));

                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
        }
    }


    public final int calculateIntake(int weight, int workTime) {
        return (int) ((((double) (weight * 100)) / 3.0d) + ((double) ((workTime / 6) * 7)));
    }


}