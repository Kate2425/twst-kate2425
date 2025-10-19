package com.example.twst.session;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.example.twst.domain.model.Card;
import com.example.twst.form.OrganizeForm;

import lombok.Data;

@Data
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrganizeSession implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Card
     */
    private Card card;

    /**
     * cardArray
     */
    private Card[] cardArray;

    /**
     * arrayIndex
     */
    private int arrayIndex;

    /**
     * organizeForm
     */
    private OrganizeForm organizeForm;

    /**
     * levelArray
     */
    private BigDecimal[] levelArray;

    /**
     * バディボーナス反映後HP
     */
    private BigDecimal[] hpArray;

    /**
     * バディボーナス反映後ATK
     */
    private BigDecimal[] atkArray;

    /**
     * 推定HP
     */
    private BigDecimal[] tempHpArray;

    /**
     * 推定ATK
     */
    private BigDecimal[] tempAtkArray;

    /**
     * tempCardArray
     */
    private Card[] tempCardArray;

    /*
     * 推定合計HP
     */
    private BigDecimal tempTotalHp;

    /**
     * バディボーナス反映後推定合計HP
     */
    private BigDecimal tempReflectedHp;

}
