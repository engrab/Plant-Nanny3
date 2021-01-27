package com.example.plantnany.dialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public BodyWeightBottomDialoge() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_body_weight_sheet, container, false);
        recyclerView = view.findViewById(R.id.rv_weight);
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
        mList.add(30);
        mList.add(31);
        mList.add(32);
        mList.add(33);
        mList.add(34);
        mList.add(35);
        mList.add(36);
        mList.add(37);
        mList.add(38);
        mList.add(39);
        mList.add(40);
        mList.add(41);
        mList.add(42);
        mList.add(43);
        mList.add(44);
        mList.add(45);
        mList.add(46);
        mList.add(47);
        mList.add(48);
        mList.add(49);
        mList.add(50);

    }


}
