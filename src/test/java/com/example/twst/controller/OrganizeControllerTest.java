package com.example.twst.controller;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.session.OrganizeSession;

@SpringBootTest
@Transactional
@WebAppConfiguration

public class OrganizeControllerTest {

        @Autowired
        OrganizeSession organizeSession;

        @Autowired
        WebApplicationContext webApplicationContext;

        MockMvc mockMvc;

        MockHttpSession mockSession = new MockHttpSession();

        @BeforeEach
        void setup() {
                mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                                .alwaysDo(log()).build();
        }

        @Test
        @DisplayName("初期表示")
        void input_01() throws Exception {

                mockMvc.perform(get("/organize"))
                                .andExpect(status().isOk())
                                .andExpect(model().attribute("totalHp", is(BigDecimal.ZERO)))
                                .andExpect(model().attribute("buddyCount", is(0)))
                                .andExpect(model().attribute("duoCount", is(0)))
                                .andExpect(model().attribute("reflectedHp", is(BigDecimal.ZERO)))
                                .andExpect(model().attribute("arrayIndex", is(0)))
                                .andExpect(model().attribute("cardArray",
                                                is(arrayContaining(null, null, null, null, null))))
                                .andExpect(model().attribute("magic", CardEnum.getValueListOfFormName("magic")))
                                .andExpect(model().attribute("buffDebuff", BuffDebuffGroupingEnum.getTypeList()))
                                .andExpect(view().name("organize.html"));
        }

        @Test
        @DisplayName("post_input_sessionあり")
        void post_input_01() throws Exception {

                MvcResult mvcResult = mockMvc.perform(post("/organize")
                                .param("tableName", "DORMITORY_CLOTHING")
                                .param("id", "dor_jade")
                                .param("num", "11")
                                .param("name", "JADE")
                                .param("clothingName", "dormitory_clothing")
                                .param("rare", "SSR")
                                .param("type", "DEFENCE")
                                .param("buddy1", "TREY")
                                .param("buddy1Grouping", "ATK_UP_SMALL")
                                .param("buddy2", "AZUL")
                                .param("buddy2Grouping", "HP_UP_SMALL")
                                .param("buddy3", "FLOYD")
                                .param("buddy3Grouping", "HP_UP_MIDDLE")
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

                mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                mvcResult = mockMvc.perform(get("/organize")
                                .session(mockSession))
                                .andExpect(status().isOk())
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

                mvcResult = mockMvc.perform(post("/organize")
                                .session(mockSession)
                                .param("tableName", "DORMITORY_CLOTHING")
                                .param("id", "dor_jamil")
                                .param("num", "14")
                                .param("name", "JAMIL")
                                .param("clothingName", "dormitory_clothing")
                                .param("rare", "SSR")
                                .param("type", "ATTACK")
                                .param("buddy1", "ACE")
                                .param("buddy1Grouping", "ATK_UP_SMALL")
                                .param("buddy2", "KALIM")
                                .param("buddy2Grouping", "HP_UP_SMALL")
                                .param("buddy3", "LILIA")
                                .param("buddy3Grouping", "HP_UP_MIDDLE")
                                .param("magic1", "ZERO_RAY")
                                .param("magic2", "FLAME_BLAST2")
                                .param("magic3", "WATER_SHOT2")
                                .param("duo", "KALIM")
                                .param("minHp", "3000")
                                .param("minAtk", "1000")
                                .param("maxHp", "9000")
                                .param("maxAtk", "6000")
                                .param("magic1BuffdebuffGrouping", "HYPHEN")
                                .param("magic2BuffdebuffGrouping", "HYPHEN")
                                .param("magic3BuffdebuffGrouping", "HYPHEN")
                                .param("arrayIndex", "1"))
                                .andExpect(status().isFound())
                                .andExpect(view().name("redirect:organize"))
                                .andReturn();

                mockSession = (MockHttpSession) mvcResult.getRequest().getSession();

                mvcResult = mockMvc.perform(get("/organize")
                                .session(mockSession))
                                .andExpect(status().isOk())
                                .andExpect(view().name("organize.html"))
                                .andExpect(model().attribute("totalHp", is(BigDecimal.valueOf(19000))))
                                .andExpect(model().attribute("buddyCount", 0))
                                .andExpect(model().attribute("duoCount", 0))
                                .andExpect(model().attribute("arrayIndex", 0))
                                .andExpect(model().attribute("cardArray", is(arrayContaining(
                                                hasProperty("id", is("dor_jade")),
                                                hasProperty("id", is("dor_jamil")),
                                                null, null, null))))
                                .andExpect(model().attribute("reflectedHp", is(BigDecimal.valueOf(19000))))
                                .andExpect(view().name("organize.html"))
                                .andReturn();

                mockSession = (MockHttpSession) mvcResult.getRequest().getSession();
                organizeSession = (OrganizeSession) mockSession.getAttribute("scopedTarget.organizeSession");

                assertThat(organizeSession.getHpArray(),
                                is(arrayContaining(BigDecimal.valueOf(10000), BigDecimal.valueOf(9000), null, null,
                                                null)));
                assertThat(organizeSession.getLevelArray(),
                                is(arrayContaining(BigDecimal.valueOf(110), BigDecimal.valueOf(110), null, null,
                                                null)));
                assertThat(organizeSession.getTempAtkArray(),
                                is(arrayContaining(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                                                BigDecimal.ZERO, BigDecimal.ZERO)));
                assertThat(organizeSession.getTempHpArray(),
                                is(arrayContaining(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO,
                                                BigDecimal.ZERO, BigDecimal.ZERO)));
                assertThat(organizeSession.getTempCardArray(), is(arrayContaining(
                                hasProperty("id", is("dor_jade")),
                                hasProperty("id", is("dor_jamil")),
                                null, null, null)));
        }

        @Test
        @DisplayName("計算結果あり")
        void input_02() throws Exception {
                BigDecimal[] levelArray = new BigDecimal[5];
                levelArray[0] = BigDecimal.valueOf(100);
                BigDecimal[] hpArray = new BigDecimal[5];
                hpArray[0] = BigDecimal.valueOf(10000);
                BigDecimal[] tempHpArray = new BigDecimal[5];
                tempHpArray[0] = BigDecimal.valueOf(8000);
                BigDecimal[] tempAtkArray = new BigDecimal[5];
                tempAtkArray[0] = BigDecimal.valueOf(7000);
                organizeSession.setLevelArray(levelArray);
                organizeSession.setHpArray(hpArray);
                organizeSession.setTempHpArray(tempHpArray);
                organizeSession.setTempAtkArray(tempAtkArray);
                mockSession.setAttribute("organizeSession", organizeSession);

                mockMvc.perform(get("/organize")
                                .session(mockSession))
                                .andExpect(status().isOk())
                                .andExpect(view().name("organize.html"))
                                .andExpect(model().attribute("totalHp", is(BigDecimal.ZERO)))
                                .andExpect(model().attribute("reflectedHp", is(BigDecimal.ZERO)))
                                .andExpect(view().name("organize.html"));
        }
}
