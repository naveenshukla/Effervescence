package com.naveen.effervescence.Utils;

/**
 * Created by betterclever on 30/8/16.
 */
public class EventInfo {
    private String eventName;
    private String eventDescription;
    private String category;
    private int image_drawable;

    public EventInfo(String eventName, String eventDescription, String category, int image_drawable){
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.category = category;
        this.image_drawable = image_drawable;
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
}
