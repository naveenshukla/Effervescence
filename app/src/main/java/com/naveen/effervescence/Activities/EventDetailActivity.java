package com.naveen.effervescence.Activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.naveen.effervescence.R;

public class EventDetailActivity extends Activity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        int titlebg = getIntent().getIntExtra("event_image",R.mipmap.blinddate);
        backdrop.setImageResource(titlebg);
        //backdrop.setImageResource(R.mipmap.blinddate);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle(getIntent().getCharSequenceExtra("event_name"));

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), titlebg);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();
            int def = 0x000000;
            int vibrant = palette.getMutedColor(def);
            Log.d("vibrant", String.format("#%06X", 0xFFFFFF & vibrant));
            Log.d("vibrant dark",String.format("#%06X", 0xFFFFFF & palette.getDarkVibrantColor(def)));
            CollapsingToolbarLayout cp = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            cp.setContentScrimColor(vibrant);
            cp.setStatusBarScrimColor(palette.getDarkMutedColor(def));
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
