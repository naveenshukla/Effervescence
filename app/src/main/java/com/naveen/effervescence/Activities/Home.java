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
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
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
import com.melnykov.fab.FloatingActionButton;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.ProShowsFragments.ProShowsList;
import com.naveen.effervescence.R;
import com.tiancaicc.springfloatingactionmenu.MenuItemView;
import com.tiancaicc.springfloatingactionmenu.OnMenuActionListener;
import com.tiancaicc.springfloatingactionmenu.SpringFloatingActionMenu;

import java.util.Timer;
import java.util.TimerTask;

import static com.naveen.effervescence.Activities.SplashActivity.wait;
import static com.naveen.effervescence.R.id.viewPager;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ImageView imageView;
    DrawerLayout drawer;
    MyDBHandler db  = new MyDBHandler(this);
    public boolean flagClicked = false;
    LinearLayout s;
    FloatingActionButton fab;
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

        /*
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);*/

        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);
        s = (LinearLayout) findViewById(R.id.aboutus);
        s.setVisibility(View.GONE);


        fab = new FloatingActionButton(this);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setImageResource(R.drawable.ic_list_white_24dp);
        fab.setColorPressedResId(R.color.colorPrimary);
        fab.setColorNormalResId(R.color.md_deep_orange_500);
        fab.setColorRippleResId(R.color.md_amber_200);
        fab.setShadow(true);

        new SpringFloatingActionMenu.Builder(this)
                .fab(fab)
                //add menu item via addMenuItem(bgColor,icon,label,label color,onClickListener)
                .addMenuItem(R.color.md_yellow_800, R.drawable.ic_categories, "Categories", R.color.md_white_1000,this)
                .addMenuItem(R.color.md_brown_400, R.drawable.ic_day_white_24dp, "Days", R.color.md_white_1000,this)
                .addMenuItem(R.color.md_deep_orange_500, R.drawable.ic_person_white_24dp, "Organizers", R.color.md_white_1000,this)
                .addMenuItem(R.color.md_blue_500, R.drawable.ic_about_us_24dp, "About Us", R.color.md_white_1000,this)
                .addMenuItem(R.color.md_purple_600, R.drawable.ic_proshows_24dp, "ProShows", R.color.md_white_1000,this)
                .addMenuItem(R.color.md_green_600, R.drawable.ic_person_white_24dp, "Developers", R.color.md_white_1000,this)
                //you can choose menu layout animation
                .animationType(SpringFloatingActionMenu.ANIMATION_TYPE_TUMBLR)
                //setup reveal color while the menu opening
                .revealColor(R.color.colorPrimary)
                .gravity(Gravity.RIGHT | Gravity.BOTTOM)
                .onMenuActionListner(new OnMenuActionListener() {
                    @Override
                    public void onMenuOpen() {
                        fab.setImageResource(R.drawable.ic_close_24dp);
                    }

                    @Override
                    public void onMenuClose() {
                        fab.setImageResource(R.drawable.ic_list_white_24dp);
                    }
                })
                .build();


        /*

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
        */
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


    @Override
    public void onClick(View view) {

        MenuItemView menuItemView = (MenuItemView) view;

        String txt = (String) menuItemView.getLabelTextView().getText();


        if(txt.equals("Categories")){
            Intent intent = new Intent(Home.this, Categories.class);
            startActivity(intent);
        }
        else if(txt.equals("Days")){
            Intent intent = new Intent(Home.this, DaysViewActivity.class);
            startActivity(intent);
        }
        else if(txt.equals("Developers")){
            Intent developersintent = new Intent(Home.this, Developers.class);
            startActivity(developersintent);
        }
        else if(txt.equals("Info")){

        }
        else if(txt.equals("Organizers")){
            Intent intent = new Intent(Home.this, OrganizersActivity.class);
            startActivity(intent);
        }
        else if(txt.equals("ProShows")){
            Intent intent = new Intent(Home.this, ProShows.class);
            startActivity(intent);
        }
        else {
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
                fab.performClick();
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
                fab.performClick();
            }
        }
    }
}
