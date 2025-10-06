package com.example.twst.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.example.twst.NameInterface;

public enum TableEnum implements NameInterface {

    DORMITORY_CLOTHING("dormitory_clothing", "寮服", "dor"),
    EXPERIMENTAL_CLOTHING("experimental_clothing", "実験着", "exp"),
    CEREMONY_CLOTHING("ceremony_clothing", "式典服", "cer"),
    BEANS_CAMO("beans_camo", "ビーンズ・カモ", "bea"),
    GALA_COUTURE("gala_couture", "ガラ・クチュール", "gal"),
    ROLL_PLAYING_BRIDEGROOM("roll_playing_bridegroom", "なりきり花婿", "bri"),
    SENDING_STAR_DRESS("sending_star_dress", "星送りの衣", "sen"),
    MAKEUP_BIRTHDAY("makeup_birthday", "おめかしバースデー", "mak"),
    SCAREY_DRESS("scarey_dress", "スケアリードレス", "sca"),
    APPRENTICE_CHEF("apprentice_chef", "見習いシェフ", "app"),
    OUTDOOR_WEAR("outdoor_wear", "アウトドア・ウェア", "out"),
    JASMIN_SILK("jasmin_silk", "ヤーサミーナシルク", "jas"),
    UNION_BIRTHDAY("union_birthday", "ユニオンバースデー", "uni"),
    NEW_YEAR_DRESS("new_year_dress", "新春の衣", "new"),
    APPLE_BOA("apple_boa", "アップル・ボア", "boa"), //TODO
    TSUM_STE("tsum_ste", "ツムステ", "tsu"),
    CLUB_WEAR("club_wear", "クラブ・ウェア", "clu"),
    PORT_WEAR("port_wear", "ポート・ウェア", "por"),
    BLOOM_BIRTHDAY("bloom_birthday", "ブルーム・バースデー", "blo"),
    MASQUERADE_DRESS("masquerade_dress", "マスカレード・ドレス", "masq"),
    COSTUME_OF_ALL_BEASTS("costume_of_all_beasts", "百獣の装束", "cos"),
    SEVENTH_CHAPTER("seventh_chapter", "七章", "sev"),
    RABBIT_WEAR("rabbit_wear", "ラビット・ウェア", "rab"),
    SWISWI_WEAR("swiswi_wear", "スイスイ・ウェア", "swi"),
    PLATINUM_JACKET("platinum_jacket", "プラチナ・ジャケット", "pla"),
    PLAYFUL_DRESS("playful_dress", "プレイフル・ドレス", "play"),
    LUXE_COUTURE("luxe_couture", "リュクスクチュール", "luxe"),
    SCHOOL_PERSONNEL("school_personnel", "教職員", "sch"),
    REST_MY_ROOM("rest_my_room", "くつろぎマイルーム", "res"),
    NIGHTMARE_SUITS("nightmare_suits", "ナイトメアースーツ", "nig"),
    CHIFFON_OF_NIGHT_SKY("chiffon_of_night_sky", "夜空のシフォン", "chi"),
    CELEBRATE_OF_THE_BEACH("celebrate_of_the_beach", "渚のセレブレイト", "cel"),
    OVER_BLOT("over_blot", "オーバーブロット", "over"),
    CD_DINER("cd_diner", "CDダイナー", "din"),
    LA_BONBONNIERE("la_bonbonniere", "ラ・ボンボニエール", "bon");

    /**
     * テーブル名.
     */
    private final String characterName;

    /**
     * 表示名.
     */
    private final String viewName;

    /**
     * id.
     */
    private final String id;

    /**
     * characterNameを取得する.
     * 
     * @return characterName
     */
    @Override
    public String getCharacterName() {
        return characterName;
    }

    /**
     * viewNameを取得する.
     * 
     * @return viewName
     */
    @Override
    public String getViewName() {
        return viewName;
    }

    /**
     * idを取得する.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * characterNameからidを取得する.
     * @param characterName
     * @return id
     */
    public static String getIdOfCharacterName(String characterName) {
        for (TableEnum tableEnum : TableEnum.values()) {
            if (tableEnum.getCharacterName().equals(characterName)) {
                return tableEnum.getId();
            }
        }
        return null;
    }

    /**
     * viewNameListのリストを取得する.
     * @return viewNameList
     */
    public List<String> getViewNameList() {
        List<String> viewNameList = new ArrayList<>();
        for (TableEnum tableEnum : TableEnum.values()) {
            viewNameList.add(tableEnum.getViewName());
        }
        return viewNameList;
    }

    /**
     * constructor
     */
    private TableEnum(String characterName, String viewName, String id) {
        this.characterName = characterName;
        this.viewName = viewName;
        this.id = id;
    }
}
