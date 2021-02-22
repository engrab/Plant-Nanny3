package com.example.plantnany;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

import com.example.plantnany.sharedpref.SharedPreferencesManager;

public class ButtonClick {

    static Context mContext;

    public ButtonClick(Context context) {
        mContext = context;
    }

    public void setOnsoundOnButtonClick() {


        if (SharedPreferencesManager.getInstance(mContext).getButtonClickSound()) {

            final MediaPlayer mp = MediaPlayer.create(mContext,
                    R.raw.clicksound);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.release();
                }
            });

        }

    }
}
