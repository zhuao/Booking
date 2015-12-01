package com.thoughtworks.android.booking;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.thoughtworks.android.booking.Fragment.RoomInformationFragment;

import me.zhuao.android.sketch.activity.DrawerLayoutActivity;

public class MainActivity extends DrawerLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_layout);
        startFragment(new RoomInformationFragment());
    }

    @Override
    protected int getDrawerLayoutResId() {
        return R.layout.main_drawer_layout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.navigation_spinner) {
        }
        return false;
    }

    public void startFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_content,fragment);
        fragmentTransaction.commit();
    }

}
