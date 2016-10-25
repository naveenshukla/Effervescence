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

    }
}
