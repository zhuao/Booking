package com.thoughtworks.android.booking.biz.Service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;

import com.thoughtworks.android.booking.biz.DatabaseOperation;
import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.persistence.server.Response.BookResponse;

public class ReminderNotificationService extends IntentService {

    private static final int MIN = 60 * 1000;
    private static final int LAST_FIVE_MINS = 0;
    private Handler handler;
    private String device_id;

    public ReminderNotificationService(String name) {
        super(name);
    }

    public ReminderNotificationService() {
        super(ReminderNotificationService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initAlarm();
        regularUpdateAlarm();
    }

    private void regularUpdateAlarm() {
        updateAlarm();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                regularUpdateAlarm();
            }
        }, 60 * MIN);
    }

    private void updateAlarm() {
        BookResponse bookResponse = new DatabaseOperation().getBookingInformation();
        if (bookResponse.getResults().isEmpty()) {
            return;
        }

        for (BookInformation bookInfo : bookResponse.getResults()) {
            if (!device_id.equals(bookInfo.getDeviceId())) {
                continue;
            }
            if (bookInfo.isCancelled()) {
                handler.removeMessages(LAST_FIVE_MINS, bookInfo);
            } else {
                if (!handler.hasMessages(LAST_FIVE_MINS, bookInfo)) {
                    handler.sendMessageAtTime(handler.obtainMessage(LAST_FIVE_MINS, bookInfo), bookInfo.getEndTime().getMillis() - 5 * MIN);
                }
            }
        }
    }

    private void initAlarm() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == LAST_FIVE_MINS) {
                    BookInformation bookInformation = (BookInformation) msg.obj;
                    createNotification(bookInformation);
                    return true;
                }
                return false;
            }
        });
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        device_id = tm.getDeviceId();
    }

    private void createNotification(BookInformation bookInformation) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(android.R.drawable.ic_notification_clear_all)
                        .setContentTitle("Five minutes left")
                        .setAutoCancel(true);
// Creates an explicit intent for an Activity in your app
//        Intent resultIntent = new Intent(this, ResultActivity.class);
//
//// The stack builder object will contain an artificial back stack for the
//// started Activity.
//// This ensures that navigating backward from the Activity leads out of
//// your application to the Home screen.
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//// Adds the back stack for the Intent (but not the Intent itself)
//        stackBuilder.addParentStack(ResultActivity.class);
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent =
//                stackBuilder.getPendingIntent(
//                        0,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );

//        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(bookInformation.getObjectId().hashCode(), mBuilder.build());
    }
}
