package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.example.twst.NameInterface;

public enum CardEnum implements NameInterface {

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
    private final String characterName;

    /**
     * 表示名
     */
    private final String viewName;

    public String getFormName() {
        return formName;
    }

    /**
     * characterNameを取得する.
     * 
     * @return characterName
     */
    @Override
    public String getCharacterName() {
        return characterName;
    }

    /**
     * viewNameを取得する.
     * 
     * @return viewName
     */
    @Override
    public String getViewName() {
        return viewName;
    }

    /**
     * enumのListを取得する.
     * 
     * @param formName
     * @return List<CardEnum>
     */
    public static List<CardEnum> getValue(String formName) {
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
    private CardEnum(String formName, String characterName, String viewName) {
        this.formName = formName;
        this.characterName = characterName;
        this.viewName = viewName;
    }
}
