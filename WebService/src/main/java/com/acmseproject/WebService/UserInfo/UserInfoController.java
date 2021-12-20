package com.acmseproject.WebService.UserInfo;

import org.json.JSONObject;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.5
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

    /** TODO || JavaDoc missing */
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
     * GET-Method to receive a users preference
     *
     * @param key API-Key for authentication
     * @param id User ID
     */
    @GetMapping(path = "/getPref")
    public String getPref(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] getPref\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return getUser(key, id).getPref();
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

    /**
     * POST-Method to change a user bio
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newBio New bio string
     */
    @PostMapping(path = "/changeBio")
    public void changeBio(
            @RequestParam String key, @RequestParam int id, @RequestParam String newBio) {
        System.out.format("[Request] changeBio\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeBio(id, newBio);
        }
    }

    /**
     * POST-Method to change a user gender
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newGender New gender
     */
    @PostMapping(path = "/changeGender")
    public void changeGender(
            @RequestParam String key, @RequestParam int id, @RequestParam String newGender) {
        System.out.format("[Request] changeGender\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeGender(id, newGender);
        }
    }

    /**
     * POST-Method to change a user race
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newRace New race
     */
    @PostMapping(path = "/changeRace")
    public void changeRace(
            @RequestParam String key, @RequestParam int id, @RequestParam String newRace) {
        System.out.format("[Request] changeRace\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeRace(id, newRace);
        }
    }

    /**
     * POST-Method to change a user's friendly status
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newFriendly Friendly status
     */
    @PostMapping(path = "/changeFriendly")
    public void changeFriendly(
            @RequestParam String key, @RequestParam int id, @RequestParam int newFriendly) {
        System.out.format("[Request] changeFriendly\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeFriendly(id, newFriendly);
        }
    }

    /**
     * POST-Method to change a user's height
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newHeight User height
     */
    @PostMapping(path = "/changeHeight")
    public void changeHeight(
            @RequestParam String key, @RequestParam int id, @RequestParam int newHeight) {
        System.out.format("[Request] changeHeight\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeHeight(id, newHeight);
        }
    }

    /**
     * POST-Method to change a user's weight
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param newWeight User weight
     */
    @PostMapping(path = "/changeWeight")
    public void changeWeight(
            @RequestParam String key, @RequestParam int id, @RequestParam int newWeight) {
        System.out.format("[Request] changeWeight\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userInfoRepository.changeWeight(id, newWeight);
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
     * POST-Method to change a users preference
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param dog Dog pref
     * @param cat Cat pref
     */
    @PostMapping(path = "/changePref")
    public void changePref(
            @RequestParam String key,
            @RequestParam int id,
            @RequestParam int cat,
            @RequestParam int dog) {
        System.out.format("[Request] changePref\n[Key] %s\n", key);
        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            UserInfo tmp = getUser(key, id);
            String json = new JSONObject().put("dog", dog).put("cat", cat).toString();
            tmp.setPref(json);
            userInfoRepository.save(tmp);
        }
    }

    /**
     * POST-Method to upload images
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param file File byte array
     */
    //    @PostMapping(path = "/upload")
    //    public void upload(
    //            @RequestParam String key, @RequestParam int id, @RequestParam("image")
    // MultipartFile file) {
    //        System.out.format("[Request] upload\n[Key] %s\n", key);
    //        if (Objects.equals(key, userInfoRepository.checkAuth(key))) {
    //            System.out.format("[Verification] Valid\n");
    //            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    //            System.out.format("Filename: %s", fileName);
    //            UserInfo tmp = getUser(key, id);
    //            String json = new JSONObject()
    //                    .put("image", "avatar."+Integer.toString(id) )
    //                    .toString();
    //            userInfoRepository.changeAvatar(id, json);
    //            FileUploadUtil.saveFile("ressources/", "avatar."+Integer.toString(id), file);
    //        }
    //    }
}
