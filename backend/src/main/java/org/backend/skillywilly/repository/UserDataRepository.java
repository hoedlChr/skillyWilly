package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for managing and accessing `UserData` entities in the database.
 * This is a Spring Data JPA repository that provides standard CRUD operations
 * and allows for the implementation of custom methods to interact with the "UserData" table.
 * <p>
 * Responsibilities:
 * - Perform CRUD operations on `UserData` entities.
 * - Enable interaction with the database using Spring Data JPA's features.
 * - Automatically generate queries based on method signatures.
 * <p>
 * Extends:
 * - `JpaRepository<UserData, Long>`: Provides JPA-specific methods for interacting
 * with the `UserData` entity, where `Long` is the type of the primary key.
 * <p>
 * Usage:
 * The repository enables seamless database operations like saving, updating, deleting,
 * and fetching `UserData` instances without the need for boilerplate code.
 * <p>
 * Annotations:
 * - `@Repository`: Indicates that this interface is a Spring-managed repository component,
 * making it available for dependency injection.
 */
@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}