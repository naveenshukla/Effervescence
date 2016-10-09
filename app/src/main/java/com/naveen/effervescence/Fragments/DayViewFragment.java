package com.naveen.effervescence.Fragments;

/**
 * Created by Naveen on 23-06-2016.
 */
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.naveen.effervescence.Adapters.EventAdapter;
import com.naveen.effervescence.Events;
import com.naveen.effervescence.Model.EventInfo;
import com.naveen.effervescence.Model.EventOrganizerInfo;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Receiver;
import com.naveen.effervescence.RecyclerViewClickListener;
import com.naveen.effervescence.Utils.EventList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

import static android.R.attr.name;
import static com.naveen.effervescence.Utils.EventList.rules;


public class DayViewFragment extends Fragment implements RecyclerViewClickListener{
    MyDBHandler db  = new MyDBHandler(getContext());


    public ArrayList<EventInfo> allEventList = new ArrayList<>();
    public ArrayList<EventInfo> day0EventList = new ArrayList<>();
    public ArrayList<EventInfo> day1EventList = new ArrayList<>();
    public ArrayList<EventInfo> day2EventList = new ArrayList<>();
    public ArrayList<EventInfo> day3EventList = new ArrayList<>();
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



    private static final Comparator<EventInfo> ALPHABETICAL_COMPARATOR = new Comparator<EventInfo>() {
        @Override
        public int compare(EventInfo a, EventInfo b) {
            return a.getEventName().compareTo(b.getEventName());
        }
    };

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
        getData(getContext());
        page = getArguments().getInt("ARG_PAGE");
        day = getArguments().getInt("ARG_DAY");
        setHasOptionsMenu(true);
    }
    @Override
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
            case 1: mAdapter = new EventAdapter(day0EventList, this, getActivity(), getContext(), ALPHABETICAL_COMPARATOR);
                break;
            case 2: mAdapter = new EventAdapter(day1EventList, this, getActivity(), getContext(), ALPHABETICAL_COMPARATOR);
                break;
            case 3: mAdapter = new EventAdapter(day2EventList,this, getActivity(), getContext(), ALPHABETICAL_COMPARATOR);
                break;
            default: mAdapter = new EventAdapter(day3EventList ,this,getActivity(),getContext(),ALPHABETICAL_COMPARATOR);
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

    public void getData(Context context) {
        MyDBHandler myDBHandler = new MyDBHandler(context);
        String Table_Name="events";

        String selectQuery = "SELECT  * FROM " + Table_Name;
        SQLiteDatabase db = myDBHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
               allEventList.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                       getDrawable(cursor.getString(7)) ,
                       cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , getOrganizers(cursor.getString(8))));
            } while (cursor.moveToNext());
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  date = 'October 14, 2016'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                day0EventList.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  date = 'October 15, 2016'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                day1EventList.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  date = 'October 16, 2016'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                day2EventList.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  date = 'October 17, 2016'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                day3EventList.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }



        db.close();
    }

    private ArrayList<EventOrganizerInfo> getOrganizers(String string) {
        ArrayList<EventOrganizerInfo> organizers  = new ArrayList<>();
        String[] s = string.split("\\$");
        String name = new String();
        String contact = new String();
        for(int i=0; i<s.length; i++){
            if(i%2==0){
                name = s[i];
            }else{
                contact = s[i];
                organizers.add(new EventOrganizerInfo(name, contact, null));
            }

        }
        return  organizers;
    }

    private int getDrawable(String string) {
        Resources resources = getContext().getResources();
        final int resourceId = resources.getIdentifier( string , "mipmap",
                getContext().getPackageName());
        if(resourceId!=0){
            return resourceId;
        }
        return R.drawable.effe;
    }
}
