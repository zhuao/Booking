package com.thoughtworks.android.booking.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thoughtworks.android.booking.Adapter.RoomInformationAdapter;
import com.thoughtworks.android.booking.AppContent.StringContent;
import com.thoughtworks.android.booking.Database.DatabaseOperation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.HttpService;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;
import com.thoughtworks.android.booking.Server.ServerInterface;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        new DatabaseOperation().getRoomInformation();
    }

    @Override
    protected String getFragmentTitle() {
        return StringContent.ROOM_LSIT_TITILE;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        showProgressBar();
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(RoomResponse roomResponse){
        roomInformationAdapter.addRoomResponseToAdapter(roomResponse);
        roomInformationAdapter.notifyDataSetChanged();
        hideProgressBar();

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
