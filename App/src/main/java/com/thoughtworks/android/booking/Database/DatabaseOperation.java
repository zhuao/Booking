package com.thoughtworks.android.booking.Database;

import android.util.Log;

import com.thoughtworks.android.booking.Server.HttpService;
import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.Server.Response.UserResponse;
import com.thoughtworks.android.booking.Server.ServerInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import de.greenrobot.event.EventBus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by hxxu on 11/3/15.
 */
public class DatabaseOperation {
    private ServerInterface httpInterface;

    public DatabaseOperation() {
        this.httpInterface = new HttpService().build();
    }

    public void getRoomInformation(){
         httpInterface.getRoomData(new Callback<RoomResponse>() {
             @Override
             public void success(RoomResponse roomResponse, Response response) {
                 EventBus.getDefault().post(roomResponse);
             }

             @Override
             public void failure(RetrofitError error) {
                 Log.d("HttpOperation","Get Room Information Error");
             }
         });
    }

    public void getBookingInformation(){
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

    public void getUserInformation(){
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

    public void deleteBookingInformationAccordingToTheTime(String objectID){
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
