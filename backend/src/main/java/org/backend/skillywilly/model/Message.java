package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Represents a message entity stored in the database.
 * This entity tracks messages sent between users, including sender and recipient details,
 * the message content, and the creation timestamp.
 * <p>
 * The Message entity is associated with the User entity through the `userFrom` and `userTo` relationships,
 * enabling navigation between messages and the sending or receiving users.
 */
@Entity
@Table(name = "Message")
@Data
public class Message {
    /**
     * The unique identifier for the Message entity.
     * This field is the primary key of the Message table
     * and is automatically generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the ID of the sender user in a message.
     * This field stores the unique identifier of the user who sent the message.
     * <p>
     * It is mapped to the "id_user_from" column in the "Message" table
     * and is a mandatory field in the database.
     */
    @Column(name = "id_user_from", nullable = false)
    private Long userFromId;

    /**
     * Represents the ID of the recipient user in a message entity.
     * This field corresponds to the "id_user_to" column in the database and is a non-nullable field.
     */
    @Column(name = "id_user_to", nullable = false)
    private Long userToId;

    /**
     * Represents the content of a message exchanged between users.
     * This field is a mandatory column in the "Message" database table.
     * It holds the text or body of the message sent from one user to another.
     */
    @Column(name = "message", nullable = false)
    private String message;

    /**
     * Represents the timestamp when the message was created.
     * It is mapped to the "created" column in the "Message" table in the database.
     * The value is stored as a {@link Timestamp}.
     */
    @Column(name = "created")
    private Timestamp created;

    /**
     * Represents the sender of the message.
     * <p>
     * This field establishes a many-to-one relationship between the `Message` entity and the `User` entity,
     * indicating that each message is associated with a single user who sent it.
     * It is mapped to the `id_user_from` column in the database table and is not directly insertable or updatable,
     * as it serves as a reference to the user data.
     */
    @ManyToOne
    @JoinColumn(name = "id_user_from", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFrom;

    /**
     * Represents the recipient of a message in the `Message` entity.
     * This field establishes a many-to-one relationship between the `Message` entity
     * and the `User` entity, indicating the user who received the message.
     * It is mapped to the "id_user_to" column in the database and is non-insertable
     * and non-updatable since it is managed through the `userToId` field.
     */
    @ManyToOne
    @JoinColumn(name = "id_user_to", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userTo;

}
