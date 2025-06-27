package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

@RestController
@RequestMapping(FollowerController.API_BASE_PATH)
public class FollowerController {

    static final String API_BASE_PATH = "/api/followers";

    @Autowired
    private FollowerService followerService;

    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> followUser(@RequestBody Follower follower) {
        if (follower == null || follower.getUserFollowedId() == null || follower.getUserFollowerId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(followerService.followUser(follower));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @DeleteMapping
    public ResponseEntity<?> unfollowUser(
            @RequestParam(name = "followerId") Long followerId,
            @RequestParam(name = "followingId") Long followingId) {
        if (followerId == null || followingId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            followerService.unfollowUser(followerId, followingId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @GetMapping("/{userId}/followers")
    public ResponseEntity<?> getFollowers(@PathVariable(name = "userId") Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(followerService.getFollowers(userId));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
    @GetMapping("/{userId}/following")
    public ResponseEntity<?> getFollowing(@PathVariable(name = "userId") Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(followerService.getFollowing(userId));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}