package com.thoughtworks.android.booking.Server.Response;

import java.util.ArrayList;
import java.util.List;

import practice.andriod.com.boro.Model.BookInformation;

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
