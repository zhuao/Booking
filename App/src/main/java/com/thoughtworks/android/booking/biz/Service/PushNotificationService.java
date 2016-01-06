package com.thoughtworks.android.booking.biz.Service;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by hxxu on 1/6/16.
 */
public class PushNotificationService extends BroadcastReceiver {
    private static final String TAG = PushNotificationService.class.getSimpleName();
    private NotificationManager notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (null == notificationManager){
            notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            Log.d(TAG, "JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的自定义消息");

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "接受到推送下来的通知");

            Bundle bundle = intent.getExtras();
            Log.d(TAG,bundle.getString(JPushInterface.EXTRA_MESSAGE));
            Toast.makeText(context,bundle.getString(JPushInterface.EXTRA_MESSAGE),Toast.LENGTH_SHORT).show();
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击打开了通知");
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }

    }
}
