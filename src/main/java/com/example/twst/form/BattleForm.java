package com.example.twst.form;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class BattleForm implements Serializable {

    /**
     * 試験
     */
    private String exam;

    /**
     * 難易度
     */
    private String difficulty;

    /**
     * 敵の魔法属性
     */
    private String enemyMagicType;

    /*
     * バディボーナス反映後合計HP
     */
    BigDecimal reflectedHp;

    /**
     * 敵のバフデバフ
     */
    String[] enemyBuffDebuffChecks;
}
