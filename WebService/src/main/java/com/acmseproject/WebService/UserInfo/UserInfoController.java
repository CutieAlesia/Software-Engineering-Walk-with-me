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
        if(Objects.equals(key, userInfoRepository.checkAuth(key))) {
            return userInfoRepository.findAll();
        } else {
            return null;
        }
    }

}
