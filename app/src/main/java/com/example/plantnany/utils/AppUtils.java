package com.example.plantnany.utils;


import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.example.plantnany.R;

public class AppUtils {

    private static final String TAG = "AppUtils";

    public static void soundButtonClick(Context context, boolean bool) {

        if (bool) {
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.buttonclick);
            mediaPlayer.start();
        }
        else {
            Log.d(TAG, "soundButtonClick: enable from setting");
        }
    }

}
