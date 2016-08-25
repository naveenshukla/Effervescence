package com.naveen.effervescence;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.naveen.effervescence.Model.Person;
import com.yalantis.flipviewpager.adapter.BaseFlipAdapter;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.List;
import java.util.Objects;

public class Organizers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizers);

        final ListView listView = (ListView) findViewById(R.id.organizers_list);


    }
    class OrganizerListAdapter extends BaseFlipAdapter{

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
                holder.leftAvatar = (ImageView) convertView.findViewById(R.id.first);
                holder.rightAvatar = (ImageView) convertView.findViewById(R.id.second);
                holder.infoPage = getLayoutInflater().inflate(R.layout.list_item_layout_expanded, parent, false);
                holder.nameTextView = (TextView) holder.infoPage.findViewById(R.id.name_of_person);
                holder.designationTextView = (TextView) holder.infoPage.findViewById(R.id.designation);
                //holder.teamTextView = (TextView) holder.infoPage.findViewById(R.id.);
                holder.facebookImageView = (ImageView) holder.infoPage.findViewById(R.id.facebookImage);
                holder.githubImageView = (ImageView) holder.infoPage.findViewById(R.id.githubImage);
                holder.phoneImageView = (ImageView) holder.infoPage.findViewById(R.id.phoneImage);

                convertView.setTag(holder);
            } else {
                holder = (PersonHolder) convertView.getTag();
            }

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
    }
}
