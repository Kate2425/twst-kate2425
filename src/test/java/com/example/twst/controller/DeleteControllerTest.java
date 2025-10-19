package com.example.twst.controller;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.service.CardService;

@SpringBootTest
@Transactional
@WebAppConfiguration

public class DeleteControllerTest {
        @Autowired
        WebApplicationContext webApplicationContext;

        private MockMvc mockMvc;

        @Mock
        private CardService service;

        @BeforeEach
        void setup() {
                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                .alwaysDo(log()).build();
        }

        @Test
        @DisplayName("input")
        void input_01() throws Exception {
                //Arrange 
                Card card = new Card();
                card.setTableName(TableEnum.GALA_COUTURE);
                card.setId("gal_ruggie");
                List<Card> cardList = new ArrayList<>();
                cardList.add(card);
                doReturn(cardList).when(service).selectMany(any(), anyString());

                MvcResult mvcResult = mockMvc.perform(get("/delete")
                                .param("tableNameChecks", "gala_couture"))
                                .andExpect(view().name("delete.html"))
                                .andExpect(status().isOk())
                                .andExpect(model().attribute("cardList",
                                                is(hasItem(hasProperty("id", is("gal_ruggie"))))))
                                .andExpect(model().attribute("tableName", is(TableEnum.GALA_COUTURE)))
                                .andReturn();

                MockHttpSession mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                doReturn(true).when(service).deleteOne(any());

                mvcResult = mockMvc.perform(post("/delete")
                                .session(mockSession)
                                .param("tableName", "GALA_COUTURE")
                                .param("name", "RUGGIE"))
                                .andExpect(status().isFound())
                                .andExpect(view().name("redirect:delete"))
                                .andReturn();

                doReturn(cardList).when(service).selectMany(any(), anyString());

                mockMvc.perform(get("/delete")
                                .session(mockSession))
                                .andExpect(view().name("delete.html"))
                                .andExpect(status().isOk())
                                .andExpect(model().attribute("cardList",
                                                is(hasItem(hasProperty("id", is(not("gal_ruggie")))))))
                                .andExpect(model().attribute("tableName", is(TableEnum.GALA_COUTURE)));
        }
}
