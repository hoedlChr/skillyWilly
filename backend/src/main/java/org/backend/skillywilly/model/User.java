package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Date;

/**
 * Entity representing a User in the system.
 */
@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "location", columnDefinition = "json")
    private Location location;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "token_expiry_date")
    private Date tokenExpiryDate;
    
    @Column(
            name = "verified",
            nullable = false,
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private boolean verified;

}
