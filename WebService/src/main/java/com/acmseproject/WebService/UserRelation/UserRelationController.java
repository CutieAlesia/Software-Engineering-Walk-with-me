package com.acmseproject.WebService.UserRelation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Dubsky
 * @version 1.3
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/relations")
public class UserRelationController {

    @Autowired public final UserRelationRepository userRelationRepository;

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
        System.out.format("[Request] getRelations\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRelationRepository.findAll();
        } else {
            System.out.format("[Verification] Failed\n");
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
    public UserRelation getRelation(
            @RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        System.out.format("[Request] getRelation\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRelationRepository.findByFirstAndSecond(id, id2);
        } else {
            System.out.format("[Verification] Failed\n");
            return null;
        }
    }

    /**
     * GET-Method for all mutual like relations for a specific user
     *
     * @param key API-Key for authentication
     * @param id User ID to search by
     * @return List of relations where users liked each others
     */
    @GetMapping(path = "/getMatches")
    public List<UserRelation> getMatches(@RequestParam String key, @RequestParam int id) {
        System.out.format("[Request] getMatches\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            return userRelationRepository.getMatches(id);
        } else {
            System.out.format("[Verification] Failed\n");
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
    public String addRelation(
            @RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        System.out.format("[Request] addRelation\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRelationRepository.save(new UserRelation(id, id2, 0, 0));
            return "200";
        } else {
            System.out.format("[Verification] Failed\n");
            return "400";
        }
    }

    /**
     * POST-Method to add a like
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     * @param like Like mode
     */
    @PostMapping(path = "/changeLike")
    public String changeLike(
            @RequestParam String key,
            @RequestParam int id,
            @RequestParam int id2,
            @RequestParam int like) {
        System.out.format("[Request] changeLike\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRelationRepository.changeLike(id, id2, like);
            return "200";
        } else {
            System.out.format("[Verification] Failed\n");
            return "400";
        }
    }

    /**
     * POST-Method to remove a like
     *
     * @param key API-Key for authentication
     * @param id First User ID
     * @param id2 Second User ID
     * @deprecated This method is no longer needed as of v2.1 - Replaced by the changeLike method
     */
    @PostMapping(path = "/removeLike")
    public String removeLike(
            @RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        System.out.format("[Request] removeLike\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRelationRepository.removeLike(id, id2);
            return "200";
        } else {
            System.out.format("[Verification] Failed\n");
            return "400";
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
        System.out.format("[Request] addBlock\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRelationRepository.addBlock(id, id2);
            return "200";
        } else {
            System.out.format("[Verification] Failed\n");
            return "400";
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
    public String removeBlock(
            @RequestParam String key, @RequestParam int id, @RequestParam int id2) {
        System.out.format("[Request] removeBlock\n[Key] %s\n", key);
        if (Objects.equals(key, userRelationRepository.checkAuth(key))) {
            System.out.format("[Verification] Valid\n");
            userRelationRepository.removeBlock(id, id2);
            return "200";
        } else {
            System.out.format("[Verification] Failed\n");
            return "400";
        }
    }
}
