package com.thoughtworks.android.booking.Model;

import java.io.Serializable;
import java.sql.Date;


/**
 * Created by hxxu on 11/3/15.
 */
public class RoomInformation implements Serializable {
    private  String objectId;
    private  Number barcode;
    private Date createdAt;
    private Date updatedAt;
    private String name;

    public String getObjectId() {
        return objectId;
    }

    public Number getBarcode() {
        return barcode;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public String getName() {
        return name;
    }

    public Number getContent() {
        return content;
    }

    public Material getMaterial() {
        return material;
    }

    private Number content;
    private Material material;
}
