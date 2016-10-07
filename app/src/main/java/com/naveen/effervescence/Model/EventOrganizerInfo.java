package com.naveen.effervescence.Model;

/**
 * Created by better_clever on 11/9/16.
 */
public class EventOrganizerInfo {
    private String name;
    private String contact;
    private String email = "";

    public EventOrganizerInfo(String name,String contact,String email){
        this.name = name;
        this.contact = contact;
        this.email = this.email + email;
    }
}
