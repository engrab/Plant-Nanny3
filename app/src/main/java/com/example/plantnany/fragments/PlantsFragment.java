package com.example.plantnany.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.plantnany.R;
import com.example.plantnany.adapters.PlantAdapter;
import com.example.plantnany.databinding.FragmentPlantsBinding;
import com.example.plantnany.model.PlantModel;

import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment {

    List<PlantModel> modelList;
    private FragmentPlantsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        initList();
        initRecyclerViewAdapter();
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

    private void initRecyclerViewAdapter() {

        PlantAdapter plantAdapter = new PlantAdapter(getActivity(), modelList);
        binding.plantRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.plantRecyclerview.setAdapter(plantAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}