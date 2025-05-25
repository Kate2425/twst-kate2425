package com.example.twst.session;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.example.twst.domain.model.Card;
import com.example.twst.form.OrganizeForm;

import lombok.Data;

@Data
@Component
@SessionScope
@SuppressWarnings("serial")
public class OrganizeSession implements Serializable {

    /*
     * Card
     */
    private Card card;

    /**
     * cardArray
     */
    private Card[] cardArray;

    /**
     * @param Card
     * @param index
     */
    public void setCard(Card card, int index) {
        this.cardArray[index] = card;
    }

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
     * hpArray
     */
    private BigDecimal[] hpArray;

    /**
     * atkArray
     */
    private BigDecimal[] atkArray;

    /**
     * tempHpArray
     */
    private BigDecimal[] tempHpArray;

    /**
     * tempAtkArray
     */
    private BigDecimal[] tempAtkArray;

    /**
     * tempCardArray
     */
    private Card[] tempCardArray;

}
