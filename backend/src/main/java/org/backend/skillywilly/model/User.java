package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = User.TABLE_NAME)
@Data
public class User {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}