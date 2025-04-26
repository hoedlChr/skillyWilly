package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing {@link User} entities.
 * Extends {@link JpaRepository} to provide standard CRUD operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by their username.
     *
     * @param username the username of the user to search for.
     * @return an {@link Optional} containing the user if found, or empty if not.
     */
    Optional<User> findByUsername(String username);

}