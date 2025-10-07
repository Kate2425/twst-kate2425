package com.example.twst.form;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import com.example.twst.domain.model.BuddyGroupingEnum;

import java.math.BigDecimal;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Component
@Data
public class CalculateForm implements Serializable {

    /**
     * レア度
     */
    String rare;

    /**
     * 初期HP
     */
    BigDecimal minHp;

    /**
     * 初期ATK
     */
    BigDecimal minAtk;

    /**
     * 最大HP
     */
    BigDecimal maxHp;

    /**
     * 最大ATK
     */
    BigDecimal maxAtk;

    /**
     * バディ１区分
     */
    BuddyGroupingEnum buddy1Grouping;

    /** 
     * バディ２区分
     */
    BuddyGroupingEnum buddy2Grouping;

    /**
     * バディ３区分
     */
    BuddyGroupingEnum buddy3Grouping;

    /**
     * インデックス
     */
    int arrayIndex;

    /**
     * 計算用レベル
     */
    @Size(min = 0, max = 3)
    BigDecimal level;

}
