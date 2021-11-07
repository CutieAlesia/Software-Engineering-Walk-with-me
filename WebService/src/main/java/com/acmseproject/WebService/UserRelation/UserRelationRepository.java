package com.acmseproject.WebService.UserRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {

    @Query(value = "" +
            "SELECT liked, blocked " +
            "FROM se.user_relation " +
            "WHERE id_first = :id " +
            "AND id_second = :id2",
            nativeQuery = true)
    UserRelation getRelation(int id,int id2);

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


}
