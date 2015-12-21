package com.thoughtworks.android.booking.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtworks.android.booking.ui.MainActivity;
import com.thoughtworks.android.booking.R;

/**
 * Created by hxxu on 12/15/15.
 */
public class BookingFragment extends BaseFragment {
    private Bundle bundle;
    private Context context;

    public BookingFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setFragmentTitle(bundle.getString("name"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         bundle = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.book_room_layout,container,false);
    }

    @Override
    protected String getFragmentTitle() {
        return bundle.getString("name");
    }

    private void setFragmentTitle(String title){
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(title);
    }
}
