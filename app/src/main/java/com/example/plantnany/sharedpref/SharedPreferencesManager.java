package com.example.plantnany.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;

import com.example.plantnany.R;

public class SharedPreferencesManager {
    private static final String APP_PREFS = "AppPrefsFile";
    private static final String NOTIFICATION_FREQUENCY_KEY = "NOTIFICATION_FREQUENCY_KEY";
    private static final String NOTIFICATION_NEW_MESSAGE = "notifications_new_message";
    private static final String NOTIFICATION_TONE_URI_KEY = "NOTIFICATION_TONE_URI_KEY";
    private static final String NOTIFICATION_MSG_KEY = "NOTIFICATION_MSG_KEY";
    private static final String NOTIFICATION_STATUS_KEY = "NOTIFICATION_STATUS_KEY";
    private static final String IS_FIRST_TIME_KEY = "is_first_time";
    private static final String IS_MUSIC_PlayING_KEY = "is_music_play";
    private static final String IS_BUTTON_CLICK_SOUND_KEY = "is_button_click_sound";



    private SharedPreferences sharedPrefs;
    private static SharedPreferencesManager instance;


    private SharedPreferencesManager(Context context) {
        sharedPrefs = context.getApplicationContext().getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }


    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null)
            instance = new SharedPreferencesManager(context);

        return instance;
    }

    public void setNotificationFrequency(int frequency) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(NOTIFICATION_FREQUENCY_KEY, frequency);
        editor.apply();
    }

    public int getNotificationFrequency() {
        return sharedPrefs.getInt(NOTIFICATION_FREQUENCY_KEY, 60);
    }

    public boolean getNewMessage() {
        return sharedPrefs.getBoolean(NOTIFICATION_NEW_MESSAGE, true);
    }

    public String getNotificationToneUri() {
        return sharedPrefs.getString(NOTIFICATION_TONE_URI_KEY, RingtoneManager.getDefaultUri(2).toString());
    }

    public String getNotificationMessage() {
        return sharedPrefs.getString(NOTIFICATION_MSG_KEY, "Lets Drink some water");
    }

    public void setNotificationStatus(boolean bool){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(NOTIFICATION_STATUS_KEY, bool);
        editor.apply();
    }

    public boolean getNotificationStatus(){
        return sharedPrefs.getBoolean(NOTIFICATION_STATUS_KEY, true);
    }

    public void setIsFirstTime(boolean bool){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_FIRST_TIME_KEY, bool);
        editor.apply();
    }
    public boolean getIsFirstTime(){
        return sharedPrefs.getBoolean(IS_FIRST_TIME_KEY, true);
    }
    public void setIsMusicPlay(boolean bool){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_MUSIC_PlayING_KEY, bool);
        editor.apply();
    }
    public boolean getMusicPlay(){
        return sharedPrefs.getBoolean(IS_MUSIC_PlayING_KEY, true);
    }

    public void setButtonClickSound(boolean bool){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_BUTTON_CLICK_SOUND_KEY, bool);
        editor.apply();
    }
    public boolean getButtonClickSound(){
        return sharedPrefs.getBoolean(IS_BUTTON_CLICK_SOUND_KEY, true);
    }
}
