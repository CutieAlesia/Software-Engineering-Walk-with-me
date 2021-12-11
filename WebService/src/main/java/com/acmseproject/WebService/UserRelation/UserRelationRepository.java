package com.acmseproject.WebService.UserRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRelationRepository extends JpaRepository<UserRelation, Integer> {

    //    /**
    //     * @param id User as subject
    //     * @param id2 User as target
    //     * @return String /w relation information
    //     */
    //    @Query(value = "" +
    //            "SELECT liked, blocked " +
    //            "FROM se.user_relation " +
    //            "WHERE id_first = :id " +
    //            "AND id_second = :id2",
    //            nativeQuery = true)

    List<UserRelation> findAll();

    UserRelation findByFirstAndSecond(int first, int second);

    @Modifying
    @Transactional
    @Query(
            value =
                    ""
                            + "UPDATE walkwithme.user_relation "
                            + "SET liked = :like "
                            + "WHERE first = :id "
                            + "AND second = :id2",
            nativeQuery = true)
    void changeLike(int id, int id2, int like);

    @Modifying
    @Transactional
    @Query(
            value =
                    ""
                            + "UPDATE walkwithme.user_relation "
                            + "SET liked = 0 "
                            + "WHERE first = :id "
                            + "AND second = :id2",
            nativeQuery = true)
    void removeLike(int id, int id2);

    @Modifying
    @Transactional
    @Query(
            value =
                    ""
                            + "UPDATE walkwithme.user_relation "
                            + "SET blocked = 1 "
                            + "WHERE first = :id "
                            + "AND second = :id2",
            nativeQuery = true)
    void addBlock(int id, int id2);

    @Modifying
    @Transactional
    @Query(
            value =
                    ""
                            + "UPDATE walkwithme.user_relation "
                            + "SET blocked = 0 "
                            + "WHERE first = :id "
                            + "AND second = :id2",
            nativeQuery = true)
    void removeBlock(int id, int id2);

    @Query(value = "SELECT apikey FROM walkwithme.api_keys WHERE apikey = :key", nativeQuery = true)
    String checkAuth(String key);
}
