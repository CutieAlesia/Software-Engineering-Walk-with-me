package com.acmseproject.WebService.UserRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {

    @Query(value = "SELECT liked, blocked FROM se.user_relation WHERE id_first = :id AND id_second = :id2", nativeQuery = true)
    UserRelation getRelation(int id,int id2);

    @Modifying
    @Query(value = "UPDATE se.user_relation SET liked = 1 WHERE id_first = :id AND id_second = :id2", nativeQuery = true)
    @Transactional
    void addLike(int id, int id2);
}
