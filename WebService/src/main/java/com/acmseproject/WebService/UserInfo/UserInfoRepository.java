package com.acmseproject.WebService.UserInfo;

import com.acmseproject.WebService.UserRelation.UserRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUserid(int userid);

    @Query(value = "SELECT apikey FROM walkwithme.api_keys WHERE apikey = :key", nativeQuery = true)
    String checkAuth(String key);
}