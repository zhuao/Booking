package com.thoughtworks.android.booking.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.StringConstant;
import com.thoughtworks.android.booking.biz.DatabaseOperation;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.Server.Response.RoomResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import de.greenrobot.event.EventBus;


/**
 * Created by hxxu on 12/29/15.
 */
public class SplashScreenActivity extends Activity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);


    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        new DatabaseOperation().getRoomInformation();
        new DatabaseOperation().getBookingInformationAsynchronious();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(BookResponse bookResponse){
        MainActivity.bookResponse = bookResponse;
        deleteTheOldBookingInformation();
        Intent mainActivityIntent = new Intent(this,MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    public void onEventMainThread(RoomResponse roomResponse){
        MainActivity.roomResponse = roomResponse;
    }

    public void deleteTheOldBookingInformation(){
        DateTime currentTime = new DateTime(DateTimeZone.forID(StringConstant.TIME_ZONE_OF_CHINA));
        for (BookInformation bookInformation:MainActivity.bookResponse.getResults()) {
            if(bookInformation.getEndTime().isBefore(currentTime)){
                new DatabaseOperation().deleteBookingInformationAccordingToTheTime(bookInformation.getObjectId());
            }
        }
    }
}
