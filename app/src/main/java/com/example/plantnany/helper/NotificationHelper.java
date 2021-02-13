package com.example.plantnany.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


import com.example.plantnany.R;
import com.example.plantnany.activities.MainActivity;
import com.example.plantnany.sharedpref.SharedPreferencesManager;

import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.FLAG_UPDATE_CURRENT;

public final class NotificationHelper {
    private final String CHANNEL_ONE_ID = "com.example.plantnany";
    private final String CHANNEL_ONE_NAME = "Channel One";
    private final Context ctx;
    private NotificationManager notificationManager;

    public NotificationHelper(Context ctx) {
        this.ctx = ctx;
    }

    public final Context getCtx() {
        return ctx;
    }

    private final void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String notificationsNewMessageRingtone = SharedPreferencesManager.getInstance(ctx).getNotificationToneUri();
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ONE_ID, CHANNEL_ONE_NAME, NotificationManager.IMPORTANCE_HIGH);
            boolean z = true;
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(-16776961);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(1);

            if (notificationsNewMessageRingtone.length() <= 0) {
                z = false;
            }
            if (z) {
                notificationChannel.setSound(Uri.parse(notificationsNewMessageRingtone), new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_NOTIFICATION).build());
            }
            NotificationManager manager = getManager();
            manager.createNotificationChannel(notificationChannel);
        }
    }

    public final NotificationCompat.Builder getNotification(String title, String body, String notificationsTone) {

        createChannels();
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this.ctx.getApplicationContext(), this.CHANNEL_ONE_ID).setContentTitle(title).setContentText(body).setLargeIcon(BitmapFactory.decodeResource(this.ctx.getResources(), R.mipmap.ic_launcher)).setSmallIcon(R.drawable.ic_small_notification).setAutoCancel(true);
        notification.setShowWhen(true);
        notification.setSound(Uri.parse(notificationsTone));
        Intent notificationIntent = new Intent(this.ctx, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notification.setContentIntent(PendingIntent.getActivity(this.ctx, 99, notificationIntent, FLAG_UPDATE_CURRENT));
        return notification;
    }


    private final long compareTimes(Date currentTime, Date timeToRun) {
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentTime);
        Calendar runCal = Calendar.getInstance();
        runCal.setTime(timeToRun);
        runCal.set(5, currentCal.get(5));
        runCal.set(2, currentCal.get(2));
        runCal.set(1, currentCal.get(1));
        return currentCal.getTimeInMillis() - runCal.getTimeInMillis();
    }

    public final void notify(long id, NotificationCompat.Builder notification) {
        if (notification != null) {
            NotificationManager manager = getManager();
            int i = (int) id;
            manager.notify(i, notification.build());
        }
    }

    private final NotificationManager getManager() {
        if (this.notificationManager == null) {
            Object systemService = this.ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            if (systemService != null) {
                this.notificationManager = (NotificationManager) systemService;
            } else {
            }
        }
        return this.notificationManager;
    }
}
