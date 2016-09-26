package com.naveen.effervescence;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.naveen.effervescence.Model.EventTimeData;

import java.util.ArrayList;

/**
 * Created by better_clever on 13/9/16.
 */
public class Effervescence extends Application {

    FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    public void onCreate() {
        super.onCreate();
/*

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (!prefs.getBoolean("firstTime", false)) {
            // <---- run your one time code here
            //databaseSetup();

            // mark first time has runned.
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }
*/

        final Context context = this;

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        final SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.datetime_sharedpref_file), Context.MODE_PRIVATE);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        firebaseRemoteConfig.setConfigSettings(configSettings);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Run mFirebaseRemoteConfig.fetch(timeout) here, and it works
                firebaseRemoteConfig.fetch(20).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {

                            firebaseRemoteConfig.activateFetched();
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("animequiz_time", firebaseRemoteConfig.getString("animequiz_time"));
                            editor.putString("animequiz_date", firebaseRemoteConfig.getString("animequiz_date"));
                            Log.d("cool", "dude");
                            Log.d("animequiz_date", firebaseRemoteConfig.getString("animequiz_date"));
                            Log.d("animequiz_time", firebaseRemoteConfig.getString("animequiz_time"));
                            editor.commit();
                        }
                        else {
                            Log.d("remoteConfig","Failed");
                        }
                    }
                });

            }
        }, 0);


    }
}
