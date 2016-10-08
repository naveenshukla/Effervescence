package com.naveen.effervescence.Model;

import android.support.v7.widget.util.SortedListAdapterCallback;

import com.naveen.effervescence.R;

import java.util.ArrayList;

/**
 * Created by betterclever on 30/8/16.
 */
public class EventInfo{
    private String eventName;
    private String eventDescription;
    private String category;
    private String timeSharedPrefVariable;
    private String dateSharedPrefVariable;
    private String location;
    private ArrayList<String> rules;
    private ArrayList<EventOrganizerInfo> organizerInfos;
    private boolean expanded = false;

    public ArrayList<EventOrganizerInfo> getOrganizerInfos() {
        return organizerInfos;
    }

    private int image_drawable;

    public EventInfo(String eventName, String eventDescription, String category,
                     int image_drawable, String timeSharedPrefVar,String dateSharedPrefVariable,
                     String location, ArrayList<String> rules, ArrayList<EventOrganizerInfo> organizerInfos){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.category = category;
        this.image_drawable = image_drawable;
        this.rules = rules;
        this.location = location;
        this.timeSharedPrefVariable = timeSharedPrefVar;
        this.organizerInfos = organizerInfos;
        this.dateSharedPrefVariable = dateSharedPrefVariable;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getCategory() {
        return category;
    }

    public int getImage_drawable() {
        return image_drawable;
    }

    public String getLocation() {
        return location;
    }

    public String getDateSharedPrefVariable() {
        return dateSharedPrefVariable;
    }

    public String getTimeSharedPrefVariable() {
        return timeSharedPrefVariable;
    }

    public ArrayList<String> getRules() {
        return rules;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
