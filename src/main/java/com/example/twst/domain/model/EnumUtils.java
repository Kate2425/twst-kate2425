package com.example.twst.domain.model;

import java.util.Arrays;

import com.example.twst.NameInterface;

public class EnumUtils {

    /**
     * Enumの中から、characterNameと一致するものを取得する.
     * 
     * @param <E>
     * @param target
     * @param characterName
     * @return Enum
     */
    @SuppressWarnings("rawtypes")
    public static <E extends Enum & NameInterface> E getViewName(Class<E> target, String characterName) {

        return Arrays.stream(target.getEnumConstants())
                .filter(data -> data.getCharacterName().equals(characterName))
                .findFirst()
                .orElse(null);
    }

    /**
     * Enumの中から、viewNameと一致するものを取得する.
     * 
     * @param <E>
     * @param target
     * @param viewName
     * @return Enum
     */
    @SuppressWarnings("rawtypes")
    public static <E extends Enum & NameInterface> E getCharacterName(Class<E> target, String viewName) {

        return Arrays.stream(target.getEnumConstants())
                .filter(data -> data.getViewName().equals(viewName))
                .findFirst()
                .orElse(null);
    }
}
