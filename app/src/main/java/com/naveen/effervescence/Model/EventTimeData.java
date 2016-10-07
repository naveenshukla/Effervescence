package com.naveen.effervescence.Model;

/**
 * Created by better_clever on 13/9/16.
 */
public class EventTimeData {
    private String eventName;
    private String time;
    private String date;
    private String location;

    public EventTimeData(){

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
