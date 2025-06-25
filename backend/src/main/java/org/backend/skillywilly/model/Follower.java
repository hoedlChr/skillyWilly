package org.backend.skillywilly.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Timestamp;

@Entity
@Table(name = "Follower")
@Data
public class Follower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created")
    private Timestamp created;

    @ManyToOne
    @JoinColumn(name = "id_user_follwed", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFollowed;

    @ManyToOne
    @JoinColumn(name = "id_user_follwer", insertable = false, updatable = false)
    @JsonIgnore
    @ToString.Exclude
    private User userFollower;
    
    @Column(name = "id_user_follwed", nullable = false)
    private Long userFollowedId;

    @Column(name = "id_user_follwer", nullable = false)
    private Long userFollowerId;
}
