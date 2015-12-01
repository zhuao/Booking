package com.thoughtworks.android.booking.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.thoughtworks.android.booking.MainActivity;

/**
 * Created by hxxu on 11/29/15.
 */
public abstract class BaseFragment extends Fragment {
    protected MainActivity parentActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = (MainActivity)getActivity();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentActivity.getSupportActionBar().setTitle(getFragmentTitle());
    }

    protected abstract String getFragmentTitle();

}
