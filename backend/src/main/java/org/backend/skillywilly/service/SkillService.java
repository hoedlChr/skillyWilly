package org.backend.skillywilly.service;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    /**
     * Creates a new skill.
     */
    public Skill createSkill(Skill skill) {
        if (skill == null) {
            return null;
        }
        return skillRepository.save(skill);
    }

    /**
     * Retrieves a skill by its ID.
     */
    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves all skills.
     */
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    /**
     * Updates an existing skill.
     */
    public Skill updateSkill(Long id, Skill skillDetails) {
        if (skillDetails == null) {
            return null;
        }

        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isEmpty()) {
            return null;
        }

        Skill existingSkill = optionalSkill.get();
        updateSkillFields(existingSkill, skillDetails);

        return skillRepository.save(existingSkill);
    }

    // Helper method to update fields of the Skill
    private void updateSkillFields(Skill existingSkill, Skill skillDetails) {
        existingSkill.setSubject(skillDetails.getSubject());
        existingSkill.setBody(skillDetails.getBody());
    }

    /**
     * Deletes a skill by its ID.
     */
    public boolean deleteSkill(Long id) {
        if (!skillRepository.existsById(id)) {
            return false;
        }
        skillRepository.deleteById(id);
        return true;
    }
}