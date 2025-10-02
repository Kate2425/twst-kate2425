package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum BuffDebuffGroupingEnum {
        /**
         * HYPHEN.
         */
        HYPHEN("0", "-", "0", "-", "-", "-"),
        /**
         * ATK DOWN（極小）（相手/1T）.
         */
        ATK_DOWN_MINIMUM_ENEMY_1T("1", "ATK_DOWN", "1", "極小", "相手", "ATK DOWN（極小）（相手/1T）"),
        /**
         * ATK DOWN（小）（相手/1T）.
         */
        ATK_DOWN_SMALL_ENEMY_1T("2", "ATK_DOWN", "1", "小", "相手", "ATK DOWN（小）（相手/1T）"),
        /**
         * ATK DOWN（中）（相手/1T）.
         */
        ATK_DOWN_MEDIUM_ENEMY_1T("3", "ATK_DOWN", "1", "中", "相手", "ATK DOWN（中）（相手/1T）"),
        /**
         * ATK DOWN（大）（相手/1T）.
         */
        ATK_DOWN_LARGE_ENEMY_1T("4", "ATK_DOWN", "1", "大", "相手", "ATK DOWN（大）（相手/1T）"),
        /**
         * ATK DOWN（小）（相手/2T）.
         */
        ATK_DOWN_SMALL_ENEMY_2T("5", "ATK_DOWN", "2", "小", "相手", "ATK DOWN（小）（相手/2T）"),
        /**
         * ATK DOWN（小）（相手/3T）.
         */
        ATK_DOWN_SMALL_ENEMY_3T("6", "ATK_DOWN", "3", "小", "相手", "ATK DOWN（小）（相手/3T）"),
        /**
         * ATK DOWN（中）（相手/3T）.
         */
        ATK_DOWN_MEDIUM_ENEMY_3T("7", "ATK_DOWN", "3", "中", "相手", "ATK DOWN（中）（相手/3T）"),
        /**
         * 火属性ダメージDOWN（小）（相手/1T）.
         */
        FIRE_DAMAGE_DOWN_SMALL_ENEMY_1T("8", "属性ダメージDOWN", "1", "小", "相手", "火属性ダメージDOWN（小）（相手/1T）"),
        /**
         * 火属性ダメージDOWN（中）（相手/1T）.
         */
        FIRE_DAMAGE_DOWN_MEDIUM_ENEMY_1T("9", "属性ダメージDOWN", "1", "中", "相手", "火属性ダメージDOWN（中）（相手/1T）"),
        /**
         * 火属性ダメージDOWN（大）（相手/1T）.
         */
        FIRE_DAMAGE_DOWN_LARGE_ENEMY_1T("10", "属性ダメージDOWN", "1", "大", "相手", "火属性ダメージDOWN（大）（相手/1T）"),
        /**
         * 水属性ダメージDOWN（小）（相手/1T）.
         */
        WATER_DAMAGE_DOWN_SMALL_ENEMY_1T("11", "属性ダメージDOWN", "1", "小", "相手", "水属性ダメージDOWN（小）（相手/1T）"),
        /**
         * 水属性ダメージDOWN（中）（相手/1T）.
         */
        WATER_DAMAGE_DOWN_MEDIUM_ENEMY_1T("12", "属性ダメージDOWN", "1", "中", "相手", "水属性ダメージDOWN（中）（相手/1T）"),
        /**
         * 水属性ダメージDOWN（大）（相手/1T）.
         */
        WATER_DAMAGE_DOWN_LARGE_ENEMY_1T("13", "属性ダメージDOWN", "1", "大", "相手", "水属性ダメージDOWN（大）（相手/1T）"),
        /**
         * 木属性ダメージDOWN（小）（相手/1T）.
         */
        LEAF_DAMAGE_DOWN_SMALL_ENEMY_1T("14", "属性ダメージDOWN", "1", "小", "相手", "木属性ダメージDOWN（小）（相手/1T）"),
        /**
         * 木属性ダメージDOWN（中）（相手/1T）.
         */
        LEAF_DAMAGE_DOWN_MEDIUM_ENEMY_1T("15", "属性ダメージDOWN", "1", "中", "相手", "木属性ダメージDOWN（中）（相手/1T）"),
        /**
         * 木属性ダメージDOWN（大）（相手/1T）.
         */
        LEAF_DAMAGE_DOWN_LARGE_ENEMY_1T("16", "属性ダメージDOWN", "1", "大", "相手", "木属性ダメージDOWN（大）（相手/1T）"),
        /**
         * 無属性ダメージDOWN（小）（相手/1T）.
         */
        VOID_DAMAGE_DOWN_SMALL_ENEMY_1T("17", "属性ダメージDOWN", "1", "小", "相手", "無属性ダメージDOWN（小）（相手/1T）"),
        /**
         * 無属性ダメージDOWN（中）（相手/1T）.
         */
        VOID_DAMAGE_DOWN_MEDIUM_ENEMY_1T("18", "属性ダメージDOWN", "1", "中", "相手", "無属性ダメージDOWN（中）（相手/1T）"),
        /**
         * 無属性ダメージDOWN（大）（相手/1T）.
         */
        VOID_DAMAGE_DOWN_LARGE_ENEMY_1T("19", "属性ダメージDOWN", "1", "大", "相手", "無属性ダメージDOWN（大）（相手/1T）"),
        /**
         * ダメージDOWN（極小）（相手/1T）.
         */
        DAMAGE_DOWN_MINIMUM_ENEMY_1T("20", "ダメージDOWN", "1", "極小", "相手", "ダメージDOWN（極小）（相手/1T）"),
        /**
         * ダメージDOWN（小）（相手/1T）.
         */
        DAMAGE_DOWN_SMALL_ENEMY_1T("21", "ダメージDOWN", "1", "小", "相手", "ダメージDOWN（小）（相手/1T）"),
        /**
         * ダメージDOWN（中）（相手/1T）.
         */
        DAMAGE_DOWN_MEDIUM_ENEMY_1T("22", "ダメージDOWN", "1", "中", "相手", "ダメージDOWN（中）（相手/1T）"),
        /**
         * ダメージDOWN（大）（相手/1T）.
         */
        DAMAGE_DOWN_LARGE_ENEMY_1T("23", "ダメージDOWN", "1", "大", "相手", "ダメージDOWN（大）（相手/1T）"),
        /**
         * ダメージDOWN（小）（相手/3T）.
         */
        DAMAGE_DOWN_SMALL_ENEMY_3T("24", "ダメージDOWN", "3", "小", "相手", "ダメージDOWN（小）（相手/3T）"),
        /**
         * ダメージDOWN（中）（相手/3T）.
         */
        DAMAGE_DOWN_MEDIUM_ENEMY_3T("25", "ダメージDOWN", "3", "中", "相手", "ダメージDOWN（中）（相手/3T）"),
        /**
         * 被ダメージDOWN（小）（自/1T）.
         */
        RECEIVE_DAMAGE_DOWN_SMALL_SELF_1T("26", "被ダメージDOWN", "1", "小", "自", "被ダメージDOWN（小）（自/1T）"),
        /**
         * 被ダメージDOWN（中）（自/1T）.
         */
        RECEIVE_DAMAGE_DOWN_MEDIUM_SELF_1T("27", "被ダメージDOWN", "1", "中", "自", "被ダメージDOWN（中）（自/1T）"),
        /**
         * 被ダメージDOWN（大）（自/1T）.
         */
        RECEIVE_DAMAGE_DOWN_LARGE_SELF_1T("28", "被ダメージDOWN", "1", "大", "自", "被ダメージDOWN（大）（自/1T）"),
        /**
         * 被ダメージDOWN（小）（自/3T）.
         */
        RECEIVE_DAMAGE_DOWN_SMALL_SELF_3T("29", "被ダメージDOWN", "3", "小", "自", "被ダメージDOWN（小）（自/3T）"),
        /**
         * 被ダメージDOWN（中）（自/3T）.
         */
        RECEIVE_DAMAGE_DOWN_MEDIUM_SELF_3T("30", "被ダメージDOWN", "3", "中", "自", "被ダメージDOWN（中）（自/3T）"),
        /**
         * 被ダメージDOWN（大）（味方全体/1T）.
         */
        RECEIVE_DAMAGE_DOWN_LARGE_FRIEND_ALL_1T("31", "被ダメージDOWN", "1", "大", "味方全体", "被ダメージDOWN（大）（味方全体/1T）"),
        /**
         * 被ダメージDOWN（大）（味方全体/3T）.
         */
        RECEIVE_DAMAGE_DOWN_LARGE_FRIEND_ALL_3T("32", "被ダメージDOWN", "3", "大", "味方全体", "被ダメージDOWN（大）（味方全体/3T）"),
        /**
         * 被ダメージDOWN（大）（味方全体/5T）.
         */
        RECEIVE_DAMAGE_DOWN_LARGE_FRIEND_ALL_5T("33", "被ダメージDOWN", "5", "大", "味方全体", "被ダメージDOWN（大）（味方全体/5T）"),
        /**
         * ATK UP（極小）（自/1T）.
         */
        ATK_UP_MINIMUM_SELF_1T("34", "ATK_UP", "1", "極小", "自", "ATK UP（極小）（自/1T）"),
        /**
         * ATK UP（小）（自/1T）.
         */
        ATK_UP_SMALL_SELF_1T("35", "ATK_UP", "1", "小", "自", "ATK UP（小）（自/1T）"),
        /**
         * ATK UP（中）（自/1T）.
         */
        ATK_UP_MEDIUM_SELF_1T("36", "ATK_UP", "1", "中", "自", "ATK UP（中）（自/1T）"),
        /**
         * ATK UP（大）（自/1T）.
         */
        ATK_UP_LARGE_SELF_1T("37", "ATK_UP", "1", "大", "自", "ATK UP（大）（自/1T）"),
        /**
         * ATK UP（小）（自/3T）.
         */
        ATK_UP_SMALL_SELF_3T("38", "ATK_UP", "3", "小", "自", "ATK UP（小）（自/3T）"),
        /**
         * "ATK UP（中）（自/3T）.
         */
        ATK_UP_MEDIUM_SELF_3T("39", "ATK_UP", "3", "中", "自", "ATK UP（中）（自/3T）"),
        /**
         * ATK UP（小）（味方/1T）.
         */
        ATK_UP_SMALL_FRIEND_1T("40", "ATK_UP", "1", "小", "味方", "ATK UP（小）（味方/1T）"),
        /**
         * ATK UP（中）（味方/1T）.
         */
        ATK_UP_MEDIUM_FRIEND_1T("41", "ATK_UP", "1", "中", "味方", "ATK UP（中）（味方/1T）"),
        /**
         * 火属性ダメージUP（小）（味方/1T）.
         */
        FIRE_DAMAGE_UP_SMALL_FRIEND_1T("42", "属性ダメージUP", "1", "小", "味方", "火属性ダメージUP（小）（味方/1T）"),
        /**
         * 火属性ダメージUP（中）（味方/1T）.
         */
        FIRE_DAMAGE_UP_MEDIUM_FRIEND_1T("43", "属性ダメージUP", "1", "中", "味方", "火属性ダメージUP（中）（味方/1T）"),
        /**
         * 火属性ダメージUP（大）（味方/1T）.
         */
        FIRE_DAMAGE_UP_LARGE_FRIEND_1T("44", "属性ダメージUP", "1", "大", "味方", "火属性ダメージUP（大）（味方/1T）"),
        /**
         * 水属性ダメージUP（小）（味方/1T）.
         */
        WATER_DAMAGE_UP_SMALL_FRIEND_1T("45", "属性ダメージUP", "1", "小", "味方", "水属性ダメージUP（小）（味方/1T）"),
        /**
         * 水属性ダメージUP（中）（味方/1T）.
         */
        WATER_DAMAGE_UP_MEDIUM_FRIEND_1T("46", "属性ダメージUP", "1", "中", "味方", "水属性ダメージUP（中）（味方/1T）"),
        /**
         * 水属性ダメージUP（大）（味方/1T）.
         */
        WATER_DAMAGE_UP_LARGE_FRIEND_1T("47", "属性ダメージUP", "1", "大", "味方", "水属性ダメージUP（大）（味方/1T）"),
        /**
         * 木属性ダメージUP（小）（味方/1T）.
         */
        LEAF_DAMAGE_UP_SMALL_FRIEND_1T("48", "属性ダメージUP", "1", "小", "味方", "木属性ダメージUP（小）（味方/1T）"),
        /**
         * 木属性ダメージUP（中）（味方/1T）.
         */
        LEAF_DAMAGE_UP_MEDIUM_FRIEND_1T("49", "属性ダメージUP", "1", "中", "味方", "木属性ダメージUP（中）（味方/1T）"),
        /**
         * 木属性ダメージUP（大）（味方/1T）.
         */
        LEAF_DAMAGE_UP_LARGE_FRIEND_1T("50", "属性ダメージUP", "1", "大", "味方", "木属性ダメージUP（大）（味方/1T）"),
        /**
         * 無属性ダメージUP（小）（味方/1T）.
         */
        VOID_DAMAGE_UP_SMALL_FRIEND_1T("51", "属性ダメージUP", "1", "小", "味方", "無属性ダメージUP（小）（味方/1T）"),
        /**
         * 無属性ダメージUP（中）（味方/1T）.
         */
        VOID_DAMAGE_UP_MEDIUM_FRIEND_1T("52", "属性ダメージUP", "1", "中", "味方", "無属性ダメージUP（中）（味方/1T）"),
        /**
         * 無属性ダメージUP（大）（味方/1T）.
         */
        VOID_DAMAGE_UP_LARGE_FRIEND_1T("53", "属性ダメージUP", "1", "大", "味方", "無属性ダメージUP（大）（味方/1T）"),
        /**
         * 火属性ダメージUP（極大）（自/1T）.
         */
        FIRE_DAMAGE_UP_MAXIMUM_SELF_1T("54", "属性ダメージUP", "1", "極大", "自", "火属性ダメージUP（極大）（自/1T）"),
        /**
         * 無属性ダメージUP（中）（味方全体/5T）.
         */
        VOID_DAMAGE_UP_MEDIUM_SELF_ALL_5T("55", "属性ダメージUP", "5", "中", "味方全体", "無属性ダメージUP（中）（味方全体/5T）"),
        /**
         * 無属性ダメージUP（大）（味方全体/5T）.
         */
        VOID_DAMAGE_UP_LARGE_FRIEND_ALL_5T("56", "属性ダメージUP", "5", "大", "味方全体", "無属性ダメージUP（大）（味方全体/5T）"),
        /**
         * ダメージUP（極小）（自/1T）.
         */
        DAMAGE_UP_MINIMUM_SELF_1T("57", "ダメージUP", "1", "極小", "自", "ダメージUP（極小）（自/1T）"),
        /**
         * ダメージUP（小）（自/1T）.
         */
        DAMAGE_UP_SMALL_SELF_1T("58", "ダメージUP", "1", "小", "自", "ダメージUP（小）（自/1T）"),
        /**
         * ダメージUP（中）（自/1T）.
         */
        DAMAGE_UP_MEDIUM_SELF_1T("59", "ダメージUP", "1", "中", "自", "ダメージUP（中）（自/1T）"),
        /**
         * ダメージUP（大）（自/1T）.
         */
        DAMAGE_UP_LARGE_SELF_1T("60", "ダメージUP", "1", "大", "自", "ダメージUP（大）（自/1T）"),
        /**
         * ダメージUP（小）（自/3T）.
         */
        DAMAGE_UP_SMALL_SELF_3T("61", "ダメージUP", "3", "小", "自", "ダメージUP（小）（自/3T）"),
        /**
         * "ダメージUP（中）（自/3T）.
         */
        DAMAGE_UP_MEDIUM_SELF_3T("62", "ダメージUP", "3", "中", "自", "ダメージUP（中）（自/3T）"),
        /**
         * ダメージUP（極小）（味方/1T）.
         */
        DAMAGE_UP_MINIMUM_FRIEND_1T("63", "ダメージUP", "1", "極小", "味方", "ダメージUP（極小）（味方/1T）"),
        /**
         * ダメージUP（小）（味方/1T）.
         */
        DAMAGE_UP_SMALL_FRIEND_1T("64", "ダメージUP", "1", "小", "味方", "ダメージUP（小）（味方/1T）"),
        /**
         * ダメージUP（中）（味方/1T）.
         */
        DAMAGE_UP_MEDIUM_FRIEND_1T("65", "ダメージUP", "1", "中", "味方", "ダメージUP（中）（味方/1T）"),
        /**
         * ダメージUP（大）（味方/1T）.
         */
        DAMAGE_UP_LARGE_FRIEND_1T("66", "ダメージUP", "1", "大", "味方", "ダメージUP（大）（味方/1T）"),
        /**
         * ダメージUP（中）（味方全体/2T）.
         */
        DAMAGE_UP_MEDIUM_FRIEND_ALL_2T("100", "ダメージUP", "2", "中", "味方全体", "ダメージUP（中）（味方全体/2T）"),
        /**
         * ダメージUP（大）（相手全体/2T）.
         */
        DAMAGE_UP_LARGE_ENEMY_ALL_2T("99", "ダメージUP", "2", "大", "相手全体", "ダメージUP（大）（相手全体/2T）"),
        /**
         * HP回復（極小）.
         */
        HP_RECOVER_MINIMUM("67", "HP回復", "1", "極小", "-", "HP回復（極小）"),
        /**
         * HP回復（小）.
         */
        HP_RECOVER_SMALL("68", "HP回復", "1", "小", "-", "HP回復（小）"),
        /**
         * HP回復（中）.
         */
        HP_RECOVER_MEDIUM("69", "HP回復", "1", "中", "-", "HP回復（中）"),
        /**
         * HP継続回復（極小）（自/3T）.
         */
        HP_CONTINUOUS_RECOVER_MINIMUM_SELF_3T("70", "HP継続回復", "3", "極小", "自", "HP継続回復（極小）（自/3T）"),
        /**
         * HP継続回復（小）（自/3T）.
         */
        HP_CONTINUOUS_RECOVER_SMALL_SELF_3T("71", "HP継続回復", "3", "小", "自", "HP継続回復（小）（自/3T）"),
        /**
         * HP継続回復（中）（自/3T）.
         */
        HP_CONTINUOUS_RECOVER_MEDIUM_SELF_3T("72", "HP継続回復", "3", "中", "自", "HP継続回復（中）（自/3T）"),
        /**
         * HP継続回復（極小）（味方/3T）.
         */
        HP_CONTINUOUS_RECOVER_MINIMUM_FRIEND_3T("73", "HP継続回復", "3", "極小", "味方", "HP継続回復（極小）（味方/3T）"),
        /**
         * HP継続回復（小）（味方/3T）.
         */
        HP_CONTINUOUS_RECOVER_SMALL_FRIEND_3T("74", "HP継続回復", "3", "小", "味方", "HP継続回復（小）（味方/3T）"),
        /**
         * 回避（極小）（自/1T）.
         */
        EVADE_MINIMUM_SELF_1T("75", "回避", "1", "極小", "自", "回避（極小）（自/1T）"),
        /**
         * 回避（小）（自/1T）.
         */
        EVADE_SMALL_SELF_1T("76", "回避", "1", "小", "自", "回避（小）（自/1T）"),
        /**
         * 回避（中）（自/1T）.
         */
        EVADE_MEDIUM_SELF_1T("77", "回避", "1", "中", "自", "回避（中）（自/1T）"),
        /**
         * 呪い（小）（相手/2T）.
         */
        CURSE_SMALL_ENEMY_2T("78", "呪い", "2", "小", "相手", "呪い（小）（相手/2T）"),
        /**
         * 呪い（中）（相手/2T）.
         */
        CURSE_MEDIUM_ENEMY_2T("79", "呪い", "2", "中", "相手", "呪い（中）（相手/2T）"),
        /**
         * 呪い（大）（相手/2T）.
         */
        CURSE_LARGE_ENEMY_2T("80", "呪い", "2", "大", "相手", "呪い（大）（相手/2T）"),
        /**
         * 呪い無効（自/1T）.
         */
        ANNUL_CURSE_SELF_1T("81", "呪い無効", "1", "-", "自", "呪い無効（自/1T）"),
        /**
         * 呪い無効（味方/1T）.
         */
        ANNUL_CURSE_FRIEND_1T("82", "呪い無効", "1", "-", "味方", "呪い無効（味方/1T）"),
        /**
         * 凍結無効（自/1T）.
         */
        ANNUAL_FREEZE_SELF_1T("83", "凍結無効", "1", "-", "自", "凍結無効（自/1T）"),
        /**
         * 凍結無効（味方/1T）.
         */
        ANNUAL_FREEZE_FRIEND_1T("84", "凍結無効", "1", "-", "味方", "凍結無効（味方/1T）"),
        /**
         * 凍結無効（味方/3T）.
         */
        ANNUAL_FREEZE_FRIEND_3T("85", "凍結無効", "1", "-", "味方", "凍結無効（味方/3T）"),
        /**
         * 暗闇無効（自/1T）.
         */
        ANNUAL_DARKNESS_SELF_1T("86", "暗闇無効", "1", "-", "自", "暗闇無効（自/1T）"),
        /**
         * 暗闇無効（味方/1T）.
         */
        ANNUAL_DARKNESS_FRIEND_1T("87", "暗闇無効", "1", "-", "味方", "暗闇無効（味方/1T）"),
        /**
         * デバフ解除（味方）.
         */
        REMOVE_DEBUFF_FRIEND("88", "デバフ解除", "1", "-", "味方", "デバフ解除（味方）"),
        /**
         * 被ダメージUP（小）（相手/1T）.
         */
        RECEIVE_DAMAGE_UP_SMALL_ENEMY_1T("89", "被ダメージUP", "1", "小", "相手", "被ダメージUP（小）（相手/1T）"),
        /**
         * 被ダメージUP（中）（相手/1T）.
         */
        RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_1T("90", "被ダメージUP", "1", "中", "相手", "被ダメージUP（中）（相手/1T）"),
        /**
         * 被ダメージUP（大）（相手/1T）.
         */
        RECEIVE_DAMAGE_UP_LARGE_ENEMY_1T("91", "被ダメージUP", "1", "大", "相手", "被ダメージUP（大）（相手/1T）"),
        /**
         * 被ダメージUP（小）（相手/1T）.
         */
        RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_2T("92", "被ダメージUP", "2", "小", "相手", "被ダメージUP（小）（相手/1T）"),
        /**
         * 被ダメージUP（中）（相手全体/2T）.
         */
        RECEIVE_DAMAGE_UP_MEDIUM_ENEMY_ALL_2T("93", "被ダメージUP", "2", "中", "相手全体", "被ダメージUP（中）（相手全体/2T）"),
        /**
         * 被ダメージUP（大）（味方/2T）.
         */
        RECEIVE_DAMAGE_UP_LARGE_FRIEND_2T("94", "被ダメージUP", "2", "大", "味方", "被ダメージUP（大）（味方/2T）"),
        /**
         * 被ダメージUP（大）（味方全体/2T）.
         */
        RECEIVE_DAMAGE_UP_LARGE_FRIEND_ALL_2T("95", "被ダメージUP", "2", "大", "味方全体", "被ダメージUP（大）（味方全体/2T）"),
        /**
         * クリティカル（中）（自/1T）.
         */
        CRITICAL_MEDIUM_SELF_1T("96", "クリティカル", "1", "中", "自", "クリティカル（中）（自/1T）"),
        /**
         * クリティカル（中）（味方/1T）.
         */
        CRITICAL_MEDIUM_FRIEND_1T("97", "クリティカル", "1", "中", "味方", "クリティカル（中）（味方/1T）"),
        /**
         * クリティカル（中）（味方/3T）.
         */
        CRITICAL_MEDIUM_FRIEND_3T("98", "クリティカル", "3", "中", "味方", "クリティカル（中）（味方/3T）"),
        /**
         * ATK DOWN（小）（相手/1T）＆ダメージUP（小）（自/1T）.
         */
        ATK_DOWN_SMALL_ENEMY_1T_AND_DAMAGE_UP_SMALL_SELF_1T("2 & 58", "ATK_DOWN＆ダメージUP", "1＆1", "小＆小",
                        "相手＆自", "ATK DOWN（小）（相手/1T）＆ダメージUP（小）（自/1T）"),
        /**
         * ATK UP（小）（自/1T）＆ダメージDOWN（小）（相手/1T）.
         */
        ATK_UP_SMALL_SELF_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T("35 & 21", "ATK_UP＆ダメージDOWN", "1＆1", "小＆小",
                        "自＆相手", "ATK UP（小）（自/1T）＆ダメージDOWN（小）（相手/1T）"),
        /**
         * "HP回復（小）＆ATK DOWN（小）（相手/1T）.
         */
        HP_RECOVER_SMALL_AND_ATK_DOWN_SMALL_ENEMY_1T("68 & 2", "HP回復＆ATK_DOWN", "1＆1", "小＆小",
                        "-＆相手", "HP回復（小）＆ATK DOWN（小）（相手/1T）"),
        /**
         * HP回復（小）＆ダメージDOWN（小）（相手/1T）.
         */
        HP_RECOVER_SMALL_AND_DAMAGE_DOWN_SMALL_ENEMY_1T("68 & 21", "HP回復＆ダメージDOWN", "1＆1", "小＆小",
                        "-＆相手", "HP回復（小）＆ダメージDOWN（小）（相手/1T）"),
        /**
         * HP回復（小）＆ATK UP（小）（自/1T）.
         */
        HP_RECOVER_SMALL_AND_ATK_UP_SMALL_SELF_1T("68 & 35", "HP回復＆ATK_UP", "1＆1", "小＆小",
                        "-＆相手", "HP回復（小）＆ATK UP（小）（自/1T）"),
        /**
         * HP回復（小）＆ダメージUP（小）（自/1T）
         */
        HP_RECOVER_SMALL_AND_DAMAGE_UP_SMALL_SELF_1T("68 & 58", "HP回復＆ダメージUP", "-＆1", "小＆小",
                        "-＆相手", "HP回復（小）＆ダメージUP（小）（自/1T）"),

        /**
         * HP継続回復（小）（自/3T）＆ダメージDOWN（小）（相手/1T）.
          */
        HP_CONTINUOUS_RECOVER_SMALL_SELF_3T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T("71 & 21", "HP継続回復＆ダメージDOWN",
                        "3＆1", "小＆小", "自＆相手", "HP継続回復（小）（自/3T）＆ダメージDOWN（小）（相手/1T）"),

        /**HP継続復（小）（自/3T）＆ATK UP（小）（自/1T）*/
        HP_CONTINUOUS_RECOVER_SMALL_SELF_3T_AND_ATK_UP_SMALL_SELF_1T("71 & 35", "HP 継小続小回復＆ATK UP", "3＆1",
                        "小＆小", "自＆自", "HP継続回復（小）（自/3T）＆ATK UP（小）（自/1T）"),

        /**
         * HP継続回復（小）（自/3T）＆ダメージUP（小）（自/1T）.
         */
        HP_CONTINUOUS_RECOVER_SMALL_SELF_3T_AND_DAMAGE_UP_SMALL_SELF_1T("71 & 58", "HP継続回復＆ダメージUP", "3＆1",
                        "小＆小", "自＆自", "HP継続回復（小）（自/3T）＆ダメージUP（小）（自/1T）"),
        /**
         * 回避（小）（自/1T）＆ATK UP（小）（自/1T）
         */
        EVADE_SMALL_SELF_1T_AND_ATK_UP_SMALL_SELF_1T("76 & 35", "回避＆ATK UP", "1＆1", "小＆小", "自＆自",
                        "回避（小）（自/1T）＆ATK UP（小）（自/1T）"),
        /**
         *回避（小）（自/1T）＆ダメージUP（小）（自/1T）
         */
        EVADE_SMALL_SELF_1T_AND_DAMAGE_UP_SMALL_SELF_1T("76 & 58", "回避＆ダメージUP", "1＆1", "小＆小", "自＆自",
                        "回避（小）（自/1T）＆ダメージUP（小）（自/1T）"),
        /**
         * 呪い（中）（相手/2T）＆ATK DOWN（小）（相手/1T）
         */
        CURSE_MEDIUM_ENEMY_2T_AND_ATK_DOWN_SMALL_ENEMY_1T("79 & 2", "呪い＆ATK DOWN", "2＆1", "中＆小", "相手＆相手",
                        "呪い（中）（相手/2T）＆ATK DOWN（小）（相手/1T）"),
        /**
         * 呪い（中）（相手/2T）＆ATK UP（小）（自/1T）
         */
        CURSE_MEDIUM_ENEMY_2T_AND_ATK_UP_SMALL_SELF_1T("79 & 35", "呪い＆ATK_UP", "2＆1", "中＆小", "相手＆自",
                        "呪い（中）（相手/2T）＆ATK UP（小）（自/1T）"),
        /**
         * 呪い（大）（相手/2T）＆ダメージUP（小）（自/1T）
         */
        CURSE_LARGE_ENEMY_2T_AND_DAMAGE_UP_SMALL_SELF_1T("80 & 58", "呪い＆ダメージUP", "2＆1", "大＆小", "相手＆自",
                        "呪い（大）（相手/2T）＆ダメージUP（小）（自/1T）"),
        /**
         * 呪い無効（味方/1T）＆ATK UP（小）（自/1T）
         */
        ANNUL_CURSE_FRIEND_1T_AND_ATK_UP_SMALL_SELF_1T("82 & 35", "呪い無効＆ATK_UP", "1＆1", "-＆小", "味方＆自",
                        "呪い無効（味方/1T）＆ATK UP（小）（自/1T）"),
        /**
         * 凍結無効（味方/1T）＆ATK UP（小）（自/1T）
         */
        ANNUAL_FREEZE_FRIEND_1T_AND_ATK_UP_SMALL_SELF_1T("84 & 35", "凍結無効＆ATK UP", "1＆1", "-＆小", "味方＆自",
                        "凍結無効（味方/1T）＆ATK UP（小）（自/1T）"),
        /**
         * 凍結無効（味方/1T）＆ATK UP（中）（自/1T）
         */
        ANNUAL_FREEZE_FRIEND_1T_AND_ATK_UP_MEDIUM_SELF_1T("84 & 36", "凍結無効＆ATK_UP", "1＆1", "-＆中", "味方＆自",
                        "凍結無効（味方/1T）＆ATK UP（中）（自/1T）"),

        /**
         * 暗闇無効（自/1T）＆HP継続回復（小）（自/3T）
         */
        ANNUAL_DARKNESS_SELF_1T_AND_HP_CONTINUOUS_RECOVER_SMALL_SELF_3T("86 & 71", "暗闇無効＆HP継続回復", "1＆3",
                        "-＆小", "自＆自", "暗闇無効（自/1T）＆HP継続回復（小）（自/3T）"),
        /**
         * 暗闇無効（自/1T）＆呪い（大）（相手/2T）
         */
        ANNUAL_DARKNESS_SELF_1T_AND_CURSE_LARGE_ENEMY_2T("86 & 80", "暗闇無効＆呪い", "1＆2", "-＆大", "自＆相手",
                        "暗闇無効（自/1T）＆呪い（大）（相手/2T）"),
        /**
         *暗闇無効（味方/1T）＆ダメージDOWN（小）（相手/1T）
         */
        ANNUAL_DARKNESS_FRIEND_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T(" 87 & 21", "暗闇無効＆ダメージDOWN", "1＆1",
                        "-＆小", "味方＆相手", "暗闇無効（味方/1T）＆ダメージDOWN（小）（相手/1T）"),
        /**
         * デバフ解除（味方）＆ATK DOWN（小）（相手/1T） */
        REMOVE_DEBUFF_FRIEND_AND_ATK_DOWN_SMALL_ENEMY_1T("88 & 2", "デバフ解除＆ATK DOWN", "-＆1", "-＆小", "味方＆相手",
                        "デバフ解除（味方）＆ATK DOWN（小）（相手/1T）"),
        /**
         * 回避（小）（自/1T）＆ATK DOWN（小）（相手/1T）
         */
        EVADE_SMALL_SELF_1T_AND_ATK_DOWN_SMALL_ENEMY_1T("76 & 2", "回避＆ATK DOWN", "1＆1", "小＆小", "自＆相手",
                        "回避（小）（自/1T）＆ATK DOWN（小）（相手/1T）"),
        /**
         * 呪い（中）（相手/2T）＆ダメージDOWN（小）（相手/1T）
         */
        CURSE_MEDIUM_ENEMY_2T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T("79 & 21", "呪い＆ダメージDOWN", "2＆1", "中＆小", "相手＆相手",
                        "呪い（中）（相手/2T）＆ダメージDOWN（小）（相手/1T）"),
        /**
         * ダメージUP（中）（自/1T）＆HP回復（極小）
         */
        DAMAGE_UP_MEDIUM_SELF_1T_AND_HP_RECOVER_MINIMUM("59 & 67", "ダメージUP＆HP回復＆-", "1＆-", "中＆極小", "自＆-",
                        "ダメージUP（中）（自/1T）＆HP回復（極小）"),
        /**
         * ATK DOWN（中）（相手/1T）＆ATK UP（小）（自/1T）
         */
        ATK_DOWN_MEDIUM_ENEMY_1T_AND_ATK_UP_SMALL_SELF_1T(" 3 & 35", "ATK DOWN＆ATK UP", "1＆1", "中＆小", "相手＆自",
                        "ATK DOWN（中）（相手/1T）＆ATK UP（小）（自/1T）");

        /**
         * バフデバフ区分.
         */
        private final String buffDebuffGrouping;

        /**
         * タイプ.
         */
        private final String type;

        /**
         * ターン.
         */
        private final String tern;

        /**
         * 強さ.
         */
        private final String strength;

        /**
         * 名称.
         */
        private final String name;

        /**
         * 対象.
         */
        private final String target;

        /**
         * typeを取得する.
         *
         * @param buffDebuffGrouping
         * @return type
         */
        public static String getType(final String buffDebuffGrouping) {
                for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                        if (buffDebuffGroupingEnum.getBuffDebuffGrouping().equals(buffDebuffGrouping)) {
                                return buffDebuffGroupingEnum.getType();
                        }
                }
                return "";
        }

        /**
         * nameを取得する.
         *
         * @param buffDebuffGrouping
         * @return name
         */
        public static String getName(final String buffDebuffGrouping) {
                for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                        if (buffDebuffGroupingEnum.getBuffDebuffGrouping().equals(buffDebuffGrouping)) {
                                return buffDebuffGroupingEnum.getName();
                        }
                }
                return "";
        }

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
         * @return List<BuffDebuffGroupingEnum>
         */
        public static List<BuffDebuffGroupingEnum> getValue(String type) {
                List<BuffDebuffGroupingEnum> valueList = new ArrayList<>();
                for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                        if (buffDebuffGroupingEnum.getType().equals(type)) {
                                valueList.add(buffDebuffGroupingEnum);
                        }
                }
                return valueList;
        }

        /**
         * buffDebuffGroupingからBuffDebuffGroupingEnumを取得する.
         *
         * @param buffDebuffGrouping
         * @return BuffDebuffGroupingEnum
         */
        public static BuffDebuffGroupingEnum getValueOfBuffDebuffGrouping(final String buffDebuffGrouping) {
                for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                        if (buffDebuffGroupingEnum.getBuffDebuffGrouping().equals(buffDebuffGrouping)) {
                                return buffDebuffGroupingEnum;
                        }
                }
                return HYPHEN;
        }

        /**
         * nameからBuffDebuffGroupingEnumを取得する.
         * @param name
         * @return BuffDebuffGroupingEnum
         */
        public static BuffDebuffGroupingEnum getValueOfName(final String name) {
                for (BuffDebuffGroupingEnum buffDebuffGroupingEnum : BuffDebuffGroupingEnum.values()) {
                        if (buffDebuffGroupingEnum.getName().equals(name)) {
                                return buffDebuffGroupingEnum;
                        }
                }
                return HYPHEN;
        }

        /**
         * typeの一覧を取得する.
         *
         * @param typeArray
         * @return typeArray
         */
        public static String[] getTypeArray(final String[] typeArray) {
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
         * constructor.
         *
         * @param buffDebuffGrouping
         * @param type
         * @param tern
         * @param strength
         * @param target
         * @param name
         */
        private BuffDebuffGroupingEnum(final String buffDebuffGrouping, final String type, final String tern,
                        final String strength, final String target, final String name) {
                this.buffDebuffGrouping = buffDebuffGrouping;
                this.type = type;
                this.tern = tern;
                this.strength = strength;
                this.target = target;
                this.name = name;

        }
}
