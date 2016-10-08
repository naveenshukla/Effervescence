package com.naveen.effervescence.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.naveen.effervescence.Model.EventInfo;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;

import java.util.ArrayList;

/**
 * Created by betterclever on 30/8/16.
 */
public class EventList {

    public static ArrayList<String> rules = new ArrayList<>();
    static {
        rules.add("Sample Rules1");
        rules.add("Sample Rules2");
        rules.add("Sample Rules3");
        rules.add("Sample Rules4");
    }
    public static ArrayList<EventInfo> onlineEventList= new ArrayList<>();
    static {
        onlineEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.perplexus,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null
                ));
        onlineEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.stegolica,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));
        onlineEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.stegolica,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));
        onlineEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.perplexus,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));

    }

    public static ArrayList<EventInfo> danceEventList= new ArrayList<>();
    static {
        danceEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.dancecharades,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));
        danceEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.footloose,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));
        danceEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.dancecharades,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,
                null));
        danceEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.footloose,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules,null));
    }

    public static ArrayList<EventInfo> musicEventList= new ArrayList<>();
    static {
        musicEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.antakshiri,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        musicEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.drawable.singer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        musicEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.antakshiri,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        musicEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.drawable.singer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
    }

    public static ArrayList<EventInfo> dramaEventList= new ArrayList<>();
    static {
        dramaEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.dumbcharades,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        dramaEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.bindaasbol,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        dramaEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.dumbcharades,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        dramaEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.bindaasbol,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
    }

    public static ArrayList<EventInfo> fineartsEventList= new ArrayList<>();
    static {
        fineartsEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.drawable.effewheelfull,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        fineartsEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.tasveer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        fineartsEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.balloon,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        fineartsEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.drawable.singer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));

    }

    public static ArrayList<EventInfo> photographyEventList= new ArrayList<>();
    static {
        photographyEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.tasveer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        photographyEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.balloon,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        photographyEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.mipmap.basketball,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        photographyEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.mipmap.balloon,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));

    }

    public static ArrayList<EventInfo> literaryEventList= new ArrayList<>();
    static {
        literaryEventList.add(new EventInfo("Perplexus",
                "Online Treasure Hunt. Sample Description",
                "Online",
                R.drawable.effewheelfull,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        literaryEventList.add(new EventInfo("Stegolica",
                "Online Crypto Hunt. Sample Description",
                "Online",
                R.drawable.singer,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));

    }


    public static ArrayList<EventInfo> informalEventList= new ArrayList<>();
    static {
        informalEventList.add(new EventInfo("Anime Quiz",
                "Sample Description. Sample Description",
                "Quiz",
                R.mipmap.bookcricket,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        informalEventList.add(new EventInfo("Anime Quiz",
                "Sample Description. Sample Description",
                "Quiz",
                R.mipmap.antakshiri,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        informalEventList.add(new EventInfo("Anime Quiz",
                "Sample Description. Sample Description",
                "Quiz",
                R.mipmap.bollywoodtambola,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
        informalEventList.add(new EventInfo("Anime Quiz",
                "Sample Description. Sample Description",
                "Quiz",
                R.mipmap.bquiz,
                "Perplexus_time",
                "Perplexus_date",
                "Online",
                rules, null));
    }

 }
