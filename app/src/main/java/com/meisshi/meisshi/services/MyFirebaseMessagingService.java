package com.meisshi.meisshi.services;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.meisshi.meisshi.R;

/**
 * Created by DevAg on 12/11/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("MyService", "remote");

        if (remoteMessage.getNotification() != null) {
            String message = remoteMessage.getData().get("alert");
            String userId = remoteMessage.getData().get("user_id");
            showNotification(
                    remoteMessage.getNotification().getTitle(),
                    message,
                    userId
            );
        }
    }

    // Quitar http://
    // Quitar
    // Continuea bloqueaada bar despues de editar

    private void showNotification(String title, String body, String userId) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(uri);

        // getExtra data from

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notBuilder.build());
    }
}
