package com.example.twst.dao;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.domain.model.CharacterEnum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

@SpringBootTest
@Transactional
public class CardDaoJdbcImplTest {
    @Autowired
    private CardDaoJdbcImpl target;

    @Test
    @DisplayName("countRecord_正常系")
    void countRecord() throws Exception {
        // Arrange

        // Act
        int count = target.countRecord();

        // Assert
        assertThat(count, is(0));
    }

    @Test
    @DisplayName("insertOne_正常系")
    void insertOne_01() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.BEANS_CAMO);
        cardForm.setName(CharacterEnum.LEONA);
        cardForm.setRare("R");
        cardForm.setType("DEFENCE");
        cardForm.setBuddy1(CharacterEnum.AZUL);
        cardForm.setBuddy2(CharacterEnum.KALIM);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1Grouping("1");
        cardForm.setMagic2Grouping("21");
        cardForm.setMagic3Grouping("0");
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.valueOf(1111));
        cardForm.setMinAtk(BigDecimal.valueOf(2222));
        cardForm.setMaxHp(BigDecimal.valueOf(3333));
        cardForm.setMaxAtk(BigDecimal.valueOf(4444));
        cardForm.setValidFlg(true);
        cardForm.setBuddy1Grouping("2");
        cardForm.setBuddy2Grouping("5");
        cardForm.setBuddy3Grouping("0");
        cardForm.setMagic1BuffdebuffGrouping("1");
        cardForm.setMagic2BuffdebuffGrouping("0");
        cardForm.setMagic3BuffdebuffGrouping("68 & 21");

        // Act
        int count = target.insertOne(cardForm);

        // Assert
        assertThat(count, is(1));
    }

    @Test
    @DisplayName("selectOne_正常系")
    void selectOne() throws Exception {
        // Arrange
        String name = "";

        // Act
        Card card = target.selectOne(name);

        // Assert
        assertThat(card, nullValue());
    }

    @Test
    @DisplayName("selectAll_atk")
    void selectAll_01() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "dormitory_clothing", "tsum_ste" };
        String[] nameChecks = { "Riddle", "Ortho" };
        String[] rareChecks = { "SSR", "SR" };
        String[] typeChecks = { "ATTACK", "BALANCE" };
        String[] magic1Param = { "FIRE", "VOID" };
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buddyChecks = { "Azul", "Sebek" };
        String[] duoChecks = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setInclude1("include");
        searchForm.setInclude2("exclude");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.DORMITORY_CLOTHING)),
                hasProperty("id", is("寮服")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.RIDDLE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.CATER)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.TREY)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.AZUL)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト[II]")),
                hasProperty("magic1Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.AZUL)),
                hasProperty("minHp", is(BigDecimal.valueOf(1962))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1536))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8250))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7979))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_hp")
    void selectAll_02() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "school_personnel", "seventh_chapter" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                hasProperty("id", is("ストリクトスーツ")),
                hasProperty("num", is(5)),
                hasProperty("name", is(CharacterEnum.TREIN)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.CREWEL)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク[II]")),
                hasProperty("magic1Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.CREWEL)),
                hasProperty("minHp", is(BigDecimal.valueOf(3419))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1189))),
                hasProperty("maxHp", is(BigDecimal.valueOf(18013))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4999))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("被ダメージDOWN（大）（味方全体/5T）"))));
    }

    @Test
    @DisplayName("selectAll_name指定なし")
    void selectAll_03() {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "union_birthday", "bloom_birthday",
                "playful_dress" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Cater", "Leona", "Sebek" };
        String[] magic1Param = { "FIRE" };
        String[] magic2Param = { "FIRE", "LEAF" };
        String[] magic3Param = { "VOID" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("exclude");
        searchForm.setInclude2("include");
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.BLOOM_BIRTHDAY)),
                hasProperty("id", is("ブルーム・ギア")),
                hasProperty("num", is(21)),
                hasProperty("name", is(CharacterEnum.ORTHO)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.SEBEK)),
                hasProperty("minHp", is(BigDecimal.valueOf(2361))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1342))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10707))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6528))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("被ダメージDOWN（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("呪い（大）（相手/2T）＆ダメージUP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_type指定")
    void selectAll_04() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "dormitory_clothing", "experimental_clothing", "ceremony_clothing", "gala_couture",
                "makeup_birthday", "sending_star_dress", "scarey_dress", "apprentice_chef", "new_year_dress",
                "seventh_chapter", "rabbit_wear", "platinum_jacket" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "ATTACK", "BALANCE", "DEFENCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.DORMITORY_CLOTHING)),
                hasProperty("id", is("寮服")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.RIDDLE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.CATER)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.TREY)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.AZUL)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト[II]")),
                hasProperty("magic1Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.AZUL)),
                hasProperty("minHp", is(BigDecimal.valueOf(1962))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1536))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8250))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7979))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_magic1指定")
    void selectAll_05() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "beans_camo" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "FIRE" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        searchForm.setInclude1("include");
        searchForm.setSort("atk");

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.BEANS_CAMO)),
                hasProperty("id", is("ビーンズ・カモ")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.CATER)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1561))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1128))),
                hasProperty("maxHp", is(BigDecimal.valueOf(5057))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4466))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic2指定")
    void selectAll_06() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "roll_playing_bridegroom" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.ROLL_PLAYING_BRIDEGROOM)),
                hasProperty("id", is("なりきり花婿")),
                hasProperty("num", is(2)),
                hasProperty("name", is(CharacterEnum.ACE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.IDEA)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト[II]")),
                hasProperty("magic1Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.RIDDLE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2065))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1458))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8683))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7574))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/3T）"))));
    }

    @Test
    @DisplayName("selectAll_magic3指定")
    void selectAll_07() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "outdoor_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "FIRE" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("exclude");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.OUTDOOR_WEAR)),
                hasProperty("id", is("アウトドア・ウェア")),
                hasProperty("num", is(5)),
                hasProperty("name", is(CharacterEnum.TREY)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JADE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.ORTHO)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト[II]")),
                hasProperty("magic1Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.JADE)),
                hasProperty("minHp", is(BigDecimal.valueOf(1974))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1527))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8300))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7932))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（中）（味方/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buddy指定")
    void selectAll_08() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "jasmin_silk" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Malleus" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.JASMIN_SILK)),
                hasProperty("id", is("ヤーサミーナシルク")),
                hasProperty("num", is(2)),
                hasProperty("name", is(CharacterEnum.TREY)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("リーフショット[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1499))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1059))),
                hasProperty("maxHp", is(BigDecimal.valueOf(4856))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4193))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_duo指定")
    void selectAll_09() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "apple_boa" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Idea" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.APPLE_BOA)),
                hasProperty("id", is("アップル・ボア")),
                hasProperty("num", is(2)),
                hasProperty("name", is(CharacterEnum.EPEL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.KALIM)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.IDEA)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.IDEA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2176))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1450))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9868))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7054))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/3T）"))));
    }

    @Test
    @DisplayName("selectAll_type,name指定")
    void selectAll_10() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "club_wear" };
        String[] nameChecks = { "Jade" };
        String[] rareChecks = {};
        String[] typeChecks = { "ATTACK" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CLUB_WEAR)),
                hasProperty("id", is("クラブ・ウェア")),
                hasProperty("num", is(14)),
                hasProperty("name", is(CharacterEnum.JADE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JAMIL)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.VIL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.MALLEUS)),
                hasProperty("minHp", is(BigDecimal.valueOf(2155))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1471))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9772))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7156))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（大）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_type,rare指定")
    void selectAll_11() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "port_wear" };
        String[] nameChecks = {};
        String[] rareChecks = { "R" };
        String[] typeChecks = { "ATTACK" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PORT_WEAR)),
                hasProperty("id", is("ポート・ウェア")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.FLOYD)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("リーフショット[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1397))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1138))),
                hasProperty("maxHp", is(BigDecimal.valueOf(4526))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4506))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic1,name指定")
    void selectAll_12() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "bloom_birthday" };
        String[] nameChecks = { "Jamil" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "LEAF" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.BLOOM_BIRTHDAY)),
                hasProperty("id", is("ブルーム・バースデー")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.JAMIL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.SILVER)),
                hasProperty("minHp", is(BigDecimal.valueOf(2900))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1128))),
                hasProperty("maxHp", is(BigDecimal.valueOf(14108))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5115))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("無属性ダメージDOWN（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージDOWN（中）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_magic1,rare指定")
    void selectAll_13() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "masquerade_dress" };
        String[] nameChecks = {};
        String[] rareChecks = { "R" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "LEAF" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.MASQUERADE_DRESS)),
                hasProperty("id", is("マスカレード・ドレス")),
                hasProperty("num", is(7)),
                hasProperty("name", is(CharacterEnum.ROOK)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("リーフショット")),
                hasProperty("magic1Effect", is("木属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2190))),
                hasProperty("minAtk", is(BigDecimal.valueOf(776))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10117))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(2933))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic1,type指定")
    void selectAll_14() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "costume_of_all_beasts" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "DEFENCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "VOID" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.COSTUME_OF_ALL_BEASTS)),
                hasProperty("id", is("百獣の装束")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.LILIA)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.JADE)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ボイドショット")),
                hasProperty("magic1Effect", is("無属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2410))),
                hasProperty("minAtk", is(BigDecimal.valueOf(953))),
                hasProperty("maxHp", is(BigDecimal.valueOf(6887))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6231))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("無属性ダメージUP（中）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic2,name指定")
    void selectAll_15() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rabbit_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "FIRE" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.RABBIT_WEAR)),
                hasProperty("id", is("ラビット・ウェア")),
                hasProperty("num", is(2)),
                hasProperty("name", is(CharacterEnum.EPEL)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("ファイアショット[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2763))),
                hasProperty("minAtk", is(BigDecimal.valueOf(908))),
                hasProperty("maxHp", is(BigDecimal.valueOf(13082))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3509))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("呪い（中）（相手/2T）＆ATK DOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic2,rare指定")
    void selectAll_16() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "swiswi_wear" };
        String[] nameChecks = {};
        String[] rareChecks = { "SR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SWISWI_WEAR)),
                hasProperty("id", is("スイスイ・ウェア")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.RIDDLE)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.FLOYD)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.ORTHO)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1682))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1393))),
                hasProperty("maxHp", is(BigDecimal.valueOf(6500))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6595))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("呪い（中）（相手/2T）＆ATK UP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic2,type指定")
    void selectAll_17() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "playful_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "BALANCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PLAYFUL_DRESS)),
                hasProperty("id", is("プレイフル・ドレス")),
                hasProperty("num", is(15)),
                hasProperty("name", is(CharacterEnum.TREY)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.JADE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("ウォーターショット")),
                hasProperty("magic1Effect", is("水属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1879))),
                hasProperty("minAtk", is(BigDecimal.valueOf(910))),
                hasProperty("maxHp", is(BigDecimal.valueOf(7891))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3822))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージDOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("回避（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic2,magic1指定")
    void selectAll_18() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "luxe_couture" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "FIRE" };
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.LUXE_COUTURE)),
                hasProperty("id", is("リュクスクチュール")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.ACE)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.JAMIL)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1724))),
                hasProperty("minAtk", is(BigDecimal.valueOf(991))),
                hasProperty("maxHp", is(BigDecimal.valueOf(7240))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4162))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("暗闇無効（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_magic3,name指定")
    void selectAll_19() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "seventh_chapter" };
        String[] nameChecks = { "Jade" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "WATER" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                hasProperty("id", is("マーメイド・フィン")),
                hasProperty("num", is(6)),
                hasProperty("name", is(CharacterEnum.JADE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.KALIM)),
                hasProperty("buddy2Effect", is("HP&ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.ROOK)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.KALIM)),
                hasProperty("minHp", is(BigDecimal.valueOf(2857))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1153))),
                hasProperty("maxHp", is(BigDecimal.valueOf(13899))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5228))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/2T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージDOWN（大）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_magic3,rare指定")
    void selectAll_20() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "platinum_jacket" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "LEAF" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PLATINUM_JACKET)),
                hasProperty("id", is("プラチナ・ジャケット")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.JAMIL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.VIL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.IDEA)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.IDEA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2143))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1470))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9718))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7151))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）＆ATK UP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("木属性ダメージUP（大）（味方/1T）"))));
    }

    @Test
    @DisplayName("selectAll_magic3,type指定")
    void selectAll_21() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "playful_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "DEFENCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "VOID" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PLAYFUL_DRESS)),
                hasProperty("id", is("プレイフル・ギア")),
                hasProperty("num", is(22)),
                hasProperty("name", is(CharacterEnum.ORTHO)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.JADE)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.JAMIL)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.LEONA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2913))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1129))),
                hasProperty("maxHp", is(BigDecimal.valueOf(14171))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5120))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/2T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）＆ダメージUP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_magic3,magic1指定")
    void selectAll_22() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "school_personnel" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "VOID" };
        String[] magic2Param = {};
        String[] magic3Param = { "VOID" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                hasProperty("id", is("レイブンジャケット")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.CROWLEY)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.VIL)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.GRIM)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ[II]")),
                hasProperty("magic1Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.GRIM)),
                hasProperty("minHp", is(BigDecimal.valueOf(2983))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1350))),
                hasProperty("maxHp", is(BigDecimal.valueOf(14020))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6345))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("HP継続回復（小）（味方/3T）"))));
    }

    @Test
    @DisplayName("selectAll_magic3,magic2指定")
    void selectAll_23() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "school_personnel" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "LEAF" };
        String[] magic3Param = { "FIRE" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                hasProperty("id", is("リッチファーコート")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.CREWEL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ROOK)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.CROWLEY)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ[II]")),
                hasProperty("magic1Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.CROWLEY)),
                hasProperty("minHp", is(BigDecimal.valueOf(2376))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1551))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9991))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(8057))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("-")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("クリティカル（中）（味方/3T）"))));
    }

    @Test
    @DisplayName("selectAll_buddy,name指定")
    void selectAll_24() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "seventh_chapter" };
        String[] nameChecks = { "Sebek" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Ortho" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                hasProperty("id", is("常世の甲冑")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.SEBEK)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.KALIM)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.ORTHO)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.ORTHO)),
                hasProperty("minHp", is(BigDecimal.valueOf(2163))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1458))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9809))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7093))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("被ダメージUP（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("暗闇無効（味方/1T）＆ダメージDOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buddy,rare指定")
    void selectAll_25() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "club_wear" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = { "Kalim" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CLUB_WEAR)),
                hasProperty("id", is("クラブ・ウェア")),
                hasProperty("num", is(2)),
                hasProperty("name", is(CharacterEnum.CATER)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.KALIM)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.KALIM)),
                hasProperty("minHp", is(BigDecimal.valueOf(2077))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1449))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8733))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7527))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buddy,type指定")
    void selectAll_26() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "port_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "BALANCE" };
        String[] buddyChecks = { "Rook" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PORT_WEAR)),
                hasProperty("id", is("ポート・ウェア")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.JACK)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.ROOK)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.ROOK)),
                hasProperty("minHp", is(BigDecimal.valueOf(10468))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1238))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10991))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5199))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("HP継続回復（小）（自/3T）＆ダメージDOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buddy,magic1指定")
    void selectAll_27() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "costume_of_all_beasts" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Kalim" };
        String[] duoChecks = {};
        String[] magic1Param = { "FIRE" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.COSTUME_OF_ALL_BEASTS)),
                hasProperty("id", is("百獣の装束")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.VIL)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.KALIM)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("リーフショット[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1379))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1150))),
                hasProperty("maxHp", is(BigDecimal.valueOf(5212))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5313))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("HP回復（小）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buddy,magic2指定")
    void selectAll_28() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rabbit_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Epel" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.RABBIT_WEAR)),
                hasProperty("id", is("ラビット・ウェア")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.SILVER)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.EPEL)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1937))),
                hasProperty("minAtk", is(BigDecimal.valueOf(890))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8406))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3613))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("HP継続回復（小）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buddy,magic3指定")
    void selectAll_29() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "swiswi_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Riddle" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "LEAF" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SWISWI_WEAR)),
                hasProperty("id", is("スイスイ・ウェア")),
                hasProperty("num", is(5)),
                hasProperty("name", is(CharacterEnum.FLOYD)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.JACK)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.JADE)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ")),
                hasProperty("magic1Effect", is("無属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.RIDDLE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2354))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1346))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10675))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6548))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージDOWN（中）（相手/3T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,name指定")
    void selectAll_30() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "masquerade_dress" };
        String[] nameChecks = { "Idea" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Epel" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.MASQUERADE_DRESS)),
                hasProperty("id", is("マスカレード・ドレス")),
                hasProperty("num", is(8)),
                hasProperty("name", is(CharacterEnum.IDEA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.KALIM)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.EPEL)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.EPEL)),
                hasProperty("minHp", is(BigDecimal.valueOf(2668))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1225))),
                hasProperty("maxHp", is(BigDecimal.valueOf(12979))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5555))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK UP（大）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,rare指定")
    void selectAll_31() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "luxe_couture" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Azul" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.LUXE_COUTURE)),
                hasProperty("id", is("リュクスクチュール")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.VIL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.AZUL)),
                hasProperty("buddy2Effect", is("HP&ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.FLOYD)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.AZUL)),
                hasProperty("minHp", is(BigDecimal.valueOf(2193))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1447))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9945))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7039))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（中）（自/3T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,type指定")
    void selectAll_32() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "nightmare_suits" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "ATTACK" };
        String[] buddyChecks = {};
        String[] duoChecks = { "Trey" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.NIGHTMARE_SUITS)),
                hasProperty("id", is("ナイトメアースーツ")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.LEONA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.TREY)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.IDEA)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.TREY)),
                hasProperty("minHp", is(BigDecimal.valueOf(2082))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1499))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9441))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7292))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("暗闇無効（自/1T）＆呪い（大）（相手/2T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,buddy指定")
    void selectAll_33() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "chiffon_of_night_sky" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Deuce" };
        String[] duoChecks = { "Deuce" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CHIFFON_OF_NIGHT_SKY)),
                hasProperty("id", is("夜空のシフォン")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.RIDDLE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                hasProperty("buddy1Effect", is("HP&ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.ORTHO)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ")),
                hasProperty("magic1Effect", is("無属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.DEUCE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2093))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1446))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8801))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7511))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（大）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("呪い無効（味方/1T）＆ATK UP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,magic1指定")
    void selectAll_34() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "celebrate_of_the_beach" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Riddle" };
        String[] magic1Param = { "LEAF" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude1("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CELEBRATE_OF_THE_BEACH)),
                hasProperty("id", is("渚のセレブレイト")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.JADE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP&ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.RIDDLE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2809))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1173))),
                hasProperty("maxHp", is(BigDecimal.valueOf(13665))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5319))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("HP回復（小）＆ダメージUP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("HP回復（小）＆ダメージDOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,magic2指定")
    void selectAll_35() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rest_my_room" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Silver" };
        String[] magic1Param = {};
        String[] magic2Param = { "FIRE" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.REST_MY_ROOM)),
                hasProperty("id", is("くつろぎマイルーム")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.TREY)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.LEONA)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.SILVER)),
                hasProperty("minHp", is(BigDecimal.valueOf(2644))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1232))),
                hasProperty("maxHp", is(BigDecimal.valueOf(12426))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5790))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("水属性ダメージUP（大）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（大）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_duo,magic3指定")
    void selectAll_36() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rest_my_room" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Jade" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "FIRE" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.REST_MY_ROOM)),
                hasProperty("id", is("くつろぎマイルーム")),
                hasProperty("num", is(9)),
                hasProperty("name", is(CharacterEnum.LILIA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.JADE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.JADE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2401))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1358))),
                hasProperty("maxHp", is(BigDecimal.valueOf(11284))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6382))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）＆ダメージUP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,name指定")
    void selectAll_37() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "masquerade_dress" };
        String[] nameChecks = { "Rollo" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ATK_UP" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.MASQUERADE_DRESS)),
                hasProperty("id", is("会長服")),
                hasProperty("num", is(12)),
                hasProperty("name", is(CharacterEnum.ROLLO)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.GRIM)),
                hasProperty("minHp", is(BigDecimal.valueOf(2264))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1402))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10267))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6820))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（大）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("火属性ダメージUP（極大）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,rare指定")
    void selectAll_38() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "dormitory_clothing" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "クリティカル" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.DORMITORY_CLOTHING)),
                hasProperty("id", is("寮服")),
                hasProperty("num", is(20)),
                hasProperty("name", is(CharacterEnum.MALLEUS)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.SILVER)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.SEBEK)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ[II]")),
                hasProperty("magic1Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.LILIA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2100))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1432))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8829))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7439))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("クリティカル（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("クリティカル（中）（味方/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,type指定")
    void selectAll_39() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "experimental_clothing" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "BALANCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ATK_DOWN" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.EXPERIMENTAL_CLOTHING)),
                hasProperty("id", is("実験着")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.DEUCE)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.JADE)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ")),
                hasProperty("magic1Effect", is("無属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("リーフショット[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2115))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1194))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9094))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5134))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（小）（相手/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,buddy指定")
    void selectAll_40() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "ceremony_clothing" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Cater", "Idea" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ATK_UP" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CEREMONY_CLOTHING)),
                hasProperty("id", is("式典服")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.RIDDLE)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.CATER)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2591))),
                hasProperty("minAtk", is(BigDecimal.valueOf(966))),
                hasProperty("maxHp", is(BigDecimal.valueOf(12268))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3733))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK UP（小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,duo指定")
    void selectAll_41() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "beans_camo" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Jack" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ダメージUP" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.BEANS_CAMO)),
                hasProperty("id", is("ビーンズ・カモ")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.AZUL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.JACK)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.ROOK)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ")),
                hasProperty("magic1Effect", is("無属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.JACK)),
                hasProperty("minHp", is(BigDecimal.valueOf(2756))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1188))),
                hasProperty("maxHp", is(BigDecimal.valueOf(11988))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4811))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（中）（相手/3T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,magic1指定")
    void selectAll_42() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "gala_couture" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "LEAF" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ATK_DOWN" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setInclude1("include");
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.GALA_COUTURE)),
                hasProperty("id", is("フェアリー・ギア")),
                hasProperty("num", is(7)),
                hasProperty("name", is(CharacterEnum.ORTHO)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.JACK)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.SILVER)),
                hasProperty("minHp", is(BigDecimal.valueOf(2382))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1361))),
                hasProperty("maxHp", is(BigDecimal.valueOf(11195))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6396))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（中）（相手/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("HP継続回復（小）（自/3T）＆ダメージUP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,magic2指定")
    void selectAll_43() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "roll_playing_bridegroom" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "属性ダメージUP" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude2("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.ROLL_PLAYING_BRIDEGROOM)),
                hasProperty("id", is("なりきり花婿")),
                hasProperty("num", is(6)),
                hasProperty("name", is(CharacterEnum.LILIA)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.IDEA)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.SEBEK)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("リーフショット")),
                hasProperty("magic1Effect", is("木属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2317))),
                hasProperty("minAtk", is(BigDecimal.valueOf(993))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9105))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3644))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("木属性ダメージUP（中）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK DOWN（小）（相手/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1,magic3指定")
    void selectAll_44() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "sending_star_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "FIRE" };
        String[] buffDebuffParam1 = { "ダメージDOWN" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setInclude3("include");
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SENDING_STAR_DRESS)),
                hasProperty("id", is("星送りの衣")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.DEUCE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP&ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.IDEA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2589))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1253))),
                hasProperty("maxHp", is(BigDecimal.valueOf(12168))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5889))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージDOWN（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK UP（小）（自/1T）＆ダメージDOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff1指定")
    void selectAll_45() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "makeup_birthday" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ダメージDOWN" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.MAKEUP_BIRTHDAY)),
                hasProperty("id", is("おめかしバースデー")),
                hasProperty("num", is(18)),
                hasProperty("name", is(CharacterEnum.DEUCE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.CATER)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.EPEL)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ[II]")),
                hasProperty("magic1Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.EPEL)),
                hasProperty("minHp", is(BigDecimal.valueOf(2285))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1381))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10362))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6718))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージDOWN（中）（相手/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（中）（自/3T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,name指定")
    void selectAll_46() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "scarey_dress" };
        String[] nameChecks = { "Sebek" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "ダメージUP" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SCAREY_DRESS)),
                hasProperty("id", is("スケアリードレス")),
                hasProperty("num", is(22)),
                hasProperty("name", is(CharacterEnum.SEBEK)),
                hasProperty("rare", is("R")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy2Effect", is("-")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ボイドショット")),
                hasProperty("magic1Effect", is("無属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1636))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1018))),
                hasProperty("maxHp", is(BigDecimal.valueOf(5366))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3583))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージUP（極小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,rare指定")
    void selectAll_47() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "apprentice_chef" };
        String[] nameChecks = {};
        String[] rareChecks = { "SR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "ATK_UP" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.APPRENTICE_CHEF)),
                hasProperty("id", is("見習いシェフ")),
                hasProperty("num", is(20)),
                hasProperty("name", is(CharacterEnum.VIL)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ボイドショット[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1807))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1297))),
                hasProperty("maxHp", is(BigDecimal.valueOf(6984))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6141))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("凍結無効（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK UP（小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,type指定")
    void selectAll_48() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "outdoor_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "DEFENCE" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "HP回復" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.OUTDOOR_WEAR)),
                hasProperty("id", is("アウトドア・ウェア")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.SEBEK)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("リーフショット")),
                hasProperty("magic1Effect", is("木属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2551))),
                hasProperty("minAtk", is(BigDecimal.valueOf(887))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10688))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(3024))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージDOWN（中）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("HP回復（小）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,buddy指定")
    void selectAll_49() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "jasmin_silk" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Jamil", "Lilia" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "HP回復" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.JASMIN_SILK)),
                hasProperty("id", is("ヤーサミーナシルク")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.CATER)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.JAMIL)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.LILIA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("ファイアショット")),
                hasProperty("magic1Effect", is("火属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2027))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1125))),
                hasProperty("maxHp", is(BigDecimal.valueOf(7702))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4275))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("HP回復（小）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,magic1指定")
    void selectAll_50() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "new_year_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "LEAF" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "ATK_UP" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setInclude1("include");
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.NEW_YEAR_DRESS)),
                hasProperty("id", is("ニューイヤー・ギア")),
                hasProperty("num", is(10)),
                hasProperty("name", is(CharacterEnum.ORTHO)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("ATK UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.FLOYD)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("ファイアショット[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1886))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1300))),
                hasProperty("maxHp", is(BigDecimal.valueOf(7836))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5778))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK UP（小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,magic2指定")
    void selectAll_51() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "apple_boa" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "WATER" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "ダメージDOWN" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setInclude2("include");
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.APPLE_BOA)),
                hasProperty("id", is("アップル・ボア")),
                hasProperty("num", is(3)),
                hasProperty("name", is(CharacterEnum.IDEA)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ボイドショット")),
                hasProperty("magic1Effect", is("無属性ダメージ（弱）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("ウォーターショット[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1726))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1226))),
                hasProperty("maxHp", is(BigDecimal.valueOf(6670))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5805))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ダメージDOWN（小）（相手/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2,buffDebuff1指定")
    void selectAll_52() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "tsum_ste" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ダメージUP" };
        String[] buffDebuffParam2 = { "呪い" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.TSUM_STE)),
                hasProperty("id", is("ツムステ")),
                hasProperty("num", is(39)),
                hasProperty("name", is(CharacterEnum.RUGGIE)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("ATK UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("リーフショット")),
                hasProperty("magic1Effect", is("木属性ダメージ（弱）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("リーフショット[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(1792))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1309))),
                hasProperty("maxHp", is(BigDecimal.valueOf(6926))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6198))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（中）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("呪い（小）（相手/2T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff2指定")
    void selectAll_53() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "masquerade_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = { "ATK_UP" };
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.MASQUERADE_DRESS)),
                hasProperty("id", is("マスカレード・ドレス")),
                hasProperty("num", is(6)),
                hasProperty("name", is(CharacterEnum.EPEL)),
                hasProperty("rare", is("SR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                hasProperty("buddy3Effect", is("-")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("ファイアショット[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("magic3Type", is("-")),
                hasProperty("magic3Name", is("-")),
                hasProperty("magic3Effect", is("-")),
                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                hasProperty("minHp", is(BigDecimal.valueOf(2073))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1183))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8613))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5258))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（小）（味方/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("ATK UP（小）（自/1T）")),
                hasProperty("magic3BuffdebuffGrouping", is("-"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,name指定")
    void selectAll_54() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "union_birthday" };
        String[] nameChecks = { "Leona" };
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ダメージUP" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.UNION_BIRTHDAY)),
                hasProperty("id", is("ユニオンバースデー")),
                hasProperty("num", is(20)),
                hasProperty("name", is(CharacterEnum.LEONA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.JAMIL)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("VOID")),
                hasProperty("magic3Name", is("ボイドショット[II]")),
                hasProperty("magic3Effect", is("2連撃の無属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.MALLEUS)),
                hasProperty("minHp", is(BigDecimal.valueOf(2221))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1422))),
                hasProperty("maxHp", is(BigDecimal.valueOf(10072))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(6918))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（大）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,rare指定")
    void selectAll_55() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "club_wear" };
        String[] nameChecks = {};
        String[] rareChecks = { "SSR" };
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ATK_DOWN" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.CLUB_WEAR)),
                hasProperty("id", is("クラブ・ウェア")),
                hasProperty("num", is(4)),
                hasProperty("name", is(CharacterEnum.ACE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.JACK)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.FLOYD)),
                hasProperty("buddy2Effect", is("HP UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.VIL)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.FLOYD)),
                hasProperty("minHp", is(BigDecimal.valueOf(2020))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1492))),
                hasProperty("maxHp", is(BigDecimal.valueOf(8494))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7750))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（大）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ATK DOWN（大）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,type指定")
    void selectAll_56() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "bloom_birthday" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = { "ATTACK" };
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "暗闇無効" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.BLOOM_BIRTHDAY)),
                hasProperty("id", is("ブルーム・バースデー")),
                hasProperty("num", is(10)),
                hasProperty("name", is(CharacterEnum.MALLEUS)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.LEONA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.ORTHO)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("WATER")),
                hasProperty("magic1Name", is("アクアウェーブ")),
                hasProperty("magic1Effect", is("水属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.ACE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2137))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1474))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9691))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7171))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("回避（小）（自/1T）＆ダメージUP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("暗闇無効（味方/1T）＆ダメージDOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,duo指定")
    void selectAll_57() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "costume_of_all_beasts" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = { "Lilia" };
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ATK_DOWN" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.COSTUME_OF_ALL_BEASTS)),
                hasProperty("id", is("百獣の装束")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.LEONA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.ORTHO)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("HP&ATK UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.LILIA)),
                hasProperty("minHp", is(BigDecimal.valueOf(2953))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1089))),
                hasProperty("maxHp", is(BigDecimal.valueOf(15340))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4579))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("HP継続回復（小）（自/3T）＆ATK UP（小）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（小）（自/1T）＆ATK DOWN（小）（相手/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,magic1指定")
    void selectAll_58() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rabbit_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = { "FIRE" };
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ダメージUP" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setInclude1("include");
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.RABBIT_WEAR)),
                hasProperty("id", is("ラビット・ウェア")),
                hasProperty("num", is(1)),
                hasProperty("name", is(CharacterEnum.DEUCE)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.LEONA)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.ORTHO)),
                hasProperty("buddy3Effect", is("ATK UP(中)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("LEAF")),
                hasProperty("magic2Name", is("フォレストストライク[II]")),
                hasProperty("magic2Effect", is("2連撃の木属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.ORTHO)),
                hasProperty("minHp", is(BigDecimal.valueOf(2200))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1443))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9977))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7020))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ダメージUP（大）（自/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（中）（自/3T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,magic2指定")
    void selectAll_59() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "swiswi_wear" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = { "FIRE" };
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "凍結無効" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setInclude2("include");
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.SWISWI_WEAR)),
                hasProperty("id", is("スイスイ・ウェア")),
                hasProperty("num", is(6)),
                hasProperty("name", is(CharacterEnum.LILIA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.ACE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.AZUL)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.KALIM)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.ACE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2801))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1176))),
                hasProperty("maxHp", is(BigDecimal.valueOf(13626))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5333))),
                hasProperty("validFlg", is(true)),
                hasProperty("magic1BuffdebuffGrouping", is("HP継続回復（小）（自/3T）＆ダメージDOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("凍結無効（味方/1T）＆ATK UP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,magic3指定")
    void selectAll_60() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "platinum_jacket" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = { "WATER" };
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ダメージUP", "HP回復" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setInclude3("include");
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PLATINUM_JACKET)),
                hasProperty("id", is("プラチナ・ジャケット")),
                hasProperty("num", is(20)),
                hasProperty("name", is(CharacterEnum.LEONA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("ATK UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.JADE)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.FLOYD)),
                hasProperty("buddy3Effect", is("HP UP(中)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.FLOYD)),
                hasProperty("minHp", is(BigDecimal.valueOf(3056))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1061))),
                hasProperty("maxHp", is(BigDecimal.valueOf(15875))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(4461))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("水属性ダメージDOWN（大）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（中）（自/1T）＆HP回復（極小）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,buddy指定")
    void selectAll_61() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "playful_dress" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = { "Cater", "Ruggie", "Lilia" };
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "凍結無効", "ATK_UP" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("atk");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.PLAYFUL_DRESS)),
                hasProperty("id", is("プレイフル・ドレス")),
                hasProperty("num", is(20)),
                hasProperty("name", is(CharacterEnum.KALIM)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("ATTACK")),
                hasProperty("buddy1", is(CharacterEnum.CATER)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy2Effect", is("HP UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("LEAF")),
                hasProperty("magic1Name", is("フォレストストライク")),
                hasProperty("magic1Effect", is("木属性ダメージ（強）")),
                hasProperty("magic2Type", is("VOID")),
                hasProperty("magic2Name", is("ゼロレイ[II]")),
                hasProperty("magic2Effect", is("2連撃の無属性ダメージ（強）")),
                hasProperty("magic3Type", is("FIRE")),
                hasProperty("magic3Name", is("ファイアショット[II]")),
                hasProperty("magic3Effect", is("2連撃の火属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.CATER)),
                hasProperty("minHp", is(BigDecimal.valueOf(2148))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1475))),
                hasProperty("maxHp", is(BigDecimal.valueOf(9741))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(7175))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK UP（中）（自/3T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("凍結無効（味方/1T）＆ATK UP（小）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3,buffDebuff1指定")
    void selectAll_62() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "rest_my_room" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = { "ATK_DOWN", "ATK_UP" };
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ダメージUP" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.REST_MY_ROOM)),
                hasProperty("id", is("くつろぎマイルーム")),
                hasProperty("num", is(8)),
                hasProperty("name", is(CharacterEnum.IDEA)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("BALANCE")),
                hasProperty("buddy1", is(CharacterEnum.TREY)),
                hasProperty("buddy1Effect", is("HP UP(小)")),
                hasProperty("buddy2", is(CharacterEnum.JACK)),
                hasProperty("buddy2Effect", is("ATK UP(中)")),
                hasProperty("buddy3", is(CharacterEnum.VIL)),
                hasProperty("buddy3Effect", is("HP UP(小)")),
                hasProperty("magic1Type", is("FIRE")),
                hasProperty("magic1Name", is("フレイムブラスト")),
                hasProperty("magic1Effect", is("火属性ダメージ（強）")),
                hasProperty("magic2Type", is("WATER")),
                hasProperty("magic2Name", is("アクアウェーブ[II]")),
                hasProperty("magic2Effect", is("2連撃の水属性ダメージ（強）")),
                hasProperty("magic3Type", is("LEAF")),
                hasProperty("magic3Name", is("リーフショット[II]")),
                hasProperty("magic3Effect", is("2連撃の木属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.JACK)),
                hasProperty("minHp", is(BigDecimal.valueOf(2764))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1185))),
                hasProperty("maxHp", is(BigDecimal.valueOf(12990))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5569))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("HP回復（小）＆ATK DOWN（小）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージUP（大）（自/1T）"))));
    }

    @Test
    @DisplayName("selectAll_buffDebuff3指定")
    void selectAll_63() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "nightmare_suits" };
        String[] nameChecks = {};
        String[] rareChecks = {};
        String[] typeChecks = {};
        String[] buddyChecks = {};
        String[] duoChecks = {};
        String[] magic1Param = {};
        String[] magic2Param = {};
        String[] magic3Param = {};
        String[] buffDebuffParam1 = {};
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = { "ダメージDOWN" };
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameChecks);
        searchForm.setRareChecks(rareChecks);
        searchForm.setTypeChecks(typeChecks);
        searchForm.setBuddyChecks(buddyChecks);
        searchForm.setDuoChecks(duoChecks);
        searchForm.setMagicChecks1(magic1Param);
        searchForm.setMagicChecks2(magic2Param);
        searchForm.setMagicChecks3(magic3Param);
        searchForm.setSort("hp");
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), allOf(
                hasProperty("tableName", is(TableEnum.NIGHTMARE_SUITS)),
                hasProperty("id", is("ナイトメアースーツ")),
                hasProperty("num", is(6)),
                hasProperty("name", is(CharacterEnum.JAMIL)),
                hasProperty("rare", is("SSR")),
                hasProperty("type", is("DEFENCE")),
                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                hasProperty("buddy1Effect", is("HP UP(中)")),
                hasProperty("buddy2", is(CharacterEnum.RUGGIE)),
                hasProperty("buddy2Effect", is("ATK UP(小)")),
                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                hasProperty("buddy3Effect", is("ATK UP(小)")),
                hasProperty("magic1Type", is("VOID")),
                hasProperty("magic1Name", is("ゼロレイ")),
                hasProperty("magic1Effect", is("無属性ダメージ（強）")),
                hasProperty("magic2Type", is("FIRE")),
                hasProperty("magic2Name", is("フレイムブラスト[II]")),
                hasProperty("magic2Effect", is("2連撃の火属性ダメージ（強）")),
                hasProperty("magic3Type", is("WATER")),
                hasProperty("magic3Name", is("ウォーターショット[II]")),
                hasProperty("magic3Effect", is("2連撃の水属性ダメージ（弱）")),
                hasProperty("duo", is(CharacterEnum.RIDDLE)),
                hasProperty("minHp", is(BigDecimal.valueOf(2681))),
                hasProperty("minAtk", is(BigDecimal.valueOf(1226))),
                hasProperty("maxHp", is(BigDecimal.valueOf(13043))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(5559))),
                hasProperty("validFlg", is(false)),
                hasProperty("magic1BuffdebuffGrouping", is("ATK DOWN（中）（相手/1T）")),
                hasProperty("magic2BuffdebuffGrouping", is("-")),
                hasProperty("magic3BuffdebuffGrouping", is("ダメージDOWN（中）（相手/1T）"))));
    }

    @Test
    @DisplayName("updateOne_正常系")
    void updateOne() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.GALA_COUTURE);
        cardForm.setRare("SR");
        cardForm.setType("ATTACK");
        cardForm.setName(CharacterEnum.JADE);
        cardForm.setBuddy1(CharacterEnum.JAMIL);
        cardForm.setBuddy2(CharacterEnum.VIL);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1Grouping("3");
        cardForm.setMagic2Grouping("20");
        cardForm.setMagic3Grouping("0");
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.valueOf(0123));
        cardForm.setMinAtk(BigDecimal.valueOf(1234));
        cardForm.setMaxHp(BigDecimal.valueOf(2345));
        cardForm.setMaxAtk(BigDecimal.valueOf(3456));

        // Act
        int count = target.updateOne(cardForm);

        // Assert
        assertThat(count, is(0));
    }

    @Test
    @DisplayName("deleteOne_正常系")
    void deleteOne() throws Exception {
        // Arrange
        String cardId = "";

        // Act
        int count = target.deleteOne(cardId);

        // Assert
        assertThat(count, is(0));
    }

    @Test
    @DisplayName("csvOut")
    void csvOut() {
        // Act
        target.csvOut();
    }

}
