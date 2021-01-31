package com.example.plantnany.dialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantnany.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static com.example.plantnany.fragments.SettingsFragment.setLangName;

public class CupVolumeBottomDialoge extends BottomSheetDialogFragment {


    RadioButton mRadml, mRadoz;
    RadioGroup mRadmloz;
    TextView mDefaultCup, mDefaultUnit, mSmallCup, mSmallUnit, mMediumCup, mMediumUnit, mLargeCup, mLargeUnit;

    double defaultCup;
    double smallCup;
    double mediumCup;
    double largeCup;

    public CupVolumeBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_cup_volume_sheet, container, false);

        mRadml = view.findViewById(R.id.rad_ml);
        mRadoz = view.findViewById(R.id.rad_oz);
        mRadmloz = view.findViewById(R.id.radg_ml_oz);

        mDefaultCup = view.findViewById(R.id.tv_default_cup);
        mDefaultUnit = view.findViewById(R.id.tv_defaul_unit);
        mSmallCup = view.findViewById(R.id.tv_small_cup);
        mSmallUnit = view.findViewById(R.id.tv_small_unit);
        mMediumCup = view.findViewById(R.id.tv_medium_cup);
        mMediumUnit = view.findViewById(R.id.tv_medium_unit);
        mLargeCup = view.findViewById(R.id.tv_large_cup);
        mLargeUnit = view.findViewById(R.id.tv_large_unit);

        mRadmloz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rad_ml:

                        mDefaultUnit.setText("ml");
                        mSmallUnit.setText("ml");
                        mMediumUnit.setText("ml");
                        mLargeUnit.setText("ml");



                        defaultCup = getML(Double.parseDouble(mDefaultCup.getText().toString()));
                        smallCup = getML(Double.parseDouble(mSmallCup.getText().toString()));
                        mediumCup = getML(Double.parseDouble(mMediumCup.getText().toString()));
                        largeCup = getML(Double.parseDouble(mLargeCup.getText().toString()));

                        mDefaultCup.setText(String.valueOf(Math.round(defaultCup)));
                        mSmallCup.setText(String.valueOf(Math.round(smallCup)));
                        mMediumCup.setText(String.valueOf(Math.round(mediumCup)));
                        mLargeCup.setText(String.valueOf(Math.round(largeCup)));

                        break;

                    case R.id.rad_oz:

                        mDefaultUnit.setText("oz");
                        mSmallUnit.setText("oz");
                        mMediumUnit.setText("oz");
                        mLargeUnit.setText("oz");

                        defaultCup = getOZ(Double.parseDouble(mDefaultCup.getText().toString()));
                        smallCup = getOZ(Double.parseDouble(mSmallCup.getText().toString()));
                        mediumCup = getOZ(Double.parseDouble(mMediumCup.getText().toString()));
                        largeCup = getOZ(Double.parseDouble(mLargeCup.getText().toString()));

                        mDefaultCup.setText(String.valueOf(Math.round(defaultCup)));
                        mSmallCup.setText(String.valueOf(Math.round(smallCup)));
                        mMediumCup.setText(String.valueOf(Math.round(mediumCup)));
                        mLargeCup.setText(String.valueOf(Math.round(largeCup)));
                        break;

                }
            }
        });

        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }

    private double getOZ(double ml) {
        return ml / 29.574;
    }

    private double getML(double oz) {
        return oz * 29.574;
    }


}
