package com.example.myfitnesstracker.view.activities;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.content.SharedPreferences;

import com.example.myfitnesstracker.R;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
         SharedPreferences settings = context.getSharedPreferences("mysettings", Context.MODE_PRIVATE);
        boolean notifyEnabled = settings.getBoolean("Notification", true);
        if (notifyEnabled) {
        Intent i = new Intent(context, MainActivity0.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "android")
                .setContentTitle("Reminder")
                .setContentText("It's time to fill out the Day Query!")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123, builder.build());
        }
    }
}
