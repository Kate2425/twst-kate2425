package com.example.twst.controller;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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
public class UpdateControllerTest {
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
                Card card = new Card();
                card.setTableName(TableEnum.OUTDOOR_WEAR);
                card.setId("out_ruggie");
                List<Card> cardList = new ArrayList<>();
                cardList.add(card);
                doReturn(cardList).when(service).selectMany(any(), anyString());

                MvcResult mvcResult = mockMvc.perform(get("/update")
                                .param("tableNameChecks", "outdoor_wear"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("update.html"))
                                .andExpect(model().attribute("cardList",
                                                allOf(hasItem(hasProperty("id", is("out_ruggie"))))))
                                .andExpect(model().attribute("characterName", CharacterEnum.values()))
                                .andExpect(model().attribute("rare", CardEnum.getValueListOfFormName("rare")))
                                .andExpect(model().attribute("type", CardEnum.getValueListOfFormName("type")))
                                .andExpect(model().attribute("magic", MagicGroupingEnum.values()))
                                .andExpect(model().attribute("buddyGrouping", BuddyGroupingEnum.values()))
                                .andExpect(model().attribute("buffDebuffGrouping", BuffDebuffGroupingEnum.values()))
                                .andReturn();

                MockHttpSession mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                doReturn(true).when(service).updateOne(any());

                mvcResult = mockMvc.perform(post("/update")
                                .session(mockSession)
                                .param("tableName", "OUTDOOR_WEAR")
                                .param("name", "JADE")
                                .param("rare", "SR")
                                .param("type", "ATTACK")
                                .param("buddy1", "TREY")
                                .param("buddy2", "MALLEUS")
                                .param("buddy3", "HYPHEN")
                                .param("magic1", "WATER_SHOT")
                                .param("magic2", "FIRE_SHOT2")
                                .param("magic3", "HYPHEN")
                                .param("magic1BuffdebuffGrouping", "DAMAGE_UP_MEDIUM_SELF_1T")
                                .param("magic2BuffdebuffGrouping", "CURSE_SMALL_ENEMY_2T")
                                .param("magic3BuffdebuffGrouping", "HYPHEN")
                                .param("duo", "HYPHEN")
                                .param("minHp", "1593")
                                .param("minAtk", "1326")
                                .param("maxHp", "6156")
                                .param("maxAtk", "6278")
                                .param("validFlg", "false")
                                .param("buddy1Grouping", "ATK_UP_MIDDLE")
                                .param("buddy2Grouping", "HP_UP_SMALL")
                                .param("buddy3Grouping", "HYPHEN"))
                                .andExpect(view().name("redirect:update"))
                                .andExpect(status().isFound())
                                .andReturn();

                mockMvc.perform(get("/update")
                                .session(mockSession))
                                .andExpect(status().isOk())
                                .andExpect(view().name("update.html"))
                                .andExpect(model().attribute("cardList",
                                                allOf(hasItem(allOf(hasProperty("id", is("out_jade")),
                                                                hasProperty("validFlg", is(false)))))));
        }
}
