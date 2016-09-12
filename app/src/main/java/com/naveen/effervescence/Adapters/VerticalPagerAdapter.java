package com.naveen.effervescence.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

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
        ImageView eventImageView = (ImageView) view.findViewById(R.id.eventImageInCategory);
        eventImageView.setImageResource(events.get(position).getImage_drawable());
        eventImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
