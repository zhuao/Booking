package com.thoughtworks.android.booking.Model;


import com.thoughtworks.android.booking.AppContent.StringContent;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by hxxu on 12/14/15.
 */
public class ParseTime implements Serializable {
    private String iso;
    private String __type;
    public DateTime toDateTime() {
        return new DateTime(iso, DateTimeZone.forID(StringContent.TIME_ZONE_OF_CHINA));
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }
}
