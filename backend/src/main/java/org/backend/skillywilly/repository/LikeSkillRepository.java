package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.LikeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing "LikeSkill" entities in the database.
 * Extends the JpaRepository to provide standard CRUD operations along with custom query methods.
 */
@Repository
public interface LikeSkillRepository extends JpaRepository<LikeSkill, Long> {

    /**
     * Finds all LikeSkill entities associated with the specified user ID.
     *
     * @param userId the ID of the user whose liked skills are to be retrieved
     * @return a list of LikeSkill entities corresponding to the given user ID
     */
    List<LikeSkill> findByUserId(Long userId);

    /**
     * Retrieves a list of LikeSkill entities associated with the specified skill ID.
     *
     * @param skillId the ID of the skill for which associated LikeSkill entities are to be retrieved
     * @return a list of LikeSkill entities linked to the provided skill ID
     */
    List<LikeSkill> findBySkillId(Long skillId);

    /**
     * Checks if a "LikeSkill" entry exists for the given user ID and skill ID.
     *
     * @param userId  the unique identifier of the user.
     * @param skillId the unique identifier of the skill.
     * @return true if a "LikeSkill" record exists with the specified user ID and skill ID, false otherwise.
     */
    boolean existsByUserIdAndSkillId(Long userId, Long skillId);
}

