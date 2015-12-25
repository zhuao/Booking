package com.thoughtworks.android.booking.ui.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.Model.RoomInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by hxxu on 12/24/15.
 */
public class BookingRecordAdapter extends BaseAdapter {
    private ArrayList<BookInformation> bookInformationsRecord;
    private Context context;

    public BookingRecordAdapter(ArrayList<BookInformation> bookInformationsRecord,Context context) {
        this.bookInformationsRecord = bookInformationsRecord;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bookInformationsRecord.size();
    }

    @Override
    public Object getItem(int position) {
        return bookInformationsRecord.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater  = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.booking_record_card_view,null);
        TextView nameOfRoom = (TextView)convertView.findViewById(R.id.room_name);
        TextView bookingTime = (TextView)convertView.findViewById(R.id.book_time);
        nameOfRoom.setText(getTheNameOfRoom(bookInformationsRecord.get(position).getBarcode()));
        bookingTime.setText("使用时间为："+bookInformationsRecord.get(position).getStartTime() + "~~~~" + bookInformationsRecord.get(position).getEndTime());
        return convertView;
    }

    private String getTheNameOfRoom(String barcode){
        for (RoomInformation roomInformation: MainActivity.roomResponse.getResults()
             ) {
            if (roomInformation.getBarcode().equals(barcode)){
                return roomInformation.getName();
            }
        }
        return null;
    }
}
