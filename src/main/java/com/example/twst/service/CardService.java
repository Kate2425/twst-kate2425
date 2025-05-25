package com.example.twst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.twst.dao.CardDao;
import com.example.twst.domain.model.Card;
import com.example.twst.form.CalculateForm;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.session.OrganizeSession;

@Service("CardService")
@SessionAttributes(value = { "hpArray", "atkArray", "tempHpArray", "tempAtkArray", "tempCardArray", "levelArray" })
public class CardService {

    @Autowired
    @Qualifier("CardDaoJdbcImpl")
    CardDao dao;

    @Autowired
    OrganizeSession organizeSession;

    /**
     * １件登録.
     * 
     * @param CardForm
     * @return 成功可否
     */
    public boolean insert(CardForm cardForm) {
        // insert実行
        int rowNumber = dao.insertOne(cardForm);
        // 判定用変数
        boolean result = false;

        if (rowNumber > 0) {
            // insert成功
            result = true;
        }
        return result;
    }

    /**
     * カウント用メソッド.
     * 
     * @return
     */
    public int count() {
        return dao.countRecord();
    }

    /**
     * １件取得.
     * 
     * @param name
     * @return Card
     */
    public Card selectOne(String name) {

        // SQL実行
        return dao.selectOne(name);
    }

    /**
     * 全件取得.
     * 
     * @param SearchForm
     * @param tableName
     * @return List<Card>
     */
    public List<Card> selectMany(SearchForm searchForm, String tableName) {
        // 全件取得
        return dao.selectMany(searchForm, tableName);
    }

    /**
     * 全テーブルを取得.
     * 
     * @param SearchForm
     * @return List<Card>
     */
    public List<Card> selectAll(SearchForm searchForm) {
        return dao.selectAll(searchForm);
    }

    /**
     * テーブルを更新.
     * 
     * @param CardForm
     * @return 件数
     * @throws DataAccessException
     */
    public int updateOne(CardForm cardForm) {
        return dao.updateOne(cardForm);
    };

    /**
     * デュオをカウント.
     * 
     * @param cardArray
     * @return duoMap
     */
    public Map<String, List<String>> duoCount(Card[] cardArray) {
        int duoCount = 0;
        Map<String, List<String>> duoMap = new HashMap<>();
        List<String> countList = new ArrayList<>();
        for (Card cardA : cardArray) {
            // cardAがnullならスキップ
            if (cardA == null) {
                continue;
            }
            for (Card cardB : cardArray) {
                List<String> duoList = new ArrayList<>();
                // cardBがnullならスキップ
                if (cardB == null) {
                    continue;
                }
                String name = cardA.getName().getCharacterName();
                // ループ中のnameとbuddyが一致する場合、カウントしてlistに追加

                if (name.equals(cardB.getDuo().getCharacterName())) {
                    duoCount++;
                    duoList.add(name);
                }
                if (!duoList.isEmpty()) {
                    duoMap.put(cardB.getName().getCharacterName(), duoList);
                }
            }
            countList.add(String.valueOf(duoCount));
            duoMap.put("duoCount", countList);
        }
        return duoMap;
    }

    /**
     * バディをカウント.
     * 
     * @param cardArray
     * @return buddyCount
     */
    public Map<String, List<String>> buddyCount(Card[] cardArray, boolean isTemp) {
        int buddyCount = 0;
        Map<String, List<String>> buddyMap = new HashMap<>();
        List<String> countList = new ArrayList<>();
        for (Card cardA : cardArray) {
            // cardAがnullならスキップ
            if (cardA == null) {
                continue;
            }
            List<String> buddyList = new ArrayList<>();
            for (Card cardB : cardArray) {
                // cardBがnullならスキップ
                if (cardB == null) {
                    continue;
                }
                String name = cardB.getName().getCharacterName();
                // ループ中のnameとbuddyが一致する場合、カウントしてlistに追加
                if (name.equals(cardA.getBuddy1().getCharacterName())) {
                    buddyBonusCalc(cardA.getBuddy1Effect(), cardArray, isTemp);
                    buddyCount++;
                    buddyList.add(name);
                } else if (name.equals(cardA.getBuddy2().getCharacterName())) {
                    buddyBonusCalc(cardA.getBuddy2Effect(), cardArray, isTemp);
                    buddyCount++;
                    buddyList.add(name);
                } else if (name.equals(cardA.getBuddy3().getCharacterName())) {
                    buddyBonusCalc(cardA.getBuddy3Effect(), cardArray, isTemp);
                    buddyCount++;
                    buddyList.add(name);
                }
                if (!buddyList.isEmpty()) {
                    buddyMap.put(cardA.getName().getCharacterName(), buddyList);
                }
            }
            countList.add(String.valueOf(buddyCount));
            buddyMap.put("buddyCount", countList);
        }
        return buddyMap;
    }

    /**
     * バディボーナスを計算.
     * 
     * @param buddyEffect
     * @param tempTotalMap
     * @return tempTotalMap
     */
    private void buddyBonusCalc(String buddyEffect, Card[] cardArray, boolean isTemp) {
        BigDecimal small = BigDecimal.valueOf(1.2);// HP UP(小),ATK UP(小)
        BigDecimal hpMedium = BigDecimal.valueOf(1.3);// HP UP(中)
        BigDecimal atkMedium = BigDecimal.valueOf(1.35);// ATK UP(中)
        BigDecimal[] tempHpArray = new BigDecimal[5];// 推定HP
        BigDecimal[] tempAtkArray = new BigDecimal[5];// 推定ATK

        for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i] == null) {
                continue;
            }
            BigDecimal[] sessionHpArray = organizeSession.getTempHpArray();
            BigDecimal[] sessionAtkArray = organizeSession.getTempAtkArray();
            if ((sessionHpArray[i] != null) && (sessionAtkArray[i] != null)
                    && (sessionHpArray[i] != cardArray[i].getMaxHp()) &&
                    (sessionAtkArray[i] != cardArray[i].getMaxAtk())) {
                tempHpArray[i] = sessionHpArray[i];
                tempAtkArray[i] = sessionAtkArray[i];
            } else {
                tempHpArray[i] = cardArray[i].getMaxHp();
                tempAtkArray[i] = cardArray[i].getMaxAtk();
            }

            switch (buddyEffect) {
                case "HP UP(小)" -> tempHpArray[i] = tempHpArray[i].multiply(small);
                case "HP UP(中)" -> tempHpArray[i] = tempHpArray[i].multiply(hpMedium);
                case "ATK UP(小)" -> tempAtkArray[i] = tempAtkArray[i].multiply(small);
                case "ATK UP(中)" -> tempAtkArray[i] = tempAtkArray[i].multiply(atkMedium);
                case "HP&ATK UP(小)" -> {
                    tempHpArray[i] = tempHpArray[i].multiply(small);
                    tempAtkArray[i] = tempAtkArray[i].multiply(small);
                }
            }
            tempHpArray[i] = tempHpArray[i].setScale(0, RoundingMode.DOWN);// 小数点以下切り捨て
            tempAtkArray[i] = tempAtkArray[i].setScale(0, RoundingMode.DOWN); // 小数点以下切り捨て
        }

        // sessionに保存する
        if (isTemp) {
            organizeSession.setTempHpArray(tempHpArray);
            organizeSession.setTempAtkArray(tempAtkArray);
        } else {
            organizeSession.setHpArray(tempHpArray);
            organizeSession.setAtkArray(tempAtkArray);
        }
    }

    /**
     * 合計値Mapを取得.
     * 
     * @param cardArray
     * @return 合計値Map
     */
    public BigDecimal sum(Card[] cardArray) {
        BigDecimal totalMaxHp = BigDecimal.ZERO;// 最大合計HP

        for (Card card : cardArray) {
            if (card == null) {
                continue;
            }
            totalMaxHp = totalMaxHp.add(card.getMaxHp());
        }
        return totalMaxHp;
    }

    /**
     * 推定HP 推定ATKを計算.
     * 
     * @param calculateForm
     * @return Map<String, BigDecimal>
     */
    public Card[] calculate(CalculateForm calculateForm, Card[] cardArray) {
        Card[] tempCardArray = new Card[5];
        for (int i = 0; i < cardArray.length; i++) {
            try {
                if (cardArray[i] == null) {
                    continue;
                }
                tempCardArray[i] = cardArray[i].clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        BigDecimal maxLevel = BigDecimal.ZERO;
        String rare = calculateForm.getRare();

        // レア度から最大Lvを設定
        switch (rare) {
            case "R" -> maxLevel = new BigDecimal(70);
            case "SR" -> maxLevel = new BigDecimal(90);
            case "SSR" -> maxLevel = new BigDecimal(110);
        }

        BigDecimal level = calculateForm.getLevel();
        BigDecimal tempHp = calculateForm.getMaxHp();
        BigDecimal tempAtk = calculateForm.getMaxAtk();

        tempHp = tempHp.divide(calculateForm.getMinHp(), 6, RoundingMode.HALF_UP);// maxHp / minHp = coefficient
        tempHp = tempHp.divide(maxLevel, 6, RoundingMode.HALF_UP);// coefficient / maxLv = 1LvCoefficient
        tempHp = tempHp.multiply(level);// 1LvCoefficient * level = currentCoefficient
        tempHp = tempHp.multiply(calculateForm.getMinHp());// currentCoefficient * minHp = currentHp
        tempHp = tempHp.setScale(0, RoundingMode.HALF_UP);// 四捨五入

        tempAtk = tempAtk.divide(calculateForm.getMinAtk(), 6, RoundingMode.HALF_UP);// maxAtk / minAtk = coefficient
        tempAtk = tempAtk.divide(maxLevel, 6, RoundingMode.HALF_UP);// coefficient / maxLv = 1LvCoefficient
        tempAtk = tempAtk.multiply(level);// 1LvCoefficient * level = currentCoefficient
        tempAtk = tempAtk.multiply(calculateForm.getMinAtk());// currentCoefficient * minAtk = currentAtk
        tempAtk = tempAtk.setScale(0, RoundingMode.HALF_UP);// 四捨五入

        tempCardArray[calculateForm.getArrayIndex()].setMaxHp(tempHp);
        tempCardArray[calculateForm.getArrayIndex()].setMaxAtk(tempAtk);

        BigDecimal[] levelArray = new BigDecimal[5];
        if (organizeSession.getLevelArray() != null) {
            levelArray = organizeSession.getLevelArray();
        }

        levelArray[calculateForm.getArrayIndex()] = level;
        organizeSession.setLevelArray(levelArray);

        return tempCardArray;
    }

    /**
     * 推定ステータスを計算.
     * 
     * @param tempHpArray
     * @return tempHpArray
     */
    public BigDecimal tempSum(BigDecimal[] hpArray) {
        BigDecimal reflectedHp = BigDecimal.ZERO;

        for (BigDecimal hp : hpArray) {
            if (hp == null) {
                continue;
            }
            reflectedHp = reflectedHp.add(hp);
        }

        return reflectedHp;
    }

    /**
     * BigDecimal.ZEROを配列に格納.
     * 
     * @param BigDecimal[] array
     * @return BigDecimal[]
     */
    public BigDecimal[] setArray(BigDecimal[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = BigDecimal.ZERO;
        }
        return array;
    }
}
