package org.backend.skillywilly.service;

import lombok.RequiredArgsConstructor;
import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.repository.LikeSkillRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeSkillService {
    private final LikeSkillRepository likeSkillRepository;

    public LikeSkill saveLike(Long userId, Long skillId) {
        LikeSkill likeSkill = new LikeSkill();
        likeSkill.setUserId(userId);
        likeSkill.setSkillId(skillId);
        return likeSkillRepository.save(likeSkill);
    }

    public void removeLike(Long userId, Long skillId) {
        likeSkillRepository.deleteByUserIdAndSkillId(userId, skillId);
    }

    public List<Long> findSkillIdsByUserId(Long userId) {
        return likeSkillRepository.findByUserId(userId)
                .stream()
                .map(LikeSkill::getSkillId)
                .toList();
    }

    public List<Long> findUserIdsBySkillId(Long skillId) {
        return likeSkillRepository.findBySkillId(skillId)
                .stream()
                .map(LikeSkill::getUserId)
                .toList();
    }

    public boolean hasUserLikedSkill(Long userId, Long skillId) {
        return likeSkillRepository.existsByUserIdAndSkillId(userId, skillId);
    }

    public long countLikesBySkillId(Long skillId) {
        return likeSkillRepository.countBySkillId(skillId);
    }
}