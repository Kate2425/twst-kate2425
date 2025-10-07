package com.example.twst.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
// import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.service.CardService;
import com.example.twst.session.OrganizeSession;

@SpringBootTest
@Transactional
@WebAppConfiguration
public class CalculateControllerTest {

        @Autowired
        OrganizeSession organizeSession;

        @Autowired
        WebApplicationContext webApplicationContext;

        @Mock
        private CardService service;

        MockMvc mockMvc;

        @BeforeEach
        void setup() {
                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                .alwaysDo(log()).build();
        }

        @Test
        @DisplayName("calculate")
        void calculate_01() throws Exception {

                MvcResult mvcResult = mockMvc.perform(post("/organize")
                                .param("tableName", "DORMITORY_CLOTHING")
                                .param("id", "dor_jade")
                                .param("num", "11")
                                .param("name", "JADE")
                                .param("clothingName", "dormitory_clothing")
                                .param("rare", "SSR")
                                .param("type", "DEFENCE")
                                .param("buddy1", "TREY")
                                .param("buddy1Grouping", "ATK UP(小)")
                                .param("buddy2", "AZUL")
                                .param("buddy2Grouping", "HP UP(小)")
                                .param("buddy3", "FLOYD")
                                .param("buddy3Grouping", "HP UP(中)")
                                .param("magic1", "AQUA_WAVE")
                                .param("magic2", "FLAME_BLAST")
                                .param("magic3", "LEAF_SHOT2")
                                .param("duo", "FLOYD")
                                .param("minHp", "3000")
                                .param("minAtk", "1000")
                                .param("maxHp", "10000")
                                .param("maxAtk", "5000")
                                .param("magic1BuffdebuffGrouping", "HYPHEN")
                                .param("magic2BuffdebuffGrouping", "HYPHEN")
                                .param("magic3BuffdebuffGrouping", "HYPHEN")
                                .param("arrayIndex", "0"))
                                .andExpect(status().isFound())
                                .andExpect(view().name("redirect:organize"))
                                .andReturn();

                MockHttpSession mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                mvcResult = mockMvc.perform(get("/organize")
                                .session(mockSession))
                                .andExpect(status().isOk())
                                .andExpect(view().name("organize.html"))
                                .andExpect(model().attribute("totalHp", is(BigDecimal.valueOf(10000))))
                                .andExpect(model().attribute("buddyCount", 0))
                                .andExpect(model().attribute("duoCount", 0))
                                .andExpect(model().attribute("arrayIndex", 0))
                                .andExpect(model().attribute("cardArray", is(arrayContaining(
                                                hasProperty("id", is("dor_jade")),
                                                null, null, null, null))))
                                .andExpect(model().attribute("reflectedHp", is(BigDecimal.valueOf(10000))))
                                .andExpect(view().name("organize.html"))
                                .andReturn();

                mockSession = (MockHttpSession) mvcResult.getRequest().getSession();
                organizeSession = (OrganizeSession) mockSession.getAttribute("scopedTarget.organizeSession");

                assertThat(organizeSession.getTempCardArray(), is(arrayContaining(
                                hasProperty("id", is("dor_jade")),
                                null, null, null, null)));

                mockMvc.perform(get("/calculate")
                                .session(mockSession)
                                .param("level", "80")
                                .param("rare", "SSR")
                                .param("minHp", "500")
                                .param("minAtk", "700")
                                .param("maxHp", "10000")
                                .param("maxAtk", "7000")
                                .param("buddy1Grouping", "effect")
                                .param("buddy2Grouping", "effect2")
                                .param("buddy3Grouping", "effect3")
                                .param("arrayIndex", "0"))
                                .andExpect(status().isFound())
                                .andExpect(view().name("redirect:organize"));

        }
}
