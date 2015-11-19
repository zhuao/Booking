package com.thoughtworks.android.booking.Model;

import java.io.Serializable;
import java.sql.Date;


/**
 * Created by hxxu on 11/3/15.
 */
public class BookInformation implements Serializable{
    private String objectId;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private Date startTime;
    private Date endTime;
    private Number userID;

    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Number getUserID() {
        return userID;
    }
}
