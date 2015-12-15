package com.thoughtworks.android.booking.Adapter;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.TextView;

import com.thoughtworks.android.booking.Fragment.BookingFragment;
import com.thoughtworks.android.booking.MainActivity;
import com.thoughtworks.android.booking.Model.BookInformation;
import com.thoughtworks.android.booking.R;
import com.thoughtworks.android.booking.Server.Response.BookResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import java.text.ParseException;
import java.util.TimeZone;


/**
 * Created by hxxu on 11/29/15.
 */
public class RoomInformationAdapter extends RecyclerView.Adapter<RoomInformationAdapter.ViewHolder> {
    public Context context;
    private  OnItemClickListener onItemClickListener;
    public RoomInformationAdapter(Context context) {
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        private OnItemClickListener onItemClickListener;
        public ViewHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            view = itemView;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
              this.onItemClickListener.onClick(view,this.getLayoutPosition());
            }
    }

    public interface OnItemClickListener {

        void onClick(View view, int position);
    }

    public  void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_information_card_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = (TextView)holder.view.findViewById(R.id.room_name_text);
        CardView cardView = (CardView)holder.view.findViewById(R.id.room_list_card_view);
        String barcode = MainActivity.roomResponse.getResults().get(position).getBarcode();
        try {
            if(isRoomUsingByCheckBookingInformation(MainActivity.roomResponse.getResults().get(position).getBarcode())){
              cardView.setCardBackgroundColor(context.getResources().getColor(R.color.red));
                textView.setText(MainActivity.roomResponse.getResults().get(position).getName() + "(有人)");
            }else {
                cardView.setCardBackgroundColor(context.getResources().getColor(R.color.green));
                textView.setText(MainActivity.roomResponse.getResults().get(position).getName() + "(无人)");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean isRoomUsingByCheckBookingInformation(String barcode) throws ParseException {
       DateTime currentTime = new DateTime(DateTimeZone.forID("PRC"));

        for (BookInformation bookInformation : MainActivity.bookResponse.getResults()
                ) {
            DateTime startTime = bookInformation.getStartTime();
            DateTime endTime = bookInformation.getEndTime();
            if(bookInformation.getBarcode().equals(barcode) &&
                    currentTime.isBefore(bookInformation.getEndTime()) &&
                    currentTime.isAfter(bookInformation.getStartTime())){
                return true;
            }

        }
        return false;
    }


    @Override
    public int getItemCount() {
        return MainActivity.roomResponse.getResults().size();
    }

    public void addRoomResponseToAdapter(BookResponse bookResponse){
        MainActivity.bookResponse = bookResponse;
        notifyDataSetChanged();
    }


}
