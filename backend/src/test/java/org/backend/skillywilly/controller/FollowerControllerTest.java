package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.Follower;
import org.backend.skillywilly.service.FollowerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FollowerController.class)
class FollowerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FollowerService followerService;

    private Follower testFollower;

    @BeforeEach
    void setUp() {
        testFollower = new Follower();
        testFollower.setId(1L);
        testFollower.setUserFollowerId(1L);
        testFollower.setUserFollowedId(2L);
    }

    @Test
    void testFollowUserSuccess() throws Exception {
        when(followerService.followUser(any(Follower.class))).thenReturn(testFollower);

        mockMvc.perform(post("/api/followers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"userFollowerId\":1,\"userFollowedId\":2}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testFollower.getId()))
                .andExpect(jsonPath("$.userFollowerId").value(testFollower.getUserFollowerId()))
                .andExpect(jsonPath("$.userFollowedId").value(testFollower.getUserFollowedId()));
    }

    @Test
    void testFollowUserBadRequest() throws Exception {
        mockMvc.perform(post("/api/followers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUnfollowUserSuccess() throws Exception {
        mockMvc.perform(delete("/api/followers")
                        .param("followerId", "1")
                        .param("followingId", "2"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testUnfollowUserBadRequest() throws Exception {
        mockMvc.perform(delete("/api/followers")
                        .param("followerId", "")
                        .param("followingId", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUnfollowUserInternalServerError() throws Exception {
        doThrow(new RuntimeException()).when(followerService).unfollowUser(1L, 2L);

        mockMvc.perform(delete("/api/followers")
                        .param("followerId", "1")
                        .param("followingId", "2"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetFollowersSuccess() throws Exception {
        List<Follower> followers = List.of(testFollower);
        when(followerService.getFollowers(1L)).thenReturn(followers);

        mockMvc.perform(get("/api/followers/1/followers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testFollower.getId()))
                .andExpect(jsonPath("$[0].userFollowerId").value(testFollower.getUserFollowerId()))
                .andExpect(jsonPath("$[0].userFollowedId").value(testFollower.getUserFollowedId()));
    }

    @Test
    void testGetFollowersBadRequest() throws Exception {
        mockMvc.perform(get("/api/followers/null/followers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetFollowersInternalServerError() throws Exception {
        when(followerService.getFollowers(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/followers/1/followers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void testGetFollowingSuccess() throws Exception {
        List<Follower> followers = List.of(testFollower);
        when(followerService.getFollowing(1L)).thenReturn(followers);

        mockMvc.perform(get("/api/followers/1/following")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(testFollower.getId()))
                .andExpect(jsonPath("$[0].userFollowerId").value(testFollower.getUserFollowerId()))
                .andExpect(jsonPath("$[0].userFollowedId").value(testFollower.getUserFollowedId()));
    }

    @Test
    void testGetFollowingBadRequest() throws Exception {
        mockMvc.perform(get("/api/followers/null/following")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetFollowingInternalServerError() throws Exception {
        when(followerService.getFollowing(1L)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/followers/1/following")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}