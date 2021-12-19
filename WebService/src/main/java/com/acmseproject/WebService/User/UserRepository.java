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
                    "SELECT * FROM walkwithme.user\n"
                        + "WHERE\n"
                        + "walkwithme.user.id NOT IN \n"
                        + "(SELECT walkwithme.user_relation.second FROM walkwithme.user_relation\n"
                        + "WHERE walkwithme.user_relation.first = 1)\n"
                        + "AND\n"
                        + "walkwithme.user.id NOT IN (\n"
                        + "SELECT walkwithme.user_relation.first FROM walkwithme.user_relation\n"
                        + "WHERE walkwithme.user_relation.second = 1 AND"
                        + " walkwithme.user_relation.blocked = 1\n"
                        + ")\n"
                        + "AND\n"
                        + "walkwithme.user.id != 1",
            nativeQuery = true)
    int getAllMatches(int first);

    @Query(
            value =
                    "SELECT id FROM walkwithme.user\n"
                        + "WHERE\n"
                        + "walkwithme.user.id NOT IN \n"
                        + "(SELECT walkwithme.user_relation.second FROM walkwithme.user_relation\n"
                        + "WHERE walkwithme.user_relation.first = :first)\n"
                        + "AND\n"
                        + "walkwithme.user.id NOT IN (\n"
                        + "SELECT walkwithme.user_relation.first FROM walkwithme.user_relation\n"
                        + "WHERE walkwithme.user_relation.second = :first AND"
                        + " walkwithme.user_relation.blocked = :first\n"
                        + ")\n"
                        + "AND\n"
                        + "walkwithme.user.id != :first\n"
                        + "ORDER BY RAND()\n"
                        + "LIMIT 1",
            nativeQuery = true)
    int getMatch(int first);

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
