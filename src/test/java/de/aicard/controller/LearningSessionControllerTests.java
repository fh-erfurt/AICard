package de.aicard.controller;

import de.aicard.services.LearnSetAboService;
import de.aicard.services.LearningSessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests get requests to LearningSessionController
 *
 * @author Daniel Michel
 */

@WebMvcTest(LearningSessionController.class)
public class LearningSessionControllerTests {
    @MockBean
    private LearnSetAboService learnSetAboService;
    @MockBean
    private LearningSessionService learningSessionService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnInitializeLearningSession() throws Exception{
        mockMvc.perform(get("/initializeLearningSession/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/initializeLearningSession"));
    }

    @Test
    @WithMockUser
    public void shouldReturnLearnCard() throws Exception{
        mockMvc.perform(get("/learnCard/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }

}
