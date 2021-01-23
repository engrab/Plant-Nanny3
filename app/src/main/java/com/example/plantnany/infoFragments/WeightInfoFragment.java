package com.example.plantnany.infoFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.plantnany.R;


public class WeightInfoFragment extends Fragment {


    double kg = 60;
    ImageView sub, add;
    TextView weight;
    RadioButton radKg, radLb;





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

        radKg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg--;

                if (radKg.isChecked()) {
                    weight.setText(String.valueOf(kg));
                } else  if (radLb.isChecked()){
                    double v1 = kg / 0.45359237;

                    weight.setText(String.valueOf((int) (v1)));
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kg++;
                if (radKg.isChecked()) {
                    weight.setText(String.valueOf(kg));
                } else if (radLb.isChecked()){
                    double v1 = kg / 0.45359237;

                    weight.setText(String.valueOf((int) (v1)));
                }
            }
        });
        return inflate;
    }
}