package com.naveen.effervescence.ProShowsFragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cleveroad.splittransformation.SquareViewPagerIndicator;
import com.cleveroad.splittransformation.TransformationAdapterWrapper;
import com.hanks.htextview.HTextView;
import com.hanks.htextview.animatetext.HText;
import com.naveen.effervescence.Activities.EventDetailActivity;
import com.naveen.effervescence.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
class FragmentData{
    String eventName;
    int imageDrawable;
    public FragmentData(String eventName, int imageDrawable){
        this.eventName = eventName;
        this.imageDrawable = imageDrawable;
    }
}

public class ProShowsList extends Fragment {

    private ViewPager viewPager;
    private SquareViewPagerIndicator indicator;
    private HTextView titleTextView;

    public static ProShowsList instance() {
        return new ProShowsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pro_shows_list, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        indicator = (SquareViewPagerIndicator) view.findViewById(R.id.indicator);
        titleTextView = (HTextView) view.findViewById(R.id.event_title_textview);
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent ;
                int currI = viewPager.getCurrentItem();
                switch (currI){
                    case 0:
                        Log.d("hello", String.valueOf(0));
                        intent = new Intent( getContext(), EventDetailActivity.class);
                        intent.putExtra("event_name", "The Local Train");
                        break;
                    case 1:
                        Log.d("hello", String.valueOf(1));
                        intent= new Intent( getContext(), EventDetailActivity.class);
                        intent.putExtra("event_name", "Coke Studio - Benny Dayal");
                        break;
                    case 2 :
                        Log.d("hello", String.valueOf(2));
                        intent = new Intent( getContext(), EventDetailActivity.class);
                        intent.putExtra("event_name", "Bollywood Night");
                        break;
                    default:
                        Log.d("hello", String.valueOf(3));
                        intent = new Intent( getContext(), EventDetailActivity.class);
                        intent.putExtra("event_name","The Local Train");
                }
                Log.d("hello", String.valueOf(currI));
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getChildFragmentManager());
        titleTextView.animateText("The Local Train");
        TransformationAdapterWrapper wrapper = TransformationAdapterWrapper
                .wrap(getContext(), adapter)
                .rows(8)
                .columns(6)
                .build();
        viewPager.setAdapter(wrapper);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: titleTextView.animateText("The Local Train"); break;
                    case 1: titleTextView.animateText("Coke Studio - Benny Dayal"); break;
                    case 2: titleTextView.animateText("Bollywood Night"); break;
                    case 3: titleTextView.animateText("The Local Train"); break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setPageTransformer(false, wrapper);
        indicator.initializeWith(viewPager);
    }


    @Override
    public void onDestroyView() {
        indicator.reset();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(R.string.proshows);
    }


    private static class SimplePagerAdapter extends FragmentStatePagerAdapter {
        private  ArrayList<FragmentData> listq = new ArrayList<>();
        private final int[] drawables = new int[]{
                R.drawable.edm,
                R.drawable.alamode2,
                R.drawable.alamode2,
                R.drawable.edm
        };
        public SimplePagerAdapter(FragmentManager fm) {
            super(fm);
            listq.add(new FragmentData("The Local Train",R.drawable.edm));
            listq.add(new FragmentData("Coke Studio - Benny Dayal",R.drawable.alamode2));
            listq.add(new FragmentData("Bollywood Night",R.drawable.alamode2));
            listq.add(new FragmentData("The Local Train",R.drawable.edm));
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentOne.instance(listq.get(position).eventName,listq.get(position).imageDrawable);
        }

        @Override
        public int getCount() {
            return drawables.length;
        }
    }
}
