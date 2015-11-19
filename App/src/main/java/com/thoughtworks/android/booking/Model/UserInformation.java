package com.thoughtworks.android.booking.Model;

import java.io.Serializable;
import java.sql.Date;


/**
 * Created by hxxu on 11/3/15.
 */
public class UserInformation implements Serializable {
    private String objectId;
    private String name;
    private Date createdAt;
    private Date updateAt;
    private Number phone;

    public String getObjectId() {
        return objectId;
    }

    public String getName() {
        return name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public Number getPhone() {
        return phone;
    }
}
