package com.naveen.effervescence.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.naveen.effervescence.ProShowsFragments.ProShowsList;
import com.naveen.effervescence.R;

import static com.naveen.effervescence.Activities.SplashActivity.wait;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageView imageView;
    DrawerLayout drawer;
    public boolean flagClicked = false;
    LinearLayout s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageView = (ImageView)findViewById(R.id.maineffe);
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
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout2);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.web:
                Toast.makeText(this,"web", Toast.LENGTH_LONG ).show();
                //your code here
                return true;
            case R.id.like:
                Toast.makeText(this,"like", Toast.LENGTH_LONG ).show();
                //your code here
                return true;
            case R.id.rate_us:
                Toast.makeText(this,"rate", Toast.LENGTH_LONG ).show();
                return true;
            case R.id.credits:
                Toast.makeText(this,"credits", Toast.LENGTH_LONG ).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
