package com.thoughtworks.android.booking.persistence.server.RequestBody;

/**
 * Created by hxxu on 12/9/15.
 */
public class RoomStatusUpdate {
    private boolean isUsing;

    public RoomStatusUpdate(boolean isUsing) {
        this.isUsing = isUsing;
    }
}
