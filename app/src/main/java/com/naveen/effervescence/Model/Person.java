package com.naveen.effervescence.Model;

/**
 * Created by better_clever on 23/8/16.
 */
public class Person {
    private int avatar;
    private int profileColor;
    private String name;
    private String designation;
    private String team;
    private String phoneNumber;
    private String facebookProfileLink;
    private String githubProfileLink;

    public Person(int avatar, String name, String designation, String team, String phoneNumber, String facebookProfileLink, String githubProfileLink, int profileColor){
        this.avatar =avatar;
        this.name = name;
        this.designation = designation;
        this.team = team;
        this.phoneNumber = phoneNumber;
        this.facebookProfileLink = facebookProfileLink;
        this.githubProfileLink = githubProfileLink;
        this.profileColor = profileColor;
    }


    public String getFacebookProfileLink() {
        return facebookProfileLink;
    }

    public String getGithubProfileLink() {
        return githubProfileLink;
    }

    public void setGithubProfileLink(String githubProfileLink) {
        this.githubProfileLink = githubProfileLink;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTeam() {
        return team;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPersonName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    public int getProfileColor() {
        return profileColor;
    }
}
