package com.acmseproject.WebService.UserInfo;

import javax.persistence.*;

/**
 * @author Dubsky
 * @version 1.3
 */
@Entity
@Table
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userid;
    private String username;
    private String bio;
    private String gender;
    private String race;
    private int friendly;
    private int height;
    private int weight;

    public UserInfo() {
    }

    public UserInfo(int id, int userid, String username) {
        this.id = id;
        this.userid = userid;
        this.username = username;
    }

    public UserInfo(int id, String bio, String gender, String race, int friendly, int height, int weight) {
        this.id = id;
        this.bio = bio;
        this.gender = gender;
        this.race = race;
        this.friendly = friendly;
        this.height = height;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", gender='" + gender + '\'' +
                ", race='" + race + '\'' +
                ", friendly=" + friendly +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getFriendly() {
        return friendly;
    }

    public void setFriendly(int friendly) {
        this.friendly = friendly;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}