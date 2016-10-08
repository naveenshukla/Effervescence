package com.naveen.effervescence.Model;

/**
 * Created by better_clever on 11/9/16.
 */
public class EventOrganizerInfo {
    private String name;
    private String contact;
    private String email = "";

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public EventOrganizerInfo(String name, String contact, String email){
        this.name = name;
        this.contact = contact;
        this.email = this.email + email;
    }
}
