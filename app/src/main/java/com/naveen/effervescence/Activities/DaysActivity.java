package com.naveen.effervescence.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.naveen.effervescence.Fragments.FourFragment;
import com.naveen.effervescence.Fragments.OneFragment;
import com.naveen.effervescence.R;

public class DaysActivity extends AppCompatActivity {

    private MaterialViewPager materialViewPager;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days);

        materialViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        materialViewPager.getViewPager().setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 4){
                    default: return new OneFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        Toolbar toolbar = materialViewPager.getToolbar();
        setSupportActionBar(toolbar);
    }

}

