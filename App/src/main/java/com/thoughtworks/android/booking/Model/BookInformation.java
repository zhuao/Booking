package com.thoughtworks.android.booking.Model;
import java.io.Serializable;

import org.joda.time.DateTime;


/**
 * Created by hxxu on 11/3/15.
 */
public class BookInformation implements Serializable{
    private String objectId;
    private ParseTime startTime;
    private ParseTime endTime;
    private String barcode;
    private String deviceId;
    private String roomId;
    private boolean cancelled = false;


    public String getBarcode() {
        return barcode;
    }

    public String getObjectId() {
        return objectId;
    }

    public DateTime getStartTime() {
        return startTime.toDateTime();
    }

    public DateTime getEndTime() {
        return endTime.toDateTime();
    }

    public String getRoomId() {
        return roomId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setStartTime(ParseTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(ParseTime endTime) {
        this.endTime = endTime;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
