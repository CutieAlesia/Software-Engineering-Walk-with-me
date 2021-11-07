package com.acmseproject.WebService.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
    User findByEmail(String email);

    @Query(value="SELECT * FROM se.user WHERE username = :user AND password = :pw", nativeQuery=true)
    User login(String user, String pw);

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE se.user SET email = :email WHERE id = :id", nativeQuery=true)
    @Transactional
    void changeEmail(int id, String email);

    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE se.user SET password = :password WHERE id = :id", nativeQuery=true)
    @Transactional
    void changePassword(int id, String password);
}
