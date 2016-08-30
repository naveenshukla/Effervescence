package com.naveen.effervescence.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.naveen.effervescence.Adapters.HorizontalPagerAdapter;
import com.naveen.effervescence.R;

public class Categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);

        HorizontalPagerAdapter horizontalPagerAdapter = new HorizontalPagerAdapter(this);
        horizontalInfiniteCycleViewPager.setAdapter(horizontalPagerAdapter);

    }
}
