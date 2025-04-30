package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.service.LikeSkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

/**
 * Rest controller handling operations related to user liking and unliking skills.
 * Provides endpoints for liking/unliking skills as well as retrieving likes associated
 * with users and skills.
 */
@RestController
@RequestMapping("/api/like-skills")
@RequiredArgsConstructor
public class LikeSkillController {

    /**
     * Service instance used for managing operations related to liking and unliking skills.
     * This includes handling the creation of like relationships, removal of likes,
     * and retrieval of likes by user or skill.
     */
    private final LikeSkillService likeSkillService;

    /**
     * Handles the "like" operation for a skill by a specific user.
     * Verifies input parameters and delegates the operation to the service layer.
     * Returns an appropriate HTTP response based on the operation's outcome.
     *
     * @param userId  The ID of the user who is liking the skill. Must not be null.
     * @param skillId The ID of the skill to be liked. Must not be null.
     * @return A ResponseEntity containing either a success response with the liked skill information
     * or an error message in case of an exception or invalid input.
     */
    @PostMapping("/like")
    public ResponseEntity<?> likeSkill(@RequestParam Long userId,
                                       @RequestParam Long skillId) {
        if (userId == null || skillId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(likeSkillService.likeSkill(userId, skillId));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Removes the association of a "like" between a user and a skill.
     *
     * @param userId  the ID of the user who wants to unlike the skill
     * @param skillId the ID of the skill to be unliked
     * @return a response entity with no content if the operation is successful,
     * or an error response entity if an exception occurs
     */
    @DeleteMapping("/unlike")
    public ResponseEntity<?> unlikeSkill(@RequestParam Long userId,
                                         @RequestParam Long skillId) {
        try {
            likeSkillService.unlikeSkill(userId, skillId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves all likes associated with a specific user.
     *
     * @param userId the unique ID of the user whose likes are to be retrieved
     * @return a ResponseEntity containing the list of likes associated with the specified user
     * or an error message if an exception occurs
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLikesByUser(@PathVariable Long userId) {
        try {
            return createOkResponse(likeSkillService.getLikesByUser(userId));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves all likes associated with a specific skill.
     *
     * @param skillId the ID of the skill for which likes are to be retrieved
     * @return a ResponseEntity containing the list of likes for the specified skill,
     * or an error response in case of an exception
     */
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<?> getLikesBySkill(@PathVariable Long skillId) {
        try {
            return createOkResponse(likeSkillService.getLikesBySkill(skillId));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}