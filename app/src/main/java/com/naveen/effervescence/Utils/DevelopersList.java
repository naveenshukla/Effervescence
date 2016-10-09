package com.naveen.effervescence.Utils;

import com.naveen.effervescence.Model.Person;
import com.naveen.effervescence.R;

import java.util.ArrayList;

/**
 * Created by betterclever on 26/8/16.
 */
public class DevelopersList {
    public static ArrayList<Person> developers= new ArrayList<>();
    static {
        developers.add(new Person(R.drawable.naveen,"Naveen Shukla","App Developer","Geekhaven","7376415693","http://fb.com/betterclever","http://github.com/betterclever",R.color.md_brown_900));
        developers.add(new Person(R.drawable.sample_profile,"Pranjal Paliwal","App Developer","Geekhaven","7206197202","http://fb.com/betterclever","http://github.com/betterclever",R.color.md_green_900));
        developers.add(new Person(R.drawable.abhishek,"Abhishek Sharma","Graphics Designer","Geekhaven","9918992179","http://fb.com/betterclever","http://github.com/betterclever",R.color.md_brown_900));

    }
}
