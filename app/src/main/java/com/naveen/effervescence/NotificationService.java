package com.naveen.effervescence;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;

/**
 * Created by Naveen on 21-06-2016.
 */
public class NotificationService extends Service{
    private NotificationManager mManager;

    public IBinder onBind(Intent arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public void onCreate()
    {
        // TODO Auto-generated method stub
        super.onCreate();
    }

    public void onStart(Intent intent, int startId)
    {
        super.onStart(intent, startId);

        mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(this.getApplicationContext(),Tab3.class);

        Notification notification = new Notification(R.drawable.ic_menu_camera,"This is a test message!", System.currentTimeMillis());
        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //notification.setLatestEventInfo(this.getApplicationContext(), "AlarmManagerDemo", "This is a test message!", pendingNotificationIntent);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        String contentTitle = "AlarmManagerDemo";
        String contentText = "This is a text message!";
        // First, ensure that key pieces of information that may have been set directly
        // are preserved
        builder.setWhen(notification.when);
        builder.setSmallIcon(notification.icon);
        builder.setPriority(notification.priority);
        builder.setTicker(notification.tickerText);
        builder.setNumber(notification.number);
        //builder.setColor(notification.color);
        //builder.mFlags = notification.flags;
        builder.setSound(notification.sound, notification.audioStreamType);
        builder.setDefaults(notification.defaults);
        builder.setVibrate(notification.vibrate);
        builder.setDeleteIntent(notification.deleteIntent);

        // now apply the latestEventInfo fields
        if (contentTitle != null) {
            builder.setContentTitle(contentTitle);
        }
        if (contentText != null) {
            builder.setContentText(contentText);
        }
        builder.setContentIntent(pendingNotificationIntent);
        notification = builder.build();
        mManager.notify(0, notification);
    }

    public void onDestroy()
    {
        // TODO Auto-generated method stub
        super.onDestroy();
    }
}
