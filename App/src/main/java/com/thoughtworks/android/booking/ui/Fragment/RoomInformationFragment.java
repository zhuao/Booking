package com.thoughtworks.android.booking.ui.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtworks.android.booking.ui.Adapter.RoomInformationAdapter;
import com.thoughtworks.android.booking.StringConstant;

import com.thoughtworks.android.booking.ui.MainActivity;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.persistence.server.Response.BookResponse;
import com.thoughtworks.android.booking.persistence.server.Response.RoomResponse;

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
        return StringConstant.ROOM_LIST_FRAGMENT_TITLE;
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
        roomInformationAdapter.setOnItemClickListener(new RoomInformationAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("name", MainActivity.roomResponse.getResults().get(position).getName());
                bundle.putString("barcode", MainActivity.roomResponse.getResults().get(position).getBarcode());

                Fragment bookingFragment = new BookingFragment(getActivity());
                bookingFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_content,bookingFragment);
                fragmentTransaction.addToBackStack("RoomList");
                fragmentTransaction.commit();
            }
        });
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
