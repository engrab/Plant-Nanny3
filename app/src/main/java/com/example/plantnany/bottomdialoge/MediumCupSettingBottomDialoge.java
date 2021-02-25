package com.example.plantnany.bottomdialoge;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.adapters.DefaultCupVolumAdapter;
import com.example.plantnany.adapters.MediumCupVolumAdapter;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MediumCupSettingBottomDialoge extends BottomSheetDialogFragment {


    ImageView back;
    RecyclerView cupVolumRecyclerView;
    private final List<Integer> mList = new ArrayList<>();
    private MediumCupVolumeListener mediumCupVolumeListener;


    public MediumCupSettingBottomDialoge(CupVolumeBottomDialoge context) {
        if (context != null) {
            mediumCupVolumeListener = context;
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.dialoge_cup_setting_sheet, container, false);

        back = view.findViewById(R.id.iv_back);

        cupVolumRecyclerView = view.findViewById(R.id.rv_cup_volume);

        for (int i = 240; i <= 1000; i++){
            mList.add(i);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.findViewById(R.id.iv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediumCupVolumeListener.mediumCupVolume(SharedPreferencesManager.getInstance(getActivity()).getMediumCup());
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        cupVolumRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        cupVolumRecyclerView.setAdapter(new MediumCupVolumAdapter(getActivity(), mList));

    }
    public interface MediumCupVolumeListener{
        void mediumCupVolume(int mediumCupVolum);
    }
}
