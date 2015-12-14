package com.thoughtworks.android.booking.Server.Response;


import com.thoughtworks.android.booking.Model.BookInformation;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


/**
 * Created by hxxu on 11/17/15.
 */
public class BookResponse{
    private List<BookInformation> results;

    public List<BookInformation> getResults() {
        return results;
    }

    public void emptyBookResponse(){
        if(results != null){
            results = new ArrayList<>();
        }
    }

    public String getBookingIDAccordingToTheTime(String roomId, Date startTime, Date endTime)
    {
//        for (BookInformation bookInformation : results) {
//            if (bookInformation.getRoomId().equals(roomId) && bookInformation.getStartTime() == startTime
//                    && bookInformation.getEndTime() == endTime) {
//                return bookInformation.getObjectId();
//            } else {
//                return null;
//            }
//        }
        return null;
    }
}
