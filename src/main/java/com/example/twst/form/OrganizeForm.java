package com.example.twst.form;

import java.io.Serializable;
import org.springframework.stereotype.Component;

import com.example.twst.domain.model.Card;

import lombok.Data;

@Component
@Data
public class OrganizeForm implements Serializable {

    private Card[] cardArray;

    /**
     * インデックス
     */
    private int arrayIndex;

}
