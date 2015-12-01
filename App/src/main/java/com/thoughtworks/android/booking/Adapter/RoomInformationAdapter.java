package com.thoughtworks.android.booking.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import android.widget.TextView;

import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;



/**
 * Created by hxxu on 11/29/15.
 */
public class RoomInformationAdapter extends RecyclerView.Adapter<RoomInformationAdapter.ViewHolder> {
    private RoomResponse roomResponse;

    public RoomInformationAdapter() {
        this.roomResponse = new RoomResponse();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView view = (CardView)LayoutInflater.from(parent.getContext()).inflate(R.layout.room_information_card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = (TextView)holder.cardView.findViewById(R.id.room_name_text);
        textView.setText(roomResponse.getResults().get(position).getName());
    }


    @Override
    public int getItemCount() {
        return roomResponse.getResults().size();
    }

    public void addRoomResponseToAdapter(RoomResponse roomResponse){
        this.roomResponse = roomResponse;
        notifyDataSetChanged();
    }

}
