package my.edu.upm.msfapp.fcm;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import my.edu.upm.msfapp.R;
import my.edu.upm.msfapp.ui.SplashScreenActivity;


public class MyFcmListenerService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";

    /**
     * Called when message is received.
     * Note: There are conditions where this method will not be call due to Firebase handled on your behalf
     * https://firebase.google.com/docs/cloud-messaging/android/receive#sample-receive
     *
     * @param message Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage message) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + message.getFrom());

        // Check if message contains a data payload.
        if (message.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + message.getData());
        }

        // Check if message contains a notification payload.
        if (message.getNotification() != null) {
            // NOTE: in Firebase console this is the "Message Text" field
            Log.d(TAG, "Message Notification Body: " + message.getNotification().getBody());
        }

        Map<String, String> data = message.getData();


        int notificationType = Integer.parseInt(data.get("NotificationType"));
        sendNotification(data);


    }
    // [END receive_message]

    private void sendNotification(Map<String, String> data) {
       Intent intent = new Intent(this, SplashScreenActivity.class);
        if (intent != null) {
            PendingIntent pi;
            int id = (int) (Math.random() * 100000);
            pi = PendingIntent.getActivity(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            displayNotification(pi, getString(R.string.app_name), getContentText(data), data);
        }
    }



    private String getContentText(Map<String, String> data) {
        String text = data.get("Text");
        if (TextUtils.isEmpty(text)) {
            text = data.get("Title");
        }

        return text;
    }


    // Put the message into a notification and post it.
    // This is just one simple example of what you might choose to do with
    // a GCM message.
    private void displayNotification(PendingIntent pi, String title, String msg, Map<String, String> data) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
//                        .setSmallIcon(R.mipmap.appicon)
//                        .setLargeIcon(getBitmap(R.mipmap.appicon))
//                        .setColor(ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                        .setContentTitle(title)
                        .setContentText(msg)
                        .setContentIntent(pi)
                        .setTicker(msg)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL);

        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify((int) (Math.random() * 10000), mBuilder.build());
    }

    private Bitmap getBitmap(int resId) {
        int mLargeIconWidth = (int) getResources().getDimension(android.R.dimen.notification_large_icon_width);
        int mLargeIconHeight = (int) getResources().getDimension(android.R.dimen.notification_large_icon_height);

        Drawable d = ContextCompat.getDrawable(getApplicationContext(), resId);
        Bitmap b = Bitmap.createBitmap(mLargeIconWidth, mLargeIconHeight, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        d.setBounds(0, 0, mLargeIconWidth, mLargeIconHeight);
        d.draw(c);

        return b;
    }

}
