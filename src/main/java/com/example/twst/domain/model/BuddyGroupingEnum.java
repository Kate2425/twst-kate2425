package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum BuddyGroupingEnum {
    HYPHEN(0, "-"),
    HP_UP_SMALL(1, "HP UP(小)"),
    HP_UP_MIDDLE(2, "HP UP(中)"),
    ATK_UP_SMALL(3, "ATK UP(小)"),
    ATK_UP_MIDDLE(4, "ATK UP(中)"),
    HP_AND_ATK_UP_SMALL(5, "HP&ATK UP(小)");

    /**
     * バディ区分.
     */
    private final int buddyGrouping;

    /**
     * 効果.
     */
    private final String effect;

    /**
     * effectを取得する.
     * 
     * @param buddyGrouping
     * @return effect
     */
    public static String getEffect(int buddyGrouping) {
        for (BuddyGroupingEnum buddyGroupingEnum : BuddyGroupingEnum.values()) {
            if (buddyGroupingEnum.getBuddyGrouping() == buddyGrouping) {
                return buddyGroupingEnum.getEffect();
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
    public static List<BuddyGroupingEnum> getValue(int buddyGrouping) {
        List<BuddyGroupingEnum> valueList = new ArrayList<>();
        for (BuddyGroupingEnum buddyGroupingEnum : BuddyGroupingEnum.values()) {
            if (buddyGroupingEnum.getBuddyGrouping() == buddyGrouping) {
                valueList.add(buddyGroupingEnum);
            }
        }
        return valueList;
    }

    /**
     * buddyGroupingからEnumを取得する.
     * @param buddyGrouping
     * @return BuddyGroupingEnum
     */
    public static BuddyGroupingEnum getValueOfBuddyGrouping(int buddyGrouping) {
        for (BuddyGroupingEnum buddyGroupingEnum : BuddyGroupingEnum.values()) {
            if (buddyGroupingEnum.getBuddyGrouping() == buddyGrouping) {
                return buddyGroupingEnum;
            }
        }
        return HYPHEN;
    }

    /**
     * constructor
     * 
     * @param buddyGrouping
     * @param effect
     */
    private BuddyGroupingEnum(int buddyGrouping, String effect) {
        this.buddyGrouping = buddyGrouping;
        this.effect = effect;
    }
}
