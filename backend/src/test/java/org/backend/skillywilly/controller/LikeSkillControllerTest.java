package org.backend.skillywilly.controller;

import org.backend.skillywilly.model.LikeSkill;
import org.backend.skillywilly.service.LikeSkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LikeSkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LikeSkillService likeSkillService;

    @Test
    public void likeSkill_ShouldReturnLikeSkill_WhenValidRequest() throws Exception {
        Long userId = 1L;
        Long skillId = 1L;

        LikeSkill likeSkill = new LikeSkill();
        likeSkill.setId(1L);
        likeSkill.setUserId(userId);
        likeSkill.setSkillId(skillId);

        when(likeSkillService.likeSkill(userId, skillId)).thenReturn(likeSkill);

        mockMvc.perform(post("/api/like-skills/like")
                        .param("userId", String.valueOf(userId))
                        .param("skillId", String.valueOf(skillId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(likeSkill.getId()))
                .andExpect(jsonPath("$.userId").value(likeSkill.getUserId()))
                .andExpect(jsonPath("$.skillId").value(likeSkill.getSkillId()));

        verify(likeSkillService, times(1)).likeSkill(userId, skillId);
    }

    @Test
    public void likeSkill_ShouldReturnBadRequest_WhenMissingParameters() throws Exception {
        mockMvc.perform(post("/api/like-skills/like")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(likeSkillService);
    }
}