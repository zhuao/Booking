package com.thoughtworks.android.booking.Database;

import android.util.Log;

import com.thoughtworks.android.booking.Server.HttpService;
import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.Server.Response.UserResponse;
import com.thoughtworks.android.booking.Server.ServerInterface;

import de.greenrobot.event.EventBus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by hxxu on 11/3/15.
 */
public class DatabaseOperation {
    private static ServerInterface httpInterface;

    public DatabaseOperation() {
        this.httpInterface = new HttpService().build();
    }

    public static void getRoomInformation(){
         httpInterface.getRoomData(new Callback<RoomResponse>() {
             @Override
             public void success(RoomResponse roomResponse, Response response) {
                 EventBus.getDefault().post(roomResponse);
                 Log.d("operation", roomResponse.toString());
             }

             @Override
             public void failure(RetrofitError error) {
                 Log.d("HttpOperation",error.getMessage());
             }
         });
    }

    public static void getBookingInformation(){
        httpInterface.getBookingData(new Callback<BookResponse>() {
            @Override
            public void success(BookResponse bookResponse, Response response) {
                EventBus.getDefault().post(bookResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("HttpOperation", "Get Booking Information Error");
            }
        });
    }

    public static void getUserInformation(){
        httpInterface.getUserData(new Callback<UserResponse>() {
            @Override
            public void success(UserResponse userResponse, Response response) {
                EventBus.getDefault().post(userResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("HttpOperation", "Get User Information Error");
            }
        });
    }

    public static void deleteBookingInformationAccordingToTheTime(String objectID){
        httpInterface.deleteSignleBookingInformation(objectID, new Callback<BookResponse>() {
            @Override
            public void success(BookResponse bookResponse, Response response) {
                Log.d("HttpOperation", "Delete booking information success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("HttpOperation", "Delete booking information error");
            }
        });
    }
}
