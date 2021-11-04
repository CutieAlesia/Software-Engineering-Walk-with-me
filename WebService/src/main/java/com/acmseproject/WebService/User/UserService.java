package com.acmseproject.WebService.User;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
public class UserService {

    @GetMapping
    public String getUsers() {
        return new User("Clemens", "pw1234", "abc@gmail.com").toString();
    }

}
