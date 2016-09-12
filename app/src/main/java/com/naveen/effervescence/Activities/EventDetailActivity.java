package com.naveen.effervescence.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.naveen.effervescence.R;

public class EventDetailActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        backdrop.setImageResource(R.mipmap.blinddate);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("Sample Event");

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.blinddate);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();
            int def = 0x000000;
            int vibrantDark = palette.getDarkVibrantColor(def);
            CollapsingToolbarLayout cp = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            cp.setContentScrimColor(vibrantDark);
            cp.setStatusBarScrimColor(palette.getDarkVibrantColor(def));
        }



        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.add_org_here);
        linearLayout.removeAllViews();
        View v1 = View.inflate(this,R.layout.organizer_event_detail,null);
        View v2 = View.inflate(this,R.layout.organizer_event_detail,null);
        View v3 = View.inflate(this,R.layout.organizer_event_detail,null);
        linearLayout.addView(v1);
        linearLayout.addView(v2);
        linearLayout.addView(v3);
    }
}
