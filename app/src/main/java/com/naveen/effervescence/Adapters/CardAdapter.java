package com.naveen.effervescence.Adapters;

import android.support.v7.widget.CardView;

/**
 * Created by Pranjal Paliwal on 9/27/2016.
 */

public interface CardAdapter {

	int MAX_ELEVATION_FACTOR = 8;

	float getBaseElevation();

	CardView getCardViewAt(int position);

	int getCount();
}
