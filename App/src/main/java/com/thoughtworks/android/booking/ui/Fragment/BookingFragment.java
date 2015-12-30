package com.thoughtworks.android.booking.ui.Fragment;


import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.android.booking.Model.ParseTime;
import com.thoughtworks.android.booking.biz.DatabaseOperation;
import com.thoughtworks.android.booking.ui.MainActivity;
import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.util.Calendar;
import com.thoughtworks.android.booking.StringConstant;

/**
 * Created by hxxu on 12/15/15.
 */
public class BookingFragment extends BaseFragment implements com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{
    private Bundle bundle;
    private Context context;
    private DateTime startTime;

    public BookingFragment(Context context) {
        this.context = context;
        startTime = new DateTime(DateTimeZone.forID(StringConstant.TIME_ZONE_OF_CHINA));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentTitle(bundle.getString("name"));
        getActivity().findViewById(R.id.choose_time_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDateDialoge();
            }
        });

        getActivity().findViewById(R.id.booking_room_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    bookTheRoom();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         bundle = getArguments();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_room_layout, container, false);
    }

    @Override
    protected String getFragmentTitle() {
        return bundle.getString("name");
    }

    private void setFragmentTitle(String title){
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onDateSet(DatePickerDialog datePickerDialog, int i, int i1, int i2) {
        startTime = startTime.withYear(i);
        startTime = startTime.withMonthOfYear(i1+1);
        startTime = startTime.withDayOfMonth(i2);
        startTimeDialog();

    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int i, int i1, int i2) {
        startTime = startTime.withHourOfDay(i);
        startTime = startTime.withMinuteOfHour(i1);
        startTime = startTime.withSecondOfMinute(i2);
        TextView textView = (TextView)getActivity().findViewById(R.id.time_text);
        textView.setText("您预订的房间时间为：" + startTime.toString());
        Button bookButton = (Button)getActivity().findViewById(R.id.booking_room_button);
        bookButton.setVisibility(View.VISIBLE);

    }

    public void startDateDialoge(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this,now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }

    public void startTimeDialog(){
        Calendar now = Calendar.getInstance();
        TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, now.get(Calendar.HOUR), now.get(Calendar.MINUTE), now.get(Calendar.SECOND), true);
        timePickerDialog.show(getFragmentManager(), "TimepickerDialog");
    }

    private void bookTheRoomWithSpecificDevice(String barcode,DateTime startTime){
        DateTime thirtyMinuteLater = startTime.plusMinutes(30);
        ParseTime myStartTime = new ParseTime();
        myStartTime.setIso(startTime.toString());
        myStartTime.set__type("Date");
        ParseTime myEndTime = new ParseTime();
        myEndTime.setIso(thirtyMinuteLater.toString());
        myEndTime.set__type("Date");

        TelephonyManager manager= (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        BookInformation bookInformation = new BookInformation();
        bookInformation.setBarcode(barcode);
        bookInformation.setStartTime(myStartTime);
        bookInformation.setEndTime(myEndTime);
        bookInformation.setDeviceId(manager.getDeviceId());

        new DatabaseOperation().bookRoom(bookInformation);
    }

    private boolean isRoomUsingByCheckBookingInformation(String barcode,DateTime bookingStartTime) throws ParseException {
        Boolean isRoomUsing = false;
        for (BookInformation bookInformation : MainActivity.bookResponse.getResults()) {
            if (bookInformation.getBarcode().equals(barcode) && bookingStartTime.isBefore(bookInformation.getEndTime()) &&
                    bookingStartTime.isAfter(bookInformation.getStartTime())){
                    isRoomUsing = true;
                }
            DateTime bookingEndTime = new DateTime(bookingStartTime.plus(StringConstant.THIRTY_MINUTE));
            if (bookInformation.getBarcode().equals(barcode) && bookingEndTime.isBefore(bookInformation.getEndTime()) &&
                    bookingEndTime.isAfter(bookInformation.getStartTime())){
                    isRoomUsing = true;
            }

        }
        return isRoomUsing;
    }
    public void bookTheRoom() throws ParseException {
        if(!isRoomUsingByCheckBookingInformation(bundle.getString("barcode"),startTime)){
            bookTheRoomWithSpecificDevice(bundle.getString("barcode"),startTime);
            Toast.makeText(context,"Booking room success,thank you!",Toast.LENGTH_SHORT).show();
            getFragmentManager().popBackStackImmediate(MainActivity.class.getSimpleName(),0);
        }else {
            Toast.makeText(context,"Sorry,the room at this time is using, please choose another one",Toast.LENGTH_SHORT).show();
        }
    }
}
