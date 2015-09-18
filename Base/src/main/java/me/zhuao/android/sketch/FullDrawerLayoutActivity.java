package me.zhuao.android.sketch;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

public abstract class FullDrawerLayoutActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_full_drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }


        ViewStub navigationView = ((ViewStub) findViewById(R.id.navigation));
        navigationView.setLayoutResource(getDrawerLayoutResId());
        NavigationView view = ((NavigationView) navigationView.inflate());
        view.setItemIconTintList(null);
        view.setNavigationItemSelectedListener(this);
        view.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
    }

    protected abstract int getDrawerLayoutResId();

    @Override
    public void setContentView(int layoutResID) {
        FrameLayout content = (FrameLayout) findViewById(R.id.fragment_content);
        getLayoutInflater().inflate(layoutResID, content);
    }

}
