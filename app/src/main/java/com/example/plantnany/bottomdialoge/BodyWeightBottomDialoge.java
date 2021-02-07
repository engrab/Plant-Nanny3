package com.example.plantnany.bottomdialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.adapters.BodyWeightAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BodyWeightBottomDialoge extends BottomSheetDialogFragment {

    RecyclerView recyclerView;
    BodyWeightAdapter mAdapter;
    List<Integer> mList;
    ImageView ok;
    RadioGroup kglb;

    public BodyWeightBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_body_weight_sheet, container, false);
        recyclerView = view.findViewById(R.id.rv_weight);
        kglb = view.findViewById(R.id.radio_group_kg_lb);

        kglb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (group.getId()){
                    case R.id.rad_kg:

                        break;

                    case R.id.rad_lb:

                        break;
                }
            }
        });
        ok = view.findViewById(R.id.iv_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        list();
        mAdapter = new BodyWeightAdapter(getActivity(), mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        return view;
    }

    private void list() {
        mList = new ArrayList<>();
        for (int i=30; i<=200; i++){
            mList.add(i);
        }

    }


}
