package com.example.twst.domain.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BattleCard {

    /**
     * カードID
     */
    private String id;

    /**
     * キャラクター名
     */
    private CharacterEnum name;

    /**
     * マジック
     */
    private MagicGroupingEnum magic;

    /**
     * デュオ
     */
    private CharacterEnum duo;

    /**
     *ATK
     */
    private BigDecimal atk;

    /**
     * マジックバフ区分
     */
    private BuffDebuffGroupingEnum magicBuffdebuffGrouping;

    /**
     * 素のATK
     */
    private BigDecimal originAtk;

    /**
     * 素のHP
     */
    private BigDecimal originHp;

}
