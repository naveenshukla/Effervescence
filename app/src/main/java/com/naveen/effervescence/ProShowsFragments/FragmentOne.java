package com.naveen.effervescence.ProShowsFragments;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.naveen.effervescence.R;

public class FragmentOne extends Fragment {
    public FragmentOne() {
        // Required empty public constructor
    }

    private static final String KEY_DRAWABLE = "DRAWABLE";
    private static final String KEY_TITLE = "TITLE";

    private TextView textView;

    public static FragmentOne instance(String title,@DrawableRes int drawableId) {
        Bundle args = new Bundle();
        args.putInt(KEY_DRAWABLE, drawableId);
        args.putString(KEY_TITLE,title);
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pro_shows, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        imageView.setImageDrawable(getContext().getResources().getDrawable(getArguments().getInt(KEY_DRAWABLE)));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //TextView textView = (TextView) view.findViewById(R.id.event_title_textview);
        //textView.setText(getContext().getResources().getString(Integer.parseInt(getArguments().getString(KEY_TITLE))));
        //FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.buttonpro);
        return view;
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_TEXT, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            textView.setText(savedInstanceState.getString(KEY_TEXT));
        }
    }*/

}
