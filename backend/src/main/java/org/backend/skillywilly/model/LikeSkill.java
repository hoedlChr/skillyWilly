package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

/**
 * Represents the "LikeSkill" entity, which establishes a link between a user and a skill they have "liked".
 * This entity is used to track "likes" that users give to various skills, providing relational mapping
 * to both the User and Skill entities.
 * <p>
 * The "LikeSkill" table in the database is configured to store this relationship.
 * It contains references to both the user who liked the skill and the skill being liked.
 */
@Entity
@Table(name = "LikeSkill")
@Data
public class LikeSkill {

    /**
     * Represents the unique identifier for the LikeSkill entity.
     * This ID is auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the ID of a user associated with a "LikeSkill" entity.
     * Acts as a foreign key linking this entity to the {@link User} entity.
     * <p>
     * This field is used to identify the user who performed the "like" action
     * on a specific skill. It is marked as non-nullable, ensuring that
     * every "LikeSkill" record is associated with a valid user.
     */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /**
     * Represents the identifier of a skill associated with a "LikeSkill" entity.
     * This field is used to establish relationships with the "Skill" entity.
     * It is mapped to the "skill_id" column in the "LikeSkill" table.
     * The value cannot be null.
     */
    @Column(name = "skill_id", nullable = false)
    private Long skillId;

    /**
     * Represents the association between the LikeSkill entity and the User entity.
     * This is a many-to-one relationship where a LikeSkill references a single User.
     * The user is identified by the foreign key column "user_id".
     * Changes to this field are not directly insertable or updatable, as it is used
     * for mapping purposes to the associated User entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User user;
    ;

    /**
     * Represents the many-to-one relationship between the LikeSkill entity and the Skill entity.
     * This variable maps to the "skill_id" column in the LikeSkill table and references the Skill entity.
     * It is marked as non-insertable and non-updatable to ensure that the value is managed only through
     * the relationship with the corresponding Skill entity.
     */
    @ManyToOne
    @JoinColumn(name = "skill_id", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private Skill skill;

}
