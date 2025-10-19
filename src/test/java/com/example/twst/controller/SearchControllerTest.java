package com.example.twst.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
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
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.TableEnum;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc
public class SearchControllerTest {

    @InjectMocks
    private SearchController target;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysDo(log()).build();
    }

    @Test
    @DisplayName("input")
    void input() throws Exception {
        mockMvc.perform(get("/search").param("arrayIndex", "0"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("rare", CardEnum.getValueListOfFormName("rare")))
                .andExpect(model().attribute("type", CardEnum.getValueListOfFormName("type")))
                .andExpect(model().attribute("magic", CardEnum.getValueListOfFormName("magic")))
                .andExpect(model().attribute("buffDebuff", BuffDebuffGroupingEnum.getTypeList()))
                .andExpect(model().attribute("heartslabyul", CharacterEnum.getValueListOfDormitoryName("Heartslabyul")))
                .andExpect(model().attribute("savanaclaw", CharacterEnum.getValueListOfDormitoryName("Savanaclaw")))
                .andExpect(model().attribute("octavinelle", CharacterEnum.getValueListOfDormitoryName("Octavinelle")))
                .andExpect(model().attribute("scarabia", CharacterEnum.getValueListOfDormitoryName("Scarabia")))
                .andExpect(model().attribute("pomefiore", CharacterEnum.getValueListOfDormitoryName("Pomefiore")))
                .andExpect(model().attribute("ignihyde", CharacterEnum.getValueListOfDormitoryName("Ignihyde")))
                .andExpect(model().attribute("diasomnia", CharacterEnum.getValueListOfDormitoryName("Diasomnia")))
                .andExpect(model().attribute("nrc", CharacterEnum.getValueListOfDormitoryName("Nrc")))
                .andExpect(model().attribute("special", CharacterEnum.getValueListOfDormitoryName("Special")))
                .andExpect(model().attribute("tableName", TableEnum.getViewNameList()))
                .andExpect(model().attribute("arrayIndex", 0))
                .andExpect(view().name("search.html"));
    }
}
