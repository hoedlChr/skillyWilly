package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.service.FollowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FollowerControllerTest {

    @Mock
    private FollowerService followerService;

    @InjectMocks
    private FollowerController followerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFollowUser_ValidFollower() {
        Follower follower = new Follower();
        follower.setUserFollowedId(1L);
        follower.setUserFollowerId(2L);

        when(followerService.followUser(follower)).thenReturn(follower);

        ResponseEntity<?> response = followerController.followUser(follower);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(follower, response.getBody());
        verify(followerService, times(1)).followUser(follower);
    }

    @Test
    void testFollowUser_InvalidFollower() {
        Follower follower = new Follower();

        ResponseEntity<?> response = followerController.followUser(follower);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
        verifyNoInteractions(followerService);
    }

    @Test
    void testFollowUser_ExceptionThrown() {
        Follower follower = new Follower();
        follower.setUserFollowedId(1L);
        follower.setUserFollowerId(2L);

        when(followerService.followUser(follower)).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<?> response = followerController.followUser(follower);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(followerService, times(1)).followUser(follower);
    }

    @Test
    void testUnfollowUser_ValidRequest() {
        Long followerId = 1L;
        Long followingId = 2L;

        doNothing().when(followerService).unfollowUser(followerId, followingId);

        ResponseEntity<?> response = followerController.unfollowUser(followerId, followingId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(followerService, times(1)).unfollowUser(followerId, followingId);
    }

    @Test
    void testUnfollowUser_InvalidRequest() {
        Long followerId = null;
        Long followingId = 2L;

        ResponseEntity<?> response = followerController.unfollowUser(followerId, followingId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verifyNoInteractions(followerService);
    }

    @Test
    void testUnfollowUser_ExceptionThrown() {
        Long followerId = 1L;
        Long followingId = 2L;

        doThrow(new RuntimeException("Test exception")).when(followerService).unfollowUser(followerId, followingId);

        ResponseEntity<?> response = followerController.unfollowUser(followerId, followingId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(followerService, times(1)).unfollowUser(followerId, followingId);
    }

    @Test
    void testGetFollowers_ValidRequest() {
        Long userId = 1L;
        List<Follower> followers = List.of(new Follower(), new Follower());

        when(followerService.getFollowers(userId)).thenReturn(followers);

        ResponseEntity<?> response = followerController.getFollowers(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(followers, response.getBody());
        verify(followerService, times(1)).getFollowers(userId);
    }

    @Test
    void testGetFollowers_InvalidRequest() {
        Long userId = null;

        ResponseEntity<?> response = followerController.getFollowers(userId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
        verifyNoInteractions(followerService);
    }

    @Test
    void testGetFollowers_ExceptionThrown() {
        Long userId = 1L;

        when(followerService.getFollowers(userId)).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<?> response = followerController.getFollowers(userId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(followerService, times(1)).getFollowers(userId);
    }

    @Test
    void testGetFollowing_ValidRequest() {
        Long userId = 1L;
        List<Follower> following = List.of(new Follower(), new Follower());

        when(followerService.getFollowing(userId)).thenReturn(following);

        ResponseEntity<?> response = followerController.getFollowing(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(following, response.getBody());
        verify(followerService, times(1)).getFollowing(userId);
    }

    @Test
    void testGetFollowing_InvalidRequest() {
        Long userId = null;

        ResponseEntity<?> response = followerController.getFollowing(userId);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(null, response.getBody());
        verifyNoInteractions(followerService);
    }

    @Test
    void testGetFollowing_ExceptionThrown() {
        Long userId = 1L;

        when(followerService.getFollowing(userId)).thenThrow(new RuntimeException("Test exception"));

        ResponseEntity<?> response = followerController.getFollowing(userId);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(followerService, times(1)).getFollowing(userId);
    }
}