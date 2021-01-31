package com.example.plantnany.infoFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;


public class WeightInfoFragment extends Fragment {


    double kg = 60;
    double lb = 132;
    ImageView sub, add;
    TextView weight;
    RadioButton radKg, radLb;
    RadioGroup mRadGroup;


    public WeightInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_weight_info, container, false);
        sub = inflate.findViewById(R.id.iv_sub);
        add = inflate.findViewById(R.id.iv_add);
        weight = inflate.findViewById(R.id.iv_weight);
        radKg = inflate.findViewById(R.id.rad_kg);
        radLb = inflate.findViewById(R.id.rad_lb);
        mRadGroup = inflate.findViewById(R.id.radio_group_weight);

        mRadGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rad_kg:

                        kg = lb * 0.45359237;
                        weight.setText(String.valueOf(Math.round(kg)));
                        break;

                    case R.id.rad_lb:
                        lb = kg / 0.45359237;
                        weight.setText(String.valueOf(Math.round(lb)));
                        break;

                }
            }
        });

        radKg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (radKg.isChecked()) {
                    kg--;
                    weight.setText(String.valueOf(Math.round(kg)));
                } else if (radLb.isChecked()) {

                    lb = lb - 2;
                    weight.setText(String.valueOf(Math.round(lb)));
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (radKg.isChecked()) {
                    kg++;
                    weight.setText(String.valueOf(Math.round(kg)));
                } else if (radLb.isChecked()) {
                    lb = lb + 2;
                    weight.setText(String.valueOf(Math.round(lb)));
                }
            }
        });
        inflate.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivity.viewPager.setCurrentItem(3);
            }
        });
        return inflate;
    }
}