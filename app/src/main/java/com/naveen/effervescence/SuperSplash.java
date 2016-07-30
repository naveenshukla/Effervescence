package com.naveen.effervescence;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by naveen on 31/7/16.
 */
public class SuperSplash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.super_splash);
        startAnim();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                stopAnim();
                Intent intent = new Intent(SuperSplash.this,Splash_activity.class);
                startActivity(intent);
            }
        },5000);
    }

    private void stopAnim() {
        findViewById(R.id.avloadingIndicatorView).setVisibility(View.GONE);
    }

    private void startAnim() {
        findViewById(R.id.avloadingIndicatorView).setVisibility(View.VISIBLE);
    }


}
