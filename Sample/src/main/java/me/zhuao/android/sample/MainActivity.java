package me.zhuao.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import me.zhuao.android.sample.activity.AudioRecorderActivity;
import me.zhuao.android.sample.activity.EditTextActivity;
import me.zhuao.android.sample.activity.MomentsActivity;
import me.zhuao.android.sample.activity.SpinnerActivity;
import me.zhuao.android.sample.activity.StepIndicatorActivity;
import me.zhuao.android.sketch.DrawerLayoutActivity;

public class MainActivity extends DrawerLayoutActivity {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function_lists);


        navigationView = (NavigationView) findViewById(R.id.navigation_main);
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

    @Override
    protected int getDrawerLayoutResId() {
        return R.layout.main_drawer_layout;
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
