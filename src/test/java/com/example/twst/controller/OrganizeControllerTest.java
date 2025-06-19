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
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.twst.TestConfig;

import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.service.CardService;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureMockMvc
public class OrganizeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CardService service;

    @InjectMocks
    private OrganizeController target;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(target)
                .alwaysDo(log()).build();
    }

    @Test
    @DisplayName("input_初期表示")
    void input_01() throws Exception {

        when(service.sum(any())).thenReturn(BigDecimal.ZERO);
        when(service.tempSum(any())).thenReturn(BigDecimal.ZERO);

        mockMvc.perform(get("/organize"))
                .andExpect(status().isOk())
                .andExpect(view().name("organize.html"))
                .andExpect(model().attribute("duoCount", is(0)))
                .andExpect(model().attribute("buddyCount", is(0)))
                .andExpect(model().attribute("totalHp", is(BigDecimal.ZERO)))
                .andExpect(model().attribute("reflectedHp", is(BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("input_cardあり")
    void input_02() throws Exception {
        Map<String, List<String>> duoMap = new HashMap<>();
        List<String> countList = new ArrayList<>();
        countList.add("2");
        List<String> duoList = new ArrayList<>();
        duoList.add("Floyd");
        duoMap.put("Jade", duoList);
        duoList.add("Jade");
        duoMap.put("Floyd", duoList);
        duoMap.put("duoCount", countList);
        when(service.duoCount(any())).thenReturn(duoMap);

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

        when(service.sum(any())).thenReturn(BigDecimal.valueOf(20000));

        when(service.tempSum(any())).thenReturn(BigDecimal.ZERO);

        mockMvc.perform(get("/organize"))
                .andExpect(status().isOk())
                .andExpect(view().name("organize.html"))
                .andExpect(model().attribute("totalHp", is(BigDecimal.valueOf(20000))))
                .andExpect(model().attribute("reflectedHp", is(BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("conform")
    void conform() throws Exception {
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.DORMITORY_CLOTHING);
        cardForm.setId("寮服");
        cardForm.setName(CharacterEnum.JADE);
        cardForm.setRare("SSR");
        cardForm.setType("DEFENCE");
        cardForm.setBuddy1(CharacterEnum.TREY);
        cardForm.setBuddy2(CharacterEnum.AZUL);
        cardForm.setBuddy3(CharacterEnum.FLOYD);
        cardForm.setDuo(CharacterEnum.FLOYD);
        cardForm.setMinHp(BigDecimal.valueOf(3000));
        cardForm.setMinAtk(BigDecimal.valueOf(1000));
        cardForm.setMaxHp(BigDecimal.valueOf(10000));
        cardForm.setMaxAtk(BigDecimal.valueOf(5000));
        cardForm.setBuddy1Effect("ATK UP(小)");
        cardForm.setBuddy2Effect("HP UP(小)");
        cardForm.setBuddy3Effect("HP UP(中)");
        cardForm.setMagic1Name("アクアウェーブ");
        cardForm.setMagic2Name("フレイムブラスト[II]");
        cardForm.setMagic3Name("リーフショット[II]");
        cardForm.setMagic1Effect("水属性ダメージ（強）");
        cardForm.setMagic2Effect("2連撃の火属性ダメージ（強）");
        cardForm.setMagic3Effect("2連撃の木属性ダメージ（弱）");
        cardForm.setMagic1Type("WATER");
        cardForm.setMagic2Type("FIRE");
        cardForm.setMagic3Type("LEAF");

        mockMvc.perform(post("/organize")
                .param("tableName", "DORMITORY_CLOTHING")
                .param("id", "寮服")
                .param("num", "11")
                .param("name", "JADE")
                .param("rare", "SSR")
                .param("type", "DEFENCE")
                .param("buddy1", "TREY")
                .param("buddy1Effect", "ATK UP(小)")
                .param("buddy2", "AZUL")
                .param("buddy2Effect", "HP UP(小)")
                .param("buddy3", "FLOYD")
                .param("buddy3Effect", "HP UP(中)")
                .param("magic1Type", "WATER")
                .param("magic1Name", "アクアウェーブ")
                .param("magic1Effect", "水属性ダメージ（強）")
                .param("magic2Type", "FIRE")
                .param("magic2Name", "フレイムブラスト[II]")
                .param("magic2Effect", "2連撃の火属性ダメージ（強）")
                .param("magic3Type", "LEAF")
                .param("magic3Name", "リーフショット[II]")
                .param("magic3Effect", "2連撃の木属性ダメージ（弱）")
                .param("duo", "FLOYD")
                .param("minHp", "3000")
                .param("minAtk", "1000")
                .param("maxHp", "10000")
                .param("maxAtk", "5000")
                .param("arrayIndex", "0"))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:organize"));
    }
}
