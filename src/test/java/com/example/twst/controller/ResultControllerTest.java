package com.example.twst.controller;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.twst.TestConfig;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.service.CardService;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc
public class ResultControllerTest {

    @InjectMocks
    private ResultController target;

    @Mock
    private CardService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysDo(log()).build();
    }

    @Test
    @DisplayName("input")
    void input() throws Exception {
        List<Card> cardList = new ArrayList<>();
        Card card = new Card();
        card.setTableName(TableEnum.SENDING_STAR_DRESS);
        cardList.add(card);

        when(service.selectAll(any())).thenReturn(cardList);
        mockMvc.perform(get("/result"))
                .andExpect(status().isOk())
                .andExpect(view().name("result.html"))
                .andExpect(model().attribute("list",
                        is(hasItem(hasProperty("tableName", is(TableEnum.SENDING_STAR_DRESS))))));
    }
}
