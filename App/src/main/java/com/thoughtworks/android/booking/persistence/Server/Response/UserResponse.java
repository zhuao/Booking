package com.thoughtworks.android.booking.persistence.server.Response;

import com.thoughtworks.android.booking.Model.UserInformation;

import java.util.ArrayList;
import java.util.List;


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
