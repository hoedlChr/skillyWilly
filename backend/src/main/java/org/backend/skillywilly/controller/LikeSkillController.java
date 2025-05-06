package org.backend.skillywilly.controller;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.service.LikeSkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/like-skills")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LikeSkillController {
    private final LikeSkillService likeSkillService;

    @PostMapping("/like")
    public ResponseEntity<LikeSkill> likeSkill(
            @RequestParam Long userId,
            @RequestParam Long skillId) {
        return ResponseEntity.ok(likeSkillService.saveLike(userId, skillId));
    }

    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeSkill(
            @RequestParam Long userId,
            @RequestParam Long skillId) {
        likeSkillService.removeLike(userId, skillId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Long>> getLikedSkillsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(likeSkillService.findSkillIdsByUserId(userId));
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity<Map<String, Object>> getSkillLikeInfo(@PathVariable Long skillId) {
        return ResponseEntity.ok(Map.of(
                "likeCount", likeSkillService.countLikesBySkillId(skillId),
                "userIds", likeSkillService.findUserIdsBySkillId(skillId)
        ));
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUserLikedSkill(
            @RequestParam Long userId,
            @RequestParam Long skillId) {
        return ResponseEntity.ok(likeSkillService.hasUserLikedSkill(userId, skillId));
    }
}