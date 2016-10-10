package com.naveen.effervescence.Activities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.ProShowsFragments.ProShowsList;
import com.naveen.effervescence.R;

import java.util.Timer;
import java.util.TimerTask;

import static com.naveen.effervescence.Activities.SplashActivity.wait;
import static com.naveen.effervescence.R.id.viewPager;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageView;
    DrawerLayout drawer;
    MyDBHandler db  = new MyDBHandler(this);
    public boolean flagClicked = false;
    LinearLayout s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView)findViewById(R.id.maineffe);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Welcome");
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        // [END subscribe_topics]

        // Log and toast
        String msg = getString(R.string.msg_subscribed);
        Log.d("hello", msg);
        //Toast.makeText(Home.this, msg, Toast.LENGTH_SHORT).show();

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
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        s = (LinearLayout) findViewById(R.id.aboutus);
        s.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flagClicked == false){
                    //imageView.setImageResource(R.drawable.bw);
                    Animation fadeOut = AnimationUtils.loadAnimation(Home.this, R.anim.fade_out);
                    imageView.startAnimation(fadeOut);

                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //Animation fadeOut = AnimationUtils.loadAnimation(Home.this, R.anim.fade_out);
                            //imageView.startAnimation(fadeOut);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    imageView.setImageResource(R.drawable.bw);
                    Animation fadeIn = AnimationUtils.loadAnimation(Home.this, R.anim.fade_in);
                    imageView.startAnimation(fadeIn);

                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //Animation fadeOut = AnimationUtils.loadAnimation(Home.this, R.anim.fade_out);
                            //imageView.startAnimation(fadeOut);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    s.setVisibility(View.VISIBLE);
                    flagClicked = true;
                    fab.setBackgroundColor(Color.BLUE);
                }
                else{
                    imageView.setImageResource(R.drawable.carnivalhome);
                    Animation fadeIn = AnimationUtils.loadAnimation(Home.this, R.anim.fade_in);
                    imageView.startAnimation(fadeIn);

                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                        }
                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //Animation fadeOut = AnimationUtils.loadAnimation(Home.this, R.anim.fade_out);
                            //imageView.startAnimation(fadeOut);
                        }
                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    s.setVisibility(View.GONE);
                    flagClicked = false;
                    fab.setBackgroundColor(Color.RED);
                }


                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });
        fab.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main , menu);
        return true;
    }

    @Override
    public void onBackPressed() {
       finish();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);

        if (id == R.id.category) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Home.this, Categories.class);
                    startActivity(intent);
                }
            }, wait);
        } else if (id == R.id.day) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Home.this, DaysViewActivity.class);
                    startActivity(intent);
                }
            }, wait);

        }  else if (id == R.id.proshows) {
            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Home.this, ProShows.class);
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
                    Intent intent = new Intent(Home.this, OrganizersActivity.class);
                    startActivity(intent);
                }
            }, wait);
        }
        else if (id == R.id.organizers) {

            drawer.closeDrawer(GravityCompat.START);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Home.this, Developers.class);
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
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.web:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.effe.org.in"));
                startActivity(browserIntent);
                //your code here
                return true;
            case R.id.like:
                Intent facebookintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/effervescence.iiita/?fref=ts"));
                startActivity(facebookintent);
                return true;
            case R.id.rate_us:
                launchMarket();
                return true;
            case R.id.credits:
                Intent developersintent = new Intent(Home.this, Developers.class);
                startActivity(developersintent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }
}
