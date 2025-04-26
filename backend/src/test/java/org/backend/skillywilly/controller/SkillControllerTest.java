package org.backend.skillywilly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.backend.skillywilly.model.Skill;
import org.backend.skillywilly.service.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SkillController.class)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;  // richtig: @MockBean, nicht @MockitoBean

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createSkill_success() throws Exception {
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setSubject("Java");
        skill.setBody("Advanced Java tutorial");
        skill.setUserId(5L);

        when(skillService.createSkill(any(Skill.class))).thenReturn(skill);

        mockMvc.perform(post("/api/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(skill)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.subject").value("Java"))
                .andExpect(jsonPath("$.body").value("Advanced Java tutorial"))
                .andExpect(jsonPath("$.userId").value(5L));
    }

    @Test
    void createSkill_badRequest() throws Exception {
        mockMvc.perform(post("/api/skills")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("null"))
                .andExpect(status().isBadRequest());
    }
}