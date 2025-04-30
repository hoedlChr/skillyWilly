package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Message} entities.
 * <p>
 * Provides CRUD operations and database interaction for the Message entity.
 * Extends {@link JpaRepository}, inheriting methods for basic entity persistence,
 * such as save, delete, and find operations.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
