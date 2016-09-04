package com.naveen.effervescence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naveen.effervescence.Events;
import com.naveen.effervescence.Main2Activity;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;
import com.naveen.effervescence.RecyclerViewClickListener;

import java.util.List;

/**
 * Created by Naveen on 23-06-2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Events> eventsList;
    public Activity activity;
    MyDBHandler dbHandler;
    public Context context;
    static Animation animation1,animation2;
    static RecyclerViewClickListener itemListener;
    Integer index;

    RecyclerView recyclerView;
    private static final String TAG = "EventAdapter";

    private String[] mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day, title, time, place, category1, category2;
        public ImageView eventImage,dropdown;
        public RelativeLayout expanded_layout;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            title = (TextView) v.findViewById(R.id.event_title_tv);
            time = (TextView) v.findViewById(R.id.time_tv);
            place = (TextView) v.findViewById(R.id.location_tv);
            category1 = (TextView) v.findViewById(R.id.category_tv);
            category2 = (TextView) v.findViewById(R.id.category_tv2);
            eventImage = (ImageView) v.findViewById(R.id.event_image);
            dropdown = (ImageView) v.findViewById(R.id.dropdown);
            expanded_layout = (RelativeLayout) v.findViewById(R.id.expanded_layout);
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView pRecyclerView) {
        super.onAttachedToRecyclerView(pRecyclerView);

        recyclerView = pRecyclerView;
    }

    public EventAdapter(List<Events> eventsList, RecyclerViewClickListener itemListener, Activity activity,Context context) {
        this.eventsList = eventsList;
        this.itemListener = itemListener;
        this.activity = activity;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_dayview_card, viewGroup, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        Events events = eventsList.get(position);
        String day = events.getDay();
        String category1 = events.getCategory1();
        String category2 = events.getCategory2();
        String title = events.getTitle();
        String time = events.getTime();
        String place = events.getPlace();
        //viewHolder.day.setText(day);
        viewHolder.category1.setText(category1);
        viewHolder.category2.setText(category2);
        viewHolder.title.setText(title);
        viewHolder.time.setText(time);
        viewHolder.place.setText(place);
        viewHolder.eventImage.setImageResource(events.getImgDrawable());
        viewHolder.eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.dropdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean a = eventsList.get(position).isExpanded();
                eventsList.get(position).setExpanded(!a);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(recyclerView);
                }
                notifyDataSetChanged();
            }
        });
        if(events.isExpanded()){
            viewHolder.expanded_layout.setVisibility(View.VISIBLE);
            viewHolder.dropdown.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }
        else {
            viewHolder.expanded_layout.setVisibility(View.GONE);
            viewHolder.dropdown.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}