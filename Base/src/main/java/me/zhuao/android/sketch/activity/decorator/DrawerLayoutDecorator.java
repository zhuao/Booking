package me.zhuao.android.sketch.activity.decorator;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;

import me.zhuao.android.sketch.R;
import me.zhuao.android.sketch.activity.BlankActivity;

public class DrawerLayoutDecorator implements ActivityDecorator {
    private int drawerLayoutResId;
    private ActivityDecorator parentDecorator;

    public DrawerLayoutDecorator(ActivityDecorator parentDecorator, int drawerLayoutResId) {
        this.parentDecorator = parentDecorator;
        this.drawerLayoutResId = drawerLayoutResId;
    }

    @Override
    public void onCreate(int containerResId) {
        if (parentDecorator != null) {
            parentDecorator.onCreate(containerResId);
            containerResId = parentDecorator.getContentElementId();
        }

        ViewStub view = (ViewStub) getActivity().findViewById(containerResId);
        view.setLayoutResource(R.layout.decorator_drawer_layout);
        view.inflate();

        ViewStub navigationView = ((ViewStub) getActivity().findViewById(R.id.drawer_navigation));
        navigationView.setLayoutResource(drawerLayoutResId);
        navigationView.inflate();
    }

    public BlankActivity getActivity() {
        return parentDecorator.getActivity();
    }

    @Override
    public void setContentView(int layoutResID) {
        ViewStub content = ((ViewStub) getActivity().findViewById(getContentElementId()));
        content.setLayoutResource(layoutResID);
        content.inflate();
    }

    @Override
    public void onPostCreate() {
        if (parentDecorator instanceof ToolbarDecorator) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, 0, 0);
            drawerToggle.syncState();
            drawerLayout.setDrawerListener(drawerToggle);
        }
    }

    @Override
    public int getContentElementId() {
        return R.id.drawer_content;
    }
}
