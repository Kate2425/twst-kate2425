package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum MagicGroupingEnum {
    HYPHEN(0, "-", "-", "-", 0, "-"),
    FIRE_SHOT(1, "FIRE", "ファイアショット", "火属性ダメージ（弱）", 1, "弱"),
    WATER_SHOT(2, "WATER", "ウォーターショット", "水属性ダメージ（弱）", 1, "弱"),
    LEAF_SHOT(3, "LEAF", "リーフショット", "木属性ダメージ（弱）", 1, "弱"),
    VOID_SHOT(4, "VOID", "ボイドショット", "無属性ダメージ（弱）", 1, "弱"),
    FIRE_SHOT2(5, "FIRE", "ファイアショット[II]", "2連撃の火属性ダメージ（弱）", 2, "弱"),
    WATER_SHOT2(6, "WATER", "ウォーターショット[II]", "2連撃の水属性ダメージ（弱）", 2, "弱"),
    LEAF_SHOT2(7, "LEAF", "リーフショット[II]", "2連撃の木属性ダメージ（弱）", 2, "弱"),
    VOID_SHOT2(8, "VOID", "ボイドショット[II]", "2連撃の無属性ダメージ（弱）", 2, "弱"),
    FLAME_BLAST(9, "FIRE", "フレイムブラスト", "火属性ダメージ（強）", 1, "強"),
    AQUA_WAVE(10, "WATER", "アクアウェーブ", "水属性ダメージ（強）", 1, "強"),
    FOREST_STRIKE(11, "LEAF", "フォレストストライク", "木属性ダメージ（強）", 1, "強"),
    ZERO_RAY(12, "VOID", "ゼロレイ", "無属性ダメージ（強）", 1, "強"),
    FLAME_BLAST2(13, "FIRE", "フレイムブラスト[II]", "2連撃の火属性ダメージ（強）", 2, "強"),
    AQUA_WAVE2(14, "WATER", "アクアウェーブ[II]", "2連撃の水属性ダメージ（強）", 2, "強"),
    FOREST_STRIKE2(15, "LEAF", "フォレストストライク[II]", "2連撃の木属性ダメージ（強）", 2, "強"),
    ZERO_RAY2(16, "VOID", "ゼロレイ[II]", "2連撃の無属性ダメージ（強）", 2, "強"),
    VOID_SHOT3(17, "VOID", "ボイドショット[III]", "3連撃の無属性ダメージ（弱）", 3, "弱"),
    RULER_OF_ALL_EVIL(18, "VOID", "全ての悪の支配者", "無属性ダメージ（強）", 1, "強"),
    RULER_OF_ALL_EVIL2(19, "VOID", "全ての悪の支配者[II]", "2連撃の無属性ダメージ（弱）", 2, "弱"),
    RULER_OF_ALL_EVIL3(20, "VOID", "全ての悪の支配者[III]", "3連撃の無属性ダメージ（弱）", 3, "弱"),
    LONELINESS_OF_THE_WITCH2(21, "LEAF", "魔女の孤独[II]", "2連撃の木属性ダメージ（強）", 2, "強"),
    PLOT_FILLED_WITH_MALICE(22, "FIRE", "悪意に満ちたたくらみ", "火属性ダメージ（強）", 1, "強"),
    PLOT_FILLED_WITH_MALICE2(23, "FIRE", "悪意に満ちたたくらみ[II]", "2連撃の火属性ダメージ（強）", 2, "強"),
    PLOT_FILLED_WITH_MALICE3(24, "FIRE", "悪意に満ちたたくらみ[III]", "3連撃の火属性ダメージ（弱）", 3, "弱"),
    GRUDGE_OF_THE_KING(25, "LEAF", "王の怨嗟", "木属性ダメージ（強）", 1, "強"),
    STOP_BREATH_FREEZE_BLOOD(26, "LEAF", "息よ止まれ。血よ凍れ", "木属性ダメージ（強）", 1, "強"),
    STOP_BREATH_FREEZE_BLOOD2(27, "LEAF", "息よ止まれ。血よ凍れ[II]", "2連撃の木属性ダメージ（弱）", 2, "弱"),
    STOP_BREATH_FREEZE_BLOOD3(28, "LEAF", "息よ止まれ。血よ凍れ[III]", "3連撃の木属性ダメージ（弱）", 1, "弱"),
    PASSION_OF_THE_QUEEN2(29, "WATER", "女王の執念[II]", "2連撃の水属性ダメージ（強）", 2, "強"),
    OVERWHELMING_POWER2(30, "WATER", "絶大なパワー[II]", "3連撃の水属性ダメージ（強）", 3, "強"),
    RULE_OF_THE_MAGICIAN(31, "FIRE", "魔術師の支配", "火属性ダメージ（強）", 1, "強");

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
    private final String viewName;

    /**
     * 効果
     */
    private final String effect;

    /**
     * 連撃
     */
    private final int consecutive;

    /**
     * 強さ
     */
    private final String strength;

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
     * viewNameを取得する.
     * 
     * @param magicGrouping
     * @return viewName
     */
    public static String getViewName(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum.getViewName();
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

    /*
     * consecutiveを取得する.
     */
    public static int getConsecutive(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum.getConsecutive();
            }
        }
        return 0;
    }

    /**
     * 魔法属性からenumのListを取得する.
     * 
     * @param buddyGrouping
     * @return List<BuddyGroupingEnum>
     */
    public static List<MagicGroupingEnum> getValue(String magicType) {
        List<MagicGroupingEnum> valueList = new ArrayList<>();
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() < 17) {
                if (magicGroupingEnum.getMagicType().equals(magicType)) {
                    valueList.add(magicGroupingEnum);
                }
            }
        }
        return valueList;
    }

    /**
     * viewNameからMagicGroupingEnumを取得する.
     * 
     * @param viewName
     * @return MagicGroupingEnum
     */
    public static MagicGroupingEnum getValueOfViewName(String viewName) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getViewName().equals(viewName)) {
                return magicGroupingEnum;
            }
        }
        return null;
    }

    /**
     * magicGroupingからMagicGroupingEnumを取得する.
     * 
     * @param magicGrouping
     * @return MagicGroupingEnum
     */
    public static MagicGroupingEnum getValueOfMagicGrouping(int magicGrouping) {
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (magicGroupingEnum.getMagicGrouping() == magicGrouping) {
                return magicGroupingEnum;
            }
        }
        return null;
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
     * 汎用のMagicGroupingEnumを取得する.
     * @return valueList
     */
    public static List<MagicGroupingEnum> getAllValue() {
        List<MagicGroupingEnum> valueList = new ArrayList<>();
        for (MagicGroupingEnum magicGroupingEnum : MagicGroupingEnum.values()) {
            if (((magicGroupingEnum.getMagicGrouping() > 0)) && (magicGroupingEnum.getMagicGrouping() < 17)) {
                valueList.add(magicGroupingEnum);
            }
        }
        return valueList;
    }

    /**
     * constructor
     * 
     * @param magicGrouping
     * @param magicType
     * @param viewName
     * @param effect
     */
    private MagicGroupingEnum(int magicGrouping, String magicType, String viewName, String effect, int consecutive,
            String strength) {
        this.magicGrouping = magicGrouping;
        this.magicType = magicType;
        this.viewName = viewName;
        this.effect = effect;
        this.consecutive = consecutive;
        this.strength = strength;
    }

}
