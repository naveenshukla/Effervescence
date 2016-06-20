package com.naveen.effervescence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Naveen on 20-06-2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="events.db";
    public static final String TABLE_EVENTS="events";
    /*Events table have following columns : title, place, category1, category2, day, date, ampm, hour, minute, _id*/
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PLACE ="place";
    public static final String COLUMN_CATEGORY1 ="category1";
    public static final String COLUMN_CATEGORY2 ="category2";
    public static final String COLUMN_DAY ="day";
    public static final String COLUMN_DATE ="date";
    public static final String COLUMN_AMPM ="ampm";
    public static final String COLUMN_HOUR ="hour";
    public static final String COLUMN_MINUTE ="minute";
    
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
       
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String query = "CREATE TABLE " +  TABLE_EVENTS + " ( " +  COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
         + COLUMN_TITLE + " TEXT, " + COLUMN_PLACE + " TEXT, " +  
                COLUMN_CATEGORY1 + " TEXT, " + COLUMN_CATEGORY2 + " TEXT, " +  COLUMN_DAY  + "  TEXT,  " +  COLUMN_DATE   + " TEXT,  " +
                COLUMN_AMPM   + " TEXT,  " + COLUMN_HOUR   + " INTEGER,  " +  COLUMN_MINUTE   + " INTEGER); "; 

        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " +  TABLE_EVENTS);
        onCreate(db);
    }

    public void addEvents(Events events){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, events.getTitle());
        values.put(COLUMN_PLACE, events.getPlace());
        values.put(COLUMN_CATEGORY1, events.getCategory1());
        values.put(COLUMN_CATEGORY2, events.getCategory2());
        values.put(COLUMN_DAY, events.getDay());
        values.put(COLUMN_DATE,events.getDate());
        values.put(COLUMN_AMPM,events.getAmpm());
        values.put(COLUMN_HOUR,events.getHour());
        values.put(COLUMN_MINUTE,events.getMinute());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_EVENTS,null,values);
        db.close();
    }

    String[] columntitle(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("title"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("title"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columnplace(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("place"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("place"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columncategory1(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("category1"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("category1"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columncategory2(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("category2"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("category2"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columnday(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("day"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("day"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columndate(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("date"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("date"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columnampm(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("ampm"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("ampm"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columnhour(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("hour"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("hour"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
    String[] columnminute(){

        String[] dbString= new String[100];
        int count = 0;
        SQLiteDatabase db= getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_EVENTS + " WHERE 1";

        Cursor c =db.rawQuery(query,null);

        c.moveToFirst();

        while (!c.isAfterLast())

        {

            if(c.getString(c.getColumnIndex("minute"))!=null)

            {

                dbString[count++] = c.getString(c.getColumnIndex("minute"));

            }

            c.moveToNext();

        }

        db.close();

        return dbString;
    }
}