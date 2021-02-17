package com.example.plantnany.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.database.AppDataBase;
import com.example.plantnany.database.DataEntity;
import com.example.plantnany.database.DateConverter;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.example.plantnany.viewmodels.HomeFragmentViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_STORAGE = 100;
    private static final String TAG = "Mytag";
    RelativeLayout screenShot, rlSeeds;
    ImageView mAddWater;
    private DataEntity dataEntity;

    TextView clover;
    ViewGroup viewGroup;
    ImageView potsRedirect;
    int id = 0;
    List<DataEntity> mListEntity = new ArrayList<>();
    DataEntity entity;

    Date curDate = new Date();
    int intakeWater;
    RewardedAd rewardedAd;
    TextView seeds;
    Context mContext;
    ImageView pots;
    private HomeFragmentViewModel mViewModel;


    public HomeFragment(Context context) {
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        initViewModel();
        entity = new DataEntity();
        init(view);
        onclickListener();


        getAllData();


        return view;

    }

    private void getAllData() {
        mViewModel.getAllData();
        mListEntity = mViewModel.mListEntity;
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(getActivity()).get(HomeFragmentViewModel.class);
    }


    private void setPots() {
        int pot = SharedPreferencesManager.getInstance(mContext).getPot();
        switch (pot) {

            case 1:
                pots.setImageResource(R.drawable.ic_pot_1);
                break;
            case 2:
                pots.setImageResource(R.drawable.ic_pot_2);
                break;
            case 3:
                pots.setImageResource(R.drawable.ic_pot_3);
                break;
            case 4:
                pots.setImageResource(R.drawable.ic_pot_4);
                break;
            case 5:
                pots.setImageResource(R.drawable.ic_pot_5);
                break;
            case 6:
                pots.setImageResource(R.drawable.ic_pot_6);
                break;

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seeds.setText(SharedPreferencesManager.getInstance(getActivity()).getSeeds() + "");
        clover.setText(SharedPreferencesManager.getInstance(getActivity()).getClover() + "");
    }


    private void loadRewardAd() {

        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdShowedFullScreenContent() {
                // Code to be invoked when the ad showed full screen content.
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                rewardedAd = null;
                // Code to be invoked when the ad dismissed full screen content.
            }
        };

        RewardedAd.load(mContext, getString(R.string.admob_rewarded_id), new AdRequest.Builder().build(), new RewardedAdLoadCallback() {

            @Override
            public void onAdLoaded(RewardedAd ad) {

                rewardedAd = ad;
                rewardedAd.setFullScreenContentCallback(fullScreenContentCallback);
            }
        });

    }

    public void showRewardedAd() {

        if (rewardedAd != null) {
            rewardedAd.show((Activity) mContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(@NonNull com.google.android.gms.ads.rewarded.RewardItem rewardItem) {
                    Toast.makeText(
                            mContext,
                            "onRewarded! currency: "
                                    + rewardItem.getType() + "    amount: "
                                    + rewardItem.getAmount(), Toast.LENGTH_SHORT).show();
                    SharedPreferencesManager.getInstance(getActivity()).setSeeds(SharedPreferencesManager.getInstance(getActivity()).getSeeds() + 1);
                    seeds.setText(SharedPreferencesManager.getInstance(getActivity()).getSeeds() + "");
                }
            });
        }
    }


    private void seedDialoge() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Are You Want to Increase Your Seeds? ");
        builder.setMessage("Then Press the OK Button below and watch full video");
        builder.setCancelable(true);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadRewardAd();
                showRewardedAd();
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void waterDialoge(String date) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Total Water = " + SharedPreferencesManager.getInstance(getActivity()).getTargetWater());
        builder.setMessage("Taken Water = " + intakeWater + "\n" + date);
        builder.setCancelable(true);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onResume() {
        super.onResume();
        setPots();


    }

    private void onclickListener() {
        screenShot.setOnClickListener(this);
        mAddWater.setOnClickListener(this);
        potsRedirect.setOnClickListener(this);
        rlSeeds.setOnClickListener(this);
    }

    private void init(View view) {

        screenShot = view.findViewById(R.id.rl_camera);
        mAddWater = view.findViewById(R.id.iv_add_water);
        clover = view.findViewById(R.id.tv_clover);
        viewGroup = view.findViewById(R.id.relativeLayout3);
        potsRedirect = view.findViewById(R.id.iv_pots_redirect);
        rlSeeds = view.findViewById(R.id.rl_seeds);
        seeds = view.findViewById(R.id.tv_seeds);
        pots = view.findViewById(R.id.iv_pots);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.iv_pots_redirect:
                MainActivity.navigation.setSelectedItemId(R.id.navigation_pots);
                break;

            case R.id.rl_camera:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    permissionCheck();
                } else {
                    takeScreenshot();
                }
                break;

            case R.id.iv_add_water:

                if (mListEntity.size() != 0) {
                    if (mListEntity.get(mListEntity.size() - 1).getDate().equals(DateConverter.dateToString(curDate.getTime()))) {
                        int lastWater = mListEntity.get(mListEntity.size() - 1).getIntakeWater();
                        dataEntity = new DataEntity(DateConverter.dateToString(curDate.getTime()), 240 + lastWater);
                    } else {
                        dataEntity = new DataEntity(DateConverter.dateToString(curDate.getTime()), 240);
                    }
                } else {
                    dataEntity = new DataEntity(DateConverter.dateToString(curDate.getTime()), 240);
                }

                mViewModel.inserData(dataEntity);
                getAllData();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getAllData();
                        if (!mListEntity.isEmpty()) {
                            String date = mListEntity.get(mListEntity.size() - 1).getDate();
                            intakeWater = mListEntity.get(mListEntity.size() - 1).getIntakeWater();

                            waterDialoge(date);
                        }
                    }
                }, 500);


                break;

            case R.id.rl_seeds:
                seedDialoge();
                break;

        }
    }

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getActivity().getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            shareScreenShot(imageFile);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }


    private void shareScreenShot(File fileScreenShot) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(fileScreenShot);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.app_name));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share via"));
        }
    }

    public void permissionCheck() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE);
            } else {
                takeScreenshot();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE) {
            if ((grantResults[0] == PackageManager.PERMISSION_GRANTED) && (grantResults[1] == PackageManager.PERMISSION_GRANTED)) {

                Log.d(TAG, "onRequestPermissionsResult: ");
            } else {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }


}