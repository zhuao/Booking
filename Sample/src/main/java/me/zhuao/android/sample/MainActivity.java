package me.zhuao.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import me.zhuao.android.sketch.BaseActivity;
import me.zhuao.android.sample.activity.AudioRecorderActivity;
import me.zhuao.android.sample.activity.EditTextActivity;
import me.zhuao.android.sample.activity.MomentsActivity;
import me.zhuao.android.sample.activity.SpinnerActivity;
import me.zhuao.android.sample.activity.StepIndicatorActivity;

public class MainActivity extends BaseActivity {

    NavigationView navigationView;

    DrawerLayout drawerLayout;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayout(me.zhuao.android.sketch.R.layout.activity_main);

        navigationView = ((NavigationView) findViewById(me.zhuao.android.sketch.R.id.navigation));
        drawerLayout = (DrawerLayout) findViewById(me.zhuao.android.sketch.R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(me.zhuao.android.sketch.R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == me.zhuao.android.sketch.R.id.navigation_spinner) {
                    spinner(null);
                }
                return false;
            }
        });
    }

    public void spinner(View view) {
        startActivity(new Intent(this, SpinnerActivity.class));
    }

    public void text(View view) {
        startActivity(new Intent(this, EditTextActivity.class));
    }

    public void recorder(View view) {
        startActivity(new Intent(this, AudioRecorderActivity.class));
    }

    public void moments(View view) {
        startActivity(new Intent(this, MomentsActivity.class));
    }

    public void stepIndicator(View view) {
        startActivity(new Intent(this, StepIndicatorActivity.class));
    }
}
