package de.aicard.controller;


import de.aicard.services.AccountService;
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
 * Tests get requests to CommentController
 *
 * @author Daniel Michel
 */

@WebMvcTest(CommentController.class)
public class CommentControllerTest
{
    @MockBean
    private AccountService accountService;
    
    @MockBean
    private LearnSetService learnSetService;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void shouldRedirectToLearnset() throws Exception
    {
        mockMvc.perform(get("/getComments/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/learnSet/1"));
    }
}
