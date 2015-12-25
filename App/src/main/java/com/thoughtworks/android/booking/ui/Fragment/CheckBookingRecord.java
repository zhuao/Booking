package com.thoughtworks.android.booking.ui.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.ui.Adapter.BookingRecordAdapter;
import com.thoughtworks.android.booking.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by hxxu on 12/24/15.
 */
public class CheckBookingRecord extends Fragment {
    private View rootView;
    private ListView listView;
    private Context context;

    public CheckBookingRecord(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.check_booking_record_layout,container,false);

        listView = (ListView)rootView.findViewById(R.id.booking_record_view);
        listView.setAdapter(new BookingRecordAdapter(findOutAllTheBookingRecordForUser(MainActivity.bookResponse),context));
        return  rootView;
    }

    private ArrayList<BookInformation> findOutAllTheBookingRecordForUser(BookResponse bookResponse){
        ArrayList<BookInformation> userBookingRecord = new ArrayList<>();
        TelephonyManager manager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceID = manager.getDeviceId();

        for (BookInformation bookInformation : bookResponse.getResults()) {
            if(bookInformation.getDeviceId().equals(deviceID)){
                userBookingRecord.add(bookInformation);
            }
        }
        return userBookingRecord;
    }
}
