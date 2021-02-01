package com.example.plantnany.dialoge;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.plantnany.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class LanguageBottomDialoge extends BottomSheetDialogFragment {

    ImageView back;
    RadioGroup mLanguageRadioGroup;
    private RadioButton mRadioButton;
    private LanguageSelectListener mListener;

    public LanguageBottomDialoge(LanguageSelectListener mListener) {
        this.mListener = mListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_language_sheet, container, false);
        back = view.findViewById(R.id.iv_back);
        mLanguageRadioGroup = view.findViewById(R.id.radio_group_language);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });
        mLanguageRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                int checkedRadioButtonId = mLanguageRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) view.findViewById(checkedRadioButtonId);

                Toast.makeText(getContext(), "selected language " + mRadioButton.getText().toString(), Toast.LENGTH_SHORT).show();

                mListener.selectedLanguage(mRadioButton.getText().toString());
            }
        });

        return view;
    }


    public interface LanguageSelectListener {
        void selectedLanguage(String lang);
    }
}
