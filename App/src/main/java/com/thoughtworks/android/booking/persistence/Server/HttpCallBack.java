package com.thoughtworks.android.booking.persistence.Server;

import android.util.Log;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by hxxu on 11/16/15.
 */
public class HttpCallBack<ResponseModel> implements Callback<ResponseModel>{

    @Override
    public void success(ResponseModel responseModel, Response response) {
         Log.d("Http","Get Response success");
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("Http","Get Response failed");

    }
}
