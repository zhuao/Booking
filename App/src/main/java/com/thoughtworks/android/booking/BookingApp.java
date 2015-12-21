package com.thoughtworks.android.booking;

import android.app.Application;
import android.content.Intent;

import com.thoughtworks.android.booking.biz.Service.ReminderNotificationService;

public class BookingApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ReminderNotificationService.class));
    }
}
