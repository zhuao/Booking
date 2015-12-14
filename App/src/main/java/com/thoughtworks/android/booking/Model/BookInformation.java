package com.thoughtworks.android.booking.Model;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import org.joda.time.DateTime;


/**
 * Created by hxxu on 11/3/15.
 */
public class BookInformation implements Serializable{
    private String objectId;
    private MyTime startTime;
    private MyTime endTime;
    private String barcode;
    private String deviceId;
    private String roomId;



    public String getBarcode() {
        return barcode;
    }

    public String getObjectId() {
        return objectId;
    }

    public DateTime getStartTime() throws ParseException {
        return startTime.getIso();
    }

    public DateTime getEndTime() throws ParseException {
        return endTime.getIso();
    }

    public String getRoomId() {
        return roomId;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
