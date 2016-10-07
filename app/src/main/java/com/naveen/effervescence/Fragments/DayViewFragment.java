package com.naveen.effervescence.Fragments;

/**
 * Created by Naveen on 23-06-2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.util.SortedList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.naveen.effervescence.Activities.EventDetailActivity;
import com.naveen.effervescence.Adapters.EventAdapter;
import com.naveen.effervescence.Events;
import com.naveen.effervescence.Model.Event;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Receiver;
import com.naveen.effervescence.RecyclerViewClickListener;
import com.naveen.effervescence.Utils.EventList;
import com.naveen.effervescence.ViewHolders.EventViewholder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class DayViewFragment extends Fragment {

	private PendingIntent pendingIntent;

	private static final String TAG = "RecyclerViewFragment";
	private static final String KEY_LAYOUT_MANAGER = "layoutManager";

	private int day;
	private int page;

	private enum LayoutManagerType {
		LINEAR_LAYOUT_MANAGER
	}

	protected LayoutManagerType mCurrentLayoutManagerType;

	private List<Events> eventList = new ArrayList<>();

	protected RecyclerView mRecyclerView;
	protected FirebaseRecyclerAdapter mAdapter;
	protected RecyclerView.LayoutManager mLayoutManager;



    /*private static final Comparator<EventInfo> ALPHABETICAL_COMPARATOR = new Comparator<EventInfo>() {
        @Override
        public int compare(EventInfo a, EventInfo b) {
            return a.getEventName().compareTo(b.getEventName());
        }
    };*/

	public static DayViewFragment newInstance(int page, int day) {
		Bundle args = new Bundle();
		args.putInt("ARG_PAGE", page);
		args.putInt("ARG_DAY", day);
		DayViewFragment fragment = new DayViewFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("ARG_PAGE");
		day = getArguments().getInt("ARG_DAY");
		setHasOptionsMenu(true);
	}

	/*@Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem mSearchMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(!query.isEmpty()){
                    mAdapter.setTemp();
                    mAdapter.setFilter(query);
                }
                else{
                    mAdapter.setTemp();
                    mAdapter.flushFilter();
                }
                return false;
            }
        });
    }
*/
	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
		rootView.setTag(TAG);

		mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
		//mRecyclerView.setHasFixedSize(true);

		mLayoutManager = new LinearLayoutManager(getActivity());
		mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
		mRecyclerView.setLayoutManager(mLayoutManager);

		DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
		reference = reference.child("day1").child("EventList");
		mAdapter = new FirebaseRecyclerAdapter<Event, EventViewholder>(Event.class, R.layout.event_dayview_card, EventViewholder.class, reference) {

			@Override
			protected void populateViewHolder(final EventViewholder viewHolder, final Event model, final int position) {
				viewHolder.title.setText(model.getEventName());
				viewHolder.category.setText(model.getEventCategory());
				//viewHolder.day.setText(model.getEventDate());
				viewHolder.time.setText(model.getEventTime());
				viewHolder.place.setText(model.getEventLocation());
				Glide.with(DayViewFragment.this)
					.load(model.getEventImage())
					.centerCrop()
					.placeholder(R.drawable.bw)
					.crossFade()
					.into(viewHolder.eventImage);

				viewHolder.eventImage.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {


						Intent intent = new Intent(getContext(), EventDetailActivity.class);
						intent.putExtra("event_name", model.getEventName());
						intent.putExtra("event_image", model.getEventImage());
						intent.putExtra("event_description", model.getEventDescription());
						intent.putExtra("event_date", model.getEventDate());
						intent.putExtra("event_time", model.getEventTime());
						intent.putExtra("child_u", "day1");
						intent.putExtra("child_l", "" + position);

						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
							ActivityOptionsCompat options = ActivityOptionsCompat.
								makeSceneTransitionAnimation(getActivity(), (View) viewHolder.eventImage, "ima");
							getActivity().startActivity(intent, options.toBundle());
						} else getActivity().startActivity(intent);

					}
				});
			}

		};
		mRecyclerView.setAdapter(mAdapter);
		return rootView;

	}



    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    private void startAlarm(Calendar calendar) {
        Intent myIntent = new Intent(getActivity().getApplicationContext(), Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getActivity().getBaseContext(),"Reminder Has been set",Toast.LENGTH_SHORT).show();
    }



}
