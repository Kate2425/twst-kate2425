package com.example.twst.form;

import java.io.Serializable;

import com.example.twst.domain.model.Card;

import lombok.Data;

@Data
public class OrganizeForm implements Serializable {

    private Card[] cardArray;

    /**
     * インデックス
     */
    private int arrayIndex;

}
