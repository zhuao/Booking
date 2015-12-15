package com.thoughtworks.android.booking.Database;

import android.util.Log;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.Server.RequestBody.RoomStatusUpdate;
import com.thoughtworks.android.booking.Server.HttpCallBack;
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
    private static final String TAG = "HttpOperation";

    public DatabaseOperation() {
        this.httpInterface = new HttpService().build();
    }

    public static void getRoomInformation(){
         httpInterface.getRoomData(new Callback<RoomResponse>() {
             @Override
             public void success(RoomResponse roomResponse, Response response) {
                 EventBus.getDefault().post(roomResponse);
                 Log.d(TAG, roomResponse.toString());
             }

             @Override
             public void failure(RetrofitError error) {
                 Log.d(TAG,error.getMessage());
             }
         });
    }

    public static void updateRoomStatus(String objectID,RoomStatusUpdate status){
        httpInterface.updateRoomStatus(objectID, status, new Callback<HttpCallBack>() {
            @Override
            public void success(HttpCallBack httpCallBack, Response response) {
                Log.d(TAG, "updtae room status success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "updtae room status fails");

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
                Log.d(TAG, error.getMessage());
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
                Log.d(TAG, "Get User Information Error");
            }
        });
    }

    public static void deleteBookingInformationAccordingToTheTime(String objectID){
        httpInterface.deleteSignleBookingInformation(objectID, new Callback<BookResponse>() {
            @Override
            public void success(BookResponse bookResponse, Response response) {
                Log.d(TAG, "Delete booking information success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Delete booking information error");
            }
        });
    }

    public static void bookRoom(BookInformation bookInformation){
        httpInterface.bookRoom(bookInformation, new Callback<HttpCallBack>() {
            @Override
            public void success(HttpCallBack httpCallBack, Response response) {
                Log.d(TAG, "Book room success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Book room failed");
            }
        });

    }
}
