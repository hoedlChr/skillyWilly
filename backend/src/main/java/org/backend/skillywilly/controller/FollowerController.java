package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(FollowerController.API_BASE_PATH)
public class FollowerController {

    static final String API_BASE_PATH = "/api/followers";

    @Autowired
    private FollowerService followerService;
    
    /**
     * Follow a user.
     *
     * @param follower Contains the follower and following user details.
     * @return Created follower relationship or Bad Request if input is invalid.
     */
    @PostMapping
    public ResponseEntity<Follower> followUser(@RequestBody Follower follower) {
        if (follower == null || follower.getUserFollowedId() == null || follower.getUserFollowerId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            Follower createdFollower = followerService.followUser(follower);
            return createOkResponse(createdFollower);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Unfollow a user based on followerId and followingId.
     *
     * @param followerId  ID of the user who is unfollowing.
     * @param followingId ID of the user being unfollowed.
     * @return No content if successful, or Bad Request if input is invalid.
     */
    @DeleteMapping
    public ResponseEntity<Void> unfollowUser(
            @RequestParam(name = "followerId") Long followerId,
            @RequestParam(name = "followingId") Long followingId) {
        if (followerId == null || followingId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            followerService.unfollowUser(followerId, followingId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get followers of a specific user.
     *
     * @param userId ID of the user whose followers should be retrieved.
     * @return List of followers as response or Bad Request if input is invalid.
     */
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Follower>> getFollowers(@PathVariable(name = "userId") Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(followerService.getFollowers(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Get users a specific user is following.
     *
     * @param userId ID of the user whose followings should be retrieved.
     * @return List of followings as response or Bad Request if input is invalid.
     */
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Follower>> getFollowing(@PathVariable(name = "userId") Long userId) {
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(followerService.getFollowing(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Utility method to build OK Response
    private <T> ResponseEntity<T> createOkResponse(T body) {
        return ResponseEntity.ok(body);
    }
}