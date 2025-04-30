package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

/**
 * A REST controller for managing Skill entities.
 * Provides endpoints to create, read, update, and delete skills.
 */
@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    /**
     * A service dependency for managing Skill entities.
     * This instance is used to handle operations such as creating, retrieving,
     * updating, and deleting skills within the SkillController.
     * The SkillService provides the business logic implementation for these operations.
     */
    private final SkillService skillService;

    /**
     * Creates a new Skill entity and saves it to the database.
     *
     * @param skill the Skill object to be created and persisted. Must not be null.
     * @return a ResponseEntity containing the created Skill in the response body with a status of OK if successful,
     * or a ResponseEntity with an error message and appropriate HTTP status if an error occurs.
     */
    @PostMapping
    public ResponseEntity<?> createSkill(@RequestBody Skill skill) {
        if (skill == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(skillService.createSkill(skill));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves a skill by its unique identifier.
     *
     * @param id The unique identifier of the skill to retrieve.
     * @return A {@link ResponseEntity} containing the skill details if found,
     * a bad request response if the identifier is null, or an error
     * response in case of an exception.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getSkillById(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(getSkillById(id));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Retrieves all skills.
     *
     * @return a ResponseEntity containing a list of all skills if successful,
     * or an appropriate error response in case of an exception.
     */
    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        try {
            return createOkResponse(skillService.getAllSkills());
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Updates an existing skill with the provided details.
     *
     * @param id           the ID of the skill to update
     * @param skillDetails the updated details of the skill
     * @return a ResponseEntity containing the updated skill if successful,
     * or an appropriate HTTP status in case of an error
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        if (id == null || skillDetails == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(skillService.updateSkill(id, skillDetails));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    /**
     * Deletes a skill by its unique identifier.
     *
     * @param id the unique identifier of the skill to be deleted
     * @return a ResponseEntity indicating the result of the operation;
     * returns an HTTP 200 OK status with a boolean result if successful,
     * an HTTP 400 Bad Request if the input is invalid,
     * or an HTTP 500 Internal Server Error if an exception occurs during the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        try {
            return createOkResponse(skillService.deleteSkill(id));
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }
}