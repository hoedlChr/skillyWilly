package org.backend.skillywilly.service;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing follower relationships between users.
 * <p>
 * This service handles business logic for creating, deleting, and retrieving follower
 * relationships. It interacts with the underlying database through the FollowerRepository.
 * Core functionalities include following users, unfollowing users, and retrieving lists
 * of followers and following users.
 * <p>
 * Annotations:
 * - @Service: Indicates that this class is a service component in the Spring framework.
 */
@Service
public class FollowerService {

    /**
     * Repository instance for managing {@link Follower} entities in the database.
     * This field is auto-wired by the Spring framework to provide access to the database layer
     * and perform CRUD operations related to follower relationships.
     */
    @Autowired
    private FollowerRepository followerRepository;

    /**
     * Saves a new follower relationship in the database.
     *
     * @param follower the Follower object representing the relationship where one user follows another
     * @return the saved Follower object, including any auto-generated fields such as the ID and timestamp
     */
    public Follower followUser(Follower follower) {
        return followerRepository.save(follower);
    }

    /**
     * Removes the follower relationship between two users.
     *
     * @param followerId  the ID of the user who is following
     * @param followingId the ID of the user being followed
     */
    public void unfollowUser(Long followerId, Long followingId) {
        followerRepository.deleteByUserFollowerIdAndUserFollowedId(followerId, followingId);
    }

    /**
     * Retrieves a list of followers for a specific user based on their user ID.
     *
     * @param userId the ID of the user whose followers are to be retrieved
     * @return a list of Follower entities associated with the specified user ID
     */
    public List<Follower> getFollowers(Long userId) {
        return followerRepository.findByUserFollowedId(userId);
    }

    /**
     * Retrieves a list of users that the specified user is currently following.
     *
     * @param userId the ID of the user whose following list is being retrieved
     * @return a list of {@link Follower} entities representing the users followed by the specified user
     */
    public List<Follower> getFollowing(Long userId) {
        return followerRepository.findByUserFollowerId(userId);
    }
}