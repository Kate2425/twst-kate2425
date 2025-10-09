package com.example.twst.form;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Component
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
     * マジック属性１
     */
    @Size(min = 0, max = 4)
    private String[] magicChecks1;

    /**
     * マジック属性２
     */
    @Size(min = 0, max = 4)
    private String[] magicChecks2;

    /**
     * マジック属性３
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
     * 魔法１効果
     */
    private String[] buffDebuffChecks;

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
