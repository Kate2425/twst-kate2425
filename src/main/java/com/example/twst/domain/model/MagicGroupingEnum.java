package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum MagicGroupingEnum {
    HYPHEN(0, "-", "-", "-"),
    FIRE_SHOT(1, "FIRE", "ファイアショット", "火属性ダメージ（弱）"),
    WATER_SHOT(2, "WATER", "ウォーターショット", "水属性ダメージ（弱）"),
    LEAF_SHOT(3, "LEAF", "リーフショット", "木属性ダメージ（弱）"),
    VOID_SHOT(4, "VOID", "ボイドショット", "無属性ダメージ（弱）"),
    FIRE_SHOT2(5, "FIRE", "ファイアショット[II]", "2連撃の火属性ダメージ（弱）"),
    WATER_SHOT2(6, "WATER", "ウォーターショット[II]", "2連撃の水属性ダメージ（弱）"),
    LEAF_SHOT2(7, "LEAF", "リーフショット[II]", "2連撃の木属性ダメージ（弱）"),
    VOID_SHOT2(8, "VOID", "ボイドショット[II]", "2連撃の無属性ダメージ（弱）"),
    FLAME_BLAST(9, "FIRE", "フレイムブラスト", "火属性ダメージ（強）"),
    AQUA_WAVE(10, "WATER", "アクアウェーブ", "水属性ダメージ（強）"),
    FOREST_STRIKE(11, "LEAF", "フォレストストライク", "木属性ダメージ（強）"),
    ZERO_RAY(12, "VOID", "ゼロレイ", "無属性ダメージ（強）"),
    FLAME_BLAST2(13, "FIRE", "フレイムブラスト[II]", "2連撃の火属性ダメージ（強）"),
    AQUA_WAVE2(14, "WATER", "アクアウェーブ[II]", "2連撃の水属性ダメージ（強）"),
    FOREST_STRIKE2(15, "LEAF", "フォレストストライク[II]", "2連撃の木属性ダメージ（強）"),
    ZERO_RAY2(16, "VOID", "ゼロレイ[II]", "2連撃の無属性ダメージ（強）"),
    VOID_SHOT3(17, "VOID", "ボイドショット[III]", "3連撃の無属性ダメージ（弱）"),
    LONELINESS_OF_THE_WITCH2(18, "LEAF", "魔女の孤独[II]", "2連撃の木属性ダメージ（強）"),
    RULER_OF_ALL_EVIL(19, "VOID", "全ての悪の支配者", "無属性ダメージ（強）"),
    RULER_OF_ALL_EVIL2(20, "VOID", "全ての悪の支配者[II]", "2連撃の無属性ダメージ（弱）"),
    RULER_OF_ALL_EVIL3(21, "VOID", "全ての悪の支配者[III]", "3連撃の無属性ダメージ（弱）");

    /**
     * マジック区分
     */
    private final int magicGrouping;

    /**
     * マジック属性
     */
    private final String magicType;

    /**
     * 名称
     */
    private final String name;

    /**
     * 効果
     */
    private final String effect;

    /**
     * magicTypeを取得する.
     * 
     * @param magicGrouping
     * @return magicType
     */
    public static String getMagicType(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum.getMagicType();
            }
        }
        return null;
    }

    /**
     * nameを取得する.
     * 
     * @param magicGrouping
     * @return name
     */
    public static String getName(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum.getName();
            }
        }
        return null;
    }

    /**
     * effectを取得する.
     * 
     * @param magicGrouping
     * @return effect
     */
    public static String getEffect(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum.getEffect();
            }
        }
        return null;
    }

    /**
     * enumのListを取得する.
     * 
     * @param buddyGrouping
     * @return List<BuddyGroupingEnum>
     */
    public static List<MagicGroupingEnum> getValue(int magicGrouping) {
        List<MagicGroupingEnum> valueList = new ArrayList<>();
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                valueList.add(magicGroupingEnum);
            }
        }
        return valueList;
    }

    /**
     * magicTypeの一覧を取得する.
     * 
     * @param magicTypeArray[]
     * @return typeArray
     */
    public static Integer[] getMagicTypeArray(String[] magicTypeArray) {
        List<Integer> typeList = new ArrayList<>();
        for (String type : magicTypeArray) {
            for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
                if (magicGroupingEnum.getMagicType().equals(type)) {
                    typeList.add(magicGroupingEnum.getMagicGrouping());
                }
            }
        }

        Integer[] typeArray = typeList.stream().toArray(Integer[]::new);
        return typeArray;
    }

    /**
     * constructor
     * 
     * @param magicGrouping
     * @param magicType
     * @param name
     * @param effect
     */
    private MagicGroupingEnum(int magicGrouping, String magicType, String name, String effect) {
        this.magicGrouping = magicGrouping;
        this.magicType = magicType;
        this.name = name;
        this.effect = effect;
    }

}
