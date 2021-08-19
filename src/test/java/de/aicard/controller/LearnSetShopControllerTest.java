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
 * Tests get requests to LearnSetShopController
 *
 * @author Daniel Michel
 */

@WebMvcTest(LearnSetShopController.class)
public class LearnSetShopControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private LearnSetService learnSetService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldRedirectLearnSetShop() throws Exception {
        mockMvc.perform(get("/followLearnSet/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSetShop"));
    }

    @Test
    @WithMockUser
    public void shouldReturnLearnSetShop() throws Exception {
        mockMvc.perform(get("/learnSetShop"))
                .andExpect(status().isOk())
                .andExpect(view().name("learnSetShop"));
    }


}
