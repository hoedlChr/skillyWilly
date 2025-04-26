package org.backend.skillywilly.service;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.model.User;
import org.backend.skillywilly.repository.LikeSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeSkillService {

    private final LikeSkillRepository likeSkillRepository;
    private final UserService userService; // Für die Benutzer-Sicht
    private final SkillService skillService; // Für die Skill-Sicht

    /**
     * Fügt einen neuen Like hinzu.
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
     * Entfernt einen Like mithilfe der Benutzer-ID und Skill-ID.
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
     * Gibt alle Likes eines Benutzers zurück.
     */
    public List<LikeSkill> getLikesByUser(Long userId) {
        return likeSkillRepository.findByUserId(userId);
    }

    /**
     * Gibt ab, wie viele Likes für eine Fähigkeit existieren.
     */
    public List<LikeSkill> getLikesBySkill(Long skillId) {
        return likeSkillRepository.findBySkillId(skillId);
    }
}
