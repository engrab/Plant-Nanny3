package com.example.plantnany.infoFragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;


public class WaterInfoFragment extends Fragment implements View.OnClickListener {


    ImageView sedentary, moderateActive, vigorouslyActive, extemelyActive;
    TextView exerciseDesc;
    Button calculateGoal;
    Context context;
    int workHour = 0;
    private TargetWaterListener targetWaterListener;
    private IntroListener introListener;

    public WaterInfoFragment(Context context) {
        // Required empty public constructor
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_water_info, container, false);

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_sedentary:
                workHour = 2;
                exerciseDesc.setText("Sedentary: Almost no exercise");
                sedentary.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_moderate_active:
                workHour = 5;
                exerciseDesc.setText("Moderately Active: Exercise 2 - 5 hours a week");

                moderateActive.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_vigorously_active:
                workHour = 7;
                exerciseDesc.setText("Vigorously Active: Exercise 5 - 7 hours a week");

                vigorouslyActive.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_extremely_active:
                workHour = 10;
                exerciseDesc.setText("Extremely Active: Exercise more than 7 hour a week");

                extemelyActive.setColorFilter(ContextCompat.getColor(context, R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;

            case R.id.btn_calc_goal:

                if (workHour == 0) {
                    Toast.makeText(context, "Please Select any activity", Toast.LENGTH_SHORT).show();
                } else {

                    introListener.isFirstTime(false);
                    double targetWater = calculateIntake(SharedPreferencesManager.getInstance(getActivity()).getWeight(), workHour);
                    targetWaterListener.targetWater(targetWater);
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TargetWaterListener) {
            targetWaterListener = (TargetWaterListener) context;
        }
        if (context instanceof IntroListener){
            introListener = (IntroListener) context;
        }

    }

    public final double calculateIntake(float weight, int workTime) {
        return (((double) (weight * 100)) / 3.0d) + ((double) ((workTime / 6) * 7));
    }

    public interface TargetWaterListener {
        void targetWater(double water);
    }
    public interface IntroListener{
        void isFirstTime(boolean isFirst);
    }
}