package com.acmseproject.WebService.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserAPI {

    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(UserService userService) {
        return userService.getUsers();
    }

//    @GetMapping
//    public String pc() {
//        return System.getProperty("os.name");
//    }

}
