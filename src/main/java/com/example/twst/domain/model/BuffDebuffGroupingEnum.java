package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum BuffDebuffGroupingEnum {
    HYPHEN("0", "-", 0, "-"),
    ATK_DOWN_MINIMUM_ENEMY_1T("1", "ATK_DOWN", 1, "ATK DOWN（極小）（相手/1T）"),
    ATK_DOWN_SMALL_ENEMY_1T("2", "ATK_DOWN", 1, "ATK DOWN（小）（相手/1T）"),
    ATK_DOWN_MEDIUM_ENEMY_1T("3", "ATK_DOWN", 1, "ATK DOWN（中）（相手/1T）"),
    ATK_DOWN_LARGE_ENEMY_1T("4", "ATK_DOWN", 1, "ATK DOWN（大）（相手/1T）"),
    ATK_DOWN_SMALL_ENEMY_2T("5", "ATK_DOWN", 2, "ATK DOWN（小）（相手/2T）"),
    ATK_DOWN_SMALL_ENEMY_3T("6", "ATK_DOWN", 3, "ATK DOWN（小）（相手/3T）"),
    ATK_DOWN_MEDIUM_ENEMY_3T("7", "ATK_DOWN", 3, "ATK DOWN（中）（相手/3T）"),
    FIRE_DAMAGE_DOWN_SMALL_ENEMY_1T("8", "属性ダメージDOWN", 1, "火属性ダメージDOWN（小）（相手/1T）"),
    FIRE_DAMAGE_DOWN_MEDIUM_ENEMY_1T("9", "属性ダメージDOWN", 1, "火属性ダメージDOWN（中）（相手/1T）"),
    FIRE_DAMAGE_DOWN_LARGE_ENEMY_1T("10", "属性ダメージDOWN", 1, "火属性ダメージDOWN（大）（相手/1T）"),
    WATER_DAMAGE_DOWN_SMALL_ENEMY_1T("11", "属性ダメージDOWN", 1, "水属性ダメージDOWN（小）（相手/1T）"),
    WATER_DAMAGE_DOWN_MEDIUM_ENEMY_1T("12", "属性ダメージDOWN", 1, "水属性ダメージDOWN（中）（相手/1T）"),
    WATER_DAMAGE_DOWN_LARGE_ENEMY_1T("13", "属性ダメージDOWN", 1, "水属性ダメージDOWN（大）（相手/1T）"),
    LEAF_DAMAGE_DOWN_SMALL_ENEMY_1T("14", "属性ダメージDOWN", 1, "木属性ダメージDOWN（小）（相手/1T）"),
    LEAF_DAMAGE_DOWN_MEDIUM_ENEMY_1T("15", "属性ダメージDOWN", 1, "木属性ダメージDOWN（中）（相手/1T）"),
    LEAF_DAMAGE_DOWN_LARGE_ENEMY_1T("16", "属性ダメージDOWN", 1, "木属性ダメージDOWN（大）（相手/1T）"),
    VOID_DAMAGE_DOWN_SMALL_ENEMY_1T("17", "属性ダメージDOWN", 1, "無属性ダメージDOWN（小）（相手/1T）"),
    VOID_DAMAGE_DOWN_MEDIUM_ENEMY_1T("18", "属性ダメージDOWN", 1, "無属性ダメージDOWN（中）（相手/1T）"),
    VOID_DAMAGE_DOWN_LARGE_ENEMY_1T("19", "属性ダメージDOWN", 1, "無属性ダメージDOWN（大）（相手/1T）"),
    DAMAGE_DOWN_MINIMUM_ENEMY_1T("20", "ダメージDOWN", 1, "ダメージDOWN（極小）（相手/1T）"),
    DAMAGE_DOWN_SMALL_ENEMY_1T("21", "ダメージDOWN", 1, "ダメージDOWN（小）（相手/1T）"),
    DAMAGE_DOWN_MEDIUM_ENEMY_1T("22", "ダメージDOWN", 1, "ダメージDOWN（中）（相手/1T）"),
    DAMAGE_DOWN_LARGE_ENEMY_1T("23", "ダメージDOWN", 1, "ダメージDOWN（大）（相手/1T）"),
    DAMAGE_DOWN_SMALL_ENEMY_3T("24", "ダメージDOWN", 3, "ダメージDOWN（小）（相手/3T）"),
    DAMAGE_DOWN_MEDIUM_ENEMY_3T("25", "ダメージDOWN", 3, "ダメージDOWN（中）（相手/3T）"),
    RECEIVE_DAMAGE_SMALL_SELF_1T("26", "被ダメージDOWN", 1, "被ダメージDOWN（小）（自/1T）"),
    RECEIVE_DAMAGE_MEDIUM_SELF_1T("27", "被ダメージDOWN", 1, "被ダメージDOWN（中）（自/1T）"),
    RECEIVE_DAMAGE_LARGE_SELF_1T("28", "被ダメージDOWN", 1, "被ダメージDOWN（大）（自/1T）"),
    RECEIVE_DAMAGE_SMALL_SEF_3T("29", "被ダメージDOWN", 3, "被ダメージDOWN（小）（自/3T）"),
    RECEIVE_DAMAGE_MEDIUM_SELF_3T("30", "被ダメージDOWN", 3, "被ダメージDOWN（中）（自/3T）"),
    RECEIVE_DAMAGE_FRIEND_ALL_1T("31", "被ダメージDOWN", 1, "被ダメージDOWN（大）（味方全体/1T）"),
    RECEIVE_DAMAGE_FRIEND_ALL_3T("32", "被ダメージDOWN", 3, "被ダメージDOWN（大）（味方全体/3T）"),
    RECEIVE_DAMAGE_FRIEND_ALL_5T("33", "被ダメージDOWN", 5, "被ダメージDOWN（大）（味方全体/5T）"),
    ATK_UP_MINIMUM_SELF_1T("34", "ATK_UP", 1, "ATK UP（極小）（自/1T）"),
    ATK_UP_SMALL_SELF_1T("35", "ATK_UP", 1, "ATK UP（小）（自/1T）"),
    ATK_UP_MEDIUM_SELF_1T("36", "ATK_UP", 1, "ATK UP（中）（自/1T）"),
    ATK_UP_LARGE_SELF_1T("37", "ATK_UP", 1, "ATK UP（大）（自/1T）"),
    ATK_UP_SMALL_SELF_3T("38", "ATK_UP", 3, "ATK UP（小）（自/3T）"),
    ATK_UP_MEDIUM_SELF_3T("39", "ATK_UP", 3, "ATK UP（中）（自/3T）"),
    ATK_UP_SMALL_FRIEND_1T("40", "ATK_UP", 1, "ATK UP（小）（味方/1T）"),
    ATK_UP_MEDIUM_FRIEND_1T("41", "ATK_UP", 1, "ATK UP（中）（味方/1T）"),
    FIRE_DAMAGE_UP_SMALL_FRIEND_1T("42", "属性ダメージUP", 1, "火属性ダメージUP（小）（味方/1T）"),
    FIRE_DAMAGE_UP_MEDIUM_FRIEND_1T("43", "属性ダメージUP", 1, "火属性ダメージUP（中）（味方/1T）"),
    FIRE_DAMAGE_UP_LARGE_FRIEND_1T("44", "属性ダメージUP", 1, "火属性ダメージUP（大）（味方/1T）"),
    WATER_DAMAGE_UP_SMALL_FRIEND_1T("45", "属性ダメージUP", 1, "水属性ダメージUP（小）（味方/1T）"),
    WATER_DAMAGE_UP_MEDIUM_FRIEND_1T("46", "属性ダメージUP", 1, "水属性ダメージUP（中）（味方/1T）"),
    WATER_DAMAGE_UP_LARGE_FRIEND_1T("47", "属性ダメージUP", 1, "水属性ダメージUP（大）（味方/1T）"),
    LEAF_DAMAGE_UP_SMALL_FRIEND_1T("48", "属性ダメージUP", 1, "木属性ダメージUP（小）（味方/1T）"),
    LEAF_DAMAGE_UP_MEDIUM_FRIEND_1T("49", "属性ダメージUP", 1, "木属性ダメージUP（中）（味方/1T）"),
    LEAF_DAMAGE_UP_LARGE_FRIEND_1T("50", "属性ダメージUP", 1, "木属性ダメージUP（大）（味方/1T）"),
    VOID_DAMAGE_UP_SMALL_FRIEND_1T("51", "属性ダメージUP", 1, "無属性ダメージUP（小）（味方/1T）"),
    VOID_DAMAGE_UP_MEDIUM_FRIEND_1T("52", "属性ダメージUP", 1, "無属性ダメージUP（中）（味方/1T）"),
    VOID_DAMAGE_UP_LARGE_FRIEND_1T("53", "属性ダメージUP", 1, "無属性ダメージUP（大）（味方/1T）"),
    FIRE_DAMAGE_UP_MAXIMUM_SELF_1T("54", "属性ダメージUP", 1, "火属性ダメージUP（極大）（自/1T）"),
    VOID_DAMAGE_UP_MEDIUM_SELF_ALL_5T("55", "属性ダメージUP", 5, "無属性ダメージUP（中）（味方全体/5T）"),
    VOID_DAMAGE_UP_LARGE_FRIEND_ALL_5T("56", "属性ダメージUP", 5, "無属性ダメージUP（大）（味方全体/5T）"),
    DAMAGE_UP_MINIMUM_SELF_1T("57", "ダメージUP", 1, "ダメージUP（極小）（自/1T）"),
    DAMAGE_UP_SMALL_SELF_1T("58", "ダメージUP", 1, "ダメージUP（小）（自/1T）"),
    DAMAGE_UP_MEDIUM_SELF_1T("59", "ダメージUP", 1, "ダメージUP（中）（自/1T）"),
    DAMAGE_UP_LARGE_SELF_1T("60", "ダメージUP", 1, "ダメージUP（大）（自/1T）"),
    DAMAGE_UP_SMALL_SELF_3T("61", "ダメージUP", 3, "ダメージUP（小）（自/3T）"),
    DAMAGE_UP_MEDIUM_SELF_3T("62", "ダメージUP", 3, "ダメージUP（中）（自/3T）"),
    DAMAGE_UP_MINIMUM_FRIEND_1T("63", "ダメージUP", 1, "ダメージUP（極小）（味方/1T）"),
    DAMAGE_UP_SMALL_FRIEND_1T("64", "ダメージUP", 1, "ダメージUP（小）（味方/1T）"),
    DAMAGE_UP_MEDIUM_FRIEND_1T("65", "ダメージUP", 1, "ダメージUP（中）（味方/1T）"),
    DAMAGE_UP_LARGE_FRIEND_1T("66", "ダメージUP", 1, "ダメージUP（大）（味方/1T）"),
    DAMAGE_UP_MEDIUM_FRIEND_ALL_2T("100", "ダメージUP", 2, "ダメージUP（中）（味方全体/2T）"),
    DAMAGE_UP_LARGE_ENEMY_ALL_2T("99", "ダメージUP", 2, "ダメージUP（大）（相手全体/2T）"),
    HP_RECOVER_MINIMUM("67", "HP回復", 1, "HP回復（極小）"),
    HP_RECOVER_SMALL("68", "HP回復", 1, "HP回復（小）"),
    HP_RECOVER_MEDIUM("69", "HP回復", 1, "HP回復（中）"),
    HP_CONTINUOUS_RECOVER_MINIMUM_SELF_3T("70", "HP継続回復", 3, "HP継続回復（極小）（自/3T）"),
    HP_CONTINUOUS_RECOVER_SMALL_SELF_3T("71", "HP継続回復", 3, "HP継続回復（小）（自/3T）"),
    HP_CONTINUOUS_RECOVER_MEDIUM_SELF_3T("72", "HP継続回復", 3, "HP継続回復（中）（自/3T）"),
    HP_CONTINUOUS_RECOVER_MINIMUM_FRIEND_3T("73", "HP継続回復", 3, "HP継続回復（極小）（味方/3T）"),
    HP_CONTINUOUS_RECOVER_SMALL_FRIEND_3T("74", "HP継続回復", 3, "HP継続回復（小）（味方/3T）"),
    EVADE_MINIMUM_SELF_1T("75", "回避", 1, "回避（極小）（自/1T）"),
    EVADE_SMALL_SELF_1T("76", "回避", 1, "回避（小）（自/1T）"),
    EVADE_MEDIUM_SELF_1T("77", "回避", 1, "回避（中）（自/1T）"),
    CURSE_SMALL_ENEMY_2T("78", "呪い", 2, "呪い（小）（相手/2T）"),
    CURSE_MEDIUM_ENEMY_2T("79", "呪い", 2, "呪い（中）（相手/2T）"),
    CURSE_LARGE_ENEMY_2T("80", "呪い", 2, "呪い（大）（相手/2T）"),
    ANNUL_CURSE_SELF_1T("81", "呪い無効", 1, "呪い無効（自/1T）"),
    ANNUL_CURSE_FRIEND_1T("82", "呪い無効", 1, "呪い無効（味方/1T）"),
    ANNUAL_FREEZE_SELF_1T("83", "凍結無効", 1, "凍結無効（自/1T）"),
    ANNUAL_FREEZE_FRIEND_1T("84", "凍結無効", 1, "凍結無効（味方/1T）"),
    ANNUAL_FREEZE_FRIEND_3T("85", "凍結無効", 1, "凍結無効（味方/3T）"),
    ANNUAL_DARKNESS_SELF_1T("86", "暗闇無効", 1, "暗闇無効（自/1T）"),
    ANNUAL_DARKNESS_FRIEND_1T("87", "暗闇無効", 1, "暗闇無効（味方/1T）"),
    REMOVE_DEBUFF_FRIEND("88", "デバフ解除", 1, "デバフ解除（味方）"),
    RECEIVE_DAMAGE_UP_SMALL_ENEMY_1T("89", "被ダメージUP", 1, "被ダメージUP（小）（相手/1T）"),
    RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_1T("90", "被ダメージUP", 1, "被ダメージUP（中）（相手/1T）"),
    RECEIVE_DAMAGE_UP_LARGE_ENEMY_1T("91", "被ダメージUP", 1, "被ダメージUP（大）（相手/1T）"),
    RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_2T("92", "被ダメージUP", 2, "被ダメージUP（小）（相手/1T）"),
    RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_ALL_2T("93", "被ダメージUP", 2, "被ダメージUP（中）（相手全体/2T）"),
    RECEIVE_DAMAGE_UP_LARGE_FRIEND_2T("94", "被ダメージUP（大）", 2, "被ダメージUP（大）（味方/2T）"),
    RECEIVE_DAMAGE_UP_LARGE_FRIEND_ALL_2T("95", "被ダメージUP（大）", 2, "被ダメージUP（大）（味方全体/2T）"),
    CRITICAL_MEDIUM_SELF_1T("96", "クリティカル", 1, "クリティカル（中）（自/1T）"),
    CRITICAL_MEDIUM_FRIEND_1T("97", "クリティカル", 1, "クリティカル（中）（味方/1T）"),
    CRITICAL_MEDIUM_FRIEND_3T("98", "クリティカル", 3, "クリティカル（中）（味方/3T）");

    /**
     * バフデバフ区分
     */
    private final String buffDebuffGrouping;

    /**
     * タイプ
     */
    private final String type;

    /**
     * ターン
     */
    private final int turn;

    /**
     * 名称
     */
    private final String name;

    /**
     * typeを取得する.
     * 
     * @param buffDebuffGrouping
     * @return type
     */
    public static String getType(String buffDebuffGrouping) {
        for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
            if (buffDebuffGroupingEnum.getBuffDebuffGrouping().equals(buffDebuffGrouping)) {
                return buffDebuffGroupingEnum.getType();
            }
        }
        return null;
    }

    /**
     * nameを取得する.
     * 
     * @param buffDebuffGrouping
     * @return name
     */
    public static String getName(String buffDebuffGrouping) {
        for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
            if (buffDebuffGroupingEnum.getBuffDebuffGrouping().equals(buffDebuffGrouping)) {
                return buffDebuffGroupingEnum.getName();
            }
        }
        return null;
    }

    // /**
    // * typeの一覧を取得する.
    // *
    // * @param typeArray[]
    // * @return typeList
    // */
    // public static List<String> getTypeList(String[] typeArray) {
    // List<String> typeList = new ArrayList<>();
    // for (String type : typeArray) {
    // if (type == null) {
    // continue;
    // }
    // for (BuffDebuffGroupingEnum buffDebuffGroupingEnum :
    // BuffDebuffGroupingEnum.values()) {
    // if (buffDebuffGroupingEnum.getType().equals(type)) {
    // typeList.add(buffDebuffGroupingEnum.getBuffDebuffGrouping());
    // }
    // }
    // }
    // return typeList;
    // }

    /**
     * typeの一覧を取得する.
     * 
     * @return typeList
     */
    public static List<String> getTypeList() {
        List<String> typeList = new ArrayList<>();
        for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
            if ((!typeList.contains(buffDebuffGroupingEnum.getType()))
                    && (!buffDebuffGroupingEnum.getType().equals("-"))) {
                typeList.add(buffDebuffGroupingEnum.getType());
            }
        }
        return typeList;
    }

    /**
     * enumのListを取得する.
     * 
     * @param type
     * @return List<name>
     */
    public static List<BuffDebuffGroupingEnum> getValue(String type) {
        List<BuffDebuffGroupingEnum> valueList = new ArrayList<>();
        for (BuffDebuffGroupingEnum name : BuffDebuffGroupingEnum.values()) {
            if (name.getName().equals(type)) {
                valueList.add(name);
            }
        }
        return valueList;
    }

    /**
     * typeの一覧を取得する.
     * 
     * @param typeArray[]
     * @return typeArray
     */
    public static String[] getTypeArray(String[] typeArray) {
        List<String> typeList = new ArrayList<>();
        for (String type : typeArray) {
            for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                if (buffDebuffGroupingEnum.getType().equals(type)) {
                    typeList.add("%" + buffDebuffGroupingEnum.getBuffDebuffGrouping() + "%");
                }
            }
        }
        return typeList.stream().toArray(String[]::new);
    }

    /**
     * 複数のbuffDebuffGroupingを連結する.
     * 
     * @param buffDebuff
     * @return name
     */
    public static String join(String buffDebuff) {
        String temp = "";
        if (buffDebuff.contains("&")) {
            String[] splitArray = buffDebuff.split("[& ]");
            for (int i = 0; i < splitArray.length; i++) {
                if (splitArray[i].equals("")) {
                    continue;
                } else {
                    temp += getName(splitArray[i]);
                }
                if (i < splitArray.length - 1) {
                    temp += "＆";
                }
            }
        } else {
            temp = getName(buffDebuff);
        }
        return temp;
    }

    /**
     * constructor
     * 
     * @param buffDebuffGrouping
     * @param type
     * @param turn
     * @param name
     */
    private BuffDebuffGroupingEnum(String buffDebuffGrouping, String type, int turn, String name) {
        this.buffDebuffGrouping = buffDebuffGrouping;
        this.type = type;
        this.turn = turn;
        this.name = name;
    }
}
