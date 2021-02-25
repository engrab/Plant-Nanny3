package com.example.plantnany.bottomdialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.adapters.BodyWeightAdapter;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BodyWeightBottomDialog extends BottomSheetDialogFragment {

    RecyclerView recyclerView;
    BodyWeightAdapter mAdapter;
    List<Integer> mList;
    RadioGroup kglb;
    private WeightListener mWeightListener;
    int totalWeight;
    boolean isKg = true;
    RadioButton kg, lb;


    public BodyWeightBottomDialog(DailyGoalBottomDialog context) {
        if (context != null) {
            mWeightListener = (WeightListener) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_body_weight_sheet, container, false);
        initView(view);

        if (SharedPreferencesManager.getInstance(getActivity()).getIsKgChecked()) {
            kg.setChecked(true);
        } else {
            lb.setChecked(true);
        }


        totalWeight = SharedPreferencesManager.getInstance(getActivity()).getWeight();
        kglb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.rad_kg:
                        isKg = true;
                        break;

                    case R.id.rad_lb:
                        isKg = false;
                        break;
                }
            }
        });

        view.findViewById(R.id.iv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalWeight = SharedPreferencesManager.getInstance(getActivity()).getWeight();
                mWeightListener.selectedWeight(totalWeight, isKg);
                dismiss();
            }
        });
        list();
        initRecyclerView();
        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_weight);
        kglb = view.findViewById(R.id.radio_group_kg_lb);
        kg = view.findViewById(R.id.rad_kg);
        lb = view.findViewById(R.id.rad_lb);
    }

    private void initRecyclerView() {
        mAdapter = new BodyWeightAdapter(getActivity(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(mAdapter);

    }

    private void list() {
        mList = new ArrayList<>();
        for (int i = 30; i <= 200; i++) {
            mList.add(i);
        }

    }


    public interface WeightListener {
        void selectedWeight(int weight, boolean isKg);
    }


}
