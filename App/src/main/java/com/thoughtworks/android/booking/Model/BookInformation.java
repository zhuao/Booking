package com.thoughtworks.android.booking.Model;

import java.io.Serializable;
import java.sql.Date;


/**
 * Created by hxxu on 11/3/15.
 */
public class BookInformation implements Serializable{
    private String objectId;
    private Date startTime;
    private Date endTime;
    private String deviceId;
    private String roomId;

    public String getObjectId() {
        return objectId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
