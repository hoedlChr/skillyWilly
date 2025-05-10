package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.LikeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeSkillRepository extends JpaRepository<LikeSkill, Long> {
    List<LikeSkill> findByUserId(Long userId);

    List<LikeSkill> findBySkillId(Long skillId);

    boolean existsByUserIdAndSkillId(Long userId, Long skillId);

    void deleteByUserIdAndSkillId(Long userId, Long skillId);

    long countBySkillId(Long skillId);
}
