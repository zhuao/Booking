package com.thoughtworks.android.booking.persistence.server;


import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.persistence.server.RequestBody.RoomStatusUpdate;
import com.thoughtworks.android.booking.persistence.server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.server.Response.RoomResponse;
import com.thoughtworks.android.booking.persistence.server.Response.UserResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by hxxu on 11/16/15.
 */
public interface ServerInterface  {
    @GET("/1/classes/Room")
    void getRoomData(Callback<RoomResponse> callback);

    @GET("/1/classes/SimpleUser")
    void getUserData(Callback<UserResponse> callback);

    @GET("/1/classes/Book")
    BookResponse getBookingData();

    @DELETE("/1/classes/Book/{param}")
    void deleteSignleBookingInformation(@Path("param")String objectID,Callback<BookResponse> callback);

    @PUT("/1/classes/Room/{param}")
    void updateRoomStatus(@Path("param")String objectID, @Body RoomStatusUpdate status, Callback<HttpCallBack> callback);

    @POST("/1/classes/Book")
    void bookRoom(@Body BookInformation bookInformation, Callback<HttpCallBack> callback);

}
