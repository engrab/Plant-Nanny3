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
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.activities.PrivacyPolicyActivity;
import com.example.plantnany.activities.SignUpActivity;
import com.example.plantnany.bottomdialoge.CupVolumeBottomDialoge;
import com.example.plantnany.bottomdialoge.DailyGoalBottomDialog;
import com.example.plantnany.bottomdialoge.LanguageBottomDialoge;
import com.example.plantnany.bottomdialoge.WaterReminderBottomDialoge;
import com.example.plantnany.sharedpref.SharedPreferencesManager;


public class SettingsFragment extends Fragment implements View.OnClickListener,
        LanguageBottomDialoge.LanguageSelectListener, DailyGoalBottomDialog.TargetWaterListener {

    private static final String TAG = "SettingsFragment";
    private static Context mContext;
    LinearLayout mPrivacyPolicy, mMoreApps, mShareApp, mRateUs, mFollowUs, mMusic, mSoundEffect, mLanguage,
            mDailyGoal, mCupVolume, llReminder;
    SwitchCompat mMusicSwitch, mSoundSwitch;
    private MusicPlayingListener mMusicPlayListener;
    private SoundClickListener mSoundListener;
    ImageView mReminder;
    static TextView mSelectedLangName;
    public LanguageBottomDialoge.LanguageSelectListener callback;
    private Context context;
    TextView mDefaultCupVolume, mTargerWater;
    static ButtonClick buttonClick;
    Button btnLogin;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MusicPlayingListener) {
            mMusicPlayListener = (MusicPlayingListener) context;
        }
        if (context instanceof SoundClickListener) {
            mSoundListener = (SoundClickListener) context;
        }
        this.context = context;
    }
    public static SettingsFragment getInstance(MainActivity context){
        mContext = context;
        buttonClick = new ButtonClick(mContext);
        SettingsFragment f = new SettingsFragment();
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Log.d(TAG, "onCreateView: ");
        init(view);
        initListener();
        mediaPlayerListener();
        callback = this;


        mMusicSwitch.setChecked(SharedPreferencesManager.getInstance(getActivity()).getMusicPlay());
        mSoundSwitch.setChecked(SharedPreferencesManager.getInstance(getActivity()).getButtonClickSound());

        mMusicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonClick.setOnsoundOnButtonClick();
                Log.d(TAG, "onCheckedChanged: " + isChecked);
                mMusicPlayListener.musicPlaying(isChecked);
            }
        });
        mSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonClick.setOnsoundOnButtonClick();
                SharedPreferencesManager.getInstance(getActivity()).setButtonClickSound(isChecked);
            }
        });

        return view;

    }

    private void initListener() {
        mPrivacyPolicy.setOnClickListener(this);
        mMoreApps.setOnClickListener(this);
        mShareApp.setOnClickListener(this);
        mRateUs.setOnClickListener(this);
        mFollowUs.setOnClickListener(this);

        mLanguage.setOnClickListener(this);
        mReminder.setOnClickListener(this);
        mDailyGoal.setOnClickListener(this);
        mCupVolume.setOnClickListener(this);
        llReminder.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    private void mediaPlayerListener() {


    }

    @Override
    public void onResume() {
        super.onResume();
        mTargerWater.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getTargetWater()));

    }

    private void init(View view) {

        mPrivacyPolicy = view.findViewById(R.id.ll_privacy_policy);
        mMoreApps = view.findViewById(R.id.ll_more_apps);
        mShareApp = view.findViewById(R.id.ll_share_app);
        mRateUs = view.findViewById(R.id.ll_rate_us);
        mFollowUs = view.findViewById(R.id.ll_follow_us);
        mMusic = view.findViewById(R.id.ll_music);
        mSoundEffect = view.findViewById(R.id.ll_sound_effect);
        mLanguage = view.findViewById(R.id.ll_language);
        mMusicSwitch = view.findViewById(R.id.sc_music);
        mReminder = view.findViewById(R.id.iv_reminder);
        mSelectedLangName = view.findViewById(R.id.tv_language_name);
        mDailyGoal = view.findViewById(R.id.ll_daily_goal);
        mCupVolume = view.findViewById(R.id.ll_cup_volume);
        llReminder = view.findViewById(R.id.ll_reminder);
        mSoundSwitch = view.findViewById(R.id.sc_sound);
        mDefaultCupVolume = view.findViewById(R.id.tv_default_cup_volume);
        mTargerWater = view.findViewById(R.id.tv_target_water);
        btnLogin = view.findViewById(R.id.btn_login);
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

                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDefaultCupVolume.setText(String.valueOf(SharedPreferencesManager.getInstance(getActivity()).getDefaultCupVolume()));
    }

    @Override
    public void selectedLanguage(String lang) {
        Log.d(TAG, "selectedLanguage: " + lang);
        mSelectedLangName.setText(lang);
    }

    @Override
    public void targetWater(int targetWater) {
        mTargerWater.setText(String.valueOf(targetWater));

    }

    public interface MusicPlayingListener {
        void musicPlaying(boolean bool);
    }

    public interface SoundClickListener {
        void soundClick(boolean bool);
    }

}