package com.thoughtworks.android.booking.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.StringConstant;
import com.thoughtworks.android.booking.persistence.Server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.ui.Fragment.CheckBookingRecord;
import com.thoughtworks.android.booking.ui.Fragment.RoomInformationFragment;
import com.thoughtworks.android.booking.ui.Fragment.ScanFragment;
import com.thoughtworks.android.booking.ui.View.RoundImageView;


import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Message;
import de.hdodenhof.circleimageview.CircleImageView;
import me.zhuao.android.sketch.activity.DrawerLayoutActivity;
import me.zhuao.android.sketch.activity.ToolbarActivity;

public class MainActivity extends DrawerLayoutActivity {

    public static RoomResponse roomResponse = new RoomResponse();
    public static BookResponse bookResponse = new BookResponse();
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_layout);
        startFragment(new RoomInformationFragment(), MainActivity.class.getSimpleName());
        navigationView = (NavigationView)findViewById(R.id.navigation_main);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.addHeaderView(View.inflate(this, R.layout.navigation_header_layout, null));
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        CircleImageView circleImageView = (CircleImageView)findViewById(R.id.navigation_user_circle_image);
        circleImageView.setBorderColor(getResources().getColor(R.color.gray_500));
        circleImageView.setImageDrawable(getResources().getDrawable(R.drawable.hao));
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());
        JMessageClient.init(getApplicationContext());

//        Message message = JMessageClient.createSingleTextMessage("1", "1");
//        JMessageClient.sendMessage(message);

    }

    @Override
    protected int getDrawerLayoutResId() {
        return R.layout.main_drawer_layout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if(menuItem.getItemId() == R.id.navigation_spinner){
            startFragment(new CheckBookingRecord(this), CheckBookingRecord.class.getSimpleName());
        }
        drawerLayout.closeDrawers();
       return super.onOptionsItemSelected(menuItem);
    }

    public void startFragment(Fragment fragment,String tag){
         if(getFragmentManager().findFragmentByTag(tag) == null){

             FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
             fragmentTransaction.replace(R.id.main_fragment_content, fragment,tag);
             fragmentTransaction.addToBackStack(tag);
             fragmentTransaction.commit();
         }else {

             getFragmentManager().popBackStackImmediate(tag, 0);
         }

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
                startFragment(new ScanFragment(this),ScanFragment.class.getSimpleName());
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
