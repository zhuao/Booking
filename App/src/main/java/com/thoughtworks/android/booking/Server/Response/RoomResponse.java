package com.thoughtworks.android.booking.Server.Response;

import com.thoughtworks.android.booking.Model.RoomInformation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hxxu on 11/17/15.
 */
public class RoomResponse {
    private List<RoomInformation> results;

    public void emptyRoomResponse(){
        if(results != null) {
            results = new ArrayList<>();
        }
    }
    public List<RoomInformation> getResults() {
        return results;
    }
}
