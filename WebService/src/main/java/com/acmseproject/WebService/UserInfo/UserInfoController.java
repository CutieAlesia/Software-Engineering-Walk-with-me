package com.acmseproject.WebService.UserInfo;

import com.acmseproject.WebService.User.User;
import com.acmseproject.WebService.UserInfo.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("api/v1/info")
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping(path="/getUsers")
    public List<UserInfo> getAllUsers(@RequestParam String key) {
        System.out.format("[Request] getUsers\n[Key] %s\n");
        if(Objects.equals(key, userInfoRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userInfoRepository.findAll();
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

    @GetMapping(path="/getUser")
    public UserInfo getAllUsers(@RequestParam String key, @RequestParam int id) {
        if(Objects.equals(key, userInfoRepository.checkAuth(key))) {
            return userInfoRepository.findByUserid(id);
        } else {
            return null;
        }
    }

}
