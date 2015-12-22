package com.thoughtworks.android.booking.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.ui.Fragment.RoomInformationFragment;
import com.thoughtworks.android.booking.ui.Fragment.ScanFragment;

import me.zhuao.android.sketch.activity.DrawerLayoutActivity;

public class MainActivity extends DrawerLayoutActivity {

    public static RoomResponse roomResponse = new RoomResponse();
    public static BookResponse bookResponse = new BookResponse();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_layout);
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
