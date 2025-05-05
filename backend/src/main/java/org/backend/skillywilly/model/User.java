package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

/**
 * Entity representing a User in the system.
 * This class is mapped to the "User" table in the database and contains
 * information about the user such as their username, email, password,
 * and associated relationships with other entities.
 * <p>
 * Fields:
 * - `id`: The unique identifier for the user.
 * - `username`: The unique username of the user. Cannot be null.
 * - `email`: The unique email address of the user. Cannot be null.
 * - `password`: The password of the user. Cannot be null.
 * - `location`: A JSON field storing location details for the user.
 * - `likeSkills`: The list of LikeSkill entities mapped to this user.
 * - `skills`: The list of Skill entities associated with this user.
 * - `followers`: The list of Follower entities representing users who follow this user.
 * - `following`: The list of Follower entities representing users that this user follows.
 * - `sentMessages`: The list of Message entities that this user has sent.
 * - `receivedMessages`: The list of Message entities that this user has received.
 */
@Entity
@Table(name = "User")
@Data
public class User {

    /**
     * The unique identifier for the User entity.
     * This field is auto-generated as a primary key using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The `username` field represents the unique username of the user.
     * This value is used as an identifier for users within the application.
     * It is mapped to the "username" column in the "User" database table and must be unique and non-null.
     */
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    /**
     * Represents the email address of a user.
     * This field is mapped to the "email" column in the database.
     * <p>
     * Constraints:
     * - This field must be unique.
     * - This field cannot be null.
     */
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    /**
     * Represents the password of the user.
     * This field is mapped to the "password" column in the "User" table in the database.
     * It is a required field and cannot be null.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * Represents a location details associated with the user.
     * The data is stored in the database using the JSON type.
     */
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "location", columnDefinition = "json")
    private Location location;

    /**
     * Represents a list of LikeSkill entities associated with a User.
     * This relationship is a one-to-many mapping where a single user can have multiple
     * 'like skills'. Each LikeSkill in the list corresponds to a skill that the user has liked.
     * <p>
     * The `mappedBy` attribute indicates that the 'user' field in the LikeSkill entity
     * defines the relationship.
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<LikeSkill> likeSkills;

    /**
     * Represents the list of skills associated with the user.
     * This field is a one-to-many relationship mapped by the "user" field in the Skill entity.
     */
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<Skill> skills;

    /**
     * Represents the list of followers associated with a user.
     * This field maps to the Follower entities where the user is being followed.
     * The "userFollowed" field in the Follower entity defines the relationship.
     */
    @OneToMany(mappedBy = "userFollowed")
    @JsonIgnore
    @ToString.Exclude
    private List<Follower> followers;

    /**
     * Represents the list of users that this user follows.
     * This is a one-to-many relationship mapped by the "userFollower" field in the Follower entity.
     * Each entry in the list denotes a Follower entity where this user is the follower.
     */
    @OneToMany(mappedBy = "userFollower")
    @JsonIgnore
    @ToString.Exclude
    private List<Follower> following;

    /**
     * Represents the list of messages sent by this user.
     * This is a one-to-many relationship mapped by the "userFrom" field in the Message entity.
     */
    @OneToMany(mappedBy = "userFrom")
    private List<Message> sentMessages;

    /**
     * Represents the messages received by the user.
     * This is a one-to-many relationship where a user can receive multiple messages.
     * The relationship is mapped by the "userTo" field in the Message entity.
     */
    @OneToMany(mappedBy = "userTo")
    private List<Message> receivedMessages;
}