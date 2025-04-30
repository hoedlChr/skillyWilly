package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * Represents the "Skill" entity which encapsulates the information related to a specific skill.
 * This entity is stored in the "Skill" table within the database and is associated with:
 * - The User entity, indicating the user who created the skill.
 * - The LikeSkill entity, tracking the "likes" this skill has received.
 * <p>
 * The Skill entity contains fields for the skill's subject, body, and metadata such as creation timestamp.
 * It also supports relational mapping to both the User and LikeSkill entities, facilitating
 * interactions between skills, users, and likes.
 * <p>
 * Annotations like `@Entity`, `@Table`, and various JPA mappings are used to define how this class
 * correlates to the underlying database structure.
 */
@Entity
@Table(name = "Skill")
@Data
public class Skill {
    /**
     * Represents the unique identifier for the "Skill" entity.
     * This field is used as the primary key within the "Skill" table
     * in the database. The value is automatically generated
     * using the "IDENTITY" generation strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the subject or title of the skill.
     * This is a mandatory field and provides a brief, descriptive label for the skill.
     * It is stored in the "subject" column of the "Skill" table.
     */
    @Column(name = "subject", nullable = false)
    private String subject;

    /**
     * Represents the main content or description of a skill.
     * This field is mandatory and corresponds to the "body" column in the "Skill" table.
     * It typically holds detailed information about the skill.
     */
    @Column(name = "body", nullable = false)
    private String body;

    /**
     * Stores the timestamp indicating when a particular skill was created.
     * This field is mapped to the "created" column in the database.
     * It helps in tracking the creation date and time of a skill entity.
     */
    @Column(name = "created")
    private Timestamp created;

    /**
     * Represents the unique identifier for the user associated with the skill.
     * This field is mapped to the "user_id" column in the database and is a non-nullable field.
     * It establishes the relationship between the "Skill" and "User" entities, linking a specific skill
     * to the user who created it.
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Represents the relationship between the "Skill" and "User" entities.
     * The user field establishes a Many-to-One association, indicating that multiple
     * skills can be associated with a single user. This mapping is non-insertable and
     * non-updatable through this entity, as the foreign key field is managed via the
     * "userId" column in the "Skill" table.
     * <p>
     * Mapped to the "user_id" column in the "Skill" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * Represents the list of "LikeSkill" entities associated with this skill.
     * This list tracks the "likes" that the skill has received from users.
     * It establishes a one-to-many relationship with the "LikeSkill" entity,
     * where a skill can have multiple likes but each "LikeSkill" is linked to a single skill.
     * <p>
     * The mapping is defined by the "skill" property in the "LikeSkill" entity.
     * This field is lazily loaded and is not nullable, ensuring that every "LikeSkill" in the list
     * is associated with a valid skill.
     */
    @OneToMany(mappedBy = "skill")
    private List<LikeSkill> likeSkills;
}
