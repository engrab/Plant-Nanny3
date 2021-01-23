package com.example.plantnany.infoFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;


public class WaterInfoFragment extends Fragment implements View.OnClickListener {


    ImageView sedentary, moderateActive, vigorouslyActive, extemelyActive;
    TextView exerciseDesc;
    Button calculateGoal;
    Context context;

    public WaterInfoFragment() {
        // Required empty public constructor
        context = getActivity();
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

        sedentary.setOnClickListener(this::onClick);
        moderateActive.setOnClickListener(this::onClick);
        vigorouslyActive.setOnClickListener(this::onClick);
        extemelyActive.setOnClickListener(this::onClick);
        calculateGoal.setOnClickListener(this::onClick);
        return inflate;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_sedentary:
                exerciseDesc.setText("Sedentary: Almost no exercise");
                sedentary.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_moderate_active:
                exerciseDesc.setText("Moderately Active: Exercise 2 - 5 hours a week");

                moderateActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_vigorously_active:
                exerciseDesc.setText("Vigorously Active: Exercise 5 - 7 hours a week");

                vigorouslyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                extemelyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;
            case R.id.iv_extremely_active:
                exerciseDesc.setText("Extremely Active: Exercise more than 7 hour a week");

                extemelyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorGray), android.graphics.PorterDuff.Mode.MULTIPLY);
                moderateActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                vigorouslyActive.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
                sedentary.setColorFilter(ContextCompat.getColor(getActivity(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);

                break;

            case R.id.btn_calc_goal:

                SharedPreferences.Editor editor = this.getActivity().getSharedPreferences("SharedPref",Context.MODE_PRIVATE).edit();
                editor.putBoolean("intro", true);
                editor.apply();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
        }
    }
}