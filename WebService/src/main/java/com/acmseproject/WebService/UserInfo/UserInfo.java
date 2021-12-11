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
    private String avatar;
    private String images;
    private int ranking;

    public UserInfo() {}

    public UserInfo(int id, int userid, String username) {
        this.id = id;
        this.userid = userid;
        this.username = username;
    }

    public UserInfo(
            int id,
            int userid,
            String username,
            int friendly,
            int height,
            int weight,
            int ranking) {
        this.id = id;
        this.userid = userid;
        this.username = username;
        this.friendly = friendly;
        this.height = height;
        this.weight = weight;
        this.ranking = ranking;
    }

    @Override
    public String toString() {
        return "UserInfo{"
                + "id="
                + id
                + ", userid="
                + userid
                + ", username='"
                + username
                + '\''
                + ", bio='"
                + bio
                + '\''
                + ", gender='"
                + gender
                + '\''
                + ", race='"
                + race
                + '\''
                + ", friendly="
                + friendly
                + ", height="
                + height
                + ", weight="
                + weight
                + ", avatar='"
                + avatar
                + '\''
                + ", images='"
                + images
                + '\''
                + ", ranking="
                + ranking
                + '}';
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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
