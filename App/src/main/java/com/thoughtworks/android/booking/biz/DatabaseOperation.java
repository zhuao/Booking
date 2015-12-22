package com.thoughtworks.android.booking.biz;

import android.util.Log;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.persistence.Server.RequestBody.RoomStatusUpdate;
import com.thoughtworks.android.booking.persistence.Server.HttpCallBack;
import com.thoughtworks.android.booking.persistence.Server.HttpService;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.persistence.Server.Response.UserResponse;
import com.thoughtworks.android.booking.persistence.Server.ServerInterface;

import de.greenrobot.event.EventBus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by hxxu on 11/3/15.
 */
public class DatabaseOperation {
    private static final String TAG = DatabaseOperation.class.getSimpleName();

    private static ServerInterface httpInterface;

    public DatabaseOperation() {
        if (httpInterface == null) {
            this.httpInterface = new HttpService().build();
        }
    }

    public void getRoomInformation(){
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

    public void updateRoomStatus(String objectID,RoomStatusUpdate status){
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

    public BookResponse getBookingInformation(){
        //TODO: add currentTime and deviceId as parameter
        return httpInterface.getBookingData();
    }

    public void getUserInformation(){
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

    public void deleteBookingInformationAccordingToTheTime(String objectID){
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

    public void bookRoom(BookInformation bookInformation){
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
