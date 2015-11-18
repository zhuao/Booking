package me.zhuao.android.sketch.activity.decorator;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;

import me.zhuao.android.sketch.R;
import me.zhuao.android.sketch.activity.BlankActivity;

public class ToolbarDecorator implements ActivityDecorator {
    private ActivityDecorator parentDecorator;

    public ToolbarDecorator(ActivityDecorator parentDecorator) {
        this.parentDecorator = parentDecorator;
    }

    @Override
    public void onCreate(int containerResId) {
        if (parentDecorator != null) {
            parentDecorator.onCreate(containerResId);
            containerResId = parentDecorator.getContentElementId();
        }

        ViewStub container = (ViewStub) getActivity().findViewById(containerResId);
        container.setLayoutResource(R.layout.decorator_toolbar);
        container.inflate();

        attachToolbar();
    }

    @Override
    public void setContentView(int contentResId) {
        ViewStub content = (ViewStub) getActivity().findViewById(getContentElementId());
        content.setLayoutResource(contentResId);
        content.inflate();
    }

    @Override
    public void onPostCreate() {
        if (parentDecorator instanceof DrawerLayoutDecorator) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            DrawerLayout drawerLayout = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, 0, 0);
            drawerToggle.syncState();
            drawerLayout.setDrawerListener(drawerToggle);
        }

    }

    @Override
    public int getContentElementId() {
        return R.id.toolbar_below;
    }

    @Override
    public BlankActivity getActivity() {
        return parentDecorator.getActivity();
    }

    private void attachToolbar() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        if (toolbar != null) {
            getActivity().setSupportActionBar(toolbar);
            getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().onBackPressed();
                }
            });
        }
    }
}
