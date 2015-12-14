package com.thoughtworks.android.booking;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.thoughtworks.android.booking.AppContent.StringContent;
import com.thoughtworks.android.booking.Database.DatabaseOperation;
import com.thoughtworks.android.booking.Fragment.RoomInformationFragment;
import com.thoughtworks.android.booking.Fragment.ScanFragment;
import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;

import java.util.Timer;
import java.util.TimerTask;

import me.zhuao.android.sketch.activity.DrawerLayoutActivity;

public class MainActivity extends DrawerLayoutActivity {

    public static RoomResponse roomResponse = new RoomResponse();
    public static BookResponse bookResponse = new BookResponse();
    private Timer requestTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_layout);
        new DatabaseOperation().getRoomInformation();
        requestTimer = new Timer();
        requestTimer.schedule(timer, 0l, StringContent.REFRESH_ROOM_INFORMATION_TIME);
        startFragment(new RoomInformationFragment(),"RoomList");
    }

    @Override
    protected int getDrawerLayoutResId() {
        return R.layout.main_drawer_layout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        return false;
    }

    public void startFragment(Fragment fragment,String tag){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_content, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.barcode_scan:
                startFragment(new ScanFragment(this),"Scan");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private TimerTask timer = new TimerTask(){
        @Override
        public void run() {
           new DatabaseOperation().getBookingInformation();
        }
    };

    @Override
    protected void onStop() {
        requestTimer.cancel();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStackImmediate();
            hideProgressBar();

        }else {
            super.onBackPressed();
        }

    }


    public void hideProgressBar(){
        this.findViewById(R.id.progress_spinner).setVisibility(View.INVISIBLE);
    }


}
