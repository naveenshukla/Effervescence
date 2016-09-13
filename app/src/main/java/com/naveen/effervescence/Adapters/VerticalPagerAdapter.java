package com.naveen.effervescence.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.naveen.effervescence.Activities.EventDetailActivity;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Model.EventInfo;

import java.util.ArrayList;

/**
 * Created by betterclever on 30/8/16.
 */
public class VerticalPagerAdapter extends PagerAdapter{
    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EventInfo> events;

    public VerticalPagerAdapter(final Context context,final ArrayList<EventInfo> events){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        this.events = events;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.item, container, false);
        final ImageView eventImageView = (ImageView) view.findViewById(R.id.eventImageInCategory);
        eventImageView.setImageResource(events.get(position).getImage_drawable());
        eventImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EventInfo event = events.get(position);
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra("event_name",event.getEventName());
                intent.putExtra("event_image",event.getImage_drawable());
                intent.putExtra("event_description",event.getEventDescription());

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation((Activity) mContext, (View) eventImageView, "ima");
                    mContext.startActivity(intent, options.toBundle());
                }
                else mContext.startActivity(intent);
                Log.d("ImageView", "Clicked  " + position);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

}
