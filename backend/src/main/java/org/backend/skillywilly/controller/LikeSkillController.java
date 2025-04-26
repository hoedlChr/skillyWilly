package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.service.LikeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/like-skills")
@RequiredArgsConstructor
public class LikeSkillController {

    private final LikeSkillService likeSkillService;

    /**
     * Liked eine Fähigkeit.
     *
     * @param userId  Die ID des Benutzers.
     * @param skillId Die ID der Fähigkeit.
     * @return Der hinzugefügte LikeSkill.
     */
    @PostMapping("/like")
    public ResponseEntity<LikeSkill> likeSkill(@RequestParam Long userId,
                                               @RequestParam Long skillId) {
        LikeSkill likeSkill = likeSkillService.likeSkill(userId, skillId);
        return ResponseEntity.ok(likeSkill);
    }

    /**
     * Entfernt ein Like von einer Fähigkeit.
     *
     * @param userId  Die ID des Benutzers.
     * @param skillId Die ID der Fähigkeit.
     */
    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeSkill(@RequestParam Long userId,
                                            @RequestParam Long skillId) {
        likeSkillService.unlikeSkill(userId, skillId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Holen Sie sich alle Likes eines Benutzers.
     *
     * @param userId Die ID des Benutzers.
     * @return Eine Liste von LikeSkills.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeSkill>> getLikesByUser(@PathVariable Long userId) {
        List<LikeSkill> likes = likeSkillService.getLikesByUser(userId);
        return ResponseEntity.ok(likes);
    }

    /**
     * Holen Sie sich alle Likes auf einer spezifischen Fähigkeit.
     *
     * @param skillId Die ID der Fähigkeit.
     * @return Eine Liste von LikeSkills.
     */
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<List<LikeSkill>> getLikesBySkill(@PathVariable Long skillId) {
        List<LikeSkill> likes = likeSkillService.getLikesBySkill(skillId);
        return ResponseEntity.ok(likes);
    }
}