package com.naveen.effervescence.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.ProShowsFragments.ProShowsList;
import com.naveen.effervescence.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.naveen.effervescence.Activities.SplashActivity.wait;

public class ProShows extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    MyDBHandler db  = new MyDBHandler(this);
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_shows);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.setDrawerListener(toggle);
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, ProShowsList.instance())
                    .commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.category) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ProShows.this, Categories.class);
                    startActivity(intent);
                }
            }, wait);
        } else if (id == R.id.day) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ProShows.this, DaysViewActivity.class);
                    startActivity(intent);
                }
            }, wait);

        }  else if (id == R.id.proshows) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ProShows.this, ProShows.class);
                    startActivity(intent);
                }
            }, wait);
        } else if (id == R.id.sponsers) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.effe.org.in"));
                    startActivity(browserIntent);
                }
            }, wait);
        } else if (id == R.id.developers) {

            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ProShows.this, OrganizersActivity.class);
                    startActivity(intent);
                }
            }, wait);
        }
        else if (id == R.id.organizers) {

            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(ProShows.this, Developers.class);
                    startActivity(intent);
                }
            }, wait);
        }
        else if(id == R.id.refreshdata){
            if(!isNetworkAvailable()){
                Toast.makeText(this, "Make sure you are connected to Internet", Toast.LENGTH_LONG).show();
                return true;
            }

            final ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Refreshing Data");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.show();

            db.update(this ,1, isNetworkAvailable());

            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                    startActivity(getIntent());
                    progress.dismiss();
                    // If you want to call Activity then call from here for 5 seconds it automatically call and your image disappear....
                }
            }, 5000);
            return true;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
