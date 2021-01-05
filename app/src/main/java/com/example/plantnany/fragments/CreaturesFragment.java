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
import com.example.plantnany.model.CreatureModel;

import java.util.ArrayList;
import java.util.List;

public class CreaturesFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<CreatureModel> modelList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creatures, container, false);
        initList();
        init(view);
        return view;

    }

    private void initList() {
        modelList = new ArrayList<>();
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
        modelList.add(new CreatureModel(R.drawable.ic_placeholder_image, "Unknown", "Upgrade your greenhouse to discover this creature."));
    }

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.creature_recyclerview);
        CreatureAdapter creatureAdapter = new CreatureAdapter(getActivity(), modelList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(creatureAdapter);
    }
}