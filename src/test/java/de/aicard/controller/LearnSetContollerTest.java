package de.aicard.controller;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LearnSetController.class)
class LearnSetControllerTest
{
    @MockBean
    private AccountService accountService;

    @MockBean
    private LearnSetService learnSetService;

    @MockBean
    private CardService cardService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnPictureFile() throws Exception{
        this.mockMvc.perform(get("/learnSetImage/1625238766608_exampleCat.png")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnLearnset() throws Exception{
        LearnSet learnSet = new LearnSet();
        RequestBuilder requestBuilder = post("/createLearnset").sessionAttr("newLearnset", learnSet);
        this.mockMvc.perform(requestBuilder).andExpect(redirectedUrl("http://localhost/login"));
    }
}