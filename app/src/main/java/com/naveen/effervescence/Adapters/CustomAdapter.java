package com.naveen.effervescence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Events> eventsList;
    public ImageView setReminder;
    public Activity activity;
    MyDBHandler dbHandler;
    public Context context;
    static Animation animation1,animation2;
    static RecyclerViewClickListener itemListener;
    Integer index;

    RecyclerView recyclerView;
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView day,title,time,place,category1,category2;
        public ImageView setReminder;
        public LinearLayout eventCards;
        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            day = (TextView)v.findViewById(R.id.day);
            title = (TextView)v.findViewById(R.id.title);
            time = (TextView)v.findViewById(R.id.time);
            place = (TextView)v.findViewById(R.id.place);
            category1 = (TextView)v.findViewById(R.id.category1);
            category2 = (TextView)v.findViewById(R.id.category2);
            setReminder  = (ImageView)v.findViewById(R.id.setReminder);
            eventCards = (LinearLayout)v.findViewById(R.id.eventcards);
            eventCards.setOnClickListener(this);
            setReminder.setOnClickListener(this);
        }
        public TextView getTitle() {
            return title;
        }
        public TextView getTime() {
            return time;
        }
        public TextView getPlace() {
            return place;
        }
        public TextView getCategory1() {
            return category1;
        }
        public TextView getCategory2() {
            return category2;
        }
        public ImageView getSetReminder() {
            return setReminder;
        }
        public TextView getDay() {
            return day;
        }

        @Override
        public void onClick(final View v) {
            if (v == eventCards) {
                Intent intent = new Intent(activity ,Main2Activity.class);
                context.startActivity(intent);

            } else {
                itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
                animation1 = new AlphaAnimation(0.0f, 1.0f);
                animation1.setDuration(100);
                animation1.setStartOffset(0);

                //animation1 AnimationListener
                animation1.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        v.startAnimation(animation2);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                animation2 = new AlphaAnimation(1.0f, 0.0f);
                animation2.setDuration(0);
                animation2.setStartOffset(0);

                //animation2 AnimationListener

                v.startAnimation(animation1);
            }
        }
    }


    public CustomAdapter(List<Events> eventsList, RecyclerViewClickListener itemListener, Activity activity,Context context) {
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
                .inflate(R.layout.text_row_item, viewGroup, false);

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
        viewHolder.getDay().setText(day);
        viewHolder.getCategory1().setText(category1);
        viewHolder.getCategory2().setText(category2);
        viewHolder.getTitle().setText(title);
        viewHolder.getTime().setText(time);
        viewHolder.getPlace().setText(place);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}