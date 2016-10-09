package com.naveen.effervescence.Adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.naveen.effervescence.Activities.Developers;
import com.naveen.effervescence.Model.Person;
import com.naveen.effervescence.R;
import com.naveen.effervescence.Utils.DevelopersList;
import com.naveen.effervescence.Utils.OrganizersList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranjal Paliwal on 9/27/2016.
 */

public class OrganizersCardPagerAdapter extends PagerAdapter implements CardAdapter {

	private List<Person> personList = OrganizersList.organizers;
	private List<CardView> mViews = new ArrayList<>();
	private float mBaseElevation;
	private Context activityContext;

	public OrganizersCardPagerAdapter(Context context, int k) {


		if(k==0){
			personList = DevelopersList.developers;
			activityContext =context;
			for (int i = 0; i < personList.size(); i++) {
				mViews.add(null);
			}
		}
		else{
			personList = OrganizersList.organizers;
			activityContext =context;
			for (int i = 0; i < personList.size(); i++) {
				mViews.add(null);
			}
		}
	}

	public float getBaseElevation() {
		return mBaseElevation;
	}

	@Override
	public CardView getCardViewAt(int position) {
		return mViews.get(position);
	}

	@Override
	public int getCount() {
		return personList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = LayoutInflater.from(container.getContext())
			.inflate(R.layout.adapter, container, false);
		container.addView(view);
		CardView cardView = (CardView) view.findViewById(R.id.cardView);

		if (mBaseElevation == 0) {
			mBaseElevation = cardView.getCardElevation();
		}

		cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);

		TextView nameTV = (TextView) view.findViewById(R.id.organizer_name);
		TextView roleTV = (TextView) view.findViewById(R.id.organizer_role);
		ImageView profileIV = (ImageView) view.findViewById(R.id.organizer_image);
		Button button = (Button) view.findViewById(R.id.call_organizer);

		nameTV.setText(personList.get(position).getPersonName());
		roleTV.setText(personList.get(position).getDesignation());
		profileIV.setImageResource(personList.get(position).getAvatar());

		final Person person = personList.get(position);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:"  + "+91" + person.getPhoneNumber()));
				if (ActivityCompat.checkSelfPermission(activityContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					return;
				}
				activityContext.startActivity(intent);
			}
		});


		mViews.set(position, cardView);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		mViews.set(position, null);
	}

}
