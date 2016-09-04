package com.naveen.effervescence;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by naveen on 3/6/16.
 */
public class Events {
    private String title,place,category1,category2,day;
    private String date,ampm;
    private int imgDrawable;
    private String hour,minute;
            private Integer _id;
    private boolean isExpanded = false;
    public Events(){
    }
    public Events(Integer _id,String title, String place, String category1, String Secondcat, String day, String hour,
                  String minute,String date, String ampm,int imgDrawable){
        this.title = title;
        this.place = place;
        this._id = _id;
        this.category1 = category1;
        this.day = day;
        this.date = date;
        this.hour = hour;
        this.minute = minute;
        this.ampm = ampm;
        this.category2 = Secondcat;
        this.imgDrawable = imgDrawable;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setPlace(String place){
        this.place = place;
    }
    public void setCategory1(String category1){
        this.category1 = category1;
    }
    public void setCategory2(String category2){
        this.category2 = category2;
    }
    public void setDay(String day){
        this.day = day;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setTime(String hour, String minute){
        this.hour = hour;
        this.minute = minute;
    }
    public String getPlace(){
        return place;
    }
    public String getCategory1(){
        return category1;
    }
    public String getCategory2(){
        return category2;
    }
    public String getDay(){
        return day;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return  day + "@ " + hour +" : " + minute + ampm;
    }
    public String getAmpm(){
        return ampm;
    }

    public String getMinute() {
        return minute;
    }

    public String getHour() {

        return hour;
    }

    public Integer get_id() {
        return _id;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getImgDrawable() {
        return imgDrawable;
    }

    public void setImgDrawable(int imgDrawable) {
        this.imgDrawable = imgDrawable;
    }
}
