package de.aicard.controller;
import de.aicard.domains.learnset.LearnSet;
import de.aicard.services.AccountService;
import de.aicard.services.CardService;
import de.aicard.services.LearnSetService;
import de.aicard.storages.CardStatusRepository;
import de.aicard.storages.LearnSetAboRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;


//import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder.*;

@RunWith(SpringRunner.class)
//@WebMvcTest(LearnSetController.class)
@SpringBootTest
public class LearnSetControllerTest
{
    @MockBean
    private AccountService accountService;

    @MockBean
    private LearnSetAboRepository learnSetAboRepository;

    @MockBean
    private LearnSetService learnSetService;

    @MockBean
    private CardService cardService;

    @MockBean
    private CardStatusRepository cardStatusRepository;

   // @Autowired
    // private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    public void shouldReturnPictureFile() throws Exception{
        this.mockMvc.perform(get("/learnSetImage/1625238766608_exampleCat.png")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturnLearnset() throws Exception{
        LearnSet learnSet = new LearnSet();
        RequestBuilder requestBuilder = post("/createLearnset").sessionAttr("newLearnset", learnSet);
        //ResultActions action = mockMvc.perform(requestBuilder).with();
    }
}