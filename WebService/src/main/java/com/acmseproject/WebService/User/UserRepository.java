package com.acmseproject.WebService.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * @author Dubsky
 * @version 1.5
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findById(int id);

    User findByEmail(String email);

    @Query(
            value =
                    "SELECT * FROM walkwithme.user WHERE username = :username AND password ="
                            + " :password",
            nativeQuery = true)
    User loginByUsername(String username, String password);

    @Query(
            value = "SELECT * FROM walkwithme.user WHERE email = :email AND password = :password",
            nativeQuery = true)
    User loginByEmail(String email, String password);

    @Query(
            value =
                    "SELECT walkwithme.user_relation.second FROM walkwithme.user\n"
                        + "INNER JOIN walkwithme.user_relation ON"
                        + " walkwithme.user.id=walkwithme.user_relation.first\n"
                        + "WHERE walkwithme.user_relation.first = :first AND"
                        + " walkwithme.user_relation.second != :first AND liked = 0 AND blocked ="
                        + " 0\n"
                        + "ORDER BY RAND()\n"
                        + "LIMIT 1",
            nativeQuery = true)
    int getRandom(int first);

    @Query(
            value =
                    "SELECT * FROM walkwithme.user\n"
                        + "WHERE walkwithme.user.id NOT IN (SELECT walkwithme.user_relation.second"
                        + " FROM walkwithme.user_relation WHERE walkwithme.user_relation.first ="
                        + " :first)\n"
                        + "AND walkwithme.user.id != :first ORDER BY RAND()\n"
                        + "LIMIT 1",
            nativeQuery = true)
    User getMatch(int first);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE walkwithme.user SET email = :email WHERE id = :id", nativeQuery = true)
    @Transactional
    void changeEmail(int id, String email);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE walkwithme.user SET password = :password WHERE id = :id",
            nativeQuery = true)
    @Transactional
    void changePassword(int id, String password);

    @Query(value = "SELECT apikey FROM walkwithme.api_keys WHERE apikey = :key", nativeQuery = true)
    String checkAuth(String key);
}
