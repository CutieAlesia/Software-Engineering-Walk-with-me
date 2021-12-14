package com.acmseproject.WebService.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Dubsky
 * @version 1.0
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUserid(int userid);

    @Query(
            value =
                    "SELECT walkwithme.user_info.ranking FROM walkwithme.user_info WHERE"
                            + " walkwithme.user_info.userid = :id",
            nativeQuery = true)
    int getRank(int id);

    @Query(
            value =
                    "SELECT * FROM walkwithme.user_info\n"
                            + "WHERE ranking > 0 AND ranking < 11\n"
                            + "ORDER BY ranking\n"
                            + "LIMIT 10",
            nativeQuery = true)
    List<UserInfo> topUsers();

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET bio = :bio WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeBio(int id, String bio);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET gender = :gender WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeGender(int id, String gender);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET race = :race WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeRace(int id, String race);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET friendly = :friendly WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeFriendly(int id, int friendly);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET height = :height WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeHeight(int id, int height);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET weight = :weight WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeWeight(int id, int weight);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user_info SET avatar = :image WHERE userid = :id",
            nativeQuery = true)
    @Transactional
    void changeAvatar(int id, String image);

    @Modifying(clearAutomatically = true)
    @Query(
            value =
                    "UPDATE walkwithme.user_info SET walkwithme.user_info.ranking = :newRank WHERE"
                            + " userid = :id",
            nativeQuery = true)
    @Transactional
    void changeRank(int id, int newRank);

    @Query(value = "SELECT apikey FROM walkwithme.api_keys WHERE apikey = :key", nativeQuery = true)
    String checkAuth(String key);
}
