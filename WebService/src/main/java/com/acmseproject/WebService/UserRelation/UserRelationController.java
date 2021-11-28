package com.acmseproject.WebService.UserRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/v1/relations")
public class UserRelationController {

    @Autowired
    public final UserRelationRepository userRelationRepository;

    public UserRelationController(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

//    @GetMapping(path = "/findByIds")
//    public UserRelation findByIds(@RequestParam int id, @RequestParam int id2) {
//        return userRelationRepository.findByIds(id, id2);
//    }
//
//    @GetMapping(path = "/getRelation")
//    public String getRelation(@RequestParam int id, @RequestParam int id2) {
//        return userRelationRepository.getRelation(id, id2);
//    }
//
//    @PostMapping(path = "/addRelation")
//    public String addRelation(@RequestParam int id, @RequestParam int id2) {
//        userRelationRepository.save(new UserRelation(id, id2, 0 ,0));
//        return "done";
//    }

    @PostMapping(path = "/addLike")
    public String addLike(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.addLike(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/removeLike")
    public String removeLike(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.removeLike(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/addBlock")
    public String addBlock (@RequestParam String key, @RequestParam int id, @RequestParam int id2){
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.addBlock(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    @PostMapping(path = "/removeBlock")
    public String removeBlock (@RequestParam String key, @RequestParam int id, @RequestParam int id2){
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.removeBlock(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

}
