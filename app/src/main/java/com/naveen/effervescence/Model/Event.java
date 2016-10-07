package com.naveen.effervescence.Model;

import java.util.ArrayList;

public class Event {
	String eventName, eventDescription, eventTime, eventDate, eventLocation, eventCategory , eventImage;
	ArrayList<Organizer> eventOrganizers;

	public Event(){}

	public Event(String eventName, String eventDescription, String eventTime, String eventDate, String eventLocation, String eventCategory, ArrayList<Organizer> eventOrganizers) {
		this.eventName = eventName;
		this.eventDescription = eventDescription;
		this.eventTime = eventTime;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventCategory = eventCategory;
		this.eventOrganizers = eventOrganizers;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventTime() {
		return eventTime;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}

	public ArrayList<Organizer> getEventOrganizers() {
		return eventOrganizers;
	}

	public void setEventOrganizers(ArrayList<Organizer> eventOrganizers) {
		this.eventOrganizers = eventOrganizers;
	}

	public String getEventImage() {
		return eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}
}
