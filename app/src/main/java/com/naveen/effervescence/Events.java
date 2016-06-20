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
    private Integer hour,minute,_id;
    public Events(){
    }
    public Events(Integer _id,String title, String place, String category1, String Secondcat, String day, Integer hour,
                  Integer minute,String date, String ampm){
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
    public void setTime(Integer hour, Integer minute){
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
}
