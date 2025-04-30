package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

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
    private List<Location> location;

    @OneToMany(mappedBy = "user")
    private List<LikeSkill> likeSkills;

    @OneToMany(mappedBy = "user")
    private List<Skill> skills;

    @OneToMany(mappedBy = "userFollowed")
    private List<Follower> followers;

    @OneToMany(mappedBy = "userFollower")
    private List<Follower> following;

    @OneToMany(mappedBy = "userFrom")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "userTo")
    private List<Message> receivedMessages;
}