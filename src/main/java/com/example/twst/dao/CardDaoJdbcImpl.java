package com.example.twst.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.EnumUtils;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;

@Repository("CardDaoJdbcImpl")
public class CardDaoJdbcImpl implements CardDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    /**
     * テーブルの件数を取得.
     * 
     * @return count
     */
    @Override
    public int countRecord() {
        int count = 0;
        return count;
    }

    /**
     * Cardテーブルにデータを1件insert.
     * 
     * @param Card
     * @return 件数
     */
    @Override
    public int insertOne(CardForm cardForm) {

        // 現在時刻を取得
        long millis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(millis);

        String tableName = cardForm.getTableName().getCharacterName();

        // SQL文
        String sql = "INSERT INTO " + tableName + "(id"
                + ", name"
                + ", rare"
                + ", type"
                + ", buddy1"
                + ", buddy2"
                + ", buddy3"
                + ", magic1_grouping"
                + ", magic2_grouping"
                + ", magic3_grouping"
                + ", duo"
                + ", min_hp"
                + ", min_atk"
                + ", max_hp"
                + ", max_atk"
                + ", regist_usr"
                + ", regist_date"
                + ", valid_flg"
                + ", buddy1_grouping"
                + ", buddy2_grouping"
                + ", buddy3_grouping)"
                + " VALUES(:id"
                + ", :name"
                + ", :rare"
                + ", :type"
                + ", :buddy1"
                + ", :buddy2"
                + ", :buddy3"
                + ", :magic1_grouping"
                + ", :magic2_grouping"
                + ", :magic3_grouping"
                + ", :duo"
                + ", :min_hp"
                + ", :min_atk"
                + ", :max_hp"
                + ", :max_atk"
                + ", :regist_usr"
                + ", :regist_date"
                + ", :valid_flg"
                + ", :buddy1_grouping"
                + ", :buddy2_grouping"
                + ", :buddy3_grouping)";

        SqlParameterSource params = new MapSqlParameterSource()

                .addValue("id", tableName.substring(0, 3) + "_" + cardForm.getName().getCharacterName().toLowerCase())
                .addValue("name", cardForm.getName().getCharacterName())
                .addValue("rare", cardForm.getRare())
                .addValue("type", cardForm.getType())
                .addValue("buddy1", cardForm.getBuddy1().getCharacterName())
                .addValue("buddy2", cardForm.getBuddy2().getCharacterName())
                .addValue("buddy3", cardForm.getBuddy3().getCharacterName())
                .addValue("magic1_grouping", Integer.valueOf(cardForm.getMagic1Grouping()))
                .addValue("magic2_grouping", Integer.valueOf(cardForm.getMagic2Grouping()))
                .addValue("magic3_grouping", Integer.valueOf(cardForm.getMagic3Grouping()))
                .addValue("duo", cardForm.getDuo().getCharacterName())
                .addValue("min_hp", cardForm.getMinHp())
                .addValue("min_atk", cardForm.getMinAtk())
                .addValue("max_hp", cardForm.getMaxHp())
                .addValue("max_atk", cardForm.getMaxAtk())
                .addValue("regist_usr", "Kate")
                .addValue("regist_date", timestamp)
                .addValue("valid_flg", cardForm.isValidFlg())
                .addValue("buddy1_grouping", Integer.valueOf(cardForm.getBuddy1Grouping()))
                .addValue("buddy2_grouping", Integer.valueOf(cardForm.getBuddy2Grouping()))
                .addValue("buddy3_grouping", Integer.valueOf(cardForm.getBuddy3Grouping()));

        // SQL実行
        return jdbc.update(sql, params);
    }

    /**
     * テーブルのデータを１件取得
     * 
     * @param name
     * @return Card
     */
    @Override
    public Card selectOne(String name) {
        return null;
    }

    /**
     * テーブルの全データを取得.
     * 
     * @param searchForm
     * @param tableName
     * @return cardList
     */
    @Override
    public List<Card> selectMany(SearchForm form, String tableName) throws DataAccessException {
        String[] nameParam = form.getNameChecks();
        String[] rareParam = form.getRareChecks();
        String[] typeParam = form.getTypeChecks();
        String[] magicParam1 = form.getMagicChecks1();
        String[] magicParam2 = form.getMagicChecks2();
        String[] magicParam3 = form.getMagicChecks3();
        String[] buddyParam = form.getBuddyChecks();
        String[] duoParam = form.getDuoChecks();
        String include1 = form.getInclude1();
        String include2 = form.getInclude2();
        String include3 = form.getInclude3();

        // SQL
        String sql = "SELECT"
                + " t1.*"
                + ", t2.magic2_name"
                + ", t2.magic2_type"
                + ", t2.magic2_effect"
                + ", t2.buddy2_effect"
                + ", t3.magic3_name"
                + ", t3.magic3_type"
                + ", t3.magic3_effect"
                + ", t3.buddy3_effect"
                + " FROM ("
                + " SELECT card.id"
                + ", card.num"
                + ", card.name AS card_name"
                + ", card.rare"
                + ", card.type"
                + ", card.buddy1"
                + ", card.buddy2"
                + ", card.buddy3"
                + ", card.duo"
                + ", card.min_hp"
                + ", card.min_atk"
                + ", card.max_hp"
                + ", card.max_atk"
                + ", card.valid_flg"
                + ", magic.name AS magic1_name"
                + ", magic.magic_type AS magic1_type"
                + ", magic.effect AS magic1_effect"
                + ", buddy.effect AS buddy1_effect"
                + " FROM " + tableName + " card"
                + " LEFT JOIN mst_buddy_grouping buddy"
                + " ON card.buddy1_grouping = buddy.buddy_grouping"
                + " LEFT JOIN mst_magic_grouping magic"
                + " ON card.magic1_grouping = magic.magic_grouping"
                + " ) t1"
                + " INNER JOIN ("
                + " SELECT"
                + " card.name"
                + ", magic.name AS magic2_name"
                + ", magic.magic_type AS magic2_type"
                + ", magic.effect AS magic2_effect"
                + ", buddy.effect AS buddy2_effect"
                + " FROM " + tableName + " card"
                + " LEFT JOIN mst_buddy_grouping buddy"
                + " ON card.buddy2_grouping = buddy.buddy_grouping"
                + " LEFT JOIN mst_magic_grouping magic"
                + " ON card.magic2_grouping = magic.magic_grouping"
                + " ) t2 ON t1.card_name = t2.name"
                + " INNER JOIN ("
                + " SELECT"
                + " card.name"
                + ", magic.name AS magic3_name"
                + ", magic.magic_type AS magic3_type"
                + ", magic.effect AS magic3_effect"
                + ", buddy.effect AS buddy3_effect"
                + " FROM " + tableName + " card"
                + " LEFT JOIN mst_buddy_grouping buddy"
                + " ON card.buddy3_grouping = buddy.buddy_grouping"
                + " LEFT JOIN mst_magic_grouping magic"
                + " ON card.magic3_grouping = magic.magic_grouping"
                + " ) t3"
                + " ON t2.name = t3.name";

        // nameに指定があればsqlに追加する
        Set<String> names = new HashSet<>();
        if (nameParam != null && nameParam.length != 0) {
            names = getParam(nameParam);
            sql += " WHERE card_name IN (:names)";
        }

        // rareに指定があればsqlに追加する
        Set<String> rares = new HashSet<>();
        if (rareParam != null && rareParam.length != 0) {
            rares = getParam(rareParam);
            // nameが未指定の場合
            if (nameParam == null) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " CAST(rare AS VARCHAR) IN (:rares)";
        }

        // typeに指定があればsqlに追加する
        Set<String> types = new HashSet<>();
        if (typeParam != null && typeParam.length != 0) {
            types = getParam(typeParam);
            // nameかつrareが未指定の場合
            if ((nameParam == null) && (rareParam == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " CAST(type AS VARCHAR) IN (:types)";
        }

        // magic1に指定があればsqlに追加する
        Set<String> magics1 = new HashSet<>();
        if (magicParam1 != null && magicParam1.length != 0) {
            magics1 = getParam(magicParam1);
            // name、rare、typeの全てが未指定の場合
            if ((nameParam == null) && (rareParam == null) && (typeParam == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include1.equals("include")) {
                sql += " magic1_type IN(:magics1)";
            } else {
                sql += " magic1_type NOT IN(:magics1)";
            }

        }

        // magic2に指定があればsqlに追加する
        Set<String> magics2 = new HashSet<>();
        if (magicParam2 != null && magicParam2.length != 0) {
            magics2 = getParam(magicParam2);
            // name、rare、typeの全てが未指定の場合
            if ((nameParam == null) && (rareParam == null) && (typeParam == null) && (magicParam1 == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include2.equals("include")) {
                sql += " magic2_type IN(:magics2)";
            } else {
                sql += " magic2_type NOT IN(:magics2)";
            }
        }

        // magic1に指定があればsqlに追加する
        Set<String> magics3 = new HashSet<>();
        if (magicParam3 != null && magicParam3.length != 0) {
            magics3 = getParam(magicParam3);
            // name、rare、typeの全てが未指定の場合
            if ((nameParam == null) && (rareParam == null) && (typeParam == null) && (magicParam1 == null)
                    && (magicParam2 == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include3.equals("include")) {
                sql += " magic3_type IN(:magics3)";
            } else {
                sql += " magic3_type NOT IN(:magics3)";
            }
        }

        // buddyに指定があればsqlに追加する
        Set<String> buddies = new HashSet<>();
        if (buddyParam != null && buddyParam.length != 0) {
            buddies = getParam(buddyParam);
            // name、rare、type、magicの全てが未指定の場合
            if ((nameParam == null) && (rareParam == null) && (typeParam == null)
                    && (magicParam1 == null) && (magicParam2 == null) && (magicParam3 == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " (buddy1 IN (:buddies)"
                    + " OR buddy2 IN (:buddies)"
                    + " OR buddy3 IN (:buddies))";
        }

        // duoに指定があればsqlに追加する
        Set<String> duos = new HashSet<>();
        if (duoParam != null && duoParam.length != 0) {
            duos = getParam(duoParam);
            // name、rare、type、magic、buddyの全てが未指定の場合
            if ((nameParam == null) && (rareParam == null) && (typeParam == null)
                    && (magicParam1 == null) && (magicParam2 == null) && (magicParam3 == null)
                    && (buddyParam == null)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " duo IN (:duos)";
        }

        // parameter
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("names", names)
                .addValue("rares", rares)
                .addValue("types", types)
                .addValue("magics1", magics1)
                .addValue("magics2", magics2)
                .addValue("magics3", magics3)
                .addValue("buddies", buddies)
                .addValue("duos", duos);

        // SQL実行
        List<Map<String, Object>> getList = jdbc.queryForList(sql, params);

        // 結果返却用のList
        List<Card> cardList = new ArrayList<>();

        // 取得データ分loop
        for (Map<String, Object> map : getList) {

            // インスタンスの生成
            Card card = new Card();

            // 取得したデータをセット
            TableEnum tableObject = EnumUtils.getViewName(TableEnum.class, tableName);
            card.setTableName(tableObject);// テーブル名

            CharacterEnum viewNameObject = EnumUtils.getViewName(CharacterEnum.class, (String) map.get("card_name"));
            card.setName(viewNameObject);// キャラクター名

            card.setId(getId(tableName, (String) map.get("card_name")));// カードID

            card.setNum((int) map.get("num"));// 項番
            card.setRare((String) map.get("rare"));// レア度
            card.setType((String) map.get("type"));// タイプ

            CharacterEnum buddy1Object = EnumUtils.getViewName(CharacterEnum.class, (String) map.get("buddy1"));
            card.setBuddy1(buddy1Object);// バディ１

            CharacterEnum buddy2Object = EnumUtils.getViewName(CharacterEnum.class, (String) map.get("buddy2"));
            if (buddy2Object == null) {
                card.setBuddy2(EnumUtils.getViewName(CharacterEnum.class, "-"));
            } else {
                card.setBuddy2(buddy2Object);// バディ２
            }

            CharacterEnum buddy3Object = EnumUtils.getViewName(CharacterEnum.class, (String) map.get("buddy3"));

            if (buddy3Object == null) {
                card.setBuddy3(EnumUtils.getViewName(CharacterEnum.class, "-"));
            } else {
                card.setBuddy3(buddy3Object);// バディ３
            }

            CharacterEnum duoObject = EnumUtils.getViewName(CharacterEnum.class, (String) map.get("duo"));
            if (duoObject == null) {
                card.setDuo(EnumUtils.getViewName(CharacterEnum.class, "-"));
            } else {
                card.setDuo(duoObject);// デュオ
            }

            card.setMinHp((BigDecimal) map.get("min_hp"));// 初期HP
            card.setMinAtk((BigDecimal) map.get("min_atk"));// 初期ATK
            card.setMaxHp((BigDecimal) map.get("max_hp"));// 最大HP
            card.setMaxAtk((BigDecimal) map.get("max_atk"));// 最大ATK
            // card.setRegistUsr((String) map.get("regist_usr"));// 登録者
            // card.setRegistDate((Date) map.get("regist_date"));// 登録日時
            card.setValidFlg((boolean) map.get("valid_flg"));// 有効フラグ

            card.setMagic1Name((String) map.get("magic1_name"));// マジック１名称
            card.setMagic1Type((String) map.get("magic1_type"));// マジック１属性
            card.setMagic1Effect((String) map.get("magic1_effect"));// マジック１効果
            card.setBuddy1Effect((String) map.get("buddy1_effect"));// バディ１効果

            card.setMagic2Name((String) map.get("magic2_name"));// マジック２名称
            card.setMagic2Type((String) map.get("magic2_type"));// マジック２属性
            card.setMagic2Effect((String) map.get("magic2_effect"));// マジック２効果
            card.setBuddy2Effect((String) map.get("buddy2_effect"));// バディ２効果

            card.setMagic3Name((String) map.get("magic3_name"));// マジック３名称
            card.setMagic3Type((String) map.get("magic3_type"));// マジック３属性
            card.setMagic3Effect((String) map.get("magic3_effect"));// マジック３効果
            card.setBuddy3Effect((String) map.get("buddy3_effect"));// バディ３効果

            // 結果返却用のListに追加
            cardList.add(card);
        }
        cardList = cardList.stream().sorted(Comparator.comparing(Card::getNum)).collect(Collectors.toList());
        return cardList;

    }

    /**
     * 全テーブルを全取得
     * 
     * @param SearchForm
     * @return resultList
     */
    @Override
    public List<Card> selectAll(SearchForm form) {
        String[] tableNameArray = form.getTableNameChecks();

        // 結果返却用のList
        List<Card> resultList = new ArrayList<>();

        List<List<Card>> allCardList = new ArrayList<>();
        for (String tableName : tableNameArray) {
            allCardList.add(selectMany(form, tableName));
        }

        for (List<Card> tempList : allCardList) {
            for (Card tempCard : tempList) {
                resultList.add(tempCard);
            }
        }

        if (form.getSort().equals("hp")) {
            resultList = resultList.stream().sorted(Comparator.comparing(Card::getMaxHp).reversed())
                    .collect(Collectors.toList());
        } else if (form.getSort().equals("atk")) {
            resultList = resultList.stream().sorted(Comparator.comparing(Card::getMaxAtk).reversed())
                    .collect(Collectors.toList());
        }
        return resultList;
    }

    /**
     * テーブルを１件更新.
     * 
     * @param Card
     * @return count
     * @throws DataAccessException
     */
    @Override
    public int updateOne(CardForm cardForm) throws DataAccessException {
        String tableName = cardForm.getTableName().getCharacterName();

        // SQL
        String sql = "UPDATE " + tableName
                + " SET"
                + " rare = :rare"
                + ", type = :type"
                + ", buddy1 = :buddy1"
                + ", buddy2 = :buddy2"
                + ", buddy3 = :buddy3"
                + ", magic1_grouping = :magic1Grouping"
                + ", magic2_grouping = :magic2Grouping"
                + ", magic3_grouping = :magic3Grouping"
                + ", max_hp = :maxHp"
                + ", max_atk = :maxAtk"
                + ", min_hp = :minHp"
                + ", min_atk = :minAtk"
                + ", duo = :duo"
                + " WHERE name = :name";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("rare", cardForm.getRare())
                .addValue("type", cardForm.getType())
                .addValue("buddy1", cardForm.getBuddy1().getCharacterName())
                .addValue("buddy2", cardForm.getBuddy2().getCharacterName())
                .addValue("buddy3", cardForm.getBuddy3().getCharacterName())
                .addValue("magic1Grouping", Integer.valueOf(cardForm.getMagic1Grouping()))
                .addValue("magic2Grouping", Integer.valueOf(cardForm.getMagic2Grouping()))
                .addValue("magic3Grouping", Integer.valueOf(cardForm.getMagic3Grouping()))
                .addValue("duo", cardForm.getDuo().getCharacterName())
                .addValue("maxHp", cardForm.getMaxHp())
                .addValue("maxAtk", cardForm.getMaxAtk())
                .addValue("minHp", cardForm.getMinHp())
                .addValue("minAtk", cardForm.getMinAtk())
                .addValue("name", cardForm.getName().getCharacterName());

        // SQL実行
        return jdbc.update(sql, params);

    }

    /**
     * テーブルを１件削除.
     * 
     * @param cardId
     * @return count
     * @throws DataAccessException
     */
    @Override
    public int deleteOne(String cardId) throws DataAccessException {
        int count = 0;
        return count;
    }

    /**
     * SQL取得結果をサーバーにCSVで保存する
     * 
     * @throws DataAccessException
     */
    @Override
    public void csvOut() throws DataAccessException {
    }

    /**
     * パラメーターを取得
     * 
     * @param parameter
     * @return paramSet
     */
    private Set<String> getParam(String[] parameter) {
        Set<String> paramSet = new HashSet<>();
        paramSet.addAll(Arrays.asList(parameter));
        return paramSet;
    }

    /**
     * idを取得
     * 
     * @param tableName
     * @param name
     * @return id
     */
    private String getId(String tableName, String name) {
        String id = tableName;

        switch (name) {
            case "Rollo" -> id = "会長服";
            case "Ortho" -> id = getOrthoId(tableName);
            case "Crowley" -> id = "レイブンジャケット";
            case "Crewel" -> id = "リッチファーコート";
            case "Trein" -> id = "ストリクトスーツ";
            default -> {
                TableEnum tableObject = EnumUtils.getViewName(TableEnum.class, tableName);
                if (id.equals("seventh_chapter")) {
                    id = getSeventhChapter(name);
                } else {
                    id = tableObject.getViewName();
                }
            }
        }

        return id;
    }

    /**
     * 七章のidNameを取得.
     * 
     * @param name
     * @return idName
     */
    private String getSeventhChapter(String name) {
        String idName = "";
        switch (name) {
            case "Lilia" -> idName = "右大将の甲冑";
            case "Sebek" -> idName = "常世の甲冑";
            case "Rook" -> idName = "サバナクロー寮服";
            case "Kalim" -> idName = "カスルサルタナート制服";
            case "Jade" -> idName = "マーメイド・フィン";
            case "Floyd" -> idName = "マーメイド・フィン";
            case "Ruggie" -> idName = "アイボリークリフ制服";
            case "Cater" -> idName = "トリッキングジャケット";
            case "Trey" -> idName = "クイーンズシェフコート";
            case "Silver" -> idName = "夜明けの甲冑";
            case "Malleus" -> idName = "深淵の支配者";
        }
        return idName;
    }

    /**
     * オルトのidNameを取得.
     * 
     * @param tableName
     * @return idName
     */
    private String getOrthoId(String tableName) {
        String idName = tableName;
        switch (idName) {
            case "dormitory_clothing" -> idName = "イグニハイド・ギア";
            case "experimental_clothing" -> idName = "プレジション・ギア";
            case "ceremony_clothing" -> idName = "バースト・ギア";
            case "gala_couture" -> idName = "フェアリー・ギア";
            case "sending_star_dress" -> idName = "スターゲイズ・ギア";
            case "makeup_birthday" -> idName = "おめかし・ギア";
            case "scarey_dress" -> idName = "スケアリー・ギア";
            case "apprentice_chef" -> idName = "クッキング・ギア";
            case "union_birthday" -> idName = "ユニオン・ギア";
            case "new_year_dress" -> idName = "ニューイヤー・ギア";
            case "bloom_birthday" -> idName = "ブルーム・ギア";
            case "seventh_chapter" -> idName = "ケルベロス・ギア";
            case "rabbit_wear" -> idName = "ラビット・ギア";
            case "platinum_jacket" -> idName = "プラチナ・ギア";
            case "playful_dress" -> idName = "プレイフル・ギア";
        }
        return idName;
    }

}
