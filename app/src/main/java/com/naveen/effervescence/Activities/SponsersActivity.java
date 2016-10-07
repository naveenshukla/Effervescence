package com.naveen.effervescence.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.naveen.effervescence.Model.Person;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Utils.OrganizersList;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.List;

public class SponsersActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	DrawerLayout drawer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sponsers);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		setTitle(R.string.Organizers);

		drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
			this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		toggle.setDrawerIndicatorEnabled(false);
		drawer.setDrawerListener(toggle);
		toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
		toggle.setToolbarNavigationClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
				if (drawer.isDrawerOpen(GravityCompat.START)) {
					drawer.closeDrawer(GravityCompat.START);
				} else {
					drawer.openDrawer(GravityCompat.START);
				}
			}
		});

		toggle.syncState();
		toggle.setHomeAsUpIndicator(R.drawable.ic_sort_white_24dp);
		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view3);
		navigationView.setNavigationItemSelectedListener(this);

		final ListView listView = (ListView) findViewById(R.id.organizers_list);
		FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
		listView.setAdapter(new OrganizerListAdapter(this, OrganizersList.organizers, settings));

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Person friend = (Person) listView.getAdapter().getItem(position);
				Toast.makeText(SponsersActivity.this, friend.getPersonName(),
					Toast.LENGTH_SHORT).show();
			}
		});

	}

	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.category) {

		} else if (id == R.id.day) {
			drawer.closeDrawer(GravityCompat.START);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(SponsersActivity.this, DaysViewActivity.class);
					startActivity(intent);
				}
			}, 250);

		} else if (id == R.id.proshows) {

			drawer.closeDrawer(GravityCompat.START);
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent intent = new Intent(SponsersActivity.this, ProShows.class);
					startActivity(intent);
				}
			}, 250);

		}  else if (id == R.id.sponsers) {

		} else if (id == R.id.developers) {


		}
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	class OrganizerListAdapter extends BaseFlipAdapter {

		private final int PAGES = 3;

		public OrganizerListAdapter(Context context, List items, FlipSettings settings) {
			super(context, items, settings);
		}

		@Override
		public View getPage(int position, View convertView, ViewGroup parent, Object person1, Object person2) {

			final PersonHolder holder;
			if (convertView == null) {
				holder = new PersonHolder();
				convertView = getLayoutInflater().inflate(R.layout.list_items_layout, parent, false);
				convertView.setClickable(true);
				convertView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Log.d("Cool Stuff","Happens");
					}
				});
				holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
				holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
				holder.infoPage = getLayoutInflater().inflate(R.layout.list_item_layout_expanded, parent, false);
				holder.infoPage.setClickable(true);
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

			if (position == 1) {
				holder.leftAvatar.setImageResource(((Person) person1).getAvatar());
				if (person2 != null) {
					holder.rightAvatar.setImageResource(((Person) person2).getAvatar());
				}
			}

			if (position == 0) {
				fillHolder(holder, (Person) person1);
				holder.infoPage.setTag(holder);
				return holder.infoPage;
			}

			if (position == 2) {
				fillHolder(holder, (Person) person2);
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

	public void fillHolder(PersonHolder personHolder, final Person person) {
		if (person == null)
			return;
		personHolder.designationTextView.setText(person.getDesignation());
		personHolder.nameTextView.setText(person.getPersonName());
		personHolder.infoPage.setBackgroundColor(getResources().getColor(person.getProfileColor()));
		personHolder.facebookImageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("FacebookImageView", "imageViewPressed");
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
				intent.setData(Uri.parse("tel:" + person.getPhoneNumber()));
				if (ActivityCompat.checkSelfPermission(SponsersActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					return;
				}
				startActivity(intent);
			}
        });
        personHolder.testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FacebookImageView","imageViewPressed");
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
