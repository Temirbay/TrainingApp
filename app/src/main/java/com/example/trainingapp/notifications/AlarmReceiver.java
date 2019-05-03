package com.example.trainingapp.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                LocalData localData = new LocalData(context);
                NotificationScheduler.cancelReminder(context, AlarmReceiver.class);
                for (Integer day : localData.getDays()) {
                    NotificationScheduler.setReminder(
                            context,
                            AlarmReceiver.class,
                            localData.getHour(),
                            localData.getMin(),
                            day
                    );
                }
                return;
            }
        }

        NotificationScheduler.showNotification(context, NotificationsActivity.class,
                "You have 5 unwatched videos", "Watch them now?");
    }

}
