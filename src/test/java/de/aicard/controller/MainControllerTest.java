package de.aicard.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
class MainControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldgetMainPage() throws Exception{
        this.mockMvc.perform(get("/")).andExpect(status().isOk()).
                andExpect(content().string(containsString("/index")))
        .andExpect(view().name("index"));
    }
}
