package de.aicard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Tests get requests to ErrorController
 *
 * @author Daniel Michel
 */

@WebMvcTest(ErrorController.class)
public class ErrorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetErrorPage() throws Exception{
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    public void shouldGetError404Page() throws Exception{
        mockMvc.perform(get("/error404"))
                .andExpect(status().isNotFound());
    }
}
