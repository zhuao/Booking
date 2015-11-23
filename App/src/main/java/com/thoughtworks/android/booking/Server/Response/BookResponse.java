package com.thoughtworks.android.booking.Server.Response;

import com.thoughtworks.android.booking.Model.BookInformation;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hxxu on 11/17/15.
 */
public class BookResponse {
    private List<BookInformation> results;

    public List<BookInformation> getResults() {
        return results;
    }

    public void emptyBookResponse(){
        if(results != null){
            results = new ArrayList<>();
        }
    }
}
