package com.thoughtworks.android.booking.ui.Fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.StringConstant;
import com.thoughtworks.android.booking.biz.DatabaseOperation;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.ui.Adapter.BookingRecordAdapter;
import com.thoughtworks.android.booking.ui.MainActivity;

import java.util.ArrayList;

/**
 * Created by hxxu on 12/24/15.
 */
public class CheckBookingRecord extends BaseFragment {
    private View rootView;
    private ListView listView;
    private Context context;
    private ArrayList<BookInformation> userBookingRecord;
    private BookingRecordAdapter recordAdapter;
    public CheckBookingRecord(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBookingRecord = findOutAllTheBookingRecordForUser(MainActivity.bookResponse);
    }

    @Override
    protected String getFragmentTitle() {
        return StringConstant.BOOKING_RECORD_LIST_OF_USER;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.check_booking_record_layout,container,false);
        listView = (ListView)rootView.findViewById(R.id.booking_record_view);
        recordAdapter = new BookingRecordAdapter(userBookingRecord, context);
        recordAdapter.notifyDataSetChanged();
        listView.setAdapter(recordAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setMessage(R.string.alert_for_delete_booking_room).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (userBookingRecord.size() != 0 ){
                            String deletObjectId = userBookingRecord.get(position).getObjectId();
                            userBookingRecord.remove(position);
                            recordAdapter.updateTheDate(userBookingRecord);
                            new DatabaseOperation().deleteBookingInformationAccordingToTheTime(deletObjectId);
                        }
                    }
                });

                alertDialog.show();
            }
        });
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
