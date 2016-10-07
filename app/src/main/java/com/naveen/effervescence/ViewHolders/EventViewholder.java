package com.naveen.effervescence.ViewHolders;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.naveen.effervescence.R;

/**
 * Created by Pranjal Paliwal on 10/7/2016.
 */

public class EventViewholder extends RecyclerView.ViewHolder {

	public TextView day, title, time, place, category;
	public ImageView eventImage;
	public RelativeLayout expanded_layout;
	public FloatingActionButton share_fab, reminder_fab;
	public LinearLayout dropdown_ll;

	public EventViewholder(View v) {
		super(v);
		title = (TextView) v.findViewById(R.id.event_title_tv);
		time = (TextView) v.findViewById(R.id.time_tv);
		place = (TextView) v.findViewById(R.id.location_tv);
		category = (TextView) v.findViewById(R.id.category_tv);
		eventImage = (ImageView) v.findViewById(R.id.event_image);/*
		share_fab = (FloatingActionButton) v.findViewById(R.id.share_button);
		reminder_fab = (FloatingActionButton) v.findViewById(R.id.reminder_button);
		dropdown_ll = (LinearLayout) v.findViewById(R.id.dropdown_ll);*/
	}
}
