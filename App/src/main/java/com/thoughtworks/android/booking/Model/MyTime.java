package com.thoughtworks.android.booking.Model;


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
public class MyTime implements Serializable {
    private String iso;
    private String __type;
    public DateTime getIso() throws ParseException {
        return new DateTime(iso, DateTimeZone.forID("PRC"));
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public void set__type(String __type) {
        this.__type = __type;
    }
}
