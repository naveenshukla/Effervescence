package com.naveen.effervescence.Fragments;

/**
 * Created by Naveen on 23-06-2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naveen.effervescence.Adapters.EventAdapter;
import com.naveen.effervescence.Events;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Receiver;
import com.naveen.effervescence.RecyclerViewClickListener;
import com.naveen.effervescence.Utils.EventList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DayViewFragment extends Fragment implements RecyclerViewClickListener {

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
    protected EventAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;


    public static DayViewFragment newInstance(int page, int day){
        Bundle args = new Bundle();
        args.putInt("ARG_PAGE", page);
        args.putInt("ARG_DAY",day);
        DayViewFragment fragment = new DayViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("ARG_PAGE");
        day = getArguments().getInt("ARG_DAY");
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

        switch (day) {
            case 1: mAdapter = new EventAdapter(EventList.danceEventList, this, getActivity(), getContext());
                break;
            case 2: mAdapter = new EventAdapter(EventList.dramaEventList, this, getActivity(), getContext());
                break;
            case 3: mAdapter = new EventAdapter(EventList.fineartsEventList,this, getActivity(), getContext());
                break;
            default: mAdapter = new EventAdapter(EventList.literaryEventList,this,getActivity(),getContext());
        }
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

    @Override
    public void recyclerViewListClicked(View v, int position) {
        /*String[] p = date[position].split("%");
        int day = Integer.parseInt(p[0]);
        int month = Integer.parseInt(p[1]);
        int year = Integer.parseInt(p[2]);

        int hourofday = Integer.parseInt(hour[position]);

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
        startAlarm(calendar);*/
    }



}
