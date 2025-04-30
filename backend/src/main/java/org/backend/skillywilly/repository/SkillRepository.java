package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Skill entities.
 * This interface extends JpaRepository, providing CRUD operations
 * and additional methods to interact with the underlying database.
 * <p>
 * SkillRepository serves as the data access layer for the Skill entity,
 * allowing interaction with the Skill table in the database.
 * <p>
 * By extending JpaRepository, it inherits methods for operations such as:
 * - Saving Skill entities.
 * - Finding Skill entities by their primary key.
 * - Deleting Skill entities.
 * - Querying Skill entities using pagination and sorting.
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
}