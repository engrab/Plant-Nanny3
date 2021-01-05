package com.example.plantnany.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.plantnany.R;
import com.example.plantnany.fragments.CreaturesFragment;
import com.example.plantnany.fragments.PlantsFragment;
import com.example.plantnany.fragments.QuestsFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PlantsFragment();
                break;
            case 1:
                fragment = new CreaturesFragment();
                break;
            case 2:
                fragment = new QuestsFragment();
                break;

        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Plants";
            case 1:
                return "Creatures";
            case 2:
                return "Quests";

        }
        return "Plants";
    }

    @Override
    public int getCount() {

        return 3;
    }
}