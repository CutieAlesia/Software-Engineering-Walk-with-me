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

    @GetMapping(path = "/getRelations")
    public List<UserRelation> getRelations(@RequestParam String key) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            return userRelationRepository.findAll();
        } else {
            return null;
        }
    }

    @GetMapping(path = "/getRelation")
    public UserRelation getRelation(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            return userRelationRepository.findByFirstAndSecond(id, id2);
        } else {
            return null;
        }
    }

    @PostMapping(path = "/addRelation")
    public String addRelation(@RequestParam int id, @RequestParam int id2) {
        userRelationRepository.save(new UserRelation(id, id2, 0 ,0));
        return "done";
    }

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
