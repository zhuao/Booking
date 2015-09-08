package me.zhuao.android.sketch;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

public abstract class DrawerLayoutActivity extends ToolbarActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_drawer_layout);
        ViewStub navigationView = ((ViewStub) findViewById(R.id.navigation));
        navigationView.setLayoutResource(getDrawerLayoutResId());
        NavigationView navigateView = ((NavigationView) navigationView.inflate());
        navigateView.setNavigationItemSelectedListener(this);
        navigateView.setVisibility(View.VISIBLE);
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
        ViewStub pageContentView = ((ViewStub) findViewById(R.id.page_content));
        pageContentView.setLayoutResource(layoutResID);
        pageContentView.inflate().setVisibility(View.VISIBLE);
    }

}
