package com.example.twst.dao;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

import com.example.twst.form.SearchForm;
import com.example.twst.domain.model.BuddyGroupingEnum;
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;

@SpringBootTest
@Transactional
public class CardDaoJdbcImplSelectManyTest {
        @Autowired
        private CardDaoJdbcImpl target;

        @Test
        @DisplayName("name,rare,type,magic1,magic2,magic3,buffDebuff指定")
        void selectMany_01() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "bloom_birthday";
                String[] nameChecks = { "Ortho" };
                String[] rareChecks = { "SSR" };
                String[] typeChecks = { "ATTACK" };
                String[] magic1Param = { "WATER" };
                String[] magic2Param = { "LEAF" };
                String[] magic3Param = { "VOID" };
                String[] buddyChecks = { "Jack" };
                String[] duoChecks = { "Sebek" };
                String[] buffDebuffParam1 = { "被ダメージDOWN" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("atk");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                //Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.BLOOM_BIRTHDAY)),
                                hasProperty("id", is("blo_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.JACK)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.EPEL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.SEBEK)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2361))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1342))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(10707))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6528))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.RECEIVE_DAMAGE_DOWN_MEDIUM_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.CURSE_LARGE_ENEMY_2T_AND_DAMAGE_UP_SMALL_SELF_1T))));
        }

        @Test
        @DisplayName("rare指定")
        void selectMany_02() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "sending_star_dress";
                String[] nameChecks = {};
                String[] rareChecks = { "SR" };
                String[] typeChecks = { "ATTACK" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SENDING_STAR_DRESS)),
                                hasProperty("id", is("sen_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(1802))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1301))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(6144))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5451))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("type指定")
        void selectMany_03() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "DEFENCE" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.VIL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.IDEA)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2665))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1233))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(12965))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5591))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_RECOVER_SMALL_AND_HP_CONTINUOUS_RECOVER_SMALL_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T))));
        }

        @Test
        @DisplayName("magic1指定")
        void selectMany_04() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "rabbit_wear";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = { "FIRE" };
                String[] magic2Param = { "FIRE" };
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("exclude");
                searchForm.setInclude2("exclude");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.RABBIT_WEAR)),
                                hasProperty("id", is("rab_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.MALLEUS)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.LEAF_SHOT)),
                                hasProperty("magic2", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(1846))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1202))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(7670))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5342))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_MEDIUM_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_DOWN_SMALL_ENEMY_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("magic1、rare指定")
        void selectMany_05() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "playful_dress";
                String[] nameChecks = {};
                String[] rareChecks = { "SSR" };
                String[] typeChecks = {};
                String[] magic1Param = { "WATER" };
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.PLAYFUL_DRESS)),
                                hasProperty("id", is("play_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.JADE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.JAMIL)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.LEONA)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2913))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1129))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(14171))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5120))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_SMALL_ENEMY_2T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_SMALL_ENEMY_1T_AND_DAMAGE_UP_SMALL_SELF_1T))));
        }

        @Test
        @DisplayName("magic1、type指定")
        void selectMany_06() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "gala_couture";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "BALANCE" };
                String[] magic1Param = { "LEAF" };
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.GALA_COUTURE)),
                                hasProperty("id", is("gal_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.JACK)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.SILVER)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2382))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1361))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(11195))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6396))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_MEDIUM_ENEMY_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_SMALL_SELF_3T_AND_DAMAGE_UP_SMALL_SELF_1T))));
        }

        @Test
        @DisplayName("magic2指定")
        void selectMany_07() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "dormitory_clothing";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = { "WATER" };
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = { "Idea" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.DORMITORY_CLOTHING)),
                                hasProperty("id", is("dor_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.IDEA)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.LEONA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.IDEA)),
                                hasProperty("minHp", is(BigDecimal.valueOf(1401))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(2253))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9124))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6094))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_UP_MEDIUM_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ANNUAL_FREEZE_FRIEND_1T_AND_ATK_UP_SMALL_SELF_1T))));
        }

        @Test
        @DisplayName("magic2、rare指定")
        void selectMany_08() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "ceremony_clothing";
                String[] nameChecks = {};
                String[] rareChecks = { "SR" };
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = { "WATER" };
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "HP回復" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.CEREMONY_CLOTHING)),
                                hasProperty("id", is("cer_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.EPEL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2240))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1128))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9632))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(4850))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HP_RECOVER_MEDIUM)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("magic2、type指定")
        void selectMany_09() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "new_year_dress";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "ATTACK" };
                String[] magic1Param = {};
                String[] magic2Param = { "FIRE" };
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.NEW_YEAR_DRESS)),
                                hasProperty("id", is("new_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.TREY)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.FLOYD)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(1886))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1300))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(7836))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5778))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_MEDIUM_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("magic3指定")
        void selectMany_10() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "makeup_birthday";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "WATER", "LEAF", "VOID" };
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("exclude");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.MAKEUP_BIRTHDAY)),
                                hasProperty("id", is("mak_jack")),
                                hasProperty("name", is(CharacterEnum.JACK)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.JAMIL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.SILVER)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY2)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.JAMIL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2560))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1268))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(10752))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5325))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_UP_LARGE_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T))));
        }

        @Test
        @DisplayName("magic3、rare指定")
        void selectMany_11() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "outdoor_wear";
                String[] nameChecks = {};
                String[] rareChecks = { "SSR" };
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "FIRE" };
                String[] buddyChecks = {};
                String[] duoChecks = { "Azul" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("exclude");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.OUTDOOR_WEAR)),
                                hasProperty("id", is("out_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.KALIM)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.AZUL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(3180))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1019))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(16520))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(4284))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_SMALL_SELF_3T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_LARGE_SELF_1T))));
        }

        @Test
        @DisplayName("magic3、type指定")
        void selectMany_12() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "union_birthday";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "DEFENCE" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "VOID" };
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.UNION_BIRTHDAY)),
                                hasProperty("id", is("uni_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.CATER)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.ROOK)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.CATER)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2844))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1151))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(13836))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5219))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.WATER_DAMAGE_UP_LARGE_FRIEND_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_MEDIUM_ENEMY_1T))));
        }

        @Test
        @DisplayName("magic3、magic1指定")
        void selectMany_13() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "platinum_jacket";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = { "FIRE" };
                String[] magic2Param = {};
                String[] magic3Param = { "VOID" };
                String[] buddyChecks = {};
                String[] duoChecks = { "Jade" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.PLATINUM_JACKET)),
                                hasProperty("id", is("pla_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.JADE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.JADE)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2373))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1334))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(10761))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6489))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_MEDIUM_SELF_1T_AND_ATK_DOWN_SMALL_ENEMY_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.ANNUAL_DARKNESS_SELF_1T_AND_CURSE_LARGE_ENEMY_2T))));
        }

        @Test
        @DisplayName("magic3、magic2指定")
        void selectMany_14() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "rest_my_room";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = { "LEAF" };
                String[] magic3Param = { "FIRE" };
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.REST_MY_ROOM)),
                                hasProperty("id", is("res_jade")),
                                hasProperty("name", is(CharacterEnum.JADE)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.FLOYD)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.JAMIL)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.JAMIL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2615))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1247))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(12290))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5860))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_LARGE_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.ATK_DOWN_LARGE_ENEMY_1T))));
        }

        @Test
        @DisplayName("buddy指定")
        void selectMany_15() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "scarey_dress";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Ace" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("exclude");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SCAREY_DRESS)),
                                hasProperty("id", is("sca_lilia")),
                                hasProperty("name", is(CharacterEnum.LILIA)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.ACE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.VIL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2297))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1099))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(8728))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(4176))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_RECOVER_MEDIUM)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("buddy、name指定")
        void selectMany_16() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "experimental_clothing";
                String[] nameChecks = { "Ortho" };
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Jade" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("exclude");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.EXPERIMENTAL_CLOTHING)),
                                hasProperty("id", is("exp_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.ACE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.JADE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2864))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(874))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(12000))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(2980))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("buddy、rare指定")
        void selectMany_17() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "tsum_ste";
                String[] nameChecks = {};
                String[] rareChecks = { "SR" };
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Kalim" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.TSUM_STE)),
                                hasProperty("id", is("tsu_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.KALIM)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.SEBEK)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY)),
                                hasProperty("magic2", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2108))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1197))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9064))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(5147))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_MEDIUM_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_SMALL_SELF_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("buddy、type指定")
        void selectMany_18() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "apprentice_chef";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "DEFENCE" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Leona" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.APPRENTICE_CHEF)),
                                hasProperty("id", is("app_ortho")),
                                hasProperty("name", is(CharacterEnum.ORTHO)),
                                hasProperty("rare", is("SR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.LEONA)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.AZUL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.HYPHEN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HYPHEN)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("magic3", is(MagicGroupingEnum.HYPHEN)),
                                hasProperty("duo", is(CharacterEnum.HYPHEN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2661))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(942))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(12599))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(3640))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_MEDIUM_ENEMY_1T)),
                                hasProperty("magic2BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_DOWN_SMALL_ENEMY_1T)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN))));
        }

        @Test
        @DisplayName("buddy、magic1指定")
        void selectMany_19() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = { "LEAF" };
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Jamil" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_lilia")),
                                hasProperty("name", is(CharacterEnum.LILIA)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.JAMIL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.SEBEK)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2002))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1505))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(8418))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7818))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_UP_LARGE_SELF_1T))));
        }

        @Test
        @DisplayName("buddy、magic2指定")
        void selectMany_20() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = { "VOID" };
                String[] magic3Param = {};
                String[] buddyChecks = { "Lilia" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_sebek")),
                                hasProperty("name", is(CharacterEnum.SEBEK)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.KALIM)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.ORTHO)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE)),
                                hasProperty("magic2", is(MagicGroupingEnum.ZERO_RAY2)),
                                hasProperty("magic3", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.ORTHO)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2163))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1458))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9809))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7093))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.RECEIVE_DAMAGE_UP_LARGE_ENEMY_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ANNUAL_DARKNESS_FRIEND_1T_AND_DAMAGE_DOWN_SMALL_ENEMY_1T))));
        }

        @Test
        @DisplayName("buddy、magic3指定")
        void selectMany_21() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "FIRE" };
                String[] buddyChecks = { "Ruggie" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_floyd")),
                                hasProperty("name", is(CharacterEnum.FLOYD)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.AZUL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.VIL)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.AZUL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2169))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1454))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9836))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7073))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.CURSE_LARGE_ENEMY_2T_AND_DAMAGE_UP_SMALL_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_UP_LARGE_SELF_1T))));
        }

        @Test
        @DisplayName("duo指定")
        void selectMany_22() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = { "Rook" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_kalim")),
                                hasProperty("name", is(CharacterEnum.KALIM)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.FLOYD)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.ROOK)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.ROOK)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2206))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1439))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(10004))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7000))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ANNUAL_FREEZE_FRIEND_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.DAMAGE_UP_LARGE_SELF_1T))));
        }

        @Test
        @DisplayName("duo、type指定")
        void selectMany_23() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "ATTACK" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = { "Ruggie" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_cater")),
                                hasProperty("name", is(CharacterEnum.CATER)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.RUGGIE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("buddy3", is(CharacterEnum.SEBEK)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.RUGGIE)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2142))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1479))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9713))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7195))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.EVADE_MEDIUM_SELF_1T))));
        }

        @Test
        @DisplayName("duo、magic3指定")
        void selectMany_24() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "WATER" };
                String[] buddyChecks = {};
                String[] duoChecks = { "Riddle" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_trey")),
                                hasProperty("name", is(CharacterEnum.TREY)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.LILIA)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.RIDDLE)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2515))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1299))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(11820))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6105))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.ANNUAL_FREEZE_FRIEND_1T_AND_ATK_UP_SMALL_FRIEND_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.REMOVE_DEBUFF_FRIEND_AND_DAMAGE_DOWN_SMALL_ENEMY_1T))));
        }

        @Test
        @DisplayName("duo、buddy指定")
        void selectMany_25() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Leona" };
                String[] duoChecks = { "Vil" };
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_rook")),
                                hasProperty("name", is(CharacterEnum.ROOK)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.LEONA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.VIL)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_MIDDLE)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.VIL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2551))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1280))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(11989))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6016))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.REMOVE_DEBUFF_FRIEND_AND_ATK_DOWN_SMALL_ENEMY_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.ATK_UP_LARGE_SELF_1T))));
        }

        @Test
        @DisplayName("buffDebuff指定")
        void selectMany_26() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "seventh_chapter";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "属性ダメージUP" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SEVENTH_CHAPTER)),
                                hasProperty("id", is("sev_ruggie")),
                                hasProperty("name", is(CharacterEnum.RUGGIE)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.JADE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.IDEA)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE)),
                                hasProperty("magic2", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.JADE)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2059))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1472))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(8658))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(7647))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.LEAF_DAMAGE_UP_LARGE_FRIEND_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.CURSE_LARGE_ENEMY_2T))));
        }

        @Test
        @DisplayName("buffDebuff、type指定")
        void selectMany_27() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "masquerade_dress";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = { "ATTACK" };
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "属性ダメージUP" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.MASQUERADE_DRESS)),
                                hasProperty("id", is("masq_rollo")),
                                hasProperty("name", is(CharacterEnum.ROLLO)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.AZUL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.MALLEUS)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FLAME_BLAST)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.GRIM)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2264))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1402))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(10267))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6820))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(
                                                BuffDebuffGroupingEnum.ATK_UP_LARGE_SELF_1T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.FIRE_DAMAGE_UP_MAXIMUM_SELF_1T))));
        }

        @Test
        @DisplayName("buffDebuff1、magic1指定")
        void selectMany_28() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "school_personnel";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = { "VOID" };
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "HP継続回復" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                                hasProperty("id", is("sch_crowley")),
                                hasProperty("name", is(CharacterEnum.CROWLEY)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.DEUCE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.VIL)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.GRIM)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.ZERO_RAY2)),
                                hasProperty("magic2", is(MagicGroupingEnum.ZERO_RAY2)),
                                hasProperty("magic3", is(MagicGroupingEnum.VOID_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.GRIM)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2983))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1350))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(14020))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6345))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.HP_CONTINUOUS_RECOVER_SMALL_FRIEND_3T))));
        }

        @Test
        @DisplayName("buffDebuff1、magic2指定")
        void selectMany_29() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "school_personnel";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = { "LEAF" };
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "クリティカル" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                                hasProperty("id", is("sch_crewel")),
                                hasProperty("name", is(CharacterEnum.CREWEL)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.ROOK)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.SILVER)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.CROWLEY)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.AQUA_WAVE2)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.FIRE_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.CROWLEY)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2376))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1551))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(9991))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(8057))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.CRITICAL_MEDIUM_FRIEND_3T))));
        }

        @Test
        @DisplayName("buffDebuff1、magic3指定")
        void selectMany_30() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "school_personnel";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = { "LEAF" };
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "被ダメージDOWN" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                                hasProperty("id", is("sch_trein")),
                                hasProperty("name", is(CharacterEnum.TREIN)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("DEFENCE")),
                                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.RUGGIE)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.CREWEL)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic2", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.LEAF_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.CREWEL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(3419))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1189))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(18013))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(4999))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.RECEIVE_DAMAGE_DOWN_LARGE_FRIEND_ALL_5T))));
        }

        @Test
        @DisplayName("buffDebuff1、buddy指定")
        void selectMany_31() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "school_personnel";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = { "Cater" };
                String[] duoChecks = {};
                String[] buffDebuffParam1 = { "ガッツ" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.SCHOOL_PERSONNEL)),
                                hasProperty("id", is("sch_vargas")),
                                hasProperty("name", is(CharacterEnum.VARGAS)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.CATER)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.ATK_UP_MIDDLE)),
                                hasProperty("buddy2", is(CharacterEnum.JACK)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.TREIN)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.FOREST_STRIKE2)),
                                hasProperty("magic2", is(MagicGroupingEnum.FLAME_BLAST2)),
                                hasProperty("magic3", is(MagicGroupingEnum.WATER_SHOT2)),
                                hasProperty("duo", is(CharacterEnum.TREIN)),
                                hasProperty("minHp", is(BigDecimal.valueOf(1940))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1733))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(8157))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(9002))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.GUTS_FRIEND_ALL_3T))));
        }

        @Test
        @DisplayName("buffDebuff1、duo指定")
        void selectMany_32() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "over_blot";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = { "Vil" };
                String[] buffDebuffParam1 = { "クリティカル" };
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.OVER_BLOT)),
                                hasProperty("id", is("over_idea")),
                                hasProperty("name", is(CharacterEnum.IDEA)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("ATTACK")),
                                hasProperty("buddy1", is(CharacterEnum.VIL)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.MALLEUS)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.GRIM)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.GRUDGE_OF_THE_KING)),
                                hasProperty("magic2", is(MagicGroupingEnum.PLOT_FILLED_WITH_MALICE2)),
                                hasProperty("magic3", is(MagicGroupingEnum.PLOT_FILLED_WITH_MALICE3)),
                                hasProperty("duo", is(CharacterEnum.VIL)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2073))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1544))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(8716))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(8021))),
                                hasProperty("validFlg", is(true)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.CRITICAL_MAXIMUM_SELF_3T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.REMOVE_DEBUFF_FRIEND))));
        }

        @Test
        @DisplayName("over_blot指定")
        void selectMany_33() {
                // Arrange
                SearchForm searchForm = new SearchForm();
                String tableName = "over_blot";
                String[] nameChecks = {};
                String[] rareChecks = {};
                String[] typeChecks = {};
                String[] magic1Param = {};
                String[] magic2Param = {};
                String[] magic3Param = {};
                String[] buddyChecks = {};
                String[] duoChecks = {};
                String[] buffDebuffParam1 = {};
                searchForm.setNameChecks(nameChecks);
                searchForm.setRareChecks(rareChecks);
                searchForm.setTypeChecks(typeChecks);
                searchForm.setMagicChecks1(magic1Param);
                searchForm.setMagicChecks2(magic2Param);
                searchForm.setMagicChecks3(magic3Param);
                searchForm.setInclude1("include");
                searchForm.setInclude2("include");
                searchForm.setInclude3("include");
                searchForm.setBuddyChecks(buddyChecks);
                searchForm.setDuoChecks(duoChecks);
                searchForm.setSort("hp");
                searchForm.setBuffDebuffChecks(buffDebuffParam1);

                //Act
                List<Card> resultList = target.selectMany(searchForm, tableName);

                // Assert
                assertThat(resultList.get(0), allOf(
                                hasProperty("tableName", is(TableEnum.OVER_BLOT)),
                                hasProperty("id", is("over_malleus")),
                                hasProperty("name", is(CharacterEnum.MALLEUS)),
                                hasProperty("rare", is("SSR")),
                                hasProperty("type", is("BALANCE")),
                                hasProperty("buddy1", is(CharacterEnum.RIDDLE)),
                                hasProperty("buddy1Grouping", is(BuddyGroupingEnum.HP_UP_SMALL)),
                                hasProperty("buddy2", is(CharacterEnum.IDEA)),
                                hasProperty("buddy2Grouping", is(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL)),
                                hasProperty("buddy3", is(CharacterEnum.GRIM)),
                                hasProperty("buddy3Grouping", is(BuddyGroupingEnum.ATK_UP_SMALL)),
                                hasProperty("magic1", is(MagicGroupingEnum.RULER_OF_ALL_EVIL)),
                                hasProperty("magic2", is(MagicGroupingEnum.LONELINESS_OF_THE_WITCH2)),
                                hasProperty("magic3", is(MagicGroupingEnum.RULER_OF_ALL_EVIL3)),
                                hasProperty("duo", is(CharacterEnum.IDEA)),
                                hasProperty("minHp", is(BigDecimal.valueOf(2529))),
                                hasProperty("minAtk", is(BigDecimal.valueOf(1391))),
                                hasProperty("maxHp", is(BigDecimal.valueOf(11886))),
                                hasProperty("maxAtk", is(BigDecimal.valueOf(6537))),
                                hasProperty("validFlg", is(false)),
                                hasProperty("magic1BuffdebuffGrouping",
                                                is(BuffDebuffGroupingEnum.VOID_DAMAGE_UP_LARGE_FRIEND_ALL_5T)),
                                hasProperty("magic2BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HYPHEN)),
                                hasProperty("magic3BuffdebuffGrouping", is(BuffDebuffGroupingEnum.HP_RECOVER_SMALL))));
        }
}
