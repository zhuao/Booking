package com.thoughtworks.android.booking;

import android.os.Bundle;
import android.view.MenuItem;

import com.thoughtworks.android.booking.Database.DatabaseOperation;

import java.util.Date;

import me.zhuao.android.sketch.activity.DrawerLayoutActivity;

public class MainActivity extends DrawerLayoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_lists);
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

}
