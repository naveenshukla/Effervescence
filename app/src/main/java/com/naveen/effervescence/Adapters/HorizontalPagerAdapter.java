package com.naveen.effervescence.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gigamole.infinitecycleviewpager.VerticalInfiniteCycleViewPager;
import com.naveen.effervescence.Model.EventInfo;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Utils.EventList;

import java.util.ArrayList;

import static com.naveen.effervescence.Utils.EventList.rules;
import static java.security.AccessController.getContext;

/**
 * Created by betterclever on 30/8/16.
 */
public class HorizontalPagerAdapter extends PagerAdapter{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<EventInfo> online = new ArrayList<>();
    private ArrayList<EventInfo> dance = new ArrayList<>();
    private ArrayList<EventInfo> drama = new ArrayList<>();
    private ArrayList<EventInfo> literary = new ArrayList<>();
    private ArrayList<EventInfo> informal = new ArrayList<>();
    private ArrayList<EventInfo> photography = new ArrayList<>();
    private ArrayList<EventInfo> music = new ArrayList<>();
    private ArrayList<EventInfo> finearts = new ArrayList<>();
    private final ArrayList[] EVENTS_BY_CATEGORIES = new ArrayList[]{
            online,
            dance,
            drama,
            literary,
            informal,
            photography,
            music,
            finearts
    };

    public HorizontalPagerAdapter(final Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        getData(mContext);
    }

    @Override
    public int getCount() {
        return EVENTS_BY_CATEGORIES.length;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view = mLayoutInflater.inflate(R.layout.two_way_layout,container,false);

        final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
        verticalInfiniteCycleViewPager.setAdapter(
                new VerticalPagerAdapter(mContext, EVENTS_BY_CATEGORIES[position])
        );
        TextView textView = (TextView) view.findViewById(R.id.event_category_textview);
        switch (position){
            case 0: textView.setText("Online"); break;
            case 1: textView.setText("Drama"); break;
            case 2: textView.setText("Dance"); break;
            case 3: textView.setText("Literary"); break;
            case 4: textView.setText("Informal"); break;
            case 5: textView.setText("Photography"); break;
            case 6: textView.setText("Music"); break;
            case 7: textView.setText("Fine Arts");
        }
        container.addView(view);
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
    public void getData(Context context) {
        MyDBHandler myDBHandler = new MyDBHandler(context);
        String Table_Name="events";

        String selectQuery = "SELECT  * FROM " + Table_Name;
        SQLiteDatabase db = myDBHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'online'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                online.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        if(online.isEmpty()){
            online.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            online.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            online.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            online.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'dance'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                dance.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        if(dance.isEmpty()){
            dance.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            dance.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            dance.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            dance.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'drama'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                drama.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }


        if(drama.isEmpty()){
            drama.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            drama.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            drama.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            drama.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'literary'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                literary.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        if(literary.isEmpty()){
            literary.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            literary.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            literary.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            literary.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'informal'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                informal.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }


        if(informal.isEmpty()){
            informal.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            informal.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            informal.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            informal.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }


        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'photography'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                photography.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }


        if(photography.isEmpty()){
            photography.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            photography.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            photography.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            photography.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'music'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                music.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }

        if(music.isEmpty()){
            music.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            music.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            music.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            music.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }

        selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  category = 'finearts'";
        //SQLiteDatabase db = myDBHandler.getReadableDatabase();
        cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                finearts.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)) ,
                        cursor.getString(2), cursor.getString(3),cursor.getString(4),rules , null));
            } while (cursor.moveToNext());
        }


        if(finearts.isEmpty()){
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
            finearts.add(new EventInfo("Perplexus",
                    "Online Treasure Hunt. Sample Description",
                    "Online",
                    R.mipmap.antakshiri,
                    "Perplexus_time",
                    "Perplexus_date",
                    "Online",
                    rules, null));
        }
        db.close();
    }

    private int getDrawable(String string) {
        Resources resources = mContext.getResources();
        final int resourceId = resources.getIdentifier( string + "2" , "mipmap",
                mContext.getPackageName());
        if(resourceId!=0){
            return resourceId;
        }
        return R.drawable.effe;
    }
}
