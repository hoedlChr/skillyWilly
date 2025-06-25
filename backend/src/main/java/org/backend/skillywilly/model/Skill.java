package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = Skill.TABLE_NAME)
@Data
public class Skill {

    public static final String TABLE_NAME = "Skill";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}