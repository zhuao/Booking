package com.thoughtworks.android.booking.Server.Response;

import java.util.ArrayList;
import java.util.List;

import practice.andriod.com.boro.Model.UserInformation;

/**
 * Created by hxxu on 11/17/15.
 */
public class UserResponse {
    private List<UserInformation> results;

    public List<UserInformation> getResults() {
        return results;
    }

    public void emmptyUserResponse(){
        if(results != null){
            results = new ArrayList<>();
        }
    }
}
