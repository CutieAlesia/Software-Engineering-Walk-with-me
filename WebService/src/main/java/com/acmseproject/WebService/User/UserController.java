package com.acmseproject.WebService.User;

import com.acmseproject.WebService.UserInfo.UserInfo;
import com.acmseproject.WebService.UserInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.7
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserController(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * GET-Method to verify login information
     *
     * @param key API-Key for authentication
     * @param username Username used to logging in
     * @param password Password used to logging in
     * @return User information in a JSON format
     */
    @GetMapping(path = "/loginByUsername")
    public User loginByUsername(
            @RequestParam String key,
            @RequestParam String username,
            @RequestParam String password) {
        System.out.format("[Request] loginByUsername\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            User tmpUser = userRepository.loginByUsername(username, password);
            if (tmpUser != null
                    && tmpUser.getUsername().equals(username)
                    && tmpUser.getPassword().equals(password)) {
                return tmpUser;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * GET-Method to verify login information
     *
     * @param key API-Key for authentication
     * @param email Username used to logging in
     * @param password Password used to logging in
     * @return User information in a JSON format
     */
    @GetMapping(path = "/loginByEmail")
    public User loginByEmail(
            @RequestParam String key, @RequestParam String email, @RequestParam String password) {
        System.out.format("[Request] loginByEmail\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            User tmpUser = userRepository.loginByEmail(email, password);
            if (tmpUser.getEmail().equals(email) && tmpUser.getPassword().equals(password)) {
                return tmpUser;
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * GET-Method to receive a list of all users
     *
     * @param key API-Key for authentication
     * @return List of all users as a JSON format
     */
    @GetMapping(path = "/getUsers")
    public List<User> getAllUsers(@RequestParam String key) {
        System.out.format("[Request] getAllUsers\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRepository.findAll();
        } else {
            return null;
        }
    }

    /**
     * GET-Method to receive user information by username
     *
     * @param key API-Key for authentication
     * @param username Username to search by
     * @return User information in a JSON format
     */
    @GetMapping(path = "/findByUsername")
    public User findByUsername(@RequestParam String key, @RequestParam String username) {
        System.out.format("[Request] findByUsername\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRepository.findByUsername(username);
        } else {
            return null;
        }
    }

    /**
     * GET-Method to receive user information by ID
     *
     * @param key API-Key for authentication
     * @param id User ID to search by
     * @return User information in a JSON format
     */
    @GetMapping(path = "/findById")
    public User findById(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] findByID\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRepository.findById(id);
        } else {
            return null;
        }
    }

    /**
     * GET-Method to receive user information by email
     *
     * @param key API-Key for authentication
     * @return User information in a JSON format
     */
    @GetMapping(path = "/findByEmail")
    public User findByEmail(@RequestParam String key, @RequestParam String email) {
        System.out.format("[Request] findByEmail\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRepository.findByEmail(email);
        } else {
            return null;
        }
    }

    /**
     * POST-Method to create a new user
     *
     * @param key API-Key for authentication
     * @param username Username for the account to be created
     * @param password Password for the account to be created
     * @return Response code
     */
    @PostMapping(path = "/newUser")
    public String newUser(
            @RequestParam String key,
            @RequestParam String username,
            @RequestParam String password) {
        System.out.format("[Request] newUser\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRepository.save(new User(username, password));
            User tmpuser = userRepository.findByUsername(username);
            userInfoRepository.save(new UserInfo(0, tmpuser.getId(), tmpuser.getUsername()));
            return "200";
        } else {
            return "400";
        }
    }

    /**
     * POST-Method to delete a user
     *
     * @param key API-Key for authentication
     * @param id User ID to be deleted
     */
    @PostMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] deleteUser\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRepository.deleteById(id);
        }
    }

    /**
     * POST-Method to change a user email
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param email Email to be changed to
     */
    @PostMapping(path = "/changeEmail")
    public void changeEmail(
            @RequestParam String key, @RequestParam int id, @RequestParam String email) {
        System.out.format("[Request] changeEmail\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRepository.changeEmail(id, email);
        }
    }

    /**
     * POST-Method to change a user email
     *
     * @param key API-Key for authentication
     * @param id User ID connected to the change
     * @param password Password to be changed to
     */
    @PostMapping(path = "/changePassword")
    public void changePassword(
            @RequestParam String key, @RequestParam int id, @RequestParam String password) {
        System.out.format("[Request] changePassword\n[Key] %s\n", key);
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRepository.changePassword(id, password);
        }
    }
}
