package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Follower} entities.
 * Provides methods for retrieving and manipulating follower data in the database.
 * <p>
 * This interface extends {@link JpaRepository} to inherit basic CRUD operations.
 */
@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    /**
     * Retrieves a list of follower entities associated with the specified user follower ID.
     *
     * @param userFollowerId the ID of the user who is following others
     * @return a list of {@link Follower} entities associated with the given user follower ID
     */
    List<Follower> findByUserFollowerId(Long userFollowerId);

    /**
     * Retrieves a list of Follower entities based on the ID of the user being followed.
     *
     * @param userFollowedId the ID of the user who is being followed
     * @return a list of Follower entities associated with the specified userFollowedId
     */
    List<Follower> findByUserFollowedId(Long userFollowedId);

    /**
     * Deletes a follower relationship based on the given follower and followed user IDs.
     *
     * @param userFollowerId the ID of the user who is following
     * @param userFollowedId the ID of the user being followed
     */
    void deleteByUserFollowerIdAndUserFollowedId(Long userFollowerId, Long userFollowedId);
}