package de.aicard.controller;

import de.aicard.services.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetRegistrationView() throws Exception{
        mockMvc.perform(get("/registration")).andExpect(status().isOk())
                .andExpect(view().name("registration"));
    }

    @Test
    public void shouldGetLoginView() throws Exception{
        mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void shouldCreateNewAccount() throws Exception{

    }
}