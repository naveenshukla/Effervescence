package com.naveen.effervescence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.facebook.internal.FacebookRequestErrorClassification.KEY_NAME;

/**
 * Created by Naveen on 20-06-2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    public String res = new String();
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="events.db";
    public static final String TABLE_EVENTS="events";
    public String query;
    /*Events table have following columns : title, place, category1, category2, day, date, ampm, hour, minute, _id*/
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY ="category";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DATE ="date";
    public static final String COLUMN_PLACE ="place";
    public static final String COLUMN_ORGANIZERS = "organizers";
    public static final String COLUMN_DRAWABLE = "drawable";
    public static final String COLUMN_RULES = "rules";
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("hello", "created database");
    }

    public void update(Context context, final int k, boolean network){

        if(k==1 && !network){
            Toast.makeText(context, "Make sure you are connected to internet", Toast.LENGTH_SHORT).show();
        }
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://effervescence-10e30.firebaseio.com/.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        res = response;
                        try {
                            JSONObject obj = new JSONObject(res);
                            JSONObject day1 = obj.getJSONObject("day0");
                            JSONArray eventarray = day1.getJSONArray("EventList");
                            for(int i=0; i < eventarray.length(); i++){


                                JSONObject event = eventarray.getJSONObject(i);
                                String category = event.getString("eventCategory");
                                String eventdate = event.getString("eventDate");
                                String eventTitle = event.getString("eventName");
                                String eventDescription = event.getString("eventDescription");
                                String eventTime = event.getString("eventTime");
                                String organizers = new String();
                                String drawable = event.getString("eventImage");
                                JSONArray eventOrg  = event.getJSONArray("eventOrganizers");
                                //Log.d("hello", String.valueOf(eventOrg.length()));
                                for(int j=0; j< eventOrg.length(); j++){
                                    JSONObject org = eventOrg.getJSONObject(j);
                                    organizers += org.getString("organizerName") + "$" +
                                            org.getString("organizerPhone") + "$";
                                    //Log.d("hello", organizers);
                                }
                                String eventPlace = event.getString("eventLocation");
                                String eventRules;


                                SQLiteDatabase db = getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(COLUMN_TITLE, eventTitle);
                                values.put(COLUMN_DESCRIPTION, eventDescription);
                                values.put(COLUMN_CATEGORY, category);
                                values.put( COLUMN_TIME, eventTime);
                                values.put( COLUMN_DATE, eventdate);
                                values.put(COLUMN_PLACE, eventPlace);
                                values.put(COLUMN_ORGANIZERS, organizers);
                                values.put(COLUMN_RULES, "to be updated");
                                values.put(COLUMN_DRAWABLE, drawable);
                                if(k == 0)
                                    db.insert(TABLE_EVENTS , null, values);
                                else
                                    db.update(TABLE_EVENTS, values,  COLUMN_TITLE + " = " + '"' + eventTitle +  '"' , null);
                                db.close();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            JSONObject obj = new JSONObject(res);
                            JSONObject day1 = obj.getJSONObject("day1");
                            JSONArray eventarray = day1.getJSONArray("EventList");
                            for(int i=0; i < eventarray.length(); i++){


                                JSONObject event = eventarray.getJSONObject(i);
                                String category = event.getString("eventCategory");
                                String eventdate = event.getString("eventDate");
                                String eventTitle = event.getString("eventName");
                                String eventDescription = event.getString("eventDescription");
                                String eventTime = event.getString("eventTime");
                                String organizers = new String();
                                String drawable = event.getString("eventImage");
                                JSONArray eventOrg  = event.getJSONArray("eventOrganizers");
                                //Log.d("hello", String.valueOf(eventOrg.length()));
                                for(int j=0; j< eventOrg.length(); j++){
                                    JSONObject org = eventOrg.getJSONObject(j);
                                    organizers += org.getString("organizerName") + "$" +
                                            org.getString("organizerPhone") + "$";
                                    //Log.d("hello", organizers);
                                }
                                String eventPlace = event.getString("eventLocation");
                                String eventRules;


                                SQLiteDatabase db = getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(COLUMN_TITLE, eventTitle);
                                values.put(COLUMN_DESCRIPTION, eventDescription);
                                values.put(COLUMN_CATEGORY, category);
                                values.put( COLUMN_TIME, eventTime);
                                values.put( COLUMN_DATE, eventdate);
                                values.put(COLUMN_PLACE, eventPlace);
                                values.put(COLUMN_ORGANIZERS, organizers);
                                values.put(COLUMN_RULES, "to be updated");
                                values.put(COLUMN_DRAWABLE, drawable);
                                if(k == 0)
                                    db.insert(TABLE_EVENTS , null, values);
                                else
                                    db.update(TABLE_EVENTS, values,  COLUMN_TITLE + " = " + '"' + eventTitle +  '"' , null);
                                db.close();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONObject obj = new JSONObject(res);
                            JSONObject day1 = obj.getJSONObject("day2");
                            JSONArray eventarray = day1.getJSONArray("EventList");
                            for(int i=0; i < eventarray.length(); i++){


                                JSONObject event = eventarray.getJSONObject(i);
                                String category = event.getString("eventCategory");
                                String eventdate = event.getString("eventDate");
                                String eventTitle = event.getString("eventName");
                                String eventDescription = event.getString("eventDescription");
                                String eventTime = event.getString("eventTime");
                                String organizers = new String();
                                String drawable = event.getString("eventImage");
                                JSONArray eventOrg  = event.getJSONArray("eventOrganizers");
                                //Log.d("hello", String.valueOf(eventOrg.length()));
                                for(int j=0; j< eventOrg.length(); j++){
                                    JSONObject org = eventOrg.getJSONObject(j);
                                    organizers += org.getString("organizerName") + "$" +
                                            org.getString("organizerPhone") + "$";
                                    //Log.d("hello", organizers);
                                }
                                String eventPlace = event.getString("eventLocation");
                                String eventRules;


                                SQLiteDatabase db = getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(COLUMN_TITLE, eventTitle);
                                values.put(COLUMN_DESCRIPTION, eventDescription);
                                values.put(COLUMN_CATEGORY, category);
                                values.put( COLUMN_TIME, eventTime);
                                values.put( COLUMN_DATE, eventdate);
                                values.put(COLUMN_PLACE, eventPlace);
                                values.put(COLUMN_ORGANIZERS, organizers);
                                values.put(COLUMN_RULES, "to be updated");
                                values.put(COLUMN_DRAWABLE, drawable);
                                if(k == 0)
                                    db.insert(TABLE_EVENTS , null, values);
                                else
                                    db.update(TABLE_EVENTS, values,  COLUMN_TITLE + " = " + '"' + eventTitle +  '"' , null);
                                db.close();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONObject obj = new JSONObject(res);
                            JSONObject day1 = obj.getJSONObject("day3");
                            JSONArray eventarray = day1.getJSONArray("EventList");
                            for(int i=0; i < eventarray.length(); i++){


                                JSONObject event = eventarray.getJSONObject(i);
                                String category = event.getString("eventCategory");
                                String eventdate = event.getString("eventDate");
                                String eventTitle = event.getString("eventName");
                                String eventDescription = event.getString("eventDescription");
                                String eventTime = event.getString("eventTime");
                                String organizers = new String();
                                String drawable = event.getString("eventImage");
                                JSONArray eventOrg  = event.getJSONArray("eventOrganizers");
                                //Log.d("hello", String.valueOf(eventOrg.length()));
                                for(int j=0; j< eventOrg.length(); j++){
                                    JSONObject org = eventOrg.getJSONObject(j);
                                    organizers += org.getString("organizerName") + "$" +
                                            org.getString("organizerPhone") + "$";
                                    //Log.d("hello", organizers);
                                }
                                String eventPlace = event.getString("eventLocation");
                                String eventRules;


                                SQLiteDatabase db = getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put(COLUMN_TITLE, eventTitle);
                                values.put(COLUMN_DESCRIPTION, eventDescription);
                                values.put(COLUMN_CATEGORY, category);
                                values.put( COLUMN_TIME, eventTime);
                                values.put( COLUMN_DATE, eventdate);
                                values.put(COLUMN_PLACE, eventPlace);
                                values.put(COLUMN_ORGANIZERS, organizers);
                                values.put(COLUMN_RULES, "to be updated");
                                values.put(COLUMN_DRAWABLE, drawable);
                                if(k == 0)
                                    db.insert(TABLE_EVENTS , null, values);
                                else
                                    db.update(TABLE_EVENTS, values,  COLUMN_TITLE + " = " + '"' + eventTitle +  '"' , null);
                                db.close();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("hello", "bakchodi");
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
         String query = "CREATE TABLE " +  TABLE_EVENTS + " ( " + COLUMN_TITLE + " TEXT PRIMARY KEY, " +
                COLUMN_CATEGORY + " TEXT, " + COLUMN_TIME + " TEXT, " +  COLUMN_DATE  + "  TEXT,  " +  COLUMN_PLACE   + " TEXT,  " +
                COLUMN_RULES   + " TEXT,  " + COLUMN_DESCRIPTION   + " TEXT ,  " +  COLUMN_DRAWABLE   + " TEXT ,  " +  COLUMN_ORGANIZERS   + " TEXT); ";

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_EVENTS);
        onCreate(db);
    }
}