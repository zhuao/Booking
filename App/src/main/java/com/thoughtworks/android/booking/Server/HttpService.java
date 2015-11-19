package com.thoughtworks.android.booking.Server;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by hxxu on 11/16/15.
 */
public class HttpService {
    private static ServerInterface serverInterface;
    public ServerInterface build(){
        return  getServerInterface();
    }

    private ServerInterface getServerInterface() {
         if(serverInterface == null) {
             serverInterface = buildRestAdapter().create(ServerInterface.class);
         }
        return serverInterface;
    }

    private RestAdapter buildRestAdapter(){
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setEndpoint("https://api.parse.com");
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        builder.setRequestInterceptor(createRequestInterceptor());

        return builder.build();
    }

    private RequestInterceptor createRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Content-Type", "application/json");
                request.addHeader("X-Parse-Application-Id", "mvcUXxqDNLaENIol9bD494Tj1mZaaaNXjq6HPQMS");
                request.addHeader("X-Parse-REST-API-Key", "7bmXn7VK3lNcf5H89d35WKOpk9AtGlyq0XnFSsA6");
            }
        };
    }
}
