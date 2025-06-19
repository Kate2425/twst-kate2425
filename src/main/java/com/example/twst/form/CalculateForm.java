package com.example.twst.form;

import java.io.Serializable;
import org.springframework.stereotype.Component;
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
     * バディ１効果
     */
    String buddy1Effect;

    /**
     * バディ２効果
     */
    String buddy2Effect;

    /**
     * バディ３効果
     */
    String buddy3Effect;

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
