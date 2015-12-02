package com.thoughtworks.android.booking.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.thoughtworks.android.booking.AppContent.StringContent;
import com.thoughtworks.android.booking.R;

import java.util.List;

/**
 * Created by hxxu on 12/2/15.
 */
public class ScanFragment extends BaseFragment {
    private View rootView;
    private CaptureManager captureManager;
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
        CompoundBarcodeView compoundBarcodeView = (CompoundBarcodeView)rootView.findViewById(R.id.zxing_barcode_scanner);
        compoundBarcodeView.decodeContinuous(barcodeCallback);

        return rootView;
    }

    private BarcodeCallback barcodeCallback = new BarcodeCallback() {

        @Override
        public void barcodeResult(BarcodeResult barcodeResult) {
            Toast.makeText(getActivity().getBaseContext(),"Hello",Toast.LENGTH_SHORT);
        }

        @Override
        public void possibleResultPoints(List<com.google.zxing.ResultPoint> list) {

        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        captureManager.onSaveInstanceState(outState);
    }
}
