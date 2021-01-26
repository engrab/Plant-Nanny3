package com.example.plantnany.fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.multidex.BuildConfig;

import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.activities.PrivacyPolicyActivity;
import com.example.plantnany.dialoge.CupVolumeBottomDialoge;
import com.example.plantnany.dialoge.DailyGoalBottomDialoge;
import com.example.plantnany.dialoge.LanguageBottomDialoge;
import com.example.plantnany.utils.AppUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static com.example.plantnany.activities.SplashScreenActivity.KEY_MUSIC;
import static com.example.plantnany.activities.SplashScreenActivity.PLAY_MUSIC;

public class SettingsFragment extends Fragment implements View.OnClickListener, LanguageBottomDialoge.LanguageSelectListener {

    private static final String TAG = "SettingsFragment";
    LinearLayout mPrivacyPolicy, mMoreApps, mShareApp, mRateUs, mFollowUs, mMusic, mSoundEffect, mLanguage,
            mDailyGoal, mCupVolume;
    SwitchCompat mMusicSwitch;
    private MusicPlayingListener mListener;
    ImageView mReminder;
    static TextView mSelectedLangName;
    public LanguageBottomDialoge.LanguageSelectListener callback;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof MusicPlayingListener) {
            mListener = (MusicPlayingListener) context;
        }
    }

    public SettingsFragment() {

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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PLAY_MUSIC, Context.MODE_PRIVATE);

        mMusicSwitch.setChecked(sharedPreferences.getBoolean(KEY_MUSIC, true));

        mMusicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: "+isChecked);
                mListener.musicPlaying(isChecked);
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
        mMusic.setOnClickListener(this);
        mSoundEffect.setOnClickListener(this);
        mLanguage.setOnClickListener(this);
        mReminder.setOnClickListener(this);
        mDailyGoal.setOnClickListener(this);
        mCupVolume.setOnClickListener(this);
    }

    private void mediaPlayerListener() {


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
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ll_privacy_policy:
                AppUtils.soundButtonClick(getActivity(), true);
                startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
                break;
            case R.id.ll_more_apps:

                final String appPackageName = getActivity().getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
            case R.id.ll_share_app:

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

                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
                break;
            case R.id.ll_follow_us:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(browserIntent);
                break;


            case R.id.ll_music:

                break;
            case R.id.ll_sound_effect:

                break;
            case R.id.ll_language:

                LanguageBottomDialoge languageBottomDialoge = new LanguageBottomDialoge(callback);
                languageBottomDialoge.show(getChildFragmentManager(), "language");
                break;


            case R.id.ll_daily_goal:
                DailyGoalBottomDialoge dailyGoalBottomDialoge = new DailyGoalBottomDialoge();
                dailyGoalBottomDialoge.show(getChildFragmentManager(), "dailygoal");

                break;

            case R.id.ll_cup_volume:
                CupVolumeBottomDialoge cupVolumeBottomDialoge = new CupVolumeBottomDialoge();
                cupVolumeBottomDialoge.show(getChildFragmentManager(), "cupvolume");
                break;


        }
    }

    public static void setLangName(String langName) {
        mSelectedLangName.setText(langName);
    }

    @Override
    public void selectedLanguage(String lang) {
        Log.d(TAG, "selectedLanguage: " + lang);
        mSelectedLangName.setText(lang);
    }

    public interface MusicPlayingListener {
        void musicPlaying(boolean bool);
    }
}