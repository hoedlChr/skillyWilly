package org.backend.skillywilly.repository;

import org.backend.skillywilly.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {

    List<Follower> findByUserFollowerId(Long userFollowerId);

    List<Follower> findByUserFollowedId(Long userFollowedId);

    void deleteByUserFollowerIdAndUserFollowedId(Long userFollowerId, Long userFollowedId);
}