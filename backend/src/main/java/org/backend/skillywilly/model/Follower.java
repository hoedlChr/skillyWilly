package org.backend.skillywilly.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "Follower")
@Data
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user_follwed", nullable = false)
    private Long userFollowedId;

    @Column(name = "id_user_follwer", nullable = false)
    private Long userFollowerId;

    @Column(name = "created")
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "id_user_follwed", insertable = false, updatable = false)
    private User userFollowed;

    @ManyToOne
    @JoinColumn(name = "id_user_follwer", insertable = false, updatable = false)
    private User userFollower;
}
