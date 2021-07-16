package de.aicard.controller;

import org.springframework.test.web.servlet.MockMvc;

class AiCardRequestBuilder {

    private final MockMvc mockMvc;

    AiCardRequestBuilder(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }
}