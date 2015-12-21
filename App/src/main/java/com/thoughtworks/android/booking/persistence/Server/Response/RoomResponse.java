package com.thoughtworks.android.booking.persistence.server.Response;

import com.thoughtworks.android.booking.Model.RoomInformation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hxxu on 11/17/15.
 */
public class RoomResponse {
    private List<RoomInformation> results;

    public RoomResponse() {
        this.results = new ArrayList<>();
    }

    public void emptyRoomResponse(){
        if(results != null) {
            results = new ArrayList<>();
        }
    }
    public List<RoomInformation> getResults() {
        return results;
    }

    public String getRoomIDAccoringToTheRoomName(String roomName){
        for (RoomInformation roomInformation:results) {
            if(roomInformation.getName() == roomName) {
                return roomInformation.getObjectId();
            }else {
                return null;
            }
        }
        return null;
    }

}
