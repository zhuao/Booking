package com.thoughtworks.android.booking.Server;


import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.Server.Response.UserResponse;

import retrofit.Callback;
import retrofit.http.GET;

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
}
