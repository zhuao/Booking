package com.thoughtworks.android.booking.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.Response.RoomResponse;



/**
 * Created by hxxu on 11/29/15.
 */
public class RoomInformationAdapter extends RecyclerView.Adapter<RoomInformationAdapter.ViewHolder> {
    private RoomResponse roomResponse;
    public Context context;
    private static AdapterView.OnItemClickListener onItemClickListener;
    public RoomInformationAdapter(Context context) {
        this.roomResponse = new RoomResponse();
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            onItemClickListener.onItemClick(getLayoutPosition(),v);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_information_card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.setIsRecyclable(false); //disable the recycler to make sure the data not change accompany with the recycler
        TextView textView = (TextView)holder.view.findViewById(R.id.room_name_text);
        textView.setText(roomResponse.getResults().get(position).getName());
        CardView cardView = (CardView)holder.view.findViewById(R.id.room_list_card_view);
        if(roomResponse.getResults().get(position).isUsing()){
          cardView.setCardBackgroundColor(context.getResources().getColor(R.color.red));
        }else {
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.green));

        }
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
