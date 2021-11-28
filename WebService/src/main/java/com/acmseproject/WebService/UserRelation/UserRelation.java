package com.acmseproject.WebService.UserRelation;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int relation_id;
    private int first;
    private int second;
    private int liked;
    private int blocked;

    public UserRelation() {

    }

    public UserRelation(int id_first, int id_second, int liked, int blocked) {
        this.first = id_first;
        this.second = id_second;
        this.liked = liked;
        this.blocked = blocked;
    }

    public UserRelation(int relation_id, int first, int second, int liked, int blocked) {
        this.relation_id = relation_id;
        this.first = first;
        this.second = second;
        this.liked = liked;
        this.blocked = blocked;
    }

    @Override
    public String toString() {
        return "UserRelation{" +
                "relation_id=" + relation_id +
                ", first=" + first +
                ", second=" + second +
                ", liked=" + liked +
                ", blocked=" + blocked +
                '}';
    }

    public int getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(int relation_id) {
        this.relation_id = relation_id;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getBlocked() {
        return blocked;
    }

    public void setBlocked(int blocked) {
        this.blocked = blocked;
    }
}
