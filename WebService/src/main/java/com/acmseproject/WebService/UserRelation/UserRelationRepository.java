package com.acmseproject.WebService.UserRelation;

import com.acmseproject.WebService.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {

//    UserRelation findByIds(int id_first, int id_second);
//
//    @Query(value = "" +
//            "SELECT * " +
//            "FROM se.user_relation " +
//            "WHERE id_first = :id " +
//            "AND id_second = :id2",
//            nativeQuery = true)
//    String getRelation(int id, int id2);

    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE se.user_relation " +
            "SET liked = 1 " +
            "WHERE id_first = :id " +
            "AND id_second = :id2",
            nativeQuery = true)
    void addLike(int id, int id2);

    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE se.user_relation " +
            "SET liked = 0 " +
            "WHERE id_first = :id " +
            "AND id_second = :id2",
            nativeQuery = true)
    void removeLike(int id, int id2);

    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE se.user_relation " +
            "SET blocked = 1 " +
            "WHERE id_first = :id " +
            "AND id_second = :id2",
            nativeQuery = true)
    void addBlock(int id, int id2);

    @Modifying
    @Transactional
    @Query(value = "" +
            "UPDATE se.user_relation " +
            "SET blocked = 0 " +
            "WHERE id_first = :id " +
            "AND id_second = :id2",
            nativeQuery = true)
    void removeBlock(int id, int id2);

    @Query(value="SELECT apikey FROM se.api_keys WHERE apikey = :key", nativeQuery=true)
    String checkAuth(String key);

}
