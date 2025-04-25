package com.example.twst.domain.model;

import lombok.Data;

@Data
public class MagicGrouping {

    /**
     * マジック区分
     */
    private int magicGrouping;

    /**
     * マジック属性
     */
    private String magicType;

    /**
     * 名称
     */
    private String name;

    /**
     * 効果
     */
    private String effect;

}
