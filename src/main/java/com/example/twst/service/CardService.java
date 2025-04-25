package com.example.twst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.twst.dao.CardDao;
import com.example.twst.domain.model.Card;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;

@Service("CardService")
public class CardService {

    @Autowired
    @Qualifier("CardDaoJdbcImpl")
    CardDao dao;

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
     * 合計値Mapを取得.
     * 
     * @param cardArray
     * @return 合計値Map
     */
    public Map<String, BigDecimal> sum(Card[] cardArray) {

        BigDecimal totalMinHp = BigDecimal.ZERO;// 最小合計HP
        BigDecimal totalMinAtk = BigDecimal.ZERO;// 最小合計ATK
        BigDecimal totalMaxHp = BigDecimal.ZERO;// 最大合計HP
        BigDecimal totalMaxAtk = BigDecimal.ZERO;// 最大合計ATK
        Map<String, BigDecimal> totalMap = new HashMap<>();// 合計値Map

        for (Card card : cardArray) {
            if (card.getMinHp() != null) {
                totalMinHp = totalMinHp.add(card.getMinHp());
            }
            if (card.getMinAtk() != null) {
                totalMinAtk = totalMinAtk.add(card.getMinAtk());
            }
            if (card.getMaxHp() != null) {
                totalMaxHp = totalMaxHp.add(card.getMaxHp());
            }
            if (card.getMaxAtk() != null) {
                totalMaxAtk = totalMaxAtk.add(card.getMaxAtk());
            }
        }

        totalMap.put("totalMinHp", totalMinHp);
        totalMap.put("totalMinAtk", totalMinAtk);
        totalMap.put("totalMaxHp", totalMaxHp);
        totalMap.put("totalMaxAtk", totalMaxAtk);

        return totalMap;
    }

    /**
     * バディをカウント.
     * 
     * @param cardArray
     * @return buddyCount
     */
    public Map<String, BigDecimal> buddyCount(Card[] cardArray, Map<String, BigDecimal> tempTotalMap) {
        BigDecimal buddyCount = BigDecimal.ZERO;

        for (Card cardA : cardArray) {
            // cardAがnullならスキップ
            if (cardA.getName() == null) {
                continue;
            }
            String name = cardA.getName().getCharacterName();
            for (Card cardB : cardArray) {
                // cardBがnullならスキップ
                if (cardB.getBuddy1() == null) {
                } else {
                    // ループ中のnameとbuddyが一致するとカウント
                    if (name.equals(cardB.getBuddy1().getCharacterName())) {
                        String buddyEffect = cardB.getBuddy1Effect();
                        tempTotalMap = buddyBonusCalc(buddyEffect, tempTotalMap);
                        buddyCount = buddyCount.add(BigDecimal.ONE);
                    } else if (name.equals(cardB.getBuddy2().getCharacterName())) {
                        String buddyEffect = cardB.getBuddy2Effect();
                        tempTotalMap = buddyBonusCalc(buddyEffect, tempTotalMap);
                        buddyCount = buddyCount.add(BigDecimal.ONE);
                    } else if (name.equals(cardB.getBuddy3().getCharacterName())) {
                        String buddyEffect = cardB.getBuddy3Effect();
                        tempTotalMap = buddyBonusCalc(buddyEffect, tempTotalMap);
                        buddyCount = buddyCount.add(BigDecimal.ONE);
                    }
                }
            }
        }
        tempTotalMap.put("buddyCount", buddyCount);
        return tempTotalMap;
    }

    /**
     * バディボーナスを計算.
     * 
     * @param buddyEffect
     * @param tempTotalMap
     * @return tempTotalMap
     */
    private Map<String, BigDecimal> buddyBonusCalc(String buddyEffect, Map<String, BigDecimal> tempTotalMap) {
        BigDecimal tempTotalHp = tempTotalMap.get("tempTotalHp");
        BigDecimal tempTotalAtk = tempTotalMap.get("tempTotalAtk");
        BigDecimal small = BigDecimal.valueOf(1.05);
        BigDecimal medium = BigDecimal.valueOf(1.1);

        switch (buddyEffect) {
            case "HP UP(小)" -> tempTotalHp = tempTotalHp.multiply(small);
            case "HP UP(中)" -> tempTotalHp = tempTotalHp.multiply(medium);
            case "ATK UP(小)" -> tempTotalAtk = tempTotalAtk.multiply(small);
            case "ATK UP(中)" -> tempTotalAtk = tempTotalAtk.multiply(medium);
            case "HP&ATK UP(小)" -> {
                tempTotalHp = tempTotalHp.multiply(small);
                tempTotalAtk = tempTotalAtk.multiply(small);
            }
        }

        tempTotalHp = tempTotalHp.setScale(0, RoundingMode.HALF_UP);
        tempTotalAtk = tempTotalAtk.setScale(0, RoundingMode.HALF_UP);

        tempTotalMap.put("tempTotalHp", tempTotalHp);
        tempTotalMap.put("tempTotalAtk", tempTotalAtk);
        return tempTotalMap;
    }

    /**
     * デュオをカウント.
     * 
     * @param cardArray
     * @return duoCount
     */
    public int duoCount(Card[] cardArray) {
        int duoCount = 0;
        for (Card card : cardArray) {
            if (card.getName() == null) {
                break;
            }
            String name = card.getName().getCharacterName();
            // for (int j = 0; j < cardArray.length; j++) {
            if (card.getDuo() == null) {
                break;
            } else {
                if (name.equals(card.getDuo().getCharacterName())) {
                    duoCount++;
                }
            }
            // }
        }
        return duoCount;
    }

    /**
     * tempHp tempAtkを計算.
     * 
     * @param calculateForm
     * @return Map<String, BigDecimal>
     */
    // public Map<String, BigDecimal> calculate(CalculateForm calculateForm) {
    // BigDecimal maxLevel = BigDecimal.ZERO;
    // Map<String, BigDecimal> calcMap = new HashMap<>();
    // String rare = calculateForm.getRare();

    // switch (rare) {
    // case "R" -> maxLevel = new BigDecimal(70);
    // case "SR" -> maxLevel = new BigDecimal(90);
    // case "SSR" -> maxLevel = new BigDecimal(110);
    // }

    // BigDecimal[] levelArray = calculateForm.getLevelArray();
    // BigDecimal level = levelArray[calculateForm.getArrayIndex()];
    // BigDecimal tempHp = calculateForm.getMaxHp();
    // BigDecimal tempAtk = calculateForm.getMaxAtk();

    // tempHp = tempHp.subtract(calculateForm.getMinHp());// maxHp - minHp = upHp
    // tempHp = tempHp.divide(maxLevel, 0, RoundingMode.HALF_UP);// upHp / maxLv
    // =1LvUpHp
    // tempHp = tempHp.multiply(level);// 1LvUpHp * currentLv = maxCurrentHp
    // tempHp = tempHp.add(calculateForm.getMinHp()); // maxCurrentHp + minHp =
    // currentHp

    // tempAtk = tempAtk.subtract(calculateForm.getMinAtk());
    // tempAtk = tempAtk.divide(maxLevel, 0, RoundingMode.HALF_UP);
    // tempAtk = tempAtk.multiply(level);
    // tempAtk = tempAtk.add(calculateForm.getMinAtk());

    // calcMap.put("tempHp", tempHp);
    // calcMap.put("tempAtk", tempAtk);
    // calcMap.put("level", level);

    // return calcMap;
    // }

    /**
     * tempTotalHp tempTotalAtkを計算.
     * 
     * @param tempHpArray
     * @param tempAtkArray
     * @return Map<String, BigDecimal>
     */
    public Map<String, BigDecimal> tempSum(BigDecimal[] tempHpArray, BigDecimal[] tempAtkArray) {
        Map<String, BigDecimal> tempTotalMap = new HashMap<>();
        BigDecimal tempTotalHp = BigDecimal.ZERO;
        BigDecimal tempTotalAtk = BigDecimal.ZERO;

        for (BigDecimal tempHp : tempHpArray) {
            if (tempHp == null) {
                continue;
            }
            tempTotalHp = tempTotalHp.add(tempHp);
        }
        for (BigDecimal tempAtk : tempAtkArray) {
            if (tempAtk == null) {
                continue;
            }
            tempTotalAtk = tempTotalAtk.add(tempAtk);
        }

        tempTotalMap.put("tempTotalHp", tempTotalHp);
        tempTotalMap.put("tempTotalAtk", tempTotalAtk);

        return tempTotalMap;
    }
}
