package com.example.plantnany.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.plantnany.R;
import com.example.plantnany.adapters.PotsAdapter;
import com.example.plantnany.model.PotModel;

import java.util.ArrayList;
import java.util.List;

public class PotsFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<PotModel> mList;

    public PotsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pots, container, false);
        init(view);
        listPots();
        setAdapterForRecyclerView();

        return view;

    }

    private void listPots() {
        mList = new ArrayList<>();
        mList.add(new PotModel(R.drawable.ic_pot_1, "Black"));
        mList.add(new PotModel(R.drawable.ic_pot_2, "Red"));
        mList.add(new PotModel(R.drawable.ic_pot_3, "Yellow"));
        mList.add(new PotModel(R.drawable.ic_pot_4, "Red Light"));
        mList.add(new PotModel(R.drawable.ic_pot_5, "Blue"));
        mList.add(new PotModel(R.drawable.ic_pot_6, "Green"));
    }

    private void setAdapterForRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(new PotsAdapter(getActivity(), mList));
    }

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_pots);
    }
}