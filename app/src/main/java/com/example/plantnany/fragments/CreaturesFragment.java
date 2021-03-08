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
import com.example.plantnany.adapters.CreatureAdapter;
import com.example.plantnany.databinding.FragmentCreaturesBinding;
import com.example.plantnany.model.CreatureModel;

import java.util.ArrayList;
import java.util.List;

public class CreaturesFragment extends Fragment {

    List<CreatureModel> modelList;
    private FragmentCreaturesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreaturesBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        initList();
        startRecyclerView();
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

    private void startRecyclerView() {

        CreatureAdapter creatureAdapter = new CreatureAdapter(getActivity(), modelList);
        binding.creatureRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.creatureRecyclerview.setAdapter(creatureAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}