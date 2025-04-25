package com.example.twst.form;

import java.io.Serializable;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SearchForm implements Serializable {

    /**
     * テーブル名
     */
    @NotEmpty(message = "選択してください")
    private String[] tableNameChecks;

    /**
     * キャラクター名
     */
    private String[] nameChecks;

    /**
     * レア度
     */
    @Size(min = 0, max = 3)
    private String[] rareChecks;

    /**
     * タイプ
     */
    @Size(min = 0, max = 3)
    private String[] typeChecks;

    /**
     * 魔法属性１
     */
    @Size(min = 0, max = 4)
    private String[] magicChecks1;

    /**
     * 魔法属性２
     */
    @Size(min = 0, max = 4)
    private String[] magicChecks2;

    /**
     * 魔法属性３
     */
    @Size(min = 0, max = 4)
    private String[] magicChecks3;

    /*
     * バディ
     */
    private String[] buddyChecks;

    /**
     * デュオ
     */
    private String[] duoChecks;

    /**
     * include or exclude1
     */
    private String include1;

    /**
     * include or exclude2
     */
    private String include2;

    /**
     * include or exclude3
     */
    private String include3;

    /**
     * ソート
     */
    @NotEmpty(message = "選択してください")
    private String sort;

}
