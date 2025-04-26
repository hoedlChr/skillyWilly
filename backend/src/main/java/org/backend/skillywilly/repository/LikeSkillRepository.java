package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.LikeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeSkillRepository extends JpaRepository<LikeSkill, Long> {

    List<LikeSkill> findByUserId(Long userId); // Alle Likes eines Benutzers abrufen

    List<LikeSkill> findBySkillId(Long skillId); // Alle Likes für eine bestimmte Fähigkeit abrufen

    boolean existsByUserIdAndSkillId(Long userId, Long skillId); // Prüfen, ob ein Benutzer eine bestimmte Fähigkeit geliked hat
}

