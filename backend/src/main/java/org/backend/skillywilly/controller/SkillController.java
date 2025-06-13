package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.backend.skillywilly.util.GeneralHelper.createExceptionResponse;
import static org.backend.skillywilly.util.GeneralHelper.createOkResponse;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {
    private final SkillService skillService;

    @CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAllSkills() {
        try {
            return createOkResponse(skillService.getAllSkills());
        } catch (Exception e) {
            return createExceptionResponse(e);
        }
    }

    @CrossOrigin
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

    @CrossOrigin
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