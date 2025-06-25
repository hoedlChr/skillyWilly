package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "Message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user_from", nullable = false)
    private Long userFromId;

    @Column(name = "id_user_to", nullable = false)
    private Long userToId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "created")
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "id_user_from", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFrom;

    @ManyToOne
    @JoinColumn(name = "id_user_to", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userTo;

}
