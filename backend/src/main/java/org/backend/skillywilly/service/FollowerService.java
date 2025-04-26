package org.backend.skillywilly.service;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowerService {

    @Autowired
    private FollowerRepository followerRepository;

    public Follower followUser(Follower follower) {
        return followerRepository.save(follower);
    }

    public void unfollowUser(Long followerId, Long followingId) {
        followerRepository.deleteByUserFollowerIdAndUserFollowedId(followerId, followingId);
    }

    public List<Follower> getFollowers(Long userId) {
        return followerRepository.findByUserFollowedId(userId);
    }

    public List<Follower> getFollowing(Long userId) {
        return followerRepository.findByUserFollowerId(userId);
    }
}