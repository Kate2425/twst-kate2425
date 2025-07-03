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
     * バディ１効果
     */
    private String buddy1Effect;

    /**
     * バディ２
     */
    private CharacterEnum buddy2;

    /**
     * バディ２効果
     */
    private String buddy2Effect;

    /**
     * バディ３
     */
    private CharacterEnum buddy3;

    /**
     * バディ３効果
     */
    private String buddy3Effect;

    /**
     * マジック１属性
     */
    private String magic1Type;

    /**
     * マジック１名称
     */
    private String magic1Name;

    /**
     * マジック１効果
     */
    private String magic1Effect;

    /**
     * マジック２属性
     */
    private String magic2Type;

    /**
     * マジック２名称
     */
    private String magic2Name;

    /**
     * マジック２効果
     */
    private String magic2Effect;

    /**
     * マジック３属性
     */
    private String magic3Type;

    /*
     * マジック３名称
     */
    private String magic3Name;

    /**
     * マジック３効果
     */
    private String magic3Effect;

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
    private String magic1BuffdebuffGrouping;

    /**
     * マジック２バフ区分
     */
    private String magic2BuffdebuffGrouping;

    /**
     * マジック３バフ区分
     */
    private String magic3BuffdebuffGrouping;

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
