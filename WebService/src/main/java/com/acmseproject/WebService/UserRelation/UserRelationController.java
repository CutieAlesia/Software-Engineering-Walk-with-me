package com.acmseproject.WebService.UserRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/relations")
public class UserRelationController {

    @Autowired
    public final UserRelationRepository userRelationRepository;

    public UserRelationController(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

    @GetMapping(path = "/getRelation")
    public UserRelation getRelation(@RequestParam int id, @RequestParam int id2) {
        return userRelationRepository.getRelation(id, id2);
    }

    @PostMapping(path = "/addRelation")
    public void addRelation(@RequestParam int id, @RequestParam int id2) {
        userRelationRepository.save(new UserRelation(id, id2, 0 ,0));
    }

    @PostMapping(path = "/addLike")
    public String addLike(@RequestParam int id, @RequestParam int id2) {
        userRelationRepository.addLike(id, id2);
        return "Done";
    }

//    @PostMapping(path = "/removedLike")
//
//    @PostMapping(path = "/addBlock")
//
//    @PostMapping(path = "/removeBlock")


}
