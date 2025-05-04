package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Represents the Follower entity that models the relationship where one user
 * follows another user within the system.
 * <p>
 * This class contains the details of the follower relationship, such as the IDs of the
 * followed and follower users and the timestamp when the relationship was created.
 * It also maps the follower and followed users through many-to-one relationships with the User entity.
 * <p>
 * Annotations:
 * - @Entity: Specifies that this is a JPA entity.
 * - @Table: Maps this entity to the "Follower" table.
 * - @Data: Generates boilerplate code such as getters, setters, and equals/hashCode implementations.
 * - @Id: Marks the primary key field.
 * - @GeneratedValue: Specifies the auto-increment strategy for the primary key.
 * - @Column: Maps class fields to specific database columns with specified constraints.
 * - @ManyToOne: Defines the many-to-one relationships with the User entity.
 * - @JoinColumn: Specifies the foreign key columns used in the relationships.
 */
@Entity
@Table(name = "Follower")
@Data
public class Follower {
    /**
     * Represents the primary key for the Follower entity.
     * <p>
     * This field is annotated as the primary key and is automatically generated
     * using the IDENTITY strategy. It uniquely identifies each record in the Follower table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the ID of the user being followed in the follower relationship.
     * <p>
     * This field maps to the "id_user_follwed" column in the "Follower" table and
     * stores the unique identifier of the user that is being followed.
     * <p>
     * Annotations:
     * - @Column: Specifies the database column name "id_user_follwed".
     * - nullable = false: Indicates this field cannot be null.
     */
    @Column(name = "id_user_follwed", nullable = false)
    private Long userFollowedId;

    /**
     * Represents the ID of the user who is following another user in the system.
     * <p>
     * This field is mapped to the "id_user_follwer" column in the "Follower" table
     * with a non-null constraint to ensure that every follower relationship has a valid follower user ID.
     * <p>
     * It serves as a foreign key referencing the primary key of the "User" entity
     * and is used to establish a bidirectional many-to-one relationship with the User entity.
     * <p>
     * Annotations:
     * - @Column: Maps this field to the "id_user_follwer" database column and specifies constraints.
     */
    @Column(name = "id_user_follwer", nullable = false)
    private Long userFollowerId;

    /**
     * Represents the timestamp when the follower relationship was created.
     * <p>
     * This field maps to the "created" column in the "Follower" database table
     * and stores the date and time when the follower relationship was established.
     * <p>
     * Annotations:
     * - @Column: Indicates that this field corresponds to the "created" column in the database.
     */
    @Column(name = "created")
    private Timestamp created;

    /**
     * Represents the user that is being followed in the follower relationship.
     * <p>
     * This field establishes a many-to-one relationship between the Follower entity
     * and the User entity, where many follower records can reference the same followed user.
     * <p>
     * Mapped to the "id_user_follwed" column in the "Follower" table, this field serves as a foreign key
     * and ensures referential integrity with the User table.
     * <p>
     * Annotations:
     * - @ManyToOne: Specifies that this field represents a many-to-one relationship to the User entity.
     * - @JoinColumn: Maps this field to a specific column in the database ("id_user_follwed")
     * and defines insert/update rules for this foreign key.
     */
    @ManyToOne
    @JoinColumn(name = "id_user_follwed", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFollowed;


    /**
     * Represents the user entity who follows another user within the system.
     * <p>
     * This variable models a many-to-one relationship with the User entity,
     * where it indicates the user who is the follower in the relationship.
     * It is mapped to the "id_user_follwer" column in the database and is
     * non-insertable and non-updatable in this entity to avoid redundant data changes.
     * <p>
     * Annotations:
     * - @ManyToOne: Defines a many-to-one relationship with the User entity.
     * - @JoinColumn: Specifies the foreign key column "id_user_follwer" used in the relationship.
     */
    @ManyToOne
    @JoinColumn(name = "id_user_follwer", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFollower;
}
