package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Message} entities.
 * <p>
 * Provides CRUD operations and database interaction for the Message entity.
 * Extends {@link JpaRepository}, inheriting methods for basic entity persistence,
 * such as save, delete, and find operations.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    /**
     * Finds all messages that a user has sent or received.
     *
     * @param userId ID of the user
     * @return List of messages where the user is either sender or recipient,
     * ordered by creation timestamp in descending order
     */
    @Query("SELECT m FROM Message m WHERE m.userFromId = :userId OR m.userToId = :userId " +
            "ORDER BY m.created DESC")
    List<Message> findAllUserMessages(@Param("userId") Long userId);
}

