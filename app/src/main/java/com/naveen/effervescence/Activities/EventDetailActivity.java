package com.naveen.effervescence.Activities;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naveen.effervescence.Fragments.DayViewFragment;
import com.naveen.effervescence.Model.Event;
import com.naveen.effervescence.Model.Organizer;
import com.naveen.effervescence.R;

public class EventDetailActivity extends Activity {

	public TextView dateTV, timeTV, descriptionTV, locationTV;
	public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);


		Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
		tb.setTitle(getIntent().getCharSequenceExtra("event_name"));

		dateTV = (TextView) findViewById(R.id.date_textview);
		timeTV = (TextView) findViewById(R.id.time_textview);
		locationTV = (TextView) findViewById(R.id.location_textview);
		descriptionTV = (TextView) findViewById(R.id.description_textview);
		imageView = (ImageView) findViewById(R.id.backdrop);

		DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
		ref =  ref.child(getIntent().getStringExtra("child_u")).child("EventList").child(getIntent().getStringExtra("child_l"));
		ValueEventListener postListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {

				Event event = dataSnapshot.getValue(Event.class);
				dateTV.setText(event.getEventDate());
				timeTV.setText(event.getEventTime());
				locationTV.setText(event.getEventLocation());
				descriptionTV.setText(event.getEventDescription());

				Glide.with(EventDetailActivity.this)
					.load(event.getEventImage())
					.centerCrop()
					.placeholder(R.drawable.bw)
					.crossFade()
					.into(imageView);

				LinearLayout linearLayout = (LinearLayout) findViewById(R.id.add_org_here);
				linearLayout.removeAllViews();

				for(Organizer organizer: event.getEventOrganizers()){
					View v = View.inflate(EventDetailActivity.this,R.layout.organizer_event_detail,null);
					TextView orgName = (TextView) v.findViewById(R.id.organizer_name);
					TextView orgPhone = (TextView) v.findViewById(R.id.organizer_phone);

					orgName.setText(organizer.getOrganizerName());
					orgPhone.setText(""+organizer.getOrganizerPhone());

					linearLayout.addView(v);

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {
				Log.w("TAG", "loadPost:onCancelled", databaseError.toException());
			}
		};
		ref.addValueEventListener(postListener);


        /*Bitmap myBitmap = null;

        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();
            int def = 0x000000;
            int vibrant = palette.getMutedColor(def);
            Log.d("vibrant", String.format("#%06X", 0xFFFFFF & vibrant));
            Log.d("vibrant dark",String.format("#%06X", 0xFFFFFF & palette.getDarkVibrantColor(def)));
            CollapsingToolbarLayout cp = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            cp.setContentScrimColor(vibrant);
            cp.setStatusBarScrimColor(palette.getDarkMutedColor(def));
        }
*/


    }
}
