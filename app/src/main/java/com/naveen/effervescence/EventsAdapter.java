package com.naveen.effervescence;

import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by naveen on 3/6/16.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    private List<Events> eventsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView day,title,time,place,category1,category2;

        public MyViewHolder(View view) {
            super(view);
            day = (TextView)view.findViewById(R.id.day);
            title = (TextView)view.findViewById(R.id.title);
            time = (TextView)view.findViewById(R.id.time);
            place = (TextView)view.findViewById(R.id.place);
            category1 = (TextView)view.findViewById(R.id.category1);
            category2 = (TextView)view.findViewById(R.id.category2);
        }
    }
    public EventsAdapter(List<Events> eventsList) {

        this.eventsList = eventsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Events events = eventsList.get(position);
        holder.category1.setText(events.getCategory1());
        holder.category2.setText(events.getCategory2());
        holder.day.setText(events.getDay());
        holder.place.setText(events.getPlace());
        holder.time.setText(events.getTime());
        holder.title.setText(events.getTitle());
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
