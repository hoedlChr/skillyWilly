package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "like_skill")
@Data
public class LikeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "skill_id", nullable = false)
    private Long skillId;
}
