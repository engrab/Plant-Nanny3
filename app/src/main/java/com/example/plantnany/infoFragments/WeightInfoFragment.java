package com.example.plantnany.infoFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.activities.StartActivity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;


public class WeightInfoFragment extends Fragment {


    double kg = 60;
    double lb = 132;
    ImageView sub, add;
    TextView weight;
    RadioButton radKg, radLb;
    RadioGroup mRadGroup;
    ButtonClick buttonClick;
    Context mContext;
    boolean isKg = true;


    public WeightInfoFragment(Context context) {
        // Required empty public constructor
        mContext = context;
        buttonClick = new ButtonClick(mContext);

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

                        isKg = true;
                        buttonClick.setOnsoundOnButtonClick();
                        kg = lb * 0.45359237;
                        weight.setText(String.valueOf(Math.round(kg)));
                        break;

                    case R.id.rad_lb:

                        isKg = false;
                        buttonClick.setOnsoundOnButtonClick();
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
                buttonClick.setOnsoundOnButtonClick();

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
                buttonClick.setOnsoundOnButtonClick();
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
                buttonClick.setOnsoundOnButtonClick();
                String weight = WeightInfoFragment.this.weight.getText().toString();
                if (isKg){

                    SharedPreferencesManager.getInstance(getActivity()).setWeight((int) Double.parseDouble(weight));
                    SharedPreferencesManager.getInstance(getActivity()).setIsKgChecked(true);

                }else {
                    lb = Double.parseDouble(weight);
                    kg = lb * 0.45359237;
                    SharedPreferencesManager.getInstance(getActivity()).setIsKgChecked(false);
                    SharedPreferencesManager.getInstance(getActivity()).setWeight((int) Double.parseDouble(String.valueOf(kg)));

                }
                StartActivity.viewPager.setCurrentItem(3);
            }
        });
        return inflate;
    }


}