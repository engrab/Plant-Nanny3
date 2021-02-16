package com.example.plantnany.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.ArrayList;
import java.util.List;

public class PotsFragment extends Fragment implements RewardedVideoAdListener {

    RecyclerView mRecyclerView;
    List<PotModel> mList;
    LinearLayout freeClover;
    RewardedVideoAd rewardedVideoAd;
    TextView clover;
    TextView seeds;

    public PotsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pots, container, false);
        init(view);
        listPots();
        setAdapterForRecyclerView();

        rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        rewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardVideoAd();
        freeClover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloverDialoge();
            }
        });

        return view;

    }

    private void loadRewardVideoAd() {
        rewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }

    private void cloverDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are You Want to Increase Your Clover? ");
        builder.setMessage("Then Press the OK Button below and watch full video");
        builder.setCancelable(true);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (rewardedVideoAd.isLoaded()){
                    rewardedVideoAd.show();
                }
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
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
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        mRecyclerView.setAdapter(new PotsAdapter(getActivity(), mList));
    }

    private void init(View view) {

        mRecyclerView = view.findViewById(R.id.rv_pots);
        freeClover = view.findViewById(R.id.ll_free_clover);
        clover = view.findViewById(R.id.tv_clover);
        seeds = view.findViewById(R.id.tv_seeds);

    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        SharedPreferencesManager.getInstance(getActivity()).setClover(SharedPreferencesManager.getInstance(getActivity()).getClover() + 1);
        clover.setText(SharedPreferencesManager.getInstance(getActivity()).getClover() + "");

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

        Toast.makeText(getActivity(), "Please Click again for rewarded ads", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}