package com.naveen.effervescence;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class List1 extends Activity {
    private List<Events> EventList = new ArrayList<>();
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        eventsAdapter = new EventsAdapter(EventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(eventsAdapter);

        prepareEventData();
    }
    private void prepareEventData(){
        Events events = new Events("Parallel World : A New Rock Music Experience","Complete Actors Place","Live","Band"
        ,"Thursday",8,23,"12%12%2014","AM");
        EventList.add(events);
        events = new Events("Parallel World : A New Rock Music Experience","Complete Actors Place","Rock","Live Band"
                ,"Thursday",8,23,"12%12%2014","AM");
        EventList.add(events);
        events = new Events("Parallel World : A New Rock Music Experience","Complete Actors Place","Rock","Live Band"
                ,"Thursday",8,23,"12%12%2014","AM");
        EventList.add(events);
        eventsAdapter.notifyDataSetChanged();
    }

}
