package org.backend.skillywilly.service;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.LikeSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing operations related to the LikeSkill entity.
 * This service includes functionality for creating, deleting, and retrieving user
 * likes for specific skills. It integrates data access layers and other services
 * such as UserService and SkillService to enforce business logic.
 */
@Service
@RequiredArgsConstructor
public class LikeSkillService {

    /**
     * Repository interface dependency for managing "LikeSkill" entities.
     * Facilitates CRUD operations and custom queries related to skill likes in the database.
     */
    private final LikeSkillRepository likeSkillRepository;
    /**
     * A service utilized for handling operations related to users. This service
     * provides methods to fetch, create, update, and delete user information and is
     * primarily used to facilitate communication between the application and the
     * user-related data in the repository layer.
     */
    private final UserService userService; // Für die Benutzer-Sicht
    /**
     * A final reference to the {@code SkillService} instance, used for managing
     * and retrieving skills-related data and operations. This field is injected
     * via constructor and utilized within the service to handle skill-related tasks
     * within the application.
     */
    private final SkillService skillService; // Für die Skill-Sicht

    /**
     * Allows a user to like a specific skill by creating a relationship in the LikeSkill entity.
     * Throws an exception if the user has already liked the skill or if the user does not exist.
     *
     * @param userId  the unique identifier of the user who wants to like the skill.
     * @param skillId the unique identifier of the skill to be liked by the user.
     * @return an instance of LikeSkill representing the relationship created between the user and the skill.
     * @throws IllegalArgumentException if the user has already liked the skill or if the user does not exist.
     */
    public LikeSkill likeSkill(Long userId, Long skillId) {
        if (likeSkillRepository.existsByUserIdAndSkillId(userId, skillId)) {
            throw new IllegalArgumentException("User already liked this skill.");
        }

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
        Skill skill = skillService.getSkillById(skillId);

        LikeSkill likeSkill = new LikeSkill();
        likeSkill.setUser(user);
        likeSkill.setSkill(skill);

        return likeSkillRepository.save(likeSkill);
    }

    /**
     * Removes the "like" relationship between a user and a skill.
     * This method identifies the specific "like" relationship based on the provided
     * user ID and skill ID, and deletes it from the repository. If no such relationship
     * exists, an exception is thrown.
     *
     * @param userId  the ID of the user who has liked the skill
     * @param skillId the ID of the skill that needs to be "unliked"
     * @throws IllegalArgumentException if the "like" relationship between the user and the skill does not exist
     */
    public void unlikeSkill(Long userId, Long skillId) {
        LikeSkill likeSkill = likeSkillRepository.findByUserId(userId)
                .stream()
                .filter(like -> like.getSkill().getId().equals(skillId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Like relationship not found."));

        likeSkillRepository.delete(likeSkill);
    }

    /**
     * Retrieves a list of LikeSkill entities associated with a given user ID.
     *
     * @param userId the ID of the user whose liked skills are to be retrieved
     * @return a list of LikeSkill entities liked by the specified user
     */
    public List<LikeSkill> getLikesByUser(Long userId) {
        return likeSkillRepository.findByUserId(userId);
    }

    /**
     * Retrieves a list of LikeSkill entities associated with the specified skill ID.
     *
     * @param skillId the ID of the skill for which the associated LikeSkill entities are to be retrieved
     * @return a list of LikeSkill entities linked to the specified skill ID
     */
    public List<LikeSkill> getLikesBySkill(Long skillId) {
        return likeSkillRepository.findBySkillId(skillId);
    }
}
