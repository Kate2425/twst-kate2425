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

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class InsertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardService service;

    @InjectMocks
    private InsertController target;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysDo(log()).build();
    }

    @Test
    @DisplayName("input")
    void input_01() throws Exception {
        Card card = new Card();
        card.setTableName(TableEnum.SENDING_STAR_DRESS);
        card.setId("sen_deuce");
        List<Card> cardList = new ArrayList<>();
        cardList.add(card);
        when(service.selectMany(any(), anyString())).thenReturn(cardList);

        mockMvc.perform(get("/insert")
                .param("tableNameChecks", "sending_star_dress"))
                .andExpect(view().name("insert.html"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("cardList", is(hasItem(hasProperty("id", is("sen_deuce"))))));
    }

    @Test
    @DisplayName("conform")
    void conform() throws Exception {

        when(service.insert(any())).thenReturn(true);
        mockMvc.perform(post("/insert")
                .param("tableName", "SENDING_STAR_DRESS")
                .param("name", "JADE")
                .param("rare", "R")
                .param("type", "ATTACK")
                .param("buddy1", "FLOYD")
                .param("buddy2", "HYPHEN")
                .param("buddy3", "HYPHEN")
                .param("magic1Grouping", "1")
                .param("magic2Grouping", "2")
                .param("magic3Grouping", "3")
                .param("duo", "HYPHEN")
                .param("minHp", "0")
                .param("minAtk", "0")
                .param("maxHp", "0")
                .param("maxAtk", "0")
                .param("validFlg", "false")
                .param("buddy1Grouping", "3")
                .param("buddy2Grouping", "2")
                .param("buddy3Grouping", "1"))
                .andExpect(view().name("redirect:insert"))
                .andExpect(status().isFound());
    }
}
