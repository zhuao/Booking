package com.thoughtworks.android.booking.Model;


import com.thoughtworks.android.booking.StringConstant;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


import java.io.Serializable;


/**
 * Created by hxxu on 12/14/15.
 */
public class ParseTime implements Serializable {
    private String iso;
    private String __type;
    public DateTime toDateTime() {
        return new DateTime(iso, DateTimeZone.forID(StringConstant.TIME_ZONE_OF_CHINA));
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }
}
