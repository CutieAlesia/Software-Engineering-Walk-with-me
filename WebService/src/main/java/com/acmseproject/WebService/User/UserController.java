package com.acmseproject.WebService.User;

import com.acmseproject.WebService.UserInfo.UserInfo;
import com.acmseproject.WebService.UserInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

//    private final UserService userService;

//    @Autowired
//    public UserAPI(UserService userService) {
//        this.userService = userService;
//    }
    private final UserRepository userRepository;
    private final UserInfoRepository userInfoRepository;

    
    @Autowired
    public UserController(UserRepository userRepository, UserInfoRepository userInfoRepository) {
        this.userRepository = userRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping(path="/getUsers")
    public List<User> getAllUsers(@RequestParam String key) {
        if(Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.findAll();
        } else {
            return null;
        }
    }

    @GetMapping(path="/findByUsername")
    public User findByUsername(@RequestParam String key, @RequestParam String username) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.findByUsername(username);
        } else {
            return null;
        }
    }

    @GetMapping(path="/login")
    public Boolean login(@RequestParam String key, @RequestParam String username, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.login(username, password).getUsername().equals(username) && userRepository.login(username, password).getPassword().equals(password);
        } else {
            return null;
        }
    }

    @GetMapping(path="/findById")
    public User findById(@RequestParam String key, @RequestParam int id) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.findById(id);
        } else {
            return null;
        }
    }

    @GetMapping(path="/findByEmail")
    public User findByEmail(@RequestParam String key, @RequestParam String email) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            return userRepository.findByEmail(email);
        } else {
            return null;
        }
    }

    @PostMapping(path = "/newUser")
    public String newUser(@RequestParam String key, @RequestParam String username, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.save(new User(username, password));
            User tmpuser = userRepository.findByUsername(username);
            userInfoRepository.save(new UserInfo(0, tmpuser.getId()));
            return "User was created";
        } else {
            return "Error";
        }
    }

    @PostMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam String key, @RequestParam int id) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.deleteById(id);
        }
    }

    @PostMapping(path = "/changeEmail")
    public void changeEmail(@RequestParam String key, @RequestParam int id, @RequestParam String email) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.changeEmail(id, email);
        }
    }

    @PostMapping(path = "/changePassword")
    public void changePassword(@RequestParam String key, @RequestParam int id, @RequestParam String password) {
        if (Objects.equals(key, userRepository.checkAuth(key))) {
            userRepository.changePassword(id, password);
        }
    }
}
