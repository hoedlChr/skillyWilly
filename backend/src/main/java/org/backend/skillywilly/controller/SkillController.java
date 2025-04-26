package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    /**
     * Creates a new skill.
     */
    @PostMapping
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        if (skill == null) {
            return ResponseEntity.badRequest().build();
        }

        Skill createdSkill = skillService.createSkill(skill);
        return ResponseEntity.status(201).body(createdSkill);
    }

    /**
     * Retrieves a skill by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable Long id) {
        Skill skill = skillService.getSkillById(id);
        return skill != null
                ? ResponseEntity.ok(skill)
                : ResponseEntity.notFound().build();
    }

    /**
     * Retrieves all skills.
     */
    @GetMapping
    public ResponseEntity<List<Skill>> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        return ResponseEntity.ok(skills);
    }

    /**
     * Updates an existing skill by its ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long id, @RequestBody Skill skillDetails) {
        if (skillDetails == null) {
            return ResponseEntity.badRequest().build();
        }

        Skill updatedSkill = skillService.updateSkill(id, skillDetails);
        return updatedSkill != null
                ? ResponseEntity.ok(updatedSkill)
                : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a skill by its ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        boolean deleted = skillService.deleteSkill(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}