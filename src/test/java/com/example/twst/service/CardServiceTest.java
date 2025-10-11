package com.example.twst.service;

import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import com.example.twst.dao.CardDaoJdbcImpl;
import com.example.twst.domain.model.BuddyGroupingEnum;
import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.MagicGroupingEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.form.CalculateForm;
import com.example.twst.session.OrganizeSession;

@SpringBootTest
@Transactional
public class CardServiceTest {

    @Autowired
    private CardService target;

    @Autowired
    OrganizeSession organizeSession;

    @MockitoSpyBean
    CardDaoJdbcImpl spyDao;

    BigDecimal[] tempHpArray = new BigDecimal[5];
    BigDecimal[] tempAtkArray = new BigDecimal[5];

    @Test
    @DisplayName("insert_正常系")
    void insert_01() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.BEANS_CAMO);
        cardForm.setName(CharacterEnum.RIDDLE);
        cardForm.setRare("R");
        cardForm.setType("ATTACK");
        cardForm.setBuddy1(CharacterEnum.ACE);
        cardForm.setBuddy2(CharacterEnum.HYPHEN);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
        cardForm.setMagic2(MagicGroupingEnum.ZERO_RAY2);
        cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_SMALL);
        cardForm.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_MINIMUM_ENEMY_1T);
        cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
        cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);

        // Act
        boolean result = target.insert(cardForm);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("insert_異常系")
    void insert_02() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.BEANS_CAMO);
        cardForm.setName(CharacterEnum.JADE);
        cardForm.setRare("R");
        cardForm.setType("ATTACK");
        cardForm.setBuddy1(CharacterEnum.ACE);
        cardForm.setBuddy2(CharacterEnum.HYPHEN);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
        cardForm.setMagic2(MagicGroupingEnum.ZERO_RAY2);
        cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_SMALL);
        cardForm.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
        cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_SMALL_ENEMY_1T);
        cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);

        // Act
        DuplicateKeyException result = assertThrows(DuplicateKeyException.class, () -> target.insert(cardForm));

        // Assert
        assertThat(result.getMessage(),
                is(cardForm.getTableName().getViewName() + " に" + cardForm.getName().getViewName() + " はすでに存在します。"));
    }

    @Test
    @DisplayName("insert_異常系2")
    void insert_03() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        doReturn(0).when(spyDao).insertOne(cardForm);

        // Act 
        boolean result = target.insert(cardForm);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("selectMany")
    void selectMany() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String tableName = "roll_playing_bridegroom";
        String[] tableNameChecks = { "roll_playing_bridegroom" };
        String[] nameParam = {};
        String[] rareParam = {};
        String[] typeParam = {};
        String[] magicParam1 = {};
        String[] magicParam2 = {};
        String[] magicParam3 = {};
        String[] buddyParam = {};
        String[] duoParam = {};
        String[] buffDebuffParam1 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks(buffDebuffParam1);

        // Act
        List<Card> result = target.selectMany(searchForm, tableName);

        // Assert
        assertThat(result.get(0), is(allOf(hasProperty("tableName", is(TableEnum.ROLL_PLAYING_BRIDEGROOM)))));
    }

    @Test
    @DisplayName("selectAll_atk")
    void selectAll_01() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "experimental_clothing" };
        searchForm.setTableNameChecks(tableNameChecks);
        String[] nameParam = {};
        String[] rareParam = {};
        String[] typeParam = {};
        String[] magicParam1 = {};
        String[] magicParam2 = {};
        String[] magicParam3 = {};
        String[] buddyParam = {};
        String[] duoParam = {};
        String[] buffDebuffParam1 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks(buffDebuffParam1);
        searchForm.setSort("atk");

        // Act
        List<Card> result = target.selectAll(searchForm);

        // Assert
        assertThat(result.get(0), is(allOf(hasProperty("id", is("exp_ruggie")))));
    }

    @Test
    @DisplayName("selectAll_hp")
    void selectAll_02() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "experimental_clothing" };
        searchForm.setTableNameChecks(tableNameChecks);
        String[] nameParam = {};
        String[] rareParam = {};
        String[] typeParam = {};
        String[] magicParam1 = {};
        String[] magicParam2 = {};
        String[] magicParam3 = {};
        String[] buddyParam = {};
        String[] duoParam = {};
        String[] buffDebuffParam1 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks(buffDebuffParam1);
        searchForm.setSort("hp");

        // Act
        List<Card> result = target.selectAll(searchForm);

        // Assert
        assertThat(result.get(0), is(allOf(hasProperty("id", is("exp_ace")))));
    }

    @Test
    @DisplayName("selectAll_reflectedAtk")
    void selectAll_03() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "experimental_clothing" };
        searchForm.setTableNameChecks(tableNameChecks);
        String[] nameParam = {};
        String[] rareParam = {};
        String[] typeParam = {};
        String[] magicParam1 = {};
        String[] magicParam2 = {};
        String[] magicParam3 = {};
        String[] buddyParam = {};
        String[] duoParam = {};
        String[] buffDebuffParam1 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks(buffDebuffParam1);
        searchForm.setSort("reflectedAtk");

        // Act
        List<Card> result = target.selectAll(searchForm);

        // Assert
        assertThat(result.get(0), is(allOf(hasProperty("id", is("exp_sebek")))));
    }

    @Test
    @DisplayName("selectAll_reflectedHp")
    void selectAll_04() throws Exception {
        // Arrange
        SearchForm searchForm = new SearchForm();
        String[] tableNameChecks = { "experimental_clothing" };
        searchForm.setTableNameChecks(tableNameChecks);
        String[] nameParam = {};
        String[] rareParam = {};
        String[] typeParam = {};
        String[] magicParam1 = {};
        String[] magicParam2 = {};
        String[] magicParam3 = {};
        String[] buddyParam = {};
        String[] duoParam = {};
        String[] buffDebuffParam1 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks(buffDebuffParam1);
        searchForm.setSort("reflectedHp");

        // Act
        List<Card> result = target.selectAll(searchForm);

        // Assert
        assertThat(result.get(0), is(allOf(hasProperty("id", is("exp_ace")))));
    }

    @Test
    @DisplayName("updateOne_正常系")
    void updateOne_01() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.GALA_COUTURE);
        cardForm.setName(CharacterEnum.ACE);
        cardForm.setRare("R");
        cardForm.setType("ATTACK");
        cardForm.setBuddy1(CharacterEnum.DEUCE);
        cardForm.setBuddy2(CharacterEnum.HYPHEN);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
        cardForm.setMagic2(MagicGroupingEnum.ZERO_RAY2);
        cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_SMALL);
        cardForm.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
        cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_SMALL_ENEMY_1T);
        cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);

        // Act
        boolean result = target.updateOne(cardForm);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("update_異常系")
    void update_02() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        doReturn(0).when(spyDao).updateOne(cardForm);

        // Act 
        boolean result = target.updateOne(cardForm);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("deleteOne")
    void deleteOne_01() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.ROLL_PLAYING_BRIDEGROOM);
        cardForm.setRare("R");
        cardForm.setType("ATTACK");
        cardForm.setName(CharacterEnum.ROOK);
        cardForm.setBuddy1(CharacterEnum.ORTHO);
        cardForm.setBuddy2(CharacterEnum.HYPHEN);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1(MagicGroupingEnum.FIRE_SHOT);
        cardForm.setMagic2(MagicGroupingEnum.FIRE_SHOT2);
        cardForm.setMagic3(MagicGroupingEnum.HYPHEN);
        cardForm.setMagic1BuffdebuffGrouping(BuffDebuffGroupingEnum.DAMAGE_UP_SMALL_SELF_1T);
        cardForm.setMagic2BuffdebuffGrouping(BuffDebuffGroupingEnum.ATK_DOWN_MINIMUM_ENEMY_1T);
        cardForm.setMagic3BuffdebuffGrouping(BuffDebuffGroupingEnum.HYPHEN);
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.valueOf(0123));
        cardForm.setMinAtk(BigDecimal.valueOf(1234));
        cardForm.setMaxHp(BigDecimal.valueOf(2345));
        cardForm.setMaxAtk(BigDecimal.valueOf(3456));

        // Act
        boolean result = target.deleteOne(cardForm);

        // Assert
        assertThat(result, is(true));
    }

    @Test
    @DisplayName("delete_異常系")
    void delete_02() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        doReturn(0).when(spyDao).deleteOne(cardForm);

        // Act 
        boolean result = target.deleteOne(cardForm);

        // Assert
        assertThat(result, is(false));
    }

    @Test
    @DisplayName("duoCount")
    void duoCount() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardA.setName(CharacterEnum.VIL);
        cardA.setDuo(CharacterEnum.LEONA);
        Card cardB = new Card();
        cardB.setName(CharacterEnum.LEONA);
        cardB.setDuo(CharacterEnum.VIL);
        cardArray[0] = cardA;
        cardArray[1] = cardB;

        // Act
        Map<String, List<String>> result = target.duoCount(cardArray);

        // Assert
        assertThat(result.get("duoCount"), is(contains("1", "2")));
        assertThat(result.get("Leona"), is(containsInAnyOrder("Vil")));
        assertThat(result.get("Vil"), is(containsInAnyOrder("Leona")));
    }

    @Test
    @DisplayName("buddyCount")
    void buddyCount_01() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardA.setName(CharacterEnum.DEUCE);
        cardA.setBuddy1(CharacterEnum.CATER);
        cardA.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_SMALL);
        cardA.setBuddy2(CharacterEnum.HYPHEN);
        cardA.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardA.setBuddy3(CharacterEnum.HYPHEN);
        cardA.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardA.setMaxHp(BigDecimal.valueOf(1000));
        cardA.setMaxAtk(BigDecimal.valueOf(1000));

        Card cardB = new Card();
        cardB.setName(CharacterEnum.CATER);
        cardB.setBuddy1(CharacterEnum.HYPHEN);
        cardB.setBuddy1Grouping(BuddyGroupingEnum.HYPHEN);
        cardB.setBuddy2(CharacterEnum.TREY);
        cardB.setBuddy2Grouping(BuddyGroupingEnum.HP_UP_MIDDLE);
        cardB.setBuddy3(CharacterEnum.HYPHEN);
        cardB.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardB.setMaxHp(BigDecimal.valueOf(2000));
        cardB.setMaxAtk(BigDecimal.valueOf(2000));

        Card cardC = new Card();
        cardC.setName(CharacterEnum.TREY);
        cardC.setBuddy1(CharacterEnum.HYPHEN);
        cardC.setBuddy1Grouping(BuddyGroupingEnum.HYPHEN);
        cardC.setBuddy2(CharacterEnum.HYPHEN);
        cardC.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardC.setBuddy3(CharacterEnum.LEONA);
        cardC.setBuddy3Grouping(BuddyGroupingEnum.HP_UP_MIDDLE);
        cardC.setMaxHp(BigDecimal.valueOf(3000));
        cardC.setMaxAtk(BigDecimal.valueOf(3000));

        Card cardD = new Card();
        cardD.setName(CharacterEnum.LEONA);
        cardD.setBuddy1(CharacterEnum.RUGGIE);
        cardD.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_MIDDLE);
        cardD.setBuddy2(CharacterEnum.HYPHEN);
        cardD.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardD.setBuddy3(CharacterEnum.HYPHEN);
        cardD.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardD.setMaxHp(BigDecimal.valueOf(4000));
        cardD.setMaxAtk(BigDecimal.valueOf(4000));

        Card cardE = new Card();
        cardE.setName(CharacterEnum.RUGGIE);
        cardE.setBuddy1(CharacterEnum.HYPHEN);
        cardE.setBuddy1Grouping(BuddyGroupingEnum.HYPHEN);
        cardE.setBuddy2(CharacterEnum.DEUCE);
        cardE.setBuddy2Grouping(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL);
        cardE.setBuddy3(CharacterEnum.HYPHEN);
        cardE.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardE.setMaxHp(BigDecimal.valueOf(5000));
        cardE.setMaxAtk(BigDecimal.valueOf(5000));

        cardArray[0] = cardA;
        cardArray[1] = cardB;
        cardArray[2] = cardC;
        cardArray[3] = cardD;
        cardArray[4] = cardE;

        organizeSession.setTempHpArray(tempHpArray);
        organizeSession.setTempAtkArray(tempAtkArray);

        // Act
        Map<String, List<String>> buddyMap = target.buddyCount(cardArray, false);
        BigDecimal[] hpArray = organizeSession.getHpArray();
        BigDecimal[] atkArray = organizeSession.getAtkArray();

        // Assert
        assertThat(buddyMap.get("buddyCount"), is(contains("1", "2", "3", "4",
                "5")));
        assertThat(buddyMap.get("Deuce"), is(containsInAnyOrder("Cater")));
        assertThat(buddyMap.get("Cater"), is(containsInAnyOrder("Trey")));
        assertThat(buddyMap.get("Trey"), is(containsInAnyOrder("Leona")));
        assertThat(buddyMap.get("Leona"), is(containsInAnyOrder("Ruggie")));
        assertThat(buddyMap.get("Ruggie"), is(containsInAnyOrder("Deuce")));
        assertThat(hpArray,
                is(arrayContaining(BigDecimal.valueOf(1200), BigDecimal.valueOf(2600), BigDecimal.valueOf(3900),
                        BigDecimal.valueOf(5200), BigDecimal.valueOf(6000))));
        assertThat(atkArray,
                is(arrayContaining(BigDecimal.valueOf(1000), BigDecimal.valueOf(2000), BigDecimal.valueOf(3000),
                        BigDecimal.valueOf(4000), BigDecimal.valueOf(6000))));
    }

    @Test
    @DisplayName("buddyCount_temp")
    void buddyCount_02() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardA.setName(CharacterEnum.DEUCE);
        cardA.setBuddy1(CharacterEnum.CATER);
        cardA.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_SMALL);
        cardA.setBuddy2(CharacterEnum.HYPHEN);
        cardA.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardA.setBuddy3(CharacterEnum.HYPHEN);
        cardA.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardA.setMaxHp(BigDecimal.valueOf(1000));
        cardA.setMaxAtk(BigDecimal.valueOf(1000));

        Card cardC = new Card();
        cardC.setName(CharacterEnum.TREY);
        cardC.setBuddy1(CharacterEnum.HYPHEN);
        cardC.setBuddy1Grouping(BuddyGroupingEnum.HYPHEN);
        cardC.setBuddy2(CharacterEnum.HYPHEN);
        cardC.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardC.setBuddy3(CharacterEnum.LEONA);
        cardC.setBuddy3Grouping(BuddyGroupingEnum.ATK_UP_SMALL);
        cardC.setMaxHp(BigDecimal.valueOf(3000));
        cardC.setMaxAtk(BigDecimal.valueOf(3000));

        Card cardD = new Card();
        cardD.setName(CharacterEnum.LEONA);
        cardD.setBuddy1(CharacterEnum.RUGGIE);
        cardD.setBuddy1Grouping(BuddyGroupingEnum.HP_UP_MIDDLE);
        cardD.setBuddy2(CharacterEnum.HYPHEN);
        cardD.setBuddy2Grouping(BuddyGroupingEnum.HYPHEN);
        cardD.setBuddy3(CharacterEnum.HYPHEN);
        cardD.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardD.setMaxHp(BigDecimal.valueOf(4000));
        cardD.setMaxAtk(BigDecimal.valueOf(4000));

        Card cardE = new Card();
        cardE.setName(CharacterEnum.RUGGIE);
        cardE.setBuddy1(CharacterEnum.HYPHEN);
        cardE.setBuddy1Grouping(BuddyGroupingEnum.HYPHEN);
        cardE.setBuddy2(CharacterEnum.DEUCE);
        cardE.setBuddy2Grouping(BuddyGroupingEnum.HP_AND_ATK_UP_SMALL);
        cardE.setBuddy3(CharacterEnum.HYPHEN);
        cardE.setBuddy3Grouping(BuddyGroupingEnum.HYPHEN);
        cardE.setMaxHp(BigDecimal.valueOf(5000));
        cardE.setMaxAtk(BigDecimal.valueOf(5000));

        cardArray[0] = cardA;
        cardArray[2] = cardC;
        cardArray[3] = cardD;
        cardArray[4] = cardE;

        organizeSession.setTempHpArray(tempHpArray);
        organizeSession.setTempAtkArray(tempAtkArray);

        // Act
        Map<String, List<String>> buddyMap = target.buddyCount(cardArray, true);
        tempHpArray = organizeSession.getTempHpArray();
        tempAtkArray = organizeSession.getTempAtkArray();

        // Assert
        assertThat(buddyMap.get("buddyCount"), is(contains("0", "1", "2", "3")));
        assertThat(buddyMap.get("Trey"), is(containsInAnyOrder("Leona")));
        assertThat(buddyMap.get("Leona"), is(containsInAnyOrder("Ruggie")));
        assertThat(buddyMap.get("Ruggie"), is(containsInAnyOrder("Deuce")));
        assertThat(tempHpArray,
                is(arrayContaining(BigDecimal.valueOf(1000), null, BigDecimal.valueOf(3000),
                        BigDecimal.valueOf(5200), BigDecimal.valueOf(6000))));
        assertThat(tempAtkArray,
                is(arrayContaining(BigDecimal.valueOf(1000), null, BigDecimal.valueOf(3600),
                        BigDecimal.valueOf(4000), BigDecimal.valueOf(6000))));
    }

    @Test
    @DisplayName("sum")
    void sum() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];

        Card cardA = new Card();
        cardA.setMaxHp(BigDecimal.valueOf(1000));

        Card cardB = new Card();
        cardB.setMaxHp(BigDecimal.valueOf(2000));

        cardArray[0] = cardA;
        cardArray[1] = cardB;

        // Act
        BigDecimal result = target.sum(cardArray);

        // Assert
        assertThat(result, is(BigDecimal.valueOf(3000)));
    }

    @Test
    @DisplayName("calculate_R")
    void calculate_01() throws Exception {
        // Arrange
        CalculateForm calculateForm = new CalculateForm();
        calculateForm.setRare("R");
        calculateForm.setLevel(BigDecimal.valueOf(50));
        calculateForm.setArrayIndex(0);
        calculateForm.setMinHp(BigDecimal.valueOf(1500));
        calculateForm.setMinAtk(BigDecimal.valueOf(1000));
        calculateForm.setMaxHp(BigDecimal.valueOf(5000));
        calculateForm.setMaxAtk(BigDecimal.valueOf(4500));

        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardArray[0] = cardA;
        BigDecimal[] levelArray = new BigDecimal[5];
        organizeSession.setLevelArray(levelArray);

        // Act
        Card[] result = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(result[0].getMaxHp(), is(BigDecimal.valueOf(3571)));
        assertThat(result[0].getMaxAtk(), is(BigDecimal.valueOf(3214)));
    }

    @Test
    @DisplayName("calculate_SR")
    void calculate_02() throws Exception {
        // Arrange
        CalculateForm calculateForm = new CalculateForm();
        calculateForm.setRare("SR");
        calculateForm.setLevel(BigDecimal.valueOf(70));
        calculateForm.setArrayIndex(1);
        calculateForm.setMinHp(BigDecimal.valueOf(2000));
        calculateForm.setMinAtk(BigDecimal.valueOf(1000));
        calculateForm.setMaxHp(BigDecimal.valueOf(7000));
        calculateForm.setMaxAtk(BigDecimal.valueOf(5000));

        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardArray[1] = cardA;
        BigDecimal[] levelArray = new BigDecimal[5];
        organizeSession.setLevelArray(levelArray);

        // Act
        Card[] result = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(result[1].getMaxHp(), is(BigDecimal.valueOf(5444)));
        assertThat(result[1].getMaxAtk(), is(BigDecimal.valueOf(3889)));
    }

    @Test
    @DisplayName("calculate_SSR")
    void calculate_03() throws Exception {
        // Arrange
        CalculateForm calculateForm = new CalculateForm();
        calculateForm.setRare("SSR");
        calculateForm.setLevel(BigDecimal.valueOf(100));
        calculateForm.setArrayIndex(2);
        calculateForm.setMinHp(BigDecimal.valueOf(3000));
        calculateForm.setMinAtk(BigDecimal.valueOf(1000));
        calculateForm.setMaxHp(BigDecimal.valueOf(12000));
        calculateForm.setMaxAtk(BigDecimal.valueOf(5000));

        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardArray[2] = cardA;

        // Act
        Card[] result = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(result[2].getMaxHp(), is(BigDecimal.valueOf(10909)));
        assertThat(result[2].getMaxAtk(), is(BigDecimal.valueOf(4546)));
    }

    @Test
    @DisplayName("tempSum")
    void tempSum() throws Exception {
        // Arrange
        BigDecimal[] hpArray = new BigDecimal[5];
        hpArray[0] = BigDecimal.valueOf(1000);
        hpArray[3] = BigDecimal.valueOf(2000);

        // Act
        BigDecimal result = target.tempSum(hpArray);

        // Assert
        assertThat(result, is(BigDecimal.valueOf(3000)));
    }

    @Test
    @DisplayName("setArray")
    void setArray() throws Exception {
        // Arrange
        BigDecimal[] result = new BigDecimal[5];

        // Act
        result = target.setArray(result);

        // Assert
        assertThat(result, is(
                arrayContaining(BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("setLevelArray_R")
    void setLevelArray_01() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card card = new Card();
        card.setRare("R");
        cardArray[1] = card;
        BigDecimal[] result = { BigDecimal.valueOf(50), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, null };

        //Act
        result = target.setLevelArray(cardArray, result);

        // Assert
        assertThat(result, is(arrayContaining(BigDecimal.valueOf(50), BigDecimal.valueOf(70), BigDecimal.ZERO,
                BigDecimal.ZERO, null)));
    }

    @Test
    @DisplayName("setLevelArray_SR")
    void setLevelArray_02() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card card = new Card();
        card.setRare("SR");
        cardArray[2] = card;
        BigDecimal[] result = { null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(80), BigDecimal.ZERO };

        //Act
        result = target.setLevelArray(cardArray, result);

        // Assert
        assertThat(result, is(arrayContaining(null, BigDecimal.ZERO, BigDecimal.valueOf(90), BigDecimal.valueOf(80),
                BigDecimal.ZERO)));
    }

    @Test
    @DisplayName("setLevelArray_SSR")
    void setLevelArray_03() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card card = new Card();
        card.setRare("SSR");
        cardArray[3] = card;
        BigDecimal[] result = { BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.valueOf(90) };

        //Act
        result = target.setLevelArray(cardArray, result);

        // Assert
        assertThat(result, is(arrayContaining(BigDecimal.ZERO, null, BigDecimal.ZERO, BigDecimal.valueOf(110),
                BigDecimal.valueOf(90))));
    }

    @Test
    @DisplayName("setTempCardArray")
    void setTempCardArray() throws Exception {
        // Arrange
        Card[] result = new Card[5];
        Card card = new Card();
        result[0] = card;
        BigDecimal[] hpArray = { BigDecimal.valueOf(2000), BigDecimal.valueOf(3000), BigDecimal.valueOf(4000),
                BigDecimal.valueOf(5000), BigDecimal.valueOf(6000) };
        BigDecimal[] atkArray = { BigDecimal.valueOf(1500), BigDecimal.valueOf(2500), BigDecimal.valueOf(3500),
                BigDecimal.valueOf(4500), BigDecimal.valueOf(5500) };

        organizeSession.setHpArray(hpArray);
        organizeSession.setAtkArray(atkArray);

        //Act
        result = target.setTempCardArray(result);

        // Assert
        assertThat(result[0], allOf(hasProperty("maxHp", is(BigDecimal.valueOf(2000))),
                hasProperty("maxAtk", is(BigDecimal.valueOf(1500)))));
    }
}
