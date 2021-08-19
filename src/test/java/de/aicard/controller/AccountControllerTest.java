package de.aicard.controller;

import de.aicard.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests get requests to Account Controller
 *
 * @author Daniel Michel
 */

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    /**
     * is redirected to /login due to SpringSecurity redirection.
     */
    public void shouldRedirectToLogin() throws Exception{
        mockMvc.perform(get("/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        mockMvc.perform(get("/updateProfile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    public void shouldRedirectToProfile() throws Exception{
        mockMvc.perform(get("/removeFriendFromFriendList/5"))
                .andExpect(redirectedUrl("/profile"));
    }

    @Test
    public void shouldRedirectToFriendProfile() throws Exception{
        mockMvc.perform(get("/addFriend/1"))
                .andExpect(redirectedUrl("/profile/1"));
    }

}
