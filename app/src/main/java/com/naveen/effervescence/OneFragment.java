package com.naveen.effervescence;

/**
 * Created by Naveen on 23-06-2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class OneFragment extends Fragment implements  RecyclerViewClickListener{
    private RecyclerView recyclerView;
    // private EventsAdapter eventsAdapter;
    // private EventsAdapter eventsAdapter;
    MyDBHandler dbHandler;
    Animation animation1,animation2;
    public  String[] title,place,category1,category2,day,date,hour,minute,ampm;
    private String[] mPlanetTitles;
    private PendingIntent pendingIntent;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView1,recyclerView2,recyclerView3;
    private ImageView setReminder;
    private ListView mDrawerList;

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";


    private void startAlarm(Calendar calendar) {
        Log.d("hello","start Alarm");
        Intent myIntent = new Intent(getActivity().getApplicationContext(), Receiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getActivity().getBaseContext(),"Reminder Has been set",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Log.d("hello","clicked");
        String[] p = date[position].split("%");
        int day = Integer.parseInt(p[0]);
        int month = Integer.parseInt(p[1]);
        int year = Integer.parseInt(p[2]);

        int hourofday = Integer.parseInt(hour[position]);
        Log.d("hello",String.valueOf(day) + " " + String.valueOf(month) + " " + String.valueOf(year) + " " + String.valueOf(hourofday));

        int minofday = Integer.parseInt(minute[position]);
        String ampmofday = ampm[position];
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, 2016);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        calendar.set(Calendar.HOUR_OF_DAY, hourofday);
        calendar.set(Calendar.MINUTE, minofday);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, ampmofday.equals("AM")?Calendar.AM:Calendar.PM);
        startAlarm(calendar);
    }


    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    private List<Events> EventList = new ArrayList<>();

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new MyDBHandler(getContext(),null,null,1);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_fragment, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new CustomAdapter(EventList,this);
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

    private void initDataset() {
        Events events = new Events(1, "Parallel World : A New  Music Experience", "Complete Best Place", "Rock n roll", "Band"
                , "Thursday", "0", "10", "23%06%2016", "AM");
        dbHandler.addEvents(events);
        events = new Events(2, "Parallel World : A   Music Experience", "Complete Best Place For Sachin", "Rock ", "Band"
                , "Thursday", "8", "23", "1%12%2014", "AM");
        dbHandler.addEvents(events);
        title = dbHandler.columntitle(-1);
        place = dbHandler.columnplace(-1);
        category1 = dbHandler.columncategory1(-1);
        category2 = dbHandler.columncategory2(-1);
        day = dbHandler.columnday(-1);
        date = dbHandler.columndate(-1);
        hour = dbHandler.columnhour(-1);
        minute = dbHandler.columnminute(-1);
        ampm = dbHandler.columnampm(-1);
        for (int i = 0; i < 2; i++) {
            events = new Events(i + 1, title[i], place[i], category1[i], category2[i]
                    , day[i], hour[i], minute[i], date[i], ampm[i]);
            EventList.add(events);
        }
    }
}
