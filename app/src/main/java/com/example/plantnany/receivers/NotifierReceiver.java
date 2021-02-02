package com.example.plantnany.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;

import androidx.core.app.NotificationCompat;

import com.example.plantnany.R;
import com.example.plantnany.helper.NotificationHelper;
import com.example.plantnany.sharedpref.SharedPreferencesManager;


public final class NotifierReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder mBuilder;
        String title = context.getResources().getString(R.string.app_name);
        String messageToShow = SharedPreferencesManager.getInstance(context).getNotificationMessage();
        NotificationHelper mHelper = new NotificationHelper(context);
        if (messageToShow != null) {
            mBuilder = mHelper.getNotification(title, messageToShow, SharedPreferencesManager.getInstance(context).getNotificationToneUri());
        } else {
            mBuilder = null;
        }
        mHelper.notify(1, mBuilder);
    }
}
