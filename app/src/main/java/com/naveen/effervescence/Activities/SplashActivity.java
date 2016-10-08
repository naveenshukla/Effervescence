package com.naveen.effervescence.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    public static int wait =  100;
    public String res = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MyDBHandler db  = new MyDBHandler(this);
        db.update(this,0);

// Request a string response from the provided URL.

        final RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);

        // Start animating the image
        final ImageView splash = (ImageView) findViewById(R.id.effewheel);
        splash.startAnimation(rotate);

        //final ShapeRipple ripple = (ShapeRipple) findViewById(R.id.ripple);
        ///ripple.setRippleShape(new Circle());
        //ripple.setRippleColor(getResources().getColor(R.color.md_deep_orange_600));
        //ripple.setEnableRandomPosition(true);
        //ripple.setEnableRandomColor(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rotate.cancel();
                //ripple.stopRipple();
                final Intent mainIntent = new Intent(SplashActivity.this, Home.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 3000);



    }
}
