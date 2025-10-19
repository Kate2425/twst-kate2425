package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum CharacterEnum {
    HYPHEN("Hyphen", "-", "-"),
    RIDDLE("Heartslabyul", "Riddle", "リドル"),
    ACE("Heartslabyul", "Ace", "エース"),
    DEUCE("Heartslabyul", "Deuce", "デュース"),
    CATER("Heartslabyul", "Cater", "ケイト"),
    TREY("Heartslabyul", "Trey", "トレイ"),
    LEONA("Savanaclaw", "Leona", "レオナ"),
    JACK("Savanaclaw", "Jack", "ジャック"),
    RUGGIE("Savanaclaw", "Ruggie", "ラギー"),
    AZUL("Octavinelle", "Azul", "アズール"),
    JADE("Octavinelle", "Jade", "ジェイド"),
    FLOYD("Octavinelle", "Floyd", "フロイド"),
    KALIM("Scarabia", "Kalim", "カリム"),
    JAMIL("Scarabia", "Jamil", "ジャミル"),
    VIL("Pomefiore", "Vil", "ヴィル"),
    EPEL("Pomefiore", "Epel", "エペル"),
    ROOK("Pomefiore", "Rook", "ルーク"),
    IDEA("Ignihyde", "Idea", "イデア"),
    ORTHO("Ignihyde", "Ortho", "オルト"),
    MALLEUS("Diasomnia", "Malleus", "マレウス"),
    SILVER("Diasomnia", "Silver", "シルバー"),
    SEBEK("Diasomnia", "Sebek", "セベク"),
    LILIA("Diasomnia", "Lilia", "リリア"),
    GRIM("Nrc", "Grim", "グリム"),
    CROWLEY("Nrc", "Crowley", "クロウリー"),
    CREWEL("Nrc", "Crewel", "クルーウェル"),
    TREIN("Nrc", "Trein", "トレイン"),
    VARGAS("Nrc", "Vargas", "バルガス"),
    ROLLO("Special", "Rollo", "ロロ"),
    FELLOW("Special", "Fellow", "フェロー"),
    ENEMY("Enemy", "Enemy", "敵");

    /**
     * 寮名
     */
    private final String dormitoryName;

    /**
     * キャラクター名
     */
    private final String characterName;

    /**
     * 表示名
     */
    private final String viewName;

    /**
     * dormitoryNameを取得する.
     * 
     * @return dormitoryName
     */
    public String getDormitoryName() {
        return dormitoryName;
    }

    /**
     * dormitoryNameを取得する.
     * 
     * @param characterName
     * @return dormitoryName
     */
    public String getDormitoryName(String characterName) {
        String tempDormitoryName = "";
        for (CharacterEnum character : CharacterEnum.values()) {
            if (character.getCharacterName().equals(characterName)) {
                tempDormitoryName = character.getDormitoryName();
            }
        }
        return tempDormitoryName;
    }

    /**
     * characterNameを取得する.
     * 
     * @param viewName
     * @return characterName
     */
    public String getCharacterName(String viewName) {
        String tempCharacterName = "";
        for (CharacterEnum character : CharacterEnum.values()) {
            if (character.getViewName().equals(viewName)) {
                tempCharacterName = character.getCharacterName();
            }
        }
        return tempCharacterName;
    }

    /**
     * ViewNameを取得する.
     * 
     * @param characterName
     * @return viewName
     */
    public String getViewName(String characterName) {
        String tempViewName = "";
        for (CharacterEnum character : CharacterEnum.values()) {
            if (character.getCharacterName().equals(characterName)) {
                tempViewName = character.getViewName();
            }
        }
        return tempViewName;
    }

    /**
     * dormitoryNameからenumのListを取得する.
     * 
     * @param dormitoryName
     * @return List<Character>
     */
    public static List<CharacterEnum> getValueListOfDormitoryName(String dormitoryName) {
        List<CharacterEnum> valueList = new ArrayList<>();
        for (CharacterEnum character : CharacterEnum.values()) {
            if (character.getDormitoryName().equals(dormitoryName)) {
                valueList.add(character);
            }
        }
        return valueList;
    }

    /**
     * characterNameからenumを取得する.
     * @param characterName
     * @return CharacterEnum
     */
    public static CharacterEnum getValueOfCharacterName(String characterName) {
        CharacterEnum characterEnum = HYPHEN;
        for (CharacterEnum values : CharacterEnum.values()) {
            if (values.getCharacterName().equals(characterName)) {
                characterEnum = values;
            }
        }
        return characterEnum;
    }

    /**
     * constructor
     * 
     * @param dormitoryName
     * @param characterName
     * @param viewName
     */
    private CharacterEnum(String dormitoryName, String characterName, String viewName) {
        this.dormitoryName = dormitoryName;
        this.characterName = characterName;
        this.viewName = viewName;
    }
}
