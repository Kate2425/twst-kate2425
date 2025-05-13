package com.example.twst.domain.model;

import com.example.twst.NameInterface;

public enum TableEnum implements NameInterface {

    DORMITORY_CLOTHING("dormitory_clothing", "寮服"),
    EXPERIMENTAL_CLOTHING("experimental_clothing", "実験着"),
    CEREMONY_CLOTHING("ceremony_clothing", "式典服"),
    BEANS_CAMO("beans_camo", "ビーンズ・カモ"),
    GALA_COUTURE("gala_couture", "ガラ・クチュール"),
    ROLL_PLAYING_BRIDEGROOM("roll_playing_bridegroom", "なりきり花婿"),
    SENDING_STAR_DRESS("sending_star_dress", "星送りの衣"),
    MAKEUP_BIRTHDAY("makeup_birthday", "おめかしバースデー"),
    SCAREY_DRESS("scarey_dress", "スケアリードレス"),
    APPRENTICE_CHEF("apprentice_chef", "見習いシェフ"),
    OUTDOOR_WEAR("outdoor_wear", "アウトドア・ウェア"),
    JASMIN_SILK("jasmin_silk", "ヤーサミーナシルク"),
    UNION_BIRTHDAY("union_birthday", "ユニオンバースデー"),
    NEW_YEAR_DRESS("new_year_dress", "新春の衣"),
    APPLE_BOA("apple_boa", "アップル・ボア"),
    TSUM_STE("tsum_ste", "ツムステ"),
    CLUB_WEAR("club_wear", "クラブ・ウェア"),
    PORT_WEAR("port_wear", "ポート・ウェア"),
    BLOOM_BIRTHDAY("bloom_birthday", "ブルーム・バースデー"),
    MASQUERADE_DRESS("masquerade_dress", "マスカレード・ドレス"),
    COSTUME_OF_ALL_BEASTS("costume_of_all_beasts", "百獣の装束"),
    SEVENTH_CHAPTER("seventh_chapter", "七章"),
    RABBIT_WEAR("rabbit_wear", "ラビット・ウェア"),
    SWISWI_WEAR("swiswi_wear", "スイスイ・ウェア"),
    PLATINUM_JACKET("platinum_jacket", "プラチナ・ジャケット"),
    PLAYFUL_DRESS("playful_dress", "プレイフル・ドレス"),
    LUXE_COUTURE("luxe_couture", "リュクスクチュール"),
    SCHOOL_PERSONNEL("school_personnel", "教職員"),
    REST_MY_ROOM("rest_my_room", "くつろぎマイルーム"),
    NIGHTMARE_SUITS("nightmare_suits", "ナイトメアースーツ"),
    CHIFFON_OF_NIGHT_SKY("chiffon_of_night_sky", "夜空のシフォン"),
    CELEBRATE_OF_THE_BEACH("celebrate_of_the_beach", "渚のセレブレイト");

    /**
     * テーブル名
     */
    private final String characterName;

    /**
     * 表示名
     */
    private final String viewName;

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
     * constructor
     */
    private TableEnum(String characterName, String viewName) {
        this.characterName = characterName;
        this.viewName = viewName;
    }
}
