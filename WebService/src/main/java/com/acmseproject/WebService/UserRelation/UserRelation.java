package com.acmseproject.WebService.UserRelation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class UserRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public UserRelation(int id_first, int id_second, int liked, int blocked) {
        this.id_first = id_first;
        this.id_second = id_second;
        this.liked = liked;
        this.blocked = blocked;
    }

    private int id_first;
    private int id_second;
    private int liked;
    private int blocked;

    public UserRelation() {

    }


    @Override
    public String toString() {
        return "UserRelation{" +
                "id=" + id +
                ", id_first=" + id_first +
                ", id_second=" + id_second +
                ", liked=" + liked +
                ", blocked=" + blocked +
                '}';
    }
}
