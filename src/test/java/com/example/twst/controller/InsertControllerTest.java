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

import com.example.twst.domain.model.BuddyGroupingEnum;
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.service.CardService;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class InsertControllerTest {
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
                card.setTableName(TableEnum.SENDING_STAR_DRESS);
                card.setId("sen_deuce");
                List<Card> cardList = new ArrayList<>();
                cardList.add(card);
                doReturn(cardList).when(service).selectMany(any(), anyString());

                MvcResult mvcResult = mockMvc.perform(get("/insert")
                                .param("tableNameChecks", "sending_star_dress"))
                                .andExpect(view().name("insert.html"))
                                .andExpect(status().isOk())
                                .andExpect(model().attribute("cardList",
                                                is(hasItem(hasProperty("id", is("sen_deuce"))))))
                                .andExpect(model().attribute("tableName", is(TableEnum.SENDING_STAR_DRESS)))
                                .andExpect(model().attribute("characterName", CharacterEnum.values()))
                                .andExpect(model().attribute("rare", CardEnum.getValueListOfFormName("rare")))
                                .andExpect(model().attribute("type", CardEnum.getValueListOfFormName("type")))
                                .andExpect(model().attribute("magicGrouping", MagicGroupingEnum.values()))
                                .andExpect(model().attribute("buddyGrouping", BuddyGroupingEnum.values()))
                                .andExpect(model().attribute("buffDebuffGrouping", BuffDebuffGroupingEnum.values()))
                                .andReturn();

                MockHttpSession mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                doReturn(true).when(service).insert(any());

                mvcResult = mockMvc.perform(post("/insert")
                                .session(mockSession)
                                .param("tableName", "SENDING_STAR_DRESS")
                                .param("name", "JADE")
                                .param("rare", "R")
                                .param("type", "ATTACK")
                                .param("buddy1", "FLOYD")
                                .param("buddy2", "HYPHEN")
                                .param("buddy3", "HYPHEN")
                                .param("magic1", "AQUA_WAVE")
                                .param("magic2", "FOREST_STRIKE")
                                .param("magic3", "FIRE_SHOT2")
                                .param("magic1BuffdebuffGrouping", "DAMAGE_DOWN_LARGE_ENEMY_1T")
                                .param("magic2BuffdebuffGrouping", "HYPHEN")
                                .param("magic3BuffdebuffGrouping",
                                                "ATK_UP_SMALL_SELF_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T")
                                .param("duo", "HYPHEN")
                                .param("minHp", "0")
                                .param("minAtk", "0")
                                .param("maxHp", "0")
                                .param("maxAtk", "0")
                                .param("validFlg", "false")
                                .param("buddy1Grouping", "ATK_UP_SMALL")
                                .param("buddy2Grouping", "HP_UP_MIDDLE")
                                .param("buddy3Grouping", "HP_UP_SMALL"))
                                .andExpect(status().isFound())
                                .andExpect(view().name("redirect:insert"))
                                .andReturn();

                doReturn(cardList).when(service).selectMany(any(), anyString());

                mockMvc.perform(get("/insert")
                                .session(mockSession))
                                .andExpect(view().name("insert.html"))
                                .andExpect(status().isOk())
                                .andExpect(model().attribute("cardList",
                                                is(hasItem(hasProperty("id", is("sen_jade"))))))
                                .andExpect(model().attribute("tableName", is(TableEnum.SENDING_STAR_DRESS)));

        }
}
