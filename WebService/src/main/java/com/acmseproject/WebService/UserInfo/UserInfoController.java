package com.acmseproject.WebService.UserInfo;

import org.json.JSONObject;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.3
 */
@RestController
@CrossOrigin(origins = "*")
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

    @GetMapping(path = "/topUsers")
    public List<UserInfo> topUsers(@RequestParam String key) {
        System.out.format("[Request] topUsers\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userInfoRepository.topUsers();
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

    /**
     * GET-Method to receive a users ranking
     *
     * @param key API-Key for authentication
     * @param id User ID
     */
    @GetMapping(path = "/getRank")
    public int getRank(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] getRank\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            try {
                return userInfoRepository.getRank(id);
            } catch (AopInvocationException e) {
                System.out.println("[Response -1] User has null rank");
                return -1;
            }
        } else {
            System.out.format("[Verification] Failed\n");
            return -1;
        }
    }

    /**
     * POST-Method to change a user ranking
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newrank New ranking
     */
    @PostMapping(path = "/changeRank")
    public void changeRank(
            @RequestParam String key, @RequestParam int id, @RequestParam int newrank) {
        System.out.format("[Request] changeRank\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeRank(id, newrank);
        }
    }

    /**
     * POST-Method to change a user avatar
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param image Image ID
     */
    @PostMapping(path = "/changeAvatar")
    public void changeAvatar(
            @RequestParam String key, @RequestParam int id, @RequestParam int image) {
        System.out.format("[Request] changeAvatar\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            String jsonString = new JSONObject().put("image", Integer.toString(image)).toString();
            userInfoRepository.changeAvatar(id, jsonString);
        }
    }
}
