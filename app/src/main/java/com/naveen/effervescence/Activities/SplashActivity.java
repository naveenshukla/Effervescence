package com.naveen.effervescence.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.naveen.effervescence.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);

        // Start animating the image
        final ImageView splash = (ImageView) findViewById(R.id.effewheel);
        splash.startAnimation(rotate);


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
