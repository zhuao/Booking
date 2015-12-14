package com.thoughtworks.android.booking.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtworks.android.booking.Adapter.RoomInformationAdapter;
import com.thoughtworks.android.booking.AppContent.StringContent;

import com.thoughtworks.android.booking.MainActivity;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.Response.BookResponse;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;

import de.greenrobot.event.EventBus;

/**
 * Created by hxxu on 11/29/15.
 */
public class RoomInformationFragment extends BaseFragment{

    private View rootView;
    private RecyclerView roomRecyclerView;
    private RoomInformationAdapter roomInformationAdapter;


    public RoomInformationFragment() {
        this.roomInformationAdapter = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    protected String getFragmentTitle() {
        return StringContent.RROM_LIST_FRAGMENT_TITLE;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        if (MainActivity.roomResponse.getResults().size() == 0) {
            showProgressBar();
        }

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(BookResponse bookResponse){
        roomInformationAdapter.addRoomResponseToAdapter(bookResponse);
        roomInformationAdapter.notifyDataSetChanged();
        hideProgressBar();

    }

    public void onEventMainThread(RoomResponse roomResponse){
       MainActivity.roomResponse = roomResponse;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.room_information_list_view,container,false);
        roomRecyclerView = (RecyclerView)rootView.findViewById(R.id.room_information_list);
        roomRecyclerView.setHasFixedSize(true);
        roomRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        roomInformationAdapter = new RoomInformationAdapter(getActivity());
        roomRecyclerView.setAdapter(roomInformationAdapter);
        return rootView;
    }

    public void showProgressBar(){
        getActivity().findViewById(R.id.progress_spinner).setVisibility(View.VISIBLE);
    }

    public void hideProgressBar(){
        getActivity().findViewById(R.id.progress_spinner).setVisibility(View.INVISIBLE);
    }


}
