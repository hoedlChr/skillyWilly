package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Represents the `UserData` entity, which is mapped to the "UserData" table in the database.
 * This class contains information about a user's attributes such as their unique identifier,
 * name details, birthday, creation timestamp, and a reference to the associated `User` entity.
 * It leverages JPA annotations to define its structure and relationships for database persistence.
 */
@Entity
@Table(name = "UserData")
@Data
public class UserData {

    /**
     * Represents the unique identifier for the `UserData` entity.
     * This field serves as the primary key in the "UserData" table within the database.
     * The value is automatically generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the unique identifier associated with a user.
     * This field is mapped to the "user_id" column in the "UserData" table.
     * <p>
     * Constraints:
     * - This field must be unique.
     * - This field cannot be null.
     */
    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    /**
     * Represents the first name of a user.
     * This field is mapped to the "firstname" column in the "UserData" database table.
     */
    @Column(name = "firstname")
    private String firstname;

    /**
     * Represents the last name of a user.
     * This field is mapped to the "lastname" column in the "UserData" table in the database.
     * It is used to store the family name or surname of the user.
     */
    @Column(name = "lastname")
    private String lastname;

    /**
     * Represents the birthday of a user.
     * This field is mapped to the "birthday" column in the "UserData" database table.
     * It stores the timestamp of the user's date of birth.
     */
    @Column(name = "birthday")
    private Timestamp birthday;

    /**
     * The `created` field represents the timestamp when the entity was created.
     * This field is mapped to the "created" column in the "UserData" table in the database.
     * <p>
     * Characteristics:
     * - Data type: `Timestamp`
     * - Defines when the record was initially stored in the database.
     * - Allows tracking the creation time of the entity for auditing purposes.
     */
    @Column(name = "created")
    private Timestamp created;

}
