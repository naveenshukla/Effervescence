package com.naveen.effervescence;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.naveen.effervescence.Model.Person;
import com.naveen.effervescence.Utils.DevelopersList;
import com.naveen.effervescence.Utils.OrganizersList;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.List;

public class Developers extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(R.string.Developers);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout4);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(false);
        drawer.setDrawerListener(toggle);
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout4);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view4);
        navigationView.setNavigationItemSelectedListener(this);

        final ListView listView = (android.widget.ListView) findViewById(R.id.developers_listview);
        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        listView.setAdapter(new DeveloperListAdapter(this, DevelopersList.developers,settings));

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    class DeveloperListAdapter extends BaseFlipAdapter {

        private final int PAGES = 3;

        public DeveloperListAdapter(Context context, List items, FlipSettings settings) {
            super(context, items, settings);
        }

        @Override
        public View getPage(int position, View convertView, ViewGroup parent, Object person1, Object person2) {

            final PersonHolder holder;
            if (convertView == null) {
                holder = new PersonHolder();
                convertView = getLayoutInflater().inflate(R.layout.list_items_layout, parent, false);
                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = getLayoutInflater().inflate(R.layout.list_item_layout_expanded, parent, false);
                holder.nameTextView = (TextView) holder.infoPage.findViewById(R.id.name_of_person);
                holder.designationTextView = (TextView) holder.infoPage.findViewById(R.id.designation);
                //holder.teamTextView = (TextView) holder.infoPage.findViewById(R.id.);
                holder.facebookImageView = (ImageView) holder.infoPage.findViewById(R.id.facebookImage);
                holder.githubImageView = (ImageView) holder.infoPage.findViewById(R.id.githubImage);
                holder.phoneImageView = (ImageView) holder.infoPage.findViewById(R.id.phoneImage);
                holder.testButton = (Button) holder.infoPage.findViewById(R.id.testButton);

                convertView.setTag(holder);
            } else {
                holder = (PersonHolder) convertView.getTag();
            }

            ArrayList<View> al = new ArrayList<>();
            al.add(holder.facebookImageView);
            al.add(holder.testButton);
            convertView.addTouchables(al);

            if(position == 1){
                holder.leftAvatar.setImageResource(((Person) person1).getAvatar());
                if(person2!=null){
                    holder.rightAvatar.setImageResource(((Person) person2).getAvatar());
                }
            }

            if(position == 0){
                fillHolder(holder,(Person) person1);
                holder.infoPage.setTag(holder);
                return holder.infoPage;
            }

            if(position == 2){
                fillHolder(holder,(Person) person2);
                holder.infoPage.setTag(holder);
                return holder.infoPage;
            }
            return convertView;
        }

        @Override
        public int getPagesCount() {
            return PAGES;
        }
    }

    public void fillHolder(PersonHolder personHolder, final Person person){
        if(person == null)
            return;
        personHolder.designationTextView.setText(person.getDesignation());
        personHolder.nameTextView.setText(person.getPersonName());
        personHolder.infoPage.setBackgroundColor(getResources().getColor(person.getProfileColor()));
        personHolder.facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FacebookImageView","imageViewPressed");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(person.getFacebookProfileLink()));
                startActivity(intent);
            }
        });
        personHolder.githubImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(person.getGithubProfileLink()));
                startActivity(intent);
            }
        });
        personHolder.phoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+person.getPhoneNumber()));
                startActivity(intent);
            }
        });
        personHolder.testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test Button Pressed","testButtonPressed");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(person.getFacebookProfileLink()));
                startActivity(intent);
            }
        });


    }

    class PersonHolder{
        ImageView leftAvatar;
        ImageView rightAvatar;
        View infoPage;
        TextView nameTextView;
        TextView designationTextView;
        TextView teamTextView;
        ImageView facebookImageView;
        ImageView githubImageView;
        ImageView phoneImageView;
        Button testButton;
    }
}
