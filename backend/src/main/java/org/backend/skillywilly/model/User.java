package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

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

    // Neue Felder zur User-Klasse hinzufügen
    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "token_expiry_date")
    private Date tokenExpiryDate;

    @Column(name = "verified", nullable = false)
    private boolean verified = false;
}