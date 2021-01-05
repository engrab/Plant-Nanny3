package com.example.plantnany.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantnany.R;
import com.example.plantnany.adapters.CreatureAdapter;
import com.example.plantnany.adapters.PlantAdapter;
import com.example.plantnany.model.PlantModel;

import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<PlantModel> modelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants, container, false);
        initList();
        init(view);
        return view;

    }

    private void initList() {
        modelList = new ArrayList<>();
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new PlantModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
    }

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.plant_recyclerview);
        PlantAdapter plantAdapter = new PlantAdapter(getActivity(), modelList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(plantAdapter);
    }
}