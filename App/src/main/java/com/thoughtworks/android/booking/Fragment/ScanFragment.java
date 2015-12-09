package com.thoughtworks.android.booking.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.thoughtworks.android.booking.Model.RoomInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.RequestBody.RoomStatusUpdate;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;

import java.util.List;

/**
 * Created by hxxu on 12/2/15.
 */
public class ScanFragment extends BaseFragment {
    private View rootView;
    private Context context;
    private CompoundBarcodeView compoundBarcodeView;

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
            for (RoomInformation roomInformation : MainActivity.roomResponse.getResults()) {
                if(roomInformation.getBarcode().equals(barcodeResult.toString())){
                       if(roomInformation.isUsing()){
                           Toast.makeText(context,"Sorry, you are not the owner of this room now",Toast.LENGTH_SHORT).show();
                           setTheRoomToBeUnUse(roomInformation.getObjectId());
                           compoundBarcodeView.resume();
                       }else {
                           setTheRoomToBeUse(roomInformation.getObjectId());
                           Toast.makeText(context,"Congratulation,you are the owner of this room now!",Toast.LENGTH_SHORT).show();

                       }
                }
            }
        }

        @Override
        public void possibleResultPoints(List<com.google.zxing.ResultPoint> list) {

        }
    };


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

    public void onMainEventThread(RoomResponse roomResponse){
        MainActivity.roomResponse = roomResponse;
    }

    public void setTheRoomToBeUse(String objectID){
        RoomStatusUpdate roomStatusUpdate = new RoomStatusUpdate(true);
        DatabaseOperation.updateRoomStatus(objectID,roomStatusUpdate);
    }

    public void setTheRoomToBeUnUse(String objectID){
        RoomStatusUpdate roomStatusUpdate = new RoomStatusUpdate(false);
        DatabaseOperation.updateRoomStatus(objectID,roomStatusUpdate);
    }


}
