package com.example.plantnany.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.plantnany.R;
import com.example.plantnany.adapters.PotsAdapter;
import com.example.plantnany.model.PotModel;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.util.ArrayList;
import java.util.List;

public class PotsFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<PotModel> mList;
    LinearLayout freeClover;
    RewardedAd rewardedAd;
    TextView clover;
    TextView seeds;
    Activity mContext;

    public PotsFragment(Activity context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pots, container, false);
        init(view);
        listPots();
        setAdapterForRecyclerView();


        freeClover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloverDialoge();
            }
        });

        return view;

    }


    private void cloverDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are You Want to Increase Your Clover? ");
        builder.setMessage("Then Press the OK Button below and watch full video");
        builder.setCancelable(true);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadRewardedAd();
                showRewardedAd();
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void loadRewardedAd() {

        FullScreenContentCallback callback = new FullScreenContentCallback() {
            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {

            }

            @Override
            public void onAdShowedFullScreenContent() {

            }

            @Override
            public void onAdDismissedFullScreenContent() {
                rewardedAd = null;
            }
        };

        RewardedAd.load(mContext, getString(R.string.admob_rewarded_id), new AdRequest.Builder().build(), new RewardedAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
            }

            @Override
            public void onAdLoaded(@NonNull RewardedAd rewarded) {
                super.onAdLoaded(rewardedAd);
                rewardedAd = rewarded;
                rewardedAd.setFullScreenContentCallback(callback);

            }
        });
    }

    private void showRewardedAd() {

        if (rewardedAd != null) {
            rewardedAd.show(mContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull com.google.android.gms.ads.rewarded.RewardItem rewardItem) {

                    SharedPreferencesManager.getInstance(getActivity()).setClover(SharedPreferencesManager.getInstance(getActivity()).getClover() + 1);
                    clover.setText(SharedPreferencesManager.getInstance(getActivity()).getClover() + "");
                }
            });
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clover.setText(SharedPreferencesManager.getInstance(getActivity()).getClover() + "");
        seeds.setText(SharedPreferencesManager.getInstance(getActivity()).getSeeds() + "");

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
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mRecyclerView.setAdapter(new PotsAdapter(getActivity(), mList));
    }

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_pots);
        freeClover = view.findViewById(R.id.ll_free_clover);
        clover = view.findViewById(R.id.tv_clover);
        seeds = view.findViewById(R.id.tv_seeds);

    }


}