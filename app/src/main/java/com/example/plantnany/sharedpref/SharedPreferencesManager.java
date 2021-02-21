package com.example.plantnany.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;

import com.example.plantnany.R;
import com.example.plantnany.model.TimeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
    private static final String REMINDER_TIME_KEY = "default_reminder_time";
    private static final String WEIGHT_KEY = "weight_key";
    private static final String TARGET_WATER_KEY = "target_water";
    private static final String DEFAULT_WATER_VOLUME = "default_water";
    private static final String SAFE_REMINDER_TIME_KEY = "safe_reminder_time";
    private static final String SEEDS_KEY = "seeds";
    private static final String CLOVER_KEY = "clover";
    private static final String SET_POT_KEY = "set_pot";
    private static final String LEVEL_KEY = "plant_level";
    private static final String PLANT_TYPE_KEY = "plant_type_key";
    private static final String IS_TARGET_COMPLETED = "is_target_completed";

    private List<TimeModel> mList;


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
        return sharedPrefs.getInt(NOTIFICATION_FREQUENCY_KEY, 240);
    }

    public boolean getNewMessage() {
        return sharedPrefs.getBoolean(NOTIFICATION_NEW_MESSAGE, true);
    }

    public String getNotificationToneUri() {
        return sharedPrefs.getString(NOTIFICATION_TONE_URI_KEY, RingtoneManager.getDefaultUri(2).toString());
    }

    public String getNotificationMessage() {
        return sharedPrefs.getString(NOTIFICATION_MSG_KEY, "Lets... Drink some water");
    }

    public void setNotificationStatus(boolean bool) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(NOTIFICATION_STATUS_KEY, bool);
        editor.apply();
    }

    public boolean getNotificationStatus() {
        return sharedPrefs.getBoolean(NOTIFICATION_STATUS_KEY, true);
    }

    public void setIsFirstTime(boolean bool) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_FIRST_TIME_KEY, bool);
        editor.apply();
    }

    public boolean getIsFirstTime() {
        return sharedPrefs.getBoolean(IS_FIRST_TIME_KEY, true);
    }

    public void setIsMusicPlay(boolean bool) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_MUSIC_PlayING_KEY, bool);
        editor.apply();
    }

    public boolean getMusicPlay() {
        return sharedPrefs.getBoolean(IS_MUSIC_PlayING_KEY, true);
    }

    public void setButtonClickSound(boolean bool) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_BUTTON_CLICK_SOUND_KEY, bool);
        editor.apply();
    }

    public boolean getButtonClickSound() {
        return sharedPrefs.getBoolean(IS_BUTTON_CLICK_SOUND_KEY, true);
    }

    public void setDefaultReminderTime(int i) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(REMINDER_TIME_KEY, i);
        editor.apply();
    }

    public int getDefaultReminderTime() {
        return sharedPrefs.getInt(REMINDER_TIME_KEY, 1);
    }

    public void setWeight(int weight) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(WEIGHT_KEY, weight);
        editor.apply();
    }

    public int getWeight() {
        return sharedPrefs.getInt(WEIGHT_KEY, 60);
    }

    public void setTargetWater(int water) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(TARGET_WATER_KEY, water);
        editor.apply();
    }

    public int getTargetWater() {
        return sharedPrefs.getInt(TARGET_WATER_KEY, 3300);
    }

    public void setDefaultCupVolume(String cupVolume) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(DEFAULT_WATER_VOLUME, cupVolume);
        editor.apply();
    }

    public String getDefaultCupVolume() {
        return sharedPrefs.getString(DEFAULT_WATER_VOLUME, 240 + "");
    }

    public void setSafeReminderTime(List<TimeModel> modelList) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String toJson = gson.toJson(modelList);
        editor.putString(SAFE_REMINDER_TIME_KEY, toJson);
        editor.apply();
    }

    public List<TimeModel> getSafeReminderTime() {
        Gson gson = new Gson();
        String json = sharedPrefs.getString(SAFE_REMINDER_TIME_KEY, null);
        Type type = new TypeToken<ArrayList<TimeModel>>() {
        }.getType();

        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList = gson.fromJson(json, type);
        return mList;
    }

    public void setSeeds(int seeds) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(SEEDS_KEY, seeds);
        editor.apply();
    }

    public int getSeeds() {
        return sharedPrefs.getInt(SEEDS_KEY, 1);
    }

    public void setClover(int clover) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(CLOVER_KEY, clover);
        editor.apply();
    }

    public int getClover() {
        return sharedPrefs.getInt(CLOVER_KEY, 1);
    }

    public void setPot(int clover) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(SET_POT_KEY, clover);
        editor.apply();
    }

    public int getPot() {
        return sharedPrefs.getInt(SET_POT_KEY, 1);
    }

    public void setLevel(int level) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(LEVEL_KEY, level);
        editor.apply();
    }

    public int getLevel() {
        return sharedPrefs.getInt(LEVEL_KEY, 1);
    }

    public void setPlantType(int plantType) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(PLANT_TYPE_KEY, plantType);
        editor.apply();
    }

    public int getPlantType() {
        return sharedPrefs.getInt(PLANT_TYPE_KEY, 1);
    }

    public void setIsTargetCompleted(int isTargetCompleted) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(IS_TARGET_COMPLETED, isTargetCompleted);
        editor.apply();
    }

    public int getIsTargetCompleted() {
        return sharedPrefs.getInt(IS_TARGET_COMPLETED, 0);
    }

}
