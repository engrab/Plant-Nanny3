package com.example.plantnany.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.plantnany.helper.AlarmHelper;
import com.example.plantnany.sharedpref.SharedPreferencesManager;


public final class BootReceiver extends BroadcastReceiver {
    private final AlarmHelper alarm = new AlarmHelper();

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null ) {
            this.alarm.cancelAlarm(context);
            if (SharedPreferencesManager.getInstance(context).getNewMessage()) {
                this.alarm.setAlarm(context, SharedPreferencesManager.getInstance(context).getNotificationFrequency());
            }
        }
    }
}
