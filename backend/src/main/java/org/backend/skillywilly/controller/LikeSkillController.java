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
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LikeSkillController {

    private final LikeSkillService likeSkillService;

    @CrossOrigin
    @PostMapping("/like")
    public ResponseEntity<LikeSkill> likeSkill(
            @RequestBody Map<String, Long> body
    ) {
        Long userId = body.get("userId");
        Long skillId = body.get("skillId");
        LikeSkill saved = likeSkillService.saveLike(userId, skillId);
        return ResponseEntity.ok(saved);
    }

    @CrossOrigin
    @DeleteMapping("/unlike")
    public ResponseEntity<Void> unlikeSkill(
            @RequestBody Map<String, Long> body
    ) {
        Long userId = body.get("userId");
        Long skillId = body.get("skillId");
        likeSkillService.removeLike(userId, skillId);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Long>> getLikedSkillsByUser(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(
                likeSkillService.findSkillIdsByUserId(userId)
        );
    }

    @CrossOrigin
    @GetMapping("/skill/{skillId}")
    public ResponseEntity<Map<String, Object>> getSkillLikeInfo(
            @PathVariable Long skillId
    ) {
        return ResponseEntity.ok(Map.of(
                "likeCount", likeSkillService.countLikesBySkillId(skillId),
                "userIds", likeSkillService.findUserIdsBySkillId(skillId)
        ));
    }

    @CrossOrigin
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkUserLikedSkill(
            @RequestParam Long userId,
            @RequestParam Long skillId
    ) {
        return ResponseEntity.ok(
                likeSkillService.hasUserLikedSkill(userId, skillId)
        );
    }
}
