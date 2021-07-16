package de.aicard.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static de.aicard.controller.WebTestConfig.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class MainControllerTest{

    private AiCardRequestBuilder requestBuilder;
    private MockMvc mockMvc;

    @BeforeEach
    void configureSystemUnderTest() {

        MainController testedController = new MainController();
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(testedController)
                .setHandlerExceptionResolvers(exceptionResolver())
                .setLocaleResolver(fixedLocaleResolver())
                .setViewResolvers(htmlViewResolver())
                .build();
        requestBuilder = new AiCardRequestBuilder(mockMvc);
    }

    @Test
    void shouldgetMainPage() throws Exception{
        this.mockMvc.perform(get("/index")).andDo(print()).andExpect(view().name("index"));
    }
}
