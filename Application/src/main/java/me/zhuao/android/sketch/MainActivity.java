package me.zhuao.android.sketch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.zhuao.android.sketch.activity.AudioRecorderActivity;
import me.zhuao.android.sketch.activity.EditTextActivity;
import me.zhuao.android.sketch.activity.MomentsActivity;
import me.zhuao.android.sketch.activity.SpinnerActivity;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.navigation)
    NavigationView navigationView;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflateLayout(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.navigation_spinner) {
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

}
