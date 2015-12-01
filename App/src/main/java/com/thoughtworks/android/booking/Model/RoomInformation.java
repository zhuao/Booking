package com.thoughtworks.android.booking.Model;

import com.thoughtworks.android.booking.Server.Response.BookResponse;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;


/**
 * Created by hxxu on 11/3/15.
 */
public class RoomInformation implements Serializable {
    private  String objectId;
    private  String barcode;
//    private Date createdAt;
//    private Date updatedAt;
    private String name;
    private boolean isUsing;
    private Number content;
    private Object material;

    public String getObjectId() {
        return objectId;
    }

    public String getBarcode() {
        return barcode;
    }

//    public Date getCreatedAt() {
//        return createdAt;
//    }
//
//    public Date getUpdatedAt() {
//        return updatedAt;
//    }

    public String getName() {
        return name;
    }

    public Number getContent() {
        return content;
    }

    public Object getMaterial() {
        return material;
    }



    public boolean isUsing() {
        return isUsing;
    }
}
