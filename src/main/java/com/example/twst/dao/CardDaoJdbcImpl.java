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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.twst.domain.model.BuddyGroupingEnum;
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;

@Repository("CardDaoJdbcImpl")
public class CardDaoJdbcImpl implements CardDao {

    private NamedParameterJdbcTemplate jdbc;

    /** 
     * Cardテーブルにデータを1件insert.
     * 
     * @param Card
     * @return 件数
     * @throws DataAccessException 
     */
    @Override
    public int insertOne(CardForm cardForm) throws DataAccessException {

        // 現在時刻を取得
        long millis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(millis);

        String tableName = cardForm.getTableName().getTableName();

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
                + ", buddy3_grouping"
                + ", magic1_buffdebuff_grouping"
                + ", magic2_buffdebuff_grouping"
                + ", magic3_buffdebuff_grouping)"
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
                + ", :buddy3_grouping"
                + ", :magic1_buffdebuff_grouping"
                + ", :magic2_buffdebuff_grouping"
                + ", :magic3_buffdebuff_grouping)";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",
                        TableEnum.getIdOfTableName(tableName) + "_"
                                + cardForm.getName().getCharacterName().toLowerCase())
                .addValue("name", cardForm.getName().getCharacterName())
                .addValue("rare", cardForm.getRare())
                .addValue("type", cardForm.getType())
                .addValue("buddy1", cardForm.getBuddy1().getCharacterName())
                .addValue("buddy2", cardForm.getBuddy2().getCharacterName())
                .addValue("buddy3", cardForm.getBuddy3().getCharacterName())
                .addValue("magic1_grouping", cardForm.getMagic1().getMagicGrouping())
                .addValue("magic2_grouping", cardForm.getMagic2().getMagicGrouping())
                .addValue("magic3_grouping", cardForm.getMagic3().getMagicGrouping())
                .addValue("duo", cardForm.getDuo().getCharacterName())
                .addValue("min_hp", cardForm.getMinHp())
                .addValue("min_atk", cardForm.getMinAtk())
                .addValue("max_hp", cardForm.getMaxHp())
                .addValue("max_atk", cardForm.getMaxAtk())
                .addValue("regist_usr", "Kate")
                .addValue("regist_date", timestamp)
                .addValue("valid_flg", cardForm.getValidFlg())
                .addValue("buddy1_grouping", cardForm.getBuddy1Grouping().getBuddyGrouping())
                .addValue("buddy2_grouping", cardForm.getBuddy2Grouping().getBuddyGrouping())
                .addValue("buddy3_grouping", cardForm.getBuddy3Grouping().getBuddyGrouping())
                .addValue("magic1_buffdebuff_grouping",
                        cardForm.getMagic1BuffdebuffGrouping().getBuffDebuffGrouping())
                .addValue("magic2_buffdebuff_grouping",
                        cardForm.getMagic2BuffdebuffGrouping().getBuffDebuffGrouping())
                .addValue("magic3_buffdebuff_grouping",
                        cardForm.getMagic3BuffdebuffGrouping().getBuffDebuffGrouping());

        return jdbc.update(sql, params);
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
        String[] buffDebuffParam = form.getBuffDebuffChecks();
        String[] buddyParam = form.getBuddyChecks();
        String[] duoParam = form.getDuoChecks();
        String include1 = form.getInclude1();
        String include2 = form.getInclude2();
        String include3 = form.getInclude3();

        String sql = " SELECT id"
                + ", num"
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
                + ", valid_flg"
                + ", buddy1_grouping"
                + ", buddy2_grouping"
                + ", buddy3_grouping"
                + ", magic1_buffdebuff_grouping"
                + ", magic2_buffdebuff_grouping"
                + ", magic3_buffdebuff_grouping"
                + " FROM " + tableName;

        // nameに指定があればsqlに追加する
        Set<String> names = new HashSet<>();
        if (nameParam.length != 0) {
            names = getParam(nameParam);
            sql += " WHERE name IN (:names)";
        }

        // rareに指定があればsqlに追加する
        Set<String> rares = new HashSet<>();
        if (rareParam.length != 0) {
            rares = getParam(rareParam);
            // nameが未指定の場合
            if (nameParam.length == 0) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " CAST(rare AS VARCHAR) IN (:rares)";
        }

        // typeに指定があればsqlに追加する
        Set<String> types = new HashSet<>();
        if (typeParam.length != 0) {
            types = getParam(typeParam);
            // name、rareが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " CAST(type AS VARCHAR) IN (:types)";
        }

        // magic1に指定があればsqlに追加する
        Set<Integer> magics1 = new HashSet<>();
        if (magicParam1.length != 0) {
            Integer[] magicGroupingArray1 = MagicGroupingEnum.getMagicTypeArray(magicParam1);
            magics1 = getIntParam(magicGroupingArray1);
            // name、rare、typeの全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include1.equals("include")) {
                sql += " magic1_grouping  IN(:magics1)";
            } else {
                sql += " magic1_grouping NOT IN(:magics1)";
            }
        }

        // magic2に指定があればsqlに追加する
        Set<Integer> magics2 = new HashSet<>();
        if (magicParam2.length != 0) {
            Integer[] magicGroupingArray2 = MagicGroupingEnum.getMagicTypeArray(magicParam2);
            magics2 = getIntParam(magicGroupingArray2);
            // name、rare、type、magic1の全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)
                    && (magicParam1.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include2.equals("include")) {
                sql += " magic2_grouping IN(:magics2)";
            } else {
                sql += " magic2_grouping NOT IN(:magics2)";
            }
        }

        // magic3に指定があればsqlに追加する
        Set<Integer> magics3 = new HashSet<>();
        if (magicParam3.length != 0) {
            Integer[] magicGroupingArray3 = MagicGroupingEnum.getMagicTypeArray(magicParam3);
            magics3 = getIntParam(magicGroupingArray3);
            // name、rare、type、magic1、magic2の全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)
                    && (magicParam1.length == 0)
                    && (magicParam2.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            if (include3.equals("include")) {
                sql += " magic3_grouping IN(:magics3)";
            } else {
                sql += " magic3_grouping NOT IN(:magics3)";
            }
        }

        // buddyに指定があればsqlに追加する
        Set<String> buddies = new HashSet<>();
        if (buddyParam.length != 0) {
            buddies = getParam(buddyParam);
            // name、rare、type、magic1、magic2、magic3の全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)
                    && (magicParam1.length == 0)
                    && (magicParam2.length == 0)
                    && (magicParam3.length == 0)) {
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
        if (duoParam.length != 0) {
            duos = getParam(duoParam);
            // name、rare、type、magic1、magic2、magic3、buddyの全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)
                    && (magicParam1.length == 0)
                    && (magicParam2.length == 0)
                    && (magicParam3.length == 0)
                    && (buddyParam.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " duo IN (:duos)";
        }

        // buffDebuffに指定があればsqlに追加する
        Set<String> buffDebuff = new HashSet<>();
        if (buffDebuffParam.length != 0) {
            String[] buffDebuffGroupingArray = BuffDebuffGroupingEnum.getTypeArray(buffDebuffParam);
            buffDebuff = getParam(buffDebuffGroupingArray);
            // name、rare、type、magic1、magic2、magic3、buddy、duoの全てが未指定の場合
            if ((nameParam.length == 0)
                    && (rareParam.length == 0)
                    && (typeParam.length == 0)
                    && (magicParam1.length == 0)
                    && (magicParam2.length == 0)
                    && (magicParam3.length == 0)
                    && (buddyParam.length == 0)
                    && (duoParam.length == 0)) {
                sql += " WHERE";
            } else {
                sql += " AND";
            }
            sql += " ( magic1_buffdebuff_grouping LIKE any (array [:buffDebuff])";
            sql += " OR magic2_buffdebuff_grouping LIKE any (array [:buffDebuff])";
            sql += " OR magic3_buffdebuff_grouping LIKE any (array [:buffDebuff]))";
        }

        // parameter
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("names", names)
                .addValue("rares", rares)
                .addValue("types", types)
                .addValue("magics1", magics1)
                .addValue("magics2", magics2)
                .addValue("magics3", magics3)
                .addValue("buffDebuff", buffDebuff)
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
            // TableEnum tableObject = EnumUtils.getViewName(TableEnum.class, tableName);
            card.setTableName(TableEnum.getValueOfTableName(tableName));// テーブル名
            card.setId((String) map.get("id"));// カードID
            card.setClothingName(getClothingName(tableName, (String) map.get("name"))); // 衣装
            card.setNum((int) map.get("num"));// 項番
            card.setName(CharacterEnum.getValueOfCharacterName((String) map.get("name")));// キャラクター名
            card.setRare((String) map.get("rare"));// レア度
            card.setType((String) map.get("type"));// タイプ
            card.setBuddy1(CharacterEnum.getValueOfCharacterName((String) map.get("buddy1")));// バディ１
            card.setBuddy1Grouping(BuddyGroupingEnum.getValueOfBuddyGrouping((int) map.get("buddy1_grouping")));// バディ１区分
            card.setBuddy2(CharacterEnum.getValueOfCharacterName((String) map.get("buddy2")));// バディ２
            card.setBuddy2Grouping(BuddyGroupingEnum.getValueOfBuddyGrouping((int) map.get("buddy2_grouping"))); // バディ２効果 
            card.setBuddy3(CharacterEnum.getValueOfCharacterName((String) map.get("buddy3")));// バディ３
            card.setBuddy3Grouping(BuddyGroupingEnum.getValueOfBuddyGrouping((int) map.get("buddy3_grouping")));// バディ３効果
            MagicGroupingEnum magic1Object = MagicGroupingEnum
                    .getValueOfMagicGrouping((int) map.get("magic1_grouping"));
            card.setMagic1(magic1Object);// マジック１
            MagicGroupingEnum magic2Object = MagicGroupingEnum
                    .getValueOfMagicGrouping((int) map.get("magic2_grouping"));
            card.setMagic2(magic2Object); // マジック２
            MagicGroupingEnum magic3Object = MagicGroupingEnum
                    .getValueOfMagicGrouping((int) map.get("magic3_grouping"));
            card.setMagic3(magic3Object);// マジック３
            card.setDuo(CharacterEnum.getValueOfCharacterName((String) map.get("duo")));// デュオ
            card.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum
                    .getValueOfBuffDebuffGrouping((String) map.get("magic1_buffDebuff_grouping")));// マジック１バフ区分
            card.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum
                    .getValueOfBuffDebuffGrouping((String) map.get("magic2_buffDebuff_grouping")));// マジック２バフ区分
            card.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum
                    .getValueOfBuffDebuffGrouping((String) map.get("magic3_buffDebuff_grouping")));// マジック３バフ区分
            card.setMinHp((BigDecimal) map.get("min_hp"));// 初期HP
            card.setMinAtk((BigDecimal) map.get("min_atk"));// 初期ATK
            card.setMaxHp((BigDecimal) map.get("max_hp"));// 最大HP
            card.setMaxAtk((BigDecimal) map.get("max_atk"));// 最大ATK
            card.setValidFlg((boolean) map.get("valid_flg"));// 有効フラグ

            // 結果返却用のListに追加
            cardList.add(card);
        }
        cardList = cardList.stream().sorted(Comparator.comparing(Card::getNum)).collect(Collectors.toList());
        return cardList;
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
        String tableName = cardForm.getTableName().getTableName();

        // SQL
        String sql = "UPDATE " + tableName
                + " SET";

        // rareに指定があればsqlに追加する
        String rare = "";
        if (cardForm.getRare() != null) {
            sql += " rare = :rare";
            rare = cardForm.getRare();
        }

        // typeに指定があればsqlに追加する
        Set<String> type = new HashSet<>();
        if (cardForm.getType() != null) {
            if (cardForm.getRare() != null) {
                sql += ", type = :type";
                type.add(cardForm.getType());
            } else {
                sql += " type = :type";
            }
        }

        //buddy1に指定があればsqlに追加する
        Set<String> buddy1 = new HashSet<>();
        if (cardForm.getBuddy1() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null)) {
                sql += ", buddy1 = :buddy1";
            } else {
                sql += " buddy1 = :buddy1";
            }
            buddy1.add(cardForm.getBuddy1().getCharacterName());
        }

        //buddy2に指定があればsqlに追加する
        Set<String> buddy2 = new HashSet<>();
        if (cardForm.getBuddy2() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)) {
                sql += ", buddy2 = :buddy2";
            } else {
                sql += " buddy2 = :buddy2";
            }
            buddy2.add(cardForm.getBuddy2().getCharacterName());
        }

        //buddy3に指定があればsqlに追加する
        Set<String> buddy3 = new HashSet<>();
        if (cardForm.getBuddy3() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null)) {
                sql += ", buddy3 = :buddy3";
            } else {
                sql += " buddy3 = :buddy3";
            }
            buddy3.add(cardForm.getBuddy3().getCharacterName());
        }

        //magic1Groupingに指定があればsqlに追加する
        Set<Integer> magic1Grouping = new HashSet<>();
        if (cardForm.getMagic1() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)) {
                sql += ", magic1_grouping = :magic1Grouping";
            } else {
                sql += " magic1_grouping = :magic1Grouping";
            }
            magic1Grouping.add(cardForm.getMagic1().getMagicGrouping());
        }

        //magic2Groupingに指定があればsqlに追加する
        Set<Integer> magic2Grouping = new HashSet<>();
        if (cardForm.getMagic2() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null)) {
                sql += ", magic2_grouping = :magic2Grouping";
            } else {
                sql += " magic2_grouping = :magic2Grouping";
            }
            magic2Grouping.add(cardForm.getMagic2().getMagicGrouping());
        }

        //magic3Groupingに指定があればsqlに追加する
        Set<Integer> magic3Grouping = new HashSet<>();
        if (cardForm.getMagic3() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)) {
                sql += ", magic3_grouping = :magic3Grouping";
            } else {
                sql += " magic3_grouping = :magic3Grouping";
            }
            magic3Grouping.add(cardForm.getMagic3().getMagicGrouping());
        }

        //maxHpに指定があればsqlに追加する
        Set<BigDecimal> maxHp = new HashSet<>();
        if (cardForm.getMaxHp() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null)) {
                sql += ", max_hp = :maxHp";
            } else {
                sql += " max_hp = :maxHp";
            }
            maxHp.add(cardForm.getMaxHp());
        }

        //maxAtkに指定があればsqlに追加する
        Set<BigDecimal> maxAtk = new HashSet<>();
        if (cardForm.getMaxAtk() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)) {
                sql += ", max_atk = :maxAtk";
            } else {
                sql += " max_atk = :maxAtk";
            }
            maxAtk.add(cardForm.getMaxAtk());
        }

        //minHpに指定があればsqlに追加する
        Set<BigDecimal> minHp = new HashSet<>();
        if (cardForm.getMinHp() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null)) {
                sql += ", min_hp = :minHp";
            } else {
                sql += " min_hp = :minHp";
            }
            minHp.add(cardForm.getMinHp());
        }

        //minAtkに指定があればsqlに追加する
        Set<BigDecimal> minAtk = new HashSet<>();
        if (cardForm.getMinAtk() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)) {
                sql += ", min_atk = :minAtk";
            } else {
                sql += " min_atk = :minAtk";
            }
            minAtk.add(cardForm.getMinAtk());
        }

        //duoに指定があればsqlに追加する
        Set<String> duo = new HashSet<>();
        if (cardForm.getDuo() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null)) {
                sql += ", duo = :duo";
            } else {
                sql += " duo = :duo";
            }
            duo.add(cardForm.getDuo().getCharacterName());
        }

        //magic1BuffdebuffGroupingに指定があればsqlに追加する
        Set<String> magic1BuffdebuffGrouping = new HashSet<>();
        if (cardForm.getMagic1BuffdebuffGrouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)) {
                sql += ", magic1_buffdebuff_grouping =:magic1BuffdebuffGrouping";
            } else {
                sql += " magic1_buffdebuff_grouping =:magic1BuffdebuffGrouping";
            }
            magic1BuffdebuffGrouping.add(cardForm.getMagic1BuffdebuffGrouping().getBuffDebuffGrouping());
        }

        //magic2BuffdebuffGroupingに指定があればsqlに追加する
        Set<String> magic2BuffdebuffGrouping = new HashSet<>();
        if (cardForm.getMagic2BuffdebuffGrouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)) {
                sql += ", magic2_buffdebuff_grouping =:magic2BuffdebuffGrouping";
            } else {
                sql += " magic2_buffdebuff_grouping =:magic2BuffdebuffGrouping";
            }
            magic2BuffdebuffGrouping.add(cardForm.getMagic2BuffdebuffGrouping().getBuffDebuffGrouping());
        }

        //magic3BuffdebuffGroupingに指定があればsqlに追加する
        Set<String> magic3BuffdebuffGrouping = new HashSet<>();
        if (cardForm.getMagic3BuffdebuffGrouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic2BuffdebuffGrouping() != null)) {
                sql += ", magic3_buffdebuff_grouping =:magic3BuffdebuffGrouping";
            } else {
                sql += " magic3_buffdebuff_grouping =:magic3BuffdebuffGrouping";
            }
            magic3BuffdebuffGrouping.add(cardForm.getMagic3BuffdebuffGrouping().getBuffDebuffGrouping());
        }

        //validFlgに指定があればsqlに追加する
        Set<Boolean> validFlg = new HashSet<>();
        if (cardForm.getValidFlg() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic2BuffdebuffGrouping() != null)
                    && (cardForm.getMagic3BuffdebuffGrouping() != null)) {
                sql += ", valid_flg = :validFlg";
            } else {
                sql += " valid_flg = :validFlg";
            }
            if (cardForm.getValidFlg().equals("true")) {
                validFlg.add(true);
            } else {
                validFlg.add(false);
            }
        }

        //buddy1Groupingに指定があればsqlに追加する
        Set<Integer> buddy1Grouping = new HashSet<>();
        if (cardForm.getBuddy1Grouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic2BuffdebuffGrouping() != null)
                    && (cardForm.getMagic3BuffdebuffGrouping() != null)
                    && (cardForm.getValidFlg() != null)) {
                sql += ", buddy1_grouping =:buddy1Grouping";
            } else {
                sql += " buddy1_grouping =:buddy1Grouping";
            }
            buddy1Grouping.add(cardForm.getBuddy1Grouping().getBuddyGrouping());
        }

        //buddy2Groupingに指定があればsqlに追加する
        Set<Integer> buddy2Grouping = new HashSet<>();
        if (cardForm.getBuddy2Grouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic2BuffdebuffGrouping() != null)
                    && (cardForm.getMagic3BuffdebuffGrouping() != null)
                    && (cardForm.getValidFlg() != null) && (cardForm.getBuddy1Grouping() != null)) {
                sql += ", buddy2_grouping =:buddy2Grouping";
            } else {
                sql += " buddy2_grouping =:buddy2Grouping";
            }
            buddy2Grouping.add(cardForm.getBuddy2Grouping().getBuddyGrouping());
        }

        //buddy3Groupingに指定があればsqlに追加する
        Set<Integer> buddy3Grouping = new HashSet<>();
        if (cardForm.getBuddy3Grouping() != null) {
            if ((cardForm.getRare() != null) && (cardForm.getType() != null) && (cardForm.getBuddy1() != null)
                    && (cardForm.getBuddy2() != null) && (cardForm.getBuddy3() != null)
                    && (cardForm.getMagic1() != null) && (cardForm.getMagic2() != null)
                    && (cardForm.getMagic3() != null) && (cardForm.getMaxHp() != null)
                    && (cardForm.getMaxAtk() != null) && (cardForm.getMinHp() != null)
                    && (cardForm.getMinAtk() != null) && (cardForm.getDuo() != null)
                    && (cardForm.getMagic1BuffdebuffGrouping() != null)
                    && (cardForm.getMagic2BuffdebuffGrouping() != null)
                    && (cardForm.getMagic3BuffdebuffGrouping() != null)
                    && (cardForm.getValidFlg() != null) && (cardForm.getBuddy1Grouping() != null)
                    && (cardForm.getBuddy2Grouping() != null)) {
                sql += ", buddy3_grouping =:buddy3Grouping";
            } else {
                sql += " buddy3_grouping =:buddy3Grouping";
            }
            buddy3Grouping.add(cardForm.getBuddy3Grouping().getBuddyGrouping());
        }

        sql += " WHERE name = :name";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("rare", rare)
                .addValue("type", type)
                .addValue("buddy1", buddy1)
                .addValue("buddy2", buddy2)
                .addValue("buddy3", buddy3)
                .addValue("magic1Grouping", magic1Grouping)
                .addValue("magic2Grouping", magic2Grouping)
                .addValue("magic3Grouping", magic3Grouping)
                .addValue("magic1BuffdebuffGrouping", magic1BuffdebuffGrouping)
                .addValue("magic2BuffdebuffGrouping", magic2BuffdebuffGrouping)
                .addValue("magic3BuffdebuffGrouping", magic3BuffdebuffGrouping)
                .addValue("duo", duo)
                .addValue("maxHp", maxHp)
                .addValue("maxAtk", maxAtk)
                .addValue("minHp", minHp)
                .addValue("minAtk", minAtk)
                .addValue("validFlg", validFlg)
                .addValue("buddy1Grouping", buddy1Grouping)
                .addValue("buddy2Grouping", buddy2Grouping)
                .addValue("buddy3Grouping", buddy3Grouping)
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
    public int deleteOne(CardForm cardForm) throws DataAccessException {
        String tableName = cardForm.getTableName().getTableName();

        // SQL
        String sql = "DELETE FROM " + tableName
                + " WHERE name = :name";

        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", cardForm.getName().getCharacterName());

        // SQL実行
        return jdbc.update(sql, params);
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
     * パラメーターを取得
     * 
     * @param parameter
     * @return paramSet
     */
    private Set<Integer> getIntParam(Integer[] parameter) {
        Set<Integer> paramSet = new HashSet<>();
        paramSet.addAll(Arrays.asList(parameter));
        return paramSet;
    }

    /**
     * clothingNameを取得
     * 
     * @param tableName
     * @param name
     * @return clothingName
     */
    private String getClothingName(String tableName, String name) {
        String clothingName = tableName;

        switch (name) {
            case "Rollo" -> clothingName = "会長服";
            case "Ortho" -> clothingName = getOrthoId(tableName);
            case "Crowley" -> clothingName = "レイブンジャケット";
            case "Crewel" -> clothingName = "リッチファーコート";
            case "Trein" -> clothingName = "ストリクトスーツ";
            case "Vargas" -> clothingName = "ハンサムジャージ";
            default -> {
                TableEnum tableObject = TableEnum.getValueOfTableName(tableName);
                switch (clothingName) {
                    case "seventh_chapter" -> clothingName = getSeventhChapter(name);
                    case "over_blot" -> clothingName = getOverBlot(name);
                    default -> clothingName = tableObject.getViewName();
                }
            }
        }
        return clothingName;
    }

    /**
     * 七章のidNameを取得.
     * 
     * @param name
     * @return name
     */
    private String getSeventhChapter(String name) {
        switch (name) {
            case "Lilia" -> name = "右大将の甲冑";
            case "Sebek" -> name = "常世の甲冑";
            case "Rook" -> name = "サバナクロー寮服";
            case "Kalim" -> name = "カスルサルタナート制服";
            case "Jade" -> name = "マーメイド・フィン";
            case "Floyd" -> name = "マーメイド・フィン";
            case "Ruggie" -> name = "アイボリークリフ制服";
            case "Cater" -> name = "トリッキングジャケット";
            case "Trey" -> name = "クイーンズシェフコート";
            case "Silver" -> name = "夜明けの甲冑";
        }
        return name;
    }

    /**
     * オルトのidNameを取得.
     * 
     * @param tableName
     * @return idName
     */
    private String getOrthoId(String tableName) {
        String idName = tableName;
        TableEnum tableObject = TableEnum.getValueOfTableName(tableName);
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
            case "rest_my_room" -> idName = "くつろぎ・ギア";
            case "outdoor_wear" -> idName = "アウトドア・ギア";
            default -> idName = tableObject.getViewName();
        }
        return idName;
    }

    /**
     * オーバーブロットのidNameを取得.
     * 
     * @param name
     * @return name
     */
    private String getOverBlot(String name) {
        switch (name) {
            case "Malleus" -> name = "深淵の支配者";
            case "Idea" -> name = "冥府の番人";
            case "Vil" -> name = "美貌の圧制者";
            case "Jamil" -> name = "熱砂の策謀家";
        }
        return name;
    }

    @Autowired
    public void setJdbc(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

}
