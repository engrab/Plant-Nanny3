package com.example.plantnany.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.multidex.BuildConfig;

import com.example.plantnany.ButtonClick;
import com.example.plantnany.R;
import com.example.plantnany.activities.FUISignOutActivity;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.activities.PrivacyPolicyActivity;
import com.example.plantnany.activities.SignUpActivity;
import com.example.plantnany.bottomdialoge.CupVolumeBottomDialoge;
import com.example.plantnany.bottomdialoge.DailyGoalBottomDialog;
import com.example.plantnany.bottomdialoge.LanguageBottomDialoge;
import com.example.plantnany.bottomdialoge.WaterReminderBottomDialoge;
import com.example.plantnany.databinding.FragmentSettingsBinding;
import com.example.plantnany.sharedpref.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SettingsFragment extends Fragment implements View.OnClickListener,
        LanguageBottomDialoge.LanguageSelectListener, DailyGoalBottomDialog.TargetWaterListener {

    private static final String TAG = "SettingsFragment";
    private static Context mContext;
    private MusicPlayingListener mMusicPlayListener;
    public LanguageBottomDialoge.LanguageSelectListener callback;
    private Context context;

    static ButtonClick buttonClick;
    static TextView mSelectedLangName;

//    TextView mDefaultCupVolume, mTargerWater;
//    ImageView mReminder;
//    Button btnLogin;
//    LinearLayout mPrivacyPolicy, mMoreApps, mShareApp, mRateUs, mFollowUs, mMusic, mSoundEffect, mLanguage,
//            mDailyGoal, mCupVolume, llReminder;
//    SwitchCompat mMusicSwitch, mSoundSwitch;

    private FragmentSettingsBinding binding;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MusicPlayingListener) {
            mMusicPlayListener = (MusicPlayingListener) context;
        }

        this.context = context;
    }
    public static SettingsFragment getInstance(MainActivity context){
        mContext = context;
        buttonClick = new ButtonClick(mContext);
        SettingsFragment f = new SettingsFragment();
        return f;
    }

    private void getUser(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!= null){
            binding.btnLogin.setVisibility(View.INVISIBLE);
            binding.tvInfo.setVisibility(View.INVISIBLE);

            binding.tvDisplayName.setVisibility(View.VISIBLE);
            binding.ivUserImage.setVisibility(View.VISIBLE);

            binding.tvDisplayName.setText(user.getDisplayName());
            

        }else {
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.tvInfo.setVisibility(View.VISIBLE);

            binding.tvDisplayName.setVisibility(View.INVISIBLE);
            binding.ivUserImage.setVisibility(View.INVISIBLE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        getUser();
        initListener();
        mediaPlayerListener();
        callback = this;


        binding.scMusic.setChecked(SharedPreferencesManager.getInstance(getActivity()).getMusicPlay());
        binding.scSound.setChecked(SharedPreferencesManager.getInstance(getActivity()).getButtonClickSound());

        binding.scMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonClick.setOnsoundOnButtonClick();
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                mMusicPlayListener.musicPlaying(isChecked);
            }
        });
        binding.scSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonClick.setOnsoundOnButtonClick();
                SharedPreferencesManager.getInstance(getActivity()).setButtonClickSound(isChecked);
            }
        });

        return view;

    }

    private void initListener() {
        binding.llPrivacyPolicy.setOnClickListener(this);
        binding.llMoreApps.setOnClickListener(this);
        binding.llShareApp.setOnClickListener(this);
        binding.llRateUs.setOnClickListener(this);
        binding.llFollowUs.setOnClickListener(this);

        binding.llLanguage.setOnClickListener(this);
        binding.llReminder.setOnClickListener(this);
        binding.llDailyGoal.setOnClickListener(this);
        binding.llCupVolume.setOnClickListener(this);
        binding.llReminder.setOnClickListener(this);
        binding.btnLogin.setOnClickListener(this);

    }

    private void mediaPlayerListener() {


    }

    @Override
    public void onResume() {
        super.onResume();
        binding.tvTargetWater.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getTargetWater()));

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.ll_privacy_policy:
                buttonClick.setOnsoundOnButtonClick();
                startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
                break;
            case R.id.ll_more_apps:
                buttonClick.setOnsoundOnButtonClick();
                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.ll_share_app:
                buttonClick.setOnsoundOnButtonClick();
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll_rate_us:
                buttonClick.setOnsoundOnButtonClick();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
                break;
            case R.id.ll_follow_us:
                buttonClick.setOnsoundOnButtonClick();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                break;


            case R.id.ll_language:
                buttonClick.setOnsoundOnButtonClick();
                LanguageBottomDialoge languageBottomDialoge = new LanguageBottomDialoge(callback);
                languageBottomDialoge.show(getChildFragmentManager(), "language");
                break;


            case R.id.ll_daily_goal:
                buttonClick.setOnsoundOnButtonClick();
                DailyGoalBottomDialog dailyGoalBottomDialog = new DailyGoalBottomDialog(SettingsFragment.this);
                dailyGoalBottomDialog.show(getChildFragmentManager(), "dailygoal");

                break;

            case R.id.ll_cup_volume:
                buttonClick.setOnsoundOnButtonClick();
                CupVolumeBottomDialoge cupVolumeBottomDialoge = new CupVolumeBottomDialoge();
                cupVolumeBottomDialoge.show(getChildFragmentManager(), "cupvolume");
                break;

            case R.id.ll_reminder:
                buttonClick.setOnsoundOnButtonClick();
                WaterReminderBottomDialoge waterReminderBottomDialoge = new WaterReminderBottomDialoge();
                waterReminderBottomDialoge.show(getChildFragmentManager(), "waterreminder");
                break;

            case R.id.btn_login:

                startActivity(new Intent(getActivity(), FUISignOutActivity.class));
                break;

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvDefaultCupVolume.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getDefaultCupVolume()));
    }

    @Override
    public void selectedLanguage(String lang) {
        Log.d(TAG, "selectedLanguage: " + lang);
        binding.tvLanguageName.setText(lang);
    }

    @Override
    public void targetWater(int targetWater) {
        binding.tvTargetWater.setText(String.valueOf(targetWater));

    }

    public interface MusicPlayingListener {
        void musicPlaying(boolean bool);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}