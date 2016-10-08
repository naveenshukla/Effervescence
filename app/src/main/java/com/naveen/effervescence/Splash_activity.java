package com.naveen.effervescence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.naveen.effervescence.Activities.SplashActivity;
import com.naveen.effervescence.Activities.DaysViewActivity;

public class Splash_activity extends Activity {
    WebView mWebView;
    Animation b;
    ImageView balloon;
    //private static int SPLASH_TIME_OUT = 1000000;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);

        startActivity(new Intent(this, SplashActivity.class));

         final LinearLayout l = (LinearLayout)findViewById(R.id.scrollView);
        TextView t = (TextView)findViewById(R.id.title);
        t.setTypeface(null, Typeface.BOLD);
        final Button tour = (Button)findViewById(R.id.tour);
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tour.setBackgroundResource(R.drawable.invert_rounded);
            }
        });
        final Button gotoevents = (Button)findViewById(R.id.gotoevents);
        gotoevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoevents.setBackgroundResource(R.drawable.invert_rounded);
                Intent intent = new Intent(Splash_activity.this,DaysViewActivity.class);
                startActivity(intent);
            }
        });
        new Handler().postDelayed(new Runnable() {
            public void run() {
                final Animation a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                b = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.reversemove);
                                l.startAnimation(b);
                            }
                        },2000);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                l.startAnimation(a);
            }
        },2000);
    }
}
