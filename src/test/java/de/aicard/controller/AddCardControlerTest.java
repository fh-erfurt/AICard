package de.aicard.controller;

import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests get requests to AddCardController
 *
 * @author Daniel Michel
 */

@WebMvcTest(AddCardController.class)
public class AddCardControlerTest
{
    @MockBean
    private AccountService accountService;
    
    @MockBean
    private CardService cardService;
    
    @MockBean
    private LearnSetService learnSetService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void shouldRedirectToLogin() throws Exception
    {
        mockMvc.perform(get("/addCard/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}
