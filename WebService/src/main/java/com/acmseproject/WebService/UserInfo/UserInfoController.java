package com.acmseproject.WebService.UserInfo;

import com.acmseproject.WebService.User.User;
import com.acmseproject.WebService.UserInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.3
 */
@RestController
@RequestMapping("api/v1/info")
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * GET-Method to receive a list of all users
     *
     * @param key API-Key for authentication
     * @return List of all users as a JSON in a list
     */
    @GetMapping(path = "/getUsers")
    public List<UserInfo> getAllUsers(@RequestParam String key) {
        System.out.format("[Request] getAllUserInfo\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userInfoRepository.findAll();
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

    /**
     * GET-Method to receive single user information by ID
     *
     * @param key API-Key for authentication
     * @return User as a JSON
     */
    @GetMapping(path = "/getUser")
    public UserInfo getUser(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] getUserInfo\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userInfoRepository.findByUserid(id);
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

}