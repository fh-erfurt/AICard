package de.aicard.controller;

import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetAboService;
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
 * Tests get requests to LearnSetController
 *
 * @author Daniel Michel
 */

@WebMvcTest(LearnSetController.class)
public class LearnSetControllerTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private LearnSetService learnSetService;

    @MockBean
    private CardService cardService;

    @MockBean
    private LearnSetAboService learnSetAboService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnLearnset() throws Exception{
        LearnSet learnSet = new LearnSet();
        mockMvc.perform(get("/createLearnset").sessionAttr("newLearnset", learnSet))
            .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldGetLearnSet() throws  Exception{
        mockMvc.perform(get("/learnSets"))
                .andExpect(status().isOk())
                .andExpect(view().name("learnSets"));
    }

    @Test
    @WithMockUser
    public void shouldRedirectToLearnSets() throws Exception{
        mockMvc.perform(get("/deleteLearnSet/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSets"));

        mockMvc.perform(get("/unfollowLearnSet/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSets"));

        mockMvc.perform(get("/cardOverview/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSets"));

        mockMvc.perform(get("/deleteCard/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSets"));


    }

    @Test
    @WithMockUser
    public void shouldRedirectToCardOverview() throws Exception{
        mockMvc.perform(get("/editLearnSet/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cardOverview/1"));
    }
}
