package com.naveen.effervescence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
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

import com.naveen.effervescence.Activities.EventDetailActivity;
import com.naveen.effervescence.Events;
import com.naveen.effervescence.Main2Activity;
import com.naveen.effervescence.Model.EventInfo;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;
import com.naveen.effervescence.RecyclerViewClickListener;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Naveen on 23-06-2016.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<EventInfo> eventsList;
    public Activity activity;
    public Context context;
    static RecyclerViewClickListener itemListener;
    Integer index;

    RecyclerView recyclerView;
    private static final String TAG = "EventAdapter";

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView day, title, time, place, category1, category2;
        public ImageView eventImage,dropdown;
        public RelativeLayout expanded_layout;
        public FloatingActionButton share_fab;
        public FloatingActionButton reminder_fab;
        public LinearLayout dropdown_ll;

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
            //category1 = (TextView) v.findViewById(R.id.category_tv);
            //category2 = (TextView) v.findViewById(R.id.category_tv2);
            eventImage = (ImageView) v.findViewById(R.id.event_image);
            dropdown = (ImageView) v.findViewById(R.id.dropdown);
            expanded_layout = (RelativeLayout) v.findViewById(R.id.expanded_layout);
            share_fab = (FloatingActionButton) v.findViewById(R.id.share_button);
            reminder_fab = (FloatingActionButton) v.findViewById(R.id.reminder_button);
            dropdown_ll = (LinearLayout) v.findViewById(R.id.dropdown_ll);
        }

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView pRecyclerView) {
        super.onAttachedToRecyclerView(pRecyclerView);
        recyclerView = pRecyclerView;
    }

    public EventAdapter(List<EventInfo> eventsList, RecyclerViewClickListener itemListener, Activity activity, Context context) {
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

        final EventInfo events = eventsList.get(position);
        String day = events.getDateSharedPrefVariable();
        String time = events.getTimeSharedPrefVariable();
        String category = events.getCategory();
        final String title = events.getEventName();
        String place = events.getLocation();

       // viewHolder.category2.setText(category);
        viewHolder.title.setText(title);
        viewHolder.time.setText(time);
        viewHolder.place.setText(place);
        viewHolder.eventImage.setImageResource(events.getImage_drawable());

        final View imageView = viewHolder.eventImage;

        viewHolder.reminder_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
				Calendar cal = Calendar.getInstance();
				Intent intent = new Intent(Intent.ACTION_EDIT);
				intent.setType("vnd.android.cursor.item/event");
				intent.putExtra("beginTime", cal.getTimeInMillis());
				intent.putExtra("allDay", false);
				intent.putExtra("rrule", "FREQ=DAILY");
				intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis()+60*60*1000);
				intent.putExtra(CalendarContract.Events.TITLE, title);
				context.startActivity(intent);
            }
        });

        viewHolder.eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("event_name",events.getEventName());
                intent.putExtra("event_image",events.getImage_drawable());
                intent.putExtra("event_description",events.getEventDescription());

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(activity, (View) imageView, "ima");
                    activity.startActivity(intent, options.toBundle());
                }
                else activity.startActivity(intent);

            }
        });
        viewHolder.dropdown_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Cool","Stuff");
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