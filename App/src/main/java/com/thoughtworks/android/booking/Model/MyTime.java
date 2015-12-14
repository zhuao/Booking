package com.thoughtworks.android.booking.Model;


import org.joda.time.DateTime;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;



/**
 * Created by hxxu on 12/14/15.
 */
public class MyTime implements Serializable {
    private String iso;
    public DateTime getIso() throws ParseException {
        return new DateTime(iso);
    }
}
