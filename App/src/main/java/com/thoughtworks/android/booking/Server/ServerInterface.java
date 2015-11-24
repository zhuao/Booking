package com.thoughtworks.android.booking.Server;


import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.Server.Response.UserResponse;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by hxxu on 11/16/15.
 */
public interface ServerInterface  {
    @GET("/1/classes/Room")
    void getRoomData(Callback<RoomResponse> callback);

    @GET("/1/classes/SimpleUser")
    void getUserData(Callback<UserResponse> callback);

    @GET("/1/classes/Book")
    void getBookingData(Callback<BookResponse> callback);

    @DELETE("/1/classes/Book/{param}")
    void deleteSignleBookingInformation(@Path("param")String objectID,Callback<BookResponse> callback);
}
