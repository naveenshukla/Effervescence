package com.naveen.effervescence;

import android.app.TabActivity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import java.util.ArrayList;
import java.util.List;

public class Tab3 extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
  //  private List<Events> EventList = new ArrayList<>();
    private RecyclerView recyclerView;
   // private EventsAdapter eventsAdapter;
    private String[] mPlanetTitles;
    private List<Events> EventList = new ArrayList<>();
    private EventsAdapter eventsAdapter;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView1,recyclerView2,recyclerView3;
    private ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab One");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab Two");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
        host.addTab(spec);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        eventsAdapter = new EventsAdapter(EventList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView1 = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView1.setLayoutManager(mLayoutManager);
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
        recyclerView1.setAdapter(eventsAdapter);
        prepareEventData();

        eventsAdapter = new EventsAdapter(EventList);
        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerView2 = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView2.setLayoutManager(mLayoutManager1);
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setAdapter(eventsAdapter);
        prepareEventData();

        eventsAdapter = new EventsAdapter(EventList);
        RecyclerView.LayoutManager mLayoutManager2 = new LinearLayoutManager(getApplicationContext());
        recyclerView3 = (RecyclerView) findViewById(R.id.recycler_view2);
        recyclerView3.setLayoutManager(mLayoutManager2);
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setAdapter(eventsAdapter);
        prepareEventData();

    }

    private void prepareEventData() {
            Events events = new Events(1,"Parallel World : A New Rock Music Experience","Complete Actors Place","Live","Band"
                    ,"Thursday",8,23,"12%12%2014","AM");
            EventList.add(events);
            events = new Events(2,"Parallel World : A New Rock Music Experience","Complete Actors Place","Rock","Live Band"
                    ,"Thursday",8,23,"12%12%2014","AM");
            EventList.add(events);
            events = new Events(3,"Parallel World : A New Rock Music Experience","Complete Actors Place","Rock","Live Band"
                    ,"Thursday",8,23,"12%12%2014","AM");
            EventList.add(events);
            eventsAdapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
