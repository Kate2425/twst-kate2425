package com.example.twst.controller;

import org.mockito.Mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.twst.TestConfig;
import com.example.twst.domain.model.Card;

import com.example.twst.service.CardService;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc
public class CalculateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardService service;

    @InjectMocks
    private CalculateController target;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysDo(log()).build();
    }

    @Test
    @DisplayName("calculate")
    void calculate_01() throws Exception {
        Card[] tempCardArray = new Card[5];
        Card card = new Card();
        card.setRare("SSR");
        card.setMinHp(BigDecimal.valueOf(500));
        card.setMinAtk(BigDecimal.valueOf(700));
        card.setMaxHp(BigDecimal.valueOf(10000));
        card.setMaxAtk(BigDecimal.valueOf(7000));
        card.setBuddy1Effect("effect");
        card.setBuddy2Effect("effect2");
        card.setBuddy3Effect("effect3");
        tempCardArray[0] = card;

        when(service.calculate(any(), any())).thenReturn(tempCardArray);

        Map<String, List<String>> buddyMap = new HashMap<>();
        List<String> buddyList = new ArrayList<>();
        buddyList.add("Floyd");
        buddyMap.put("Jade", buddyList);
        buddyList.add("Jade");
        buddyMap.put("Floyd", buddyList);
        List<String> buddyCountList = new ArrayList<>();
        buddyCountList.add("2");
        buddyMap.put("buddyCount", buddyCountList);
        when(service.buddyCount(any(), anyBoolean())).thenReturn(buddyMap);

        when(service.sum(any())).thenReturn(BigDecimal.valueOf(15000));

        when(service.tempSum(any())).thenReturn(BigDecimal.valueOf(10000));

        mockMvc.perform(get("/calculate")
                .param("level", "80")
                .param("rare", "SSR")
                .param("minHp", "500")
                .param("minAtk", "700")
                .param("maxHp", "10000")
                .param("maxAtk", "7000")
                .param("buddy1Effect", "effect")
                .param("buddy2Effect", "effect2")
                .param("buddy3Effect", "effect3")
                .param("arrayIndex", "0"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:organize"));
    }
}
