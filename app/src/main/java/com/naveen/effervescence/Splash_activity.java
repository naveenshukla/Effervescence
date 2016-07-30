package com.naveen.effervescence;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.api.Scope;

import org.w3c.dom.Text;

public class Splash_activity extends Activity {
    WebView mWebView;
    Animation b;
    ImageView balloon;
    //private static int SPLASH_TIME_OUT = 1000000;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_activity);
        //ScrollView s = (ScrollView)findViewById(R.id.scrollview);
        final LinearLayout l = (LinearLayout)findViewById(R.id.scrollView);
        //ImageView img = (ImageView)findViewById(R.id.splashbg);
        //img.setBackgroundResource(R.drawable.spin_animation);
        //AnimationDrawable frameAnimation = (AnimationDrawable)img.getBackground();
        //frameAnimation.setEnterFadeDuration(1000);
        //frameAnimation.setExitFadeDuration(1000);
        TextView t = (TextView)findViewById(R.id.title);
        t.setTypeface(null, Typeface.BOLD);
        //frameAnimation.start();
        Button button = (Button)findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Splash_activity.this,Tab3.class);
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
