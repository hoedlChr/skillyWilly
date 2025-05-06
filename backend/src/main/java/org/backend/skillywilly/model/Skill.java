package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents the "Skill" entity, encapsulating information related to a specific skill.
 */
@Entity
@Table(name = Skill.TABLE_NAME)
@Data
public class Skill {

    public static final String TABLE_NAME = "Skill";

    /**
     * Primary key of the "Skill" entity, auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Subject or title of the skill.
     */
    @Column(name = "subject", nullable = false)
    private String subject;

    /**
     * Detailed description of the skill.
     */
    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;

    /**
     * Timestamp indicating when the skill was created.
     */
    @Column(name = "created")
    private Timestamp created;

    /**
     * ID of the user who created the skill.
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;
}