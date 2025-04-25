package com.example.twst.form;

import java.io.Serializable;
import java.math.BigDecimal;

import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.TableEnum;

import lombok.Data;

@Data
public class CardForm implements Serializable {
    /**
     * テーブル名
     */
    private TableEnum tableName;

    /**
     * カードID
     */
    private String id;

    /**
     * 項番
     */
    private int num;

    /**
     * キャラクター名
     */
    private CharacterEnum name;

    /**
     * レア度
     */
    private String rare;

    /**
     * タイプ
     */
    private String type;

    /**
     * バディ１
     */
    private CharacterEnum buddy1;

    /**
     * バディ２
     */
    private CharacterEnum buddy2;

    /**
     * バディ３
     */
    private CharacterEnum buddy3;

    /**
     * マジック１属性
     */
    private String magic1Type;

    /**
     * マジック２属性
     */
    private String magic2Type;

    /**
     * マジック３属性
     */
    private String magic3Type;

    /**
     * デュオ
     */
    private CharacterEnum duo;

    /**
     * 初期HP
     */
    private BigDecimal minHp;

    /**
     * 初期ATK
     */
    private BigDecimal minAtk;

    /**
     * 最大HP
     */
    private BigDecimal maxHp;

    /**
     * 最大ATK
     */
    private BigDecimal maxAtk;

    /**
     * 有効フラグ
     */
    private boolean validFlg;

    /**
     * バディ１効果
     */
    private String buddy1Effect;

    /**
     * バディ２効果
     */
    private String buddy2Effect;

    /**
     * バディ３効果
     */
    private String buddy3Effect;

    /**
     * マジック１名称
     */
    private String magic1Name;

    /**
     * マジック２名称
     */
    private String magic2Name;

    /**
     * マジック３名称
     */
    private String magic3Name;

    /**
     * マジック１効果
     */
    private String magic1Effect;

    /**
     * マジック２効果
     */
    private String magic2Effect;

    /**
     * マジック３効果
     */
    private String magic3Effect;

    /**
     * マジック１区分
     */
    private String magic1Grouping;

    /**
     * マジック２区分
     */
    private String magic2Grouping;

    /**
     * マジック３区分
     */
    private String magic3Grouping;

    /**
     * バディ１区分
     */
    private String buddy1Grouping;

    /**
     * バディ２区分
     */
    private String buddy2Grouping;

    /**
     * バディ３区分
     */
    private String buddy3Grouping;

}
