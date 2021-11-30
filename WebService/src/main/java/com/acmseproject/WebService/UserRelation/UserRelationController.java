package com.acmseproject.WebService.UserRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.3
 */
@RestController
@RequestMapping("api/v1/relations")
public class UserRelationController {

    @Autowired
    public final UserRelationRepository userRelationRepository;

    public UserRelationController(UserRelationRepository userRelationRepository) {
        this.userRelationRepository = userRelationRepository;
    }

    /**
     * GET-Method for a list of all relation information
     *
     * @param key API-Key for authentication
     * @return List of all user relations
     */
    @GetMapping(path = "/getRelations")
    public List<UserRelation> getRelations(@RequestParam String key) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            return userRelationRepository.findAll();
        } else {
            return null;
        }
    }

    /**
     * GET-Method for a single relation information
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     * @return Single user relation
     */
    @GetMapping(path = "/getRelation")
    public UserRelation getRelation(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            return userRelationRepository.findByFirstAndSecond(id, id2);
        } else {
            return null;
        }
    }

    /**
     * POST-Method to add a new user relation
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     */
    @PostMapping(path = "/addRelation")
    public String addRelation(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {

        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.save(new UserRelation(id, id2, 0, 0));
            return "done";
        } else {
            return "error";
        }
    }

    /**
     * POST-Method to add a like
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     */
    @PostMapping(path = "/addLike")
    public String addLike(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.addLike(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    /**
     * POST-Method to remove a like
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     */
    @PostMapping(path = "/removeLike")
    public String removeLike(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.removeLike(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    /**
     * POST-Method to add a block
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     */
    @PostMapping(path = "/addBlock")
    public String addBlock(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.addBlock(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

    /**
     * POST-Method to remove a block
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     */
    @PostMapping(path = "/removeBlock")
    public String removeBlock(@RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            userRelationRepository.removeBlock(id, id2);
            return "done";
        } else {
            return "error";
        }
    }

}
