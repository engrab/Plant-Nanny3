package com.example.plantnany.bottomdialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantnany.R;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class CupVolumeBottomDialoge extends BottomSheetDialogFragment
        implements DefaultCupSettingBottomDialoge.DefaultCupVolumeListener,
        LargeCupSettingBottomDialoge.LargeCupVolumeListener,
        MediumCupSettingBottomDialoge.MediumCupVolumeListener,
        SmallCupSettingBottomDialoge.SmallCupVolumeListener {


    RadioButton mRadml, mRadoz;
    RadioGroup mRadmloz;
    TextView mDefaultCup, mDefaultUnit, mSmallCup, mSmallUnit, mMediumCup, mMediumUnit, mLargeCup, mLargeUnit;

    double defaultCup;
    double smallCup;
    double mediumCup;
    double largeCup;
    LinearLayout llDefault, llMedium, llSmall, llLarge;
    CheckBox checkSmall, checkMedium, checkLarge;


    public CupVolumeBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_cup_volume_sheet, container, false);

        initView(view);


        llDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DefaultCupSettingBottomDialoge defaultCupSettingBottomDialoge = new DefaultCupSettingBottomDialoge(CupVolumeBottomDialoge.this);
                defaultCupSettingBottomDialoge.show(getChildFragmentManager(), "default_custom_volume_setting_dialoge");
                mRadml.setChecked(true);
            }
        });
        llSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SmallCupSettingBottomDialoge smallCupSettingBottomDialoge = new SmallCupSettingBottomDialoge(CupVolumeBottomDialoge.this);
                smallCupSettingBottomDialoge.show(getChildFragmentManager(), "small_custom_volume_setting_dialoge");
                mRadml.setChecked(true);
            }
        });
        llMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MediumCupSettingBottomDialoge mediumCupSettingBottomDialoge = new MediumCupSettingBottomDialoge(CupVolumeBottomDialoge.this);
                mediumCupSettingBottomDialoge.show(getChildFragmentManager(), "medium_custom_volume_setting_dialoge");
                mRadml.setChecked(true);
            }
        });
        llLarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LargeCupSettingBottomDialoge largeCupSettingBottomDialoge = new LargeCupSettingBottomDialoge(CupVolumeBottomDialoge.this);
                largeCupSettingBottomDialoge.show(getChildFragmentManager(), "large_custom_volume_setting_dialoge");
                mRadml.setChecked(true);
            }
        });

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

        checkSmall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkSmall.setChecked(isChecked);
                SharedPreferencesManager.getInstance(getActivity()).setIsSmallCupChecked(isChecked);

            }
        });

        checkMedium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkMedium.setChecked(isChecked);
                SharedPreferencesManager.getInstance(getActivity()).setIsMediumCupChecked(isChecked);

            }
        });

        checkLarge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkLarge.setChecked(isChecked);
                SharedPreferencesManager.getInstance(getActivity()).setIsLargeCupChecked(isChecked);

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void initView(View view) {
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
        llDefault = view.findViewById(R.id.ll_default_cup_volume);
        llSmall = view.findViewById(R.id.ll_small);
        llMedium = view.findViewById(R.id.ll_medium);
        llLarge = view.findViewById(R.id.ll_large);

        checkSmall = view.findViewById(R.id.checkbox_small);
        checkMedium = view.findViewById(R.id.checkbox_medium);
        checkLarge = view.findViewById(R.id.checkbox_large);
    }

    private double getOZ(double ml) {
        return ml / 29.574;
    }

    private double getML(double oz) {
        return oz * 29.574;
    }

    @Override
    public void onResume() {
        super.onResume();
        mDefaultCup.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getDefaultCupVolume()));
        mLargeCup.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getLargeCup()));
        mMediumCup.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getMediumCup()));
        mSmallCup.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getSmallCup()));

        checkLarge.setChecked(SharedPreferencesManager.getInstance(getActivity()).getIsLargeCupChecked());
        checkMedium.setChecked(SharedPreferencesManager.getInstance(getActivity()).getIsMediumCupChecked());
        checkSmall.setChecked(SharedPreferencesManager.getInstance(getActivity()).getIsSmallCupChecked());
    }

    @Override
    public void defaultCupVolume(int defaultCupVolum) {
        mDefaultCup.setText(String.valueOf(defaultCupVolum));

    }

    @Override
    public void largeCupVolume(int largeCupVolum) {
        mLargeCup.setText(String.valueOf(largeCupVolum));
    }

    @Override
    public void mediumCupVolume(int mediumCupVolum) {
        mMediumCup.setText(String.valueOf(mediumCupVolum));
    }

    @Override
    public void smallCupVolume(int smallCupVolum) {
        mSmallCup.setText(String.valueOf(smallCupVolum));
    }
}
