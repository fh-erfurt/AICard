package de.aicard.controller;

import de.aicard.services.AccountService;
import de.aicard.services.LearnSetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests get requests to SocialController
 *
 * @author Daniel Michel
 */
@WebMvcTest(SocialController.class)
public class SocialControllerTest {
    @MockBean
    private LearnSetService learnSetService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldRedirectToIndex() throws Exception{
        this.mockMvc.perform(get("/comments/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSets"));
    }
}
