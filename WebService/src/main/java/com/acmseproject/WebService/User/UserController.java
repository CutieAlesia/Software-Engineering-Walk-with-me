package com.acmseproject.WebService.User;

import com.acmseproject.WebService.UserInfo.UserInfo;
import com.acmseproject.WebService.UserInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.6
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
    public Boolean loginByUsername(@RequestParam String key, @RequestParam String username, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.loginByUsername(username, password).getUsername().equals(username) && userRepository.loginByUsername(username, password).getPassword().equals(password);
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
    public Boolean loginByEmail(@RequestParam String key, @RequestParam String email, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.loginByEmail(email, password).getUsername().equals(email) && userRepository.loginByEmail(email, password).getPassword().equals(password);
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
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
    public String newUser(@RequestParam String key, @RequestParam String username, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.save(new User(username, password));
            User tmpuser = userRepository.findByUsername(username);
            userInfoRepository.save(new UserInfo(0, tmpuser.getId()));
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
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
    public void changeEmail(@RequestParam String key, @RequestParam int id, @RequestParam String email) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
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
    public void changePassword(@RequestParam String key, @RequestParam int id, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.changePassword(id, password);
        }
    }
}
