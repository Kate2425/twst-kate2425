package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum CardEnum {

    SSR("rare", "SSR", "SSR"),
    SR("rare", "SR", "SR"),
    R("rare", "R", "R"),
    ATTACK("type", "ATTACK", "ATTACK"),
    BALANCE("type", "BALANCE", "BALANCE"),
    DEFENCE("type", "DEFENCE", "DEFENCE"),
    FIRE("magic", "FIRE", "火"),
    WATER("magic", "WATER", "水"),
    LEAF("magic", "LEAF", "木"),
    VOID("magic", "VOID", "無");

    /**
     *
     * フォーム名
     */
    private final String formName;

    /**
     * キャラクター名
     */
    private final String elementName;

    /**
     * 表示名
     */
    private final String viewName;

    /**
     * enumのListを取得する.
     * 
     * @param formName
     * @return List<CardEnum>
     */
    public static List<CardEnum> getValueListOfFormName(String formName) {
        List<CardEnum> valueList = new ArrayList<>();
        for (CardEnum cardEnum : CardEnum.values()) {
            if (cardEnum.getFormName().equals(formName)) {
                valueList.add(cardEnum);
            }
        }
        return valueList;
    }

    /**
     * constructor
     * 
     * @param characterName
     * @param viewName
     */
    private CardEnum(String formName, String elementName, String viewName) {
        this.formName = formName;
        this.elementName = elementName;
        this.viewName = viewName;
    }
}
