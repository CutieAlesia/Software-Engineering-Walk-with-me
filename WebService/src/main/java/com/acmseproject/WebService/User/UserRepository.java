package com.acmseproject.WebService.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findById(int id);
    User findByEmail(String email);

    @Query(value="SELECT * FROM se.user WHERE username = :user AND password = :pw",nativeQuery=true)
    User login(String user, String pw);
}
