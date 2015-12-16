package com.thoughtworks.android.booking.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.thoughtworks.android.booking.AppContent.StringContent;
import com.thoughtworks.android.booking.Database.DatabaseOperation;
import com.thoughtworks.android.booking.MainActivity;
import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.Model.MyTime;
import com.thoughtworks.android.booking.Model.RoomInformation;
import com.thoughtworks.android.booking.R;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.util.List;

/**
 * Created by hxxu on 12/2/15.
 */
public class ScanFragment extends BaseFragment {

    private View rootView;
    private Context context;
    private CompoundBarcodeView compoundBarcodeView;
    private String deviceIDOfUsingRoom;
    private String objecgIDOfUsingRoom;

    public ScanFragment(Context context) {
        this.context = context;
    }

    @Override
    protected String getFragmentTitle() {
        return StringContent.SCAN_BARCODE_FRAGMENT_TITLE;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.room_scan_layout,container,false);
        compoundBarcodeView = (CompoundBarcodeView)rootView.findViewById(R.id.zxing_barcode_scanner);
        compoundBarcodeView.decodeContinuous(barcodeCallback);
        return rootView;
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {

        @Override
        public void barcodeResult(BarcodeResult barcodeResult) {
            compoundBarcodeView.pause();

            if(!isRoomExist(barcodeResult.toString())){
                Toast.makeText(context,"Sorry, the room you can does not exist",Toast.LENGTH_SHORT).show();
                compoundBarcodeView.resume();
                return;
            }

            if (canRoomBeBooked(barcodeResult.toString())){
                bookTheRoomWithSpecificDevice(barcodeResult.toString());
                Toast.makeText(context,"Congratulation,you are the owner of this room now!",Toast.LENGTH_SHORT).show();
            }else {
                TelephonyManager manager= (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
                if(manager.getDeviceId().equals(deviceIDOfUsingRoom)){
                    new DatabaseOperation().deleteBookingInformationAccordingToTheTime(objecgIDOfUsingRoom);
                    Toast.makeText(context,"Congratulation,you choose to leave this room success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context,"Sorry, you can not cancel this room since you are not the owner",Toast.LENGTH_SHORT).show();
                }
            }
            compoundBarcodeView.resume();
        }

        @Override
        public void possibleResultPoints(List<com.google.zxing.ResultPoint> list) {

        }
    };

    private boolean isRoomExist(String barcode) {
        for (RoomInformation roomInformation : MainActivity.roomResponse.getResults()) {
            if(barcode.equals(roomInformation.getBarcode())){
                return true;
            }
        }
        return false;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onStart() {
        super.onStart();
        compoundBarcodeView.resume();

    }

    @Override
    public void onStop() {
        super.onStop();
        compoundBarcodeView.pause();
    }

    private boolean isRoomUsingByCheckBookingInformation(String barcode) throws ParseException {
        DateTime currentTime = new DateTime(DateTimeZone.forID(StringContent.TIME_ZONE_OF_CHINA));

        for (BookInformation bookInformation : MainActivity.bookResponse.getResults()
                ) {
            DateTime startTime = bookInformation.getStartTime();
            DateTime endTime = bookInformation.getEndTime();
            if(bookInformation.getBarcode().equals(barcode) &&
                    currentTime.isBefore(bookInformation.getEndTime()) &&
                    currentTime.isAfter(bookInformation.getStartTime())){
                return true;
            }

        }
        return false;
    }

    private void bookTheRoomWithSpecificDevice(String barcode){
        DateTime currentTime = new DateTime(DateTimeZone.forID(StringContent.TIME_ZONE_OF_CHINA));
        DateTime thirtyMinuteLater = currentTime.plusMinutes(30);
        MyTime startTime = new MyTime();
        startTime.setIso(currentTime.toString());
        startTime.set__type("Date");
        MyTime endTime = new MyTime();
        endTime.setIso(thirtyMinuteLater.toString());
        endTime.set__type("Date");

        TelephonyManager manager= (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        BookInformation bookInformation = new BookInformation();
        bookInformation.setBarcode(barcode);
        bookInformation.setStartTime(startTime);
        bookInformation.setEndTime(endTime);
        bookInformation.setDeviceId(manager.getDeviceId());

        new DatabaseOperation().bookRoom(bookInformation);
    }

    private boolean canRoomBeBooked(String barcode){
        for (BookInformation bookInformation : MainActivity.bookResponse.getResults()) {
            String barcodeOfRoom = bookInformation.getBarcode();
            if(barcode.equals(barcodeOfRoom)){
                try {
                    if(isRoomUsingByCheckBookingInformation(barcodeOfRoom)){
                        deviceIDOfUsingRoom = bookInformation.getDeviceId();
                        objecgIDOfUsingRoom = bookInformation.getObjectId();
                          return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
        return true;
    }

}
