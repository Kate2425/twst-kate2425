package com.example.twst.domain.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class Card implements Cloneable {
    /**
     * テーブル名
     */
    private TableEnum tableName;

    /**
     * カードID
     */
    private String id;

    /*
     * 項番
     */
    private int num;

    /**
     * キャラクター名
     */
    private CharacterEnum name;

    /**
     * 衣装
     */
    private String clothingName;

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
     * バディ１区分
     */
    private BuddyGroupingEnum buddy1Grouping;

    /**
     * バディ２
     */
    private CharacterEnum buddy2;

    /**
     * バディ２区分
     */
    private BuddyGroupingEnum buddy2Grouping;

    /**
     * バディ３
     */
    private CharacterEnum buddy3;

    /**
     * バディ３区分
     */
    private BuddyGroupingEnum buddy3Grouping;

    /**
     * マジック１
     */
    private MagicGroupingEnum magic1;

    /**
     * マジック２
     */
    private MagicGroupingEnum magic2;

    /**
     * マジック３
     */
    private MagicGroupingEnum magic3;

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
     * マジック１バフ区分
     */
    private BuffDebuffGroupingEnum magic1BuffdebuffGrouping;

    /**
     * マジック２バフ区分
     */
    private BuffDebuffGroupingEnum magic2BuffdebuffGrouping;

    /**
     * マジック３バフ区分
     */
    private BuffDebuffGroupingEnum magic3BuffdebuffGrouping;

    /**
     * バディボーナス後HP
     */
    private BigDecimal reflectedBonusHp;

    /*
     * バディボーナス後ATK
     */
    private BigDecimal reflectedBonusAtk;

    /**
     * 登録者
     */
    private String registUsr;

    /**
     * 登録日時
     */
    private Date registDate;

    /**
     * 有効フラグ
     */
    private boolean validFlg;

    /**
     * Cardをクローンする.
     * 
     * @return Card
     */
    @Override
    public Card clone() throws CloneNotSupportedException {
        try {
            return (Card) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}
