package com.naveen.effervescence.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.naveen.effervescence.Model.EventInfo;
import com.naveen.effervescence.Model.EventOrganizerInfo;
import com.naveen.effervescence.MyDBHandler;
import com.naveen.effervescence.R;

import java.util.ArrayList;

import static com.naveen.effervescence.Utils.EventList.rules;
import static java.security.AccessController.getContext;

public class EventDetailActivity extends AppCompatActivity {

    TextView time, date, location, detail;
    String organizer;

    ArrayList<EventInfo> detaillist = new ArrayList<>();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        int titlebg = getIntent().getIntExtra("event_image", R.mipmap.blinddate);
        String title = getIntent().getStringExtra("event_name");
        getData(this, title);
        Log.d("hello", title);
        titlebg = detaillist.get(0).getImage_drawable();
        backdrop.setImageResource(titlebg);
        //backdrop.setImageResource(R.mipmap.blinddate);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setHomeButtonEnabled(true);

        tb.setTitle(getIntent().getCharSequenceExtra(title));
        time = (TextView) findViewById(R.id.time_textview);
        date = (TextView) findViewById(R.id.date_textView);
        location = (TextView) findViewById(R.id.location_textview);
        detail = (TextView) findViewById(R.id.detail_tv);


        tb.setTitle(detaillist.get(0).getEventName());
        time.setText(detaillist.get(0).getTimeSharedPrefVariable());
        date.setText(detaillist.get(0).getDateSharedPrefVariable().substring(0,10));
        location.setText(detaillist.get(0).getLocation());
        detail.setText(detaillist.get(0).getEventDescription());

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), titlebg);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();
            int def = 0x000000;
            int vibrant = palette.getMutedColor(def);
            Log.d("vibrant", String.format("#%06X", 0xFFFFFF & vibrant));
            Log.d("vibrant dark", String.format("#%06X", 0xFFFFFF & palette.getDarkVibrantColor(def)));
            CollapsingToolbarLayout cp = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            cp.setContentScrimColor(vibrant);
            cp.setStatusBarScrimColor(palette.getDarkMutedColor(def));
        }


        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.add_org_here);
        linearLayout.removeAllViews();
        int size = detaillist.get(0).getOrganizerInfos().size();
        ArrayList<EventOrganizerInfo> orgs = detaillist.get(0).getOrganizerInfos();
        Log.d("hello", String.valueOf(size));
        for (int i = 0; i < size; i++) {
            View v = View.inflate(this, R.layout.organizer_event_detail, null);
            final TextView phone = (TextView) v.findViewById(R.id.phonenumber);
            phone.setText(orgs.get(i).getContact());
            TextView name = (TextView) v.findViewById(R.id.textView);
            name.setText(orgs.get(i).getName());
            ImageView callHim = (ImageView) v.findViewById(R.id.callHim);
            callHim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_CALL);

                    intent.setData(Uri.parse("tel:" + "+91" + (String)phone.getText()));
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    startActivity(intent);
                    Log.d("hello", (String) phone.getText());
                }
            });
            linearLayout.addView(v);
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void getData(Context context, String title) {
        MyDBHandler myDBHandler = new MyDBHandler(context);
        String Table_Name="events";

        String selectQuery = "SELECT  * FROM " + Table_Name + " WHERE  title = " + '"' + title  + '"';
        SQLiteDatabase db = myDBHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<String> data = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                detaillist.add(new EventInfo(cursor.getString(0), cursor.getString(6), cursor.getString(1),
                        getDrawable(cursor.getString(7)),
                        cursor.getString(2), cursor.getString(3), cursor.getString(4), rules, getOrganizers(cursor.getString(8))));
                Log.d("hello", cursor.getString(8));
            } while (cursor.moveToNext());
        }
        db.close();
    }


    private ArrayList<EventOrganizerInfo> getOrganizers(String string) {
        ArrayList<EventOrganizerInfo> organizers  = new ArrayList<>();
        String[] s = string.split("\\$");
        Log.d("hello", String.valueOf(string.split("$").length));
        String name = new String();
        String contact = new String();
        Log.d("hello", "length is " + String.valueOf(s.length));

        for(int i=0; i<s.length; i++){
            Log.d("hello", s[i]);
            if(i%2==0){
                name = s[i];
            }else{
                contact = s[i];
                organizers.add(new EventOrganizerInfo(name, contact, null));
            }
        }
        return  organizers;
    }
    private int getDrawable(String string) {
        Resources resources = this.getResources();
        final int resourceId = resources.getIdentifier( string , "mipmap",
                this.getPackageName());
        if(resourceId!=0){
            return resourceId;
        }
        return R.drawable.effe;
    }
}
