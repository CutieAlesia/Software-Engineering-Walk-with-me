package com.acmseproject.WebService.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

//    private final UserService userService;

//    @Autowired
//    public UserAPI(UserService userService) {
//        this.userService = userService;
//    }
    private final UserRepository userRepository;

    
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path="/getUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/findByUsername")
    public User findByUsername(@RequestParam String username) {
        return userRepository.findByUsername(username);
    }

    @GetMapping(path="/login")
    public Boolean login(@RequestParam String username, @RequestParam String password) {
        return userRepository.login(username, password).getUsername().equals(username) && userRepository.login(username, password).getPassword().equals(password);
    }

    @GetMapping(path="/findById")
    public User findById(@RequestParam int id) {
        return userRepository.findById(id);
    }

    @GetMapping(path="/findByEmail")
    public User findByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping(path = "/newUser")
    public String newUser(@RequestParam String username, @RequestParam String password) {
        userRepository.save(new User(username, password));
        return "User was created";
    }

    @PostMapping(path = "/deleteUser")
    public void deleteUser(@RequestParam int id) {
        userRepository.deleteById(id);
    }

    @PostMapping(path = "/changeEmail")
    public void changeEmail(@RequestParam int id, @RequestParam String email) {
        userRepository.changeEmail(id, email);
    }

    @PostMapping(path = "/changePassword")
    public void changePassword(@RequestParam int id, @RequestParam String password) {
        userRepository.changePassword(id, password);
    }
}
