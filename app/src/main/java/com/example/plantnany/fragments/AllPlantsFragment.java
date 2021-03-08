package com.example.plantnany.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.adapters.SectionsPagerAdapter;
import com.example.plantnany.databinding.FragmentPlantsAllBinding;
import com.google.android.material.tabs.TabLayout;

public class AllPlantsFragment extends Fragment {
    Context mContext;
    ButtonClick buttonClick;
    private FragmentPlantsAllBinding binding;

    public AllPlantsFragment(Context context) {
        mContext = context;
        buttonClick = new ButtonClick(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantsAllBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getChildFragmentManager());
        binding.viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(binding.viewPager);
        return view;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}