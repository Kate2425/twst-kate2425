package com.example.twst.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.twst.domain.model.BuffDebuffGroupingEnum;

@SpringBootTest
public class BuffDebuffServiceTest {

    @Autowired
    BuffDebuffService target;

    @Test
    @DisplayName("getBuffDebuffPower_ATK_極小")
    void getBuffDebuffPower_01() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.ATK_DOWN_MINIMUM_ENEMY_1T, "VOID", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.075))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ATK_小")
    void getBuffDebuffPower_02() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T, "FIRE", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.15))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ATK_中")
    void getBuffDebuffPower_03() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.ATK_DOWN_MEDIUM_ENEMY_1T, "WATER", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.275))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ATK_大")
    void getBuffDebuffPower_04() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.ATK_UP_LARGE_SELF_1T, "LEAF", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.5))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_属性ダメージ_小")
    void getBuffDebuffPower_05() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.FIRE_DAMAGE_DOWN_SMALL_ENEMY_1T,
                "火属性ダメージ（弱）", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.045))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_属性ダメージ_中")
    void getBuffDebuffPower_06() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.WATER_DAMAGE_UP_MEDIUM_FRIEND_1T,
                "2連撃の水属性ダメージ（弱）", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.0825))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_属性ダメージ_大")
    void getBuffDebuffPower_07() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.LEAF_DAMAGE_UP_LARGE_FRIEND_1T,
                "木属性ダメージ（強）", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.15))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_属性ダメージ_極大")
    void getBuffDebuffPower_08() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.FIRE_DAMAGE_UP_MAXIMUM_SELF_1T,
                "2連撃の火属性ダメージ（強）", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.2))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ダメージ_極小")
    void getBuffDebuffPower_09() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.DAMAGE_DOWN_MINIMUM_ENEMY_1T, "FIRE", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.025))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ダメージ_小")
    void getBuffDebuffPower_10() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.DAMAGE_UP_SMALL_SELF_1T, "WATER", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.0375))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ダメージ_中")
    void getBuffDebuffPower_11() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.DAMAGE_DOWN_MEDIUM_ENEMY_1T, "LEAF", 5);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.06875))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_ダメージ_大")
    void getBuffDebuffPower_12() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.DAMAGE_UP_LARGE_SELF_1T, "VOID", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.125))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_属性ダメージ_属性不一致")
    void getBuffDebuffPower_13() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.LEAF_DAMAGE_UP_LARGE_FRIEND_1T,
                "2連撃の火属性ダメージ（強）", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("getBuffDebuffPower_被ダメージ_小")
    void getBuffDebuffPower_14() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.RECEIVE_DAMAGE_UP_SMALL_ENEMY_1T, "VOID",
                10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(0.05))));
    }

    @Test
    @DisplayName("getBuffDebuffPower_error")
    void getBuffDebuffPower_00() throws Exception {
        // Act
        BigDecimal result = target.getBuffDebuffPower(BuffDebuffGroupingEnum.HYPHEN, "VOID", 10);

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(-1))));
    }

    @Test
    @DisplayName("recoverHp_HP回復_極小")
    void recoverHp_01() throws Exception {
        // Act
        BigDecimal result = target.recoverHp(BuffDebuffGroupingEnum.HP_RECOVER_MINIMUM, 5, BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(550))));
    }

    @Test
    @DisplayName("recoverHp_HP回復_小")
    void recoverHp_02() throws Exception {
        // Act
        BigDecimal result = target.recoverHp(BuffDebuffGroupingEnum.HP_RECOVER_SMALL, 5, BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(1000))));
    }

    @Test
    @DisplayName("recoverHp_HP回復_中")
    void recoverHp_03() throws Exception {
        // Act
        BigDecimal result = target.recoverHp(BuffDebuffGroupingEnum.HP_RECOVER_MEDIUM, 5, BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(1500))));
    }

    @Test
    @DisplayName("hpContinuousRecover_HP継続回復_極小")
    void hpContinuousRecover_01() throws Exception {
        // Act
        BigDecimal result = target.hpContinuousRecover(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_MINIMUM_SELF_3T, 5,
                BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(75))));
    }

    @Test
    @DisplayName("hpContinuousRecover_HP継続回復_小")
    void hpContinuousRecover_02() throws Exception {
        // Act
        BigDecimal result = target.hpContinuousRecover(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_SMALL_FRIEND_3T, 5,
                BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(125))));
    }

    @Test
    @DisplayName("hpContinuousRecover_HP継続回復_中")
    void hpContinuousRecover_03() throws Exception {
        // Act
        BigDecimal result = target.hpContinuousRecover(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_MEDIUM_SELF_3T, 5,
                BigDecimal.valueOf(1000));

        // Assert
        assertThat(0, is(result.compareTo(BigDecimal.valueOf(225))));
    }

    @Test
    @DisplayName("isEvade_回避_極小")
    void isEvade_01() throws Exception {
        // Act
        boolean result = target.isEvade(BuffDebuffGroupingEnum.EVADE_MINIMUM_SELF_1T, BigDecimal.valueOf(0.376), 5);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("isEvade_回避_小")
    void isEvade_02() throws Exception {
        // Act
        boolean result = target.isEvade(BuffDebuffGroupingEnum.EVADE_SMALL_SELF_1T, BigDecimal.valueOf(0.75), 5);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("isEvade_回避_中")
    void isEvade_03() throws Exception {
        // Act
        boolean result = target.isEvade(BuffDebuffGroupingEnum.EVADE_MEDIUM_SELF_1T, BigDecimal.valueOf(1.374), 5);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("isCurse_呪い_小")
    void isCurse_01() throws Exception {
        //Act
        boolean result = target.isCurse(BuffDebuffGroupingEnum.CURSE_SMALL_ENEMY_2T, BigDecimal.valueOf(0.15), 5);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("isCurse_呪い_中")
    void isCurse_02() throws Exception {
        //Act
        boolean result = target.isCurse(BuffDebuffGroupingEnum.CURSE_MEDIUM_ENEMY_2T, BigDecimal.valueOf(0.224), 5);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("isCurse_呪い_大")
    void isCurse_03() throws Exception {
        //Act
        boolean result = target.isCurse(BuffDebuffGroupingEnum.CURSE_LARGE_ENEMY_2T, BigDecimal.valueOf(0.525), 5);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("isCurse_呪い_極大")
    void isCurse_04() throws Exception {
        //Act
        boolean result = target.isCurse(BuffDebuffGroupingEnum.CURSE_MAXIMUM_ENEMY_ALL_3T, BigDecimal.valueOf(0.5), 5);

        // Assert
        assertThat(result, is(false));
    }

}
