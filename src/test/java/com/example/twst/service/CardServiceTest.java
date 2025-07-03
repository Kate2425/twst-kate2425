package com.example.twst.service;

import java.math.BigDecimal;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.domain.model.CharacterEnum;
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
        cardForm.setMagic1Grouping("1");
        cardForm.setMagic2Grouping("2");
        cardForm.setMagic3Grouping("0");
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping("1");
        cardForm.setBuddy2Grouping("0");
        cardForm.setBuddy3Grouping("0");
        cardForm.setMagic1BuffdebuffGrouping("1");
        cardForm.setMagic2BuffdebuffGrouping("-");
        cardForm.setMagic3BuffdebuffGrouping("-");

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
        cardForm.setMagic1Grouping("1");
        cardForm.setMagic2Grouping("2");
        cardForm.setMagic3Grouping("0");
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping("1");
        cardForm.setBuddy2Grouping("0");
        cardForm.setBuddy3Grouping("0");
        cardForm.setMagic1BuffdebuffGrouping("-");
        cardForm.setMagic2BuffdebuffGrouping("2");
        cardForm.setMagic3BuffdebuffGrouping("-");

        // Act
        DuplicateKeyException result = assertThrows(DuplicateKeyException.class, () -> target.insert(cardForm));

        // Assert
        assertThat(result.getMessage(),
                is(cardForm.getTableName().getViewName() + " に" + cardForm.getName().getViewName() + " はすでに存在します。"));
    }

    @Test
    @DisplayName("count")
    void count() throws Exception {
        // Arrange

        // Act
        int count = target.count();

        // Assert
        assertThat(count, is(0));
    }

    @Test
    @DisplayName("selectOne")
    void selectOne() throws Exception {
        // Arrange
        String name = "";

        // Act
        Card card = target.selectOne(name);

        // Assert
        assertThat(card, is(nullValue()));
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
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);

        // Act
        List<Card> resultList = target.selectMany(searchForm, tableName);

        // Assert
        assertThat(resultList.get(0), is(allOf(hasProperty("tableName", is(TableEnum.ROLL_PLAYING_BRIDEGROOM)))));
    }

    @Test
    @DisplayName("selectAll")
    void selectAll() throws Exception {
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
        String[] buffDebuffParam2 = {};
        String[] buffDebuffParam3 = {};
        searchForm.setTableNameChecks(tableNameChecks);
        searchForm.setNameChecks(nameParam);
        searchForm.setRareChecks(rareParam);
        searchForm.setTypeChecks(typeParam);
        searchForm.setMagicChecks1(magicParam1);
        searchForm.setMagicChecks2(magicParam2);
        searchForm.setMagicChecks3(magicParam3);
        searchForm.setBuddyChecks(buddyParam);
        searchForm.setDuoChecks(duoParam);
        searchForm.setBuffDebuffChecks1(buffDebuffParam1);
        searchForm.setBuffDebuffChecks2(buffDebuffParam2);
        searchForm.setBuffDebuffChecks3(buffDebuffParam3);
        searchForm.setSort("atk");

        // Act
        List<Card> resultList = target.selectAll(searchForm);

        // Assert
        assertThat(resultList.get(0), is(allOf(hasProperty("tableName", is(TableEnum.EXPERIMENTAL_CLOTHING)))));
    }

    @Test
    @DisplayName("updateOne")
    void updateOne() throws Exception {
        // Arrange
        CardForm cardForm = new CardForm();
        cardForm.setTableName(TableEnum.GALA_COUTURE);
        cardForm.setName(CharacterEnum.ACE);
        cardForm.setRare("R");
        cardForm.setType("ATTACK");
        cardForm.setBuddy1(CharacterEnum.DEUCE);
        cardForm.setBuddy2(CharacterEnum.HYPHEN);
        cardForm.setBuddy3(CharacterEnum.HYPHEN);
        cardForm.setMagic1Grouping("1");
        cardForm.setMagic2Grouping("2");
        cardForm.setMagic3Grouping("0");
        cardForm.setDuo(CharacterEnum.HYPHEN);
        cardForm.setMinHp(BigDecimal.ZERO);
        cardForm.setMinAtk(BigDecimal.ZERO);
        cardForm.setMaxHp(BigDecimal.ZERO);
        cardForm.setMaxAtk(BigDecimal.ZERO);
        cardForm.setValidFlg(false);
        cardForm.setBuddy1Grouping("1");
        cardForm.setBuddy2Grouping("0");
        cardForm.setBuddy3Grouping("0");

        // Act
        int count = target.updateOne(cardForm);

        // Assert
        assertThat(count, is(1));
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
        Map<String, List<String>> duoMap = target.duoCount(cardArray);

        // Assert
        assertThat(duoMap.get("duoCount"), is(contains("1", "2")));
        assertThat(duoMap.get("Leona"), is(containsInAnyOrder("Vil")));
        assertThat(duoMap.get("Vil"), is(containsInAnyOrder("Leona")));
    }

    @Test
    @DisplayName("buddyCount")
    void buddyCount_01() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardA.setName(CharacterEnum.DEUCE);
        cardA.setBuddy1(CharacterEnum.CATER);
        cardA.setBuddy1Effect("HP UP(小)");
        cardA.setBuddy2(CharacterEnum.HYPHEN);
        cardA.setBuddy2Effect("-");
        cardA.setBuddy3(CharacterEnum.HYPHEN);
        cardA.setBuddy3Effect("-");
        cardA.setMaxHp(BigDecimal.valueOf(1000));
        cardA.setMaxAtk(BigDecimal.valueOf(1000));

        Card cardB = new Card();
        cardB.setName(CharacterEnum.CATER);
        cardB.setBuddy1(CharacterEnum.HYPHEN);
        cardB.setBuddy1Effect("-");
        cardB.setBuddy2(CharacterEnum.TREY);
        cardB.setBuddy2Effect("HP UP(中)");
        cardB.setBuddy3(CharacterEnum.HYPHEN);
        cardB.setBuddy3Effect("-");
        cardB.setMaxHp(BigDecimal.valueOf(2000));
        cardB.setMaxAtk(BigDecimal.valueOf(2000));

        Card cardC = new Card();
        cardC.setName(CharacterEnum.TREY);
        cardC.setBuddy1(CharacterEnum.HYPHEN);
        cardC.setBuddy1Effect("-");
        cardC.setBuddy2(CharacterEnum.HYPHEN);
        cardC.setBuddy2Effect("-");
        cardC.setBuddy3(CharacterEnum.LEONA);
        cardC.setBuddy3Effect("ATK UP(小)");
        cardC.setMaxHp(BigDecimal.valueOf(3000));
        cardC.setMaxAtk(BigDecimal.valueOf(3000));

        Card cardD = new Card();
        cardD.setName(CharacterEnum.LEONA);
        cardD.setBuddy1(CharacterEnum.RUGGIE);
        cardD.setBuddy1Effect("ATK UP(中)");
        cardD.setBuddy2(CharacterEnum.HYPHEN);
        cardD.setBuddy2Effect("-");
        cardD.setBuddy3(CharacterEnum.HYPHEN);
        cardD.setBuddy3Effect("-");
        cardD.setMaxHp(BigDecimal.valueOf(4000));
        cardD.setMaxAtk(BigDecimal.valueOf(4000));

        Card cardE = new Card();
        cardE.setName(CharacterEnum.RUGGIE);
        cardE.setBuddy1(CharacterEnum.HYPHEN);
        cardE.setBuddy1Effect("-");
        cardE.setBuddy2(CharacterEnum.DEUCE);
        cardE.setBuddy2Effect("HP&ATK UP(小)");
        cardE.setBuddy3(CharacterEnum.HYPHEN);
        cardE.setBuddy3Effect("-");
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
                is(arrayContaining(BigDecimal.valueOf(1200), BigDecimal.valueOf(2600), BigDecimal.valueOf(3000),
                        BigDecimal.valueOf(4000), BigDecimal.valueOf(6000))));
        assertThat(atkArray,
                is(arrayContaining(BigDecimal.valueOf(1000), BigDecimal.valueOf(2000), BigDecimal.valueOf(3600),
                        BigDecimal.valueOf(5400), BigDecimal.valueOf(6000))));
    }

    @Test
    @DisplayName("buddyCount_temp")
    void buddyCount_02() throws Exception {
        // Arrange
        Card[] cardArray = new Card[5];
        Card cardA = new Card();
        cardA.setName(CharacterEnum.DEUCE);
        cardA.setBuddy1(CharacterEnum.CATER);
        cardA.setBuddy1Effect("HP UP(小)");
        cardA.setBuddy2(CharacterEnum.HYPHEN);
        cardA.setBuddy2Effect("-");
        cardA.setBuddy3(CharacterEnum.HYPHEN);
        cardA.setBuddy3Effect("-");
        cardA.setMaxHp(BigDecimal.valueOf(1000));
        cardA.setMaxAtk(BigDecimal.valueOf(1000));

        Card cardC = new Card();
        cardC.setName(CharacterEnum.TREY);
        cardC.setBuddy1(CharacterEnum.HYPHEN);
        cardC.setBuddy1Effect("-");
        cardC.setBuddy2(CharacterEnum.HYPHEN);
        cardC.setBuddy2Effect("-");
        cardC.setBuddy3(CharacterEnum.LEONA);
        cardC.setBuddy3Effect("ATK UP(小)");
        cardC.setMaxHp(BigDecimal.valueOf(3000));
        cardC.setMaxAtk(BigDecimal.valueOf(3000));

        Card cardD = new Card();
        cardD.setName(CharacterEnum.LEONA);
        cardD.setBuddy1(CharacterEnum.RUGGIE);
        cardD.setBuddy1Effect("ATK UP(中)");
        cardD.setBuddy2(CharacterEnum.HYPHEN);
        cardD.setBuddy2Effect("-");
        cardD.setBuddy3(CharacterEnum.HYPHEN);
        cardD.setBuddy3Effect("-");
        cardD.setMaxHp(BigDecimal.valueOf(4000));
        cardD.setMaxAtk(BigDecimal.valueOf(4000));

        Card cardE = new Card();
        cardE.setName(CharacterEnum.RUGGIE);
        cardE.setBuddy1(CharacterEnum.HYPHEN);
        cardE.setBuddy1Effect("-");
        cardE.setBuddy2(CharacterEnum.DEUCE);
        cardE.setBuddy2Effect("HP&ATK UP(小)");
        cardE.setBuddy3(CharacterEnum.HYPHEN);
        cardE.setBuddy3Effect("-");
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
                        BigDecimal.valueOf(4000), BigDecimal.valueOf(6000))));
        assertThat(tempAtkArray,
                is(arrayContaining(BigDecimal.valueOf(1000), null, BigDecimal.valueOf(3600),
                        BigDecimal.valueOf(5400), BigDecimal.valueOf(6000))));
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
        BigDecimal totalMaxHp = target.sum(cardArray);

        // Assert
        assertThat(totalMaxHp, is(BigDecimal.valueOf(3000)));
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
        Card[] tempCardArray = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(tempCardArray[0].getMaxHp(), is(BigDecimal.valueOf(3571)));
        assertThat(tempCardArray[0].getMaxAtk(), is(BigDecimal.valueOf(3214)));
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
        Card[] tempCardArray = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(tempCardArray[1].getMaxHp(), is(BigDecimal.valueOf(5444)));
        assertThat(tempCardArray[1].getMaxAtk(), is(BigDecimal.valueOf(3889)));
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
        Card[] tempCardArray = target.calculate(calculateForm, cardArray);

        // Assert
        assertThat(tempCardArray[2].getMaxHp(), is(BigDecimal.valueOf(10909)));
        assertThat(tempCardArray[2].getMaxAtk(), is(BigDecimal.valueOf(4546)));
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
        BigDecimal[] array = new BigDecimal[5];

        // Act
        array = target.setArray(array);

        // Assert
        assertThat(array, is(
                arrayContaining(BigDecimal.ZERO, BigDecimal.ZERO,
                        BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)));
    }
}
