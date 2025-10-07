package com.example.twst.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.twst.dao.CardDao;
import com.example.twst.domain.model.Card;
import com.example.twst.form.CalculateForm;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.session.OrganizeSession;

@Service("CardService")
@SessionAttributes(value = "OrganizeSession")
public class CardService {

    private CardDao dao;

    private final OrganizeSession organizeSession;

    /**
     * １件登録.
     * 
     * @param CardForm
     * @return result
     */
    public boolean insert(CardForm cardForm) throws DuplicateKeyException {
        // 判定用変数
        boolean result = false;

        try {
            // insert実行
            if (dao.insertOne(cardForm) == 1) {
                result = true;
            }
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(
                    cardForm.getTableName().getViewName() + " に" + cardForm.getName().getViewName() + " はすでに存在します。");
        }

        return result;
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
    * 全テーブルを全取得
    *   
    * @param SearchForm
    * @return resultList
    */
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
                getReflectedStatus(tempCard);
                resultList.add(tempCard);
            }
        }

        String sortKey = form.getSort();
        switch (sortKey) {
            case "hp" -> resultList = resultList.stream().sorted(Comparator.comparing(Card::getMaxHp).reversed())
                    .collect(Collectors.toList());
            case "atk" -> resultList = resultList.stream().sorted(Comparator.comparing(Card::getMaxAtk).reversed())
                    .collect(Collectors.toList());
            case "reflectedAtk" ->
                resultList = resultList.stream().sorted(Comparator.comparing(Card::getReflectedBonusAtk).reversed())
                        .collect(Collectors.toList());
            case "reflectedHp" ->
                resultList = resultList.stream().sorted(Comparator.comparing(Card::getReflectedBonusHp).reversed())
                        .collect(Collectors.toList());
        }
        return resultList;
    }

    /**
     * テーブルを更新.
     * 
     * @param CardForm
     * @return 件数
     */
    public boolean updateOne(CardForm cardForm) {
        // 判定用変数
        boolean result = false;

        // update実行
        if (dao.updateOne(cardForm) == 1) {
            result = true;
        }
        return result;
    }

    /**
     * レコードを削除.
     * @param cardForm
     * @return 件数
     */
    public boolean deleteOne(CardForm cardForm) {
        // 判定用変数
        boolean result = false;

        // delete実行
        if (dao.deleteOne(cardForm) == 1) {
            result = true;
        }
        return result;
    }

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
        Map<String, BigDecimal> statusMap = new HashMap<>();
        BigDecimal[] tempHpArray = new BigDecimal[5];
        BigDecimal[] tempAtkArray = new BigDecimal[5];
        int buddyCount = 0;
        Map<String, List<String>> buddyMap = new HashMap<>();

        List<String> countList = new ArrayList<>();
        for (int i = 0; i < cardArray.length; i++) {
            // cardAがnullならスキップ
            if (cardArray[i] == null) {
                continue;
            }
            List<String> buddyList = new ArrayList<>();
            BigDecimal buddyBonusPowerHp = BigDecimal.ZERO;
            BigDecimal buddyBonusPowerAtk = BigDecimal.ZERO;
            for (Card cardB : cardArray) {
                if (tempHpArray[i] == null) {
                    tempHpArray[i] = cardArray[i].getMaxHp();
                }
                if (tempAtkArray[i] == null) {
                    tempAtkArray[i] = cardArray[i].getMaxAtk();
                }
                // cardBがnullなら、Max値を格納しループから抜ける
                if (cardB == null) {
                    continue;
                }
                String name = cardB.getName().getCharacterName();

                // ループ中のnameとbuddyが一致する場合、カウントしてlistに追加
                if (name.equals(cardArray[i].getBuddy1().getCharacterName())) {
                    statusMap = getBuddyBonusPower(cardArray[i].getBuddy1Grouping().getEffect());
                    buddyBonusPowerHp = buddyBonusPowerHp.add(statusMap.get("hpPower"));
                    buddyBonusPowerAtk = buddyBonusPowerAtk.add(statusMap.get("atkPower"));
                    buddyCount++;
                    buddyList.add(name);
                } else if (name.equals(cardArray[i].getBuddy2().getCharacterName())) {
                    statusMap = getBuddyBonusPower(cardArray[i].getBuddy2Grouping().getEffect());
                    buddyBonusPowerHp = buddyBonusPowerHp.add(statusMap.get("hpPower"));
                    buddyBonusPowerAtk = buddyBonusPowerAtk.add(statusMap.get("atkPower"));
                    buddyCount++;
                    buddyList.add(name);
                } else if (name.equals(cardArray[i].getBuddy3().getCharacterName())) {
                    statusMap = getBuddyBonusPower(cardArray[i].getBuddy3Grouping().getEffect());
                    buddyBonusPowerHp = buddyBonusPowerHp.add(statusMap.get("hpPower"));
                    buddyBonusPowerAtk = buddyBonusPowerAtk.add(statusMap.get("atkPower"));
                    buddyCount++;
                    buddyList.add(name);
                }
                if (!buddyList.isEmpty()) {
                    buddyMap.put(cardArray[i].getName().getCharacterName(), buddyList);

                }
            }
            countList.add(String.valueOf(buddyCount));
            buddyMap.put("buddyCount", countList);
            tempHpArray[i] = cardArray[i].getMaxHp().multiply(BigDecimal.ONE.add(buddyBonusPowerHp)).setScale(0,
                    RoundingMode.DOWN);// 小数点以下切り捨て
            tempAtkArray[i] = cardArray[i].getMaxAtk().multiply(BigDecimal.ONE.add(buddyBonusPowerAtk)).setScale(0,
                    RoundingMode.DOWN);// 小数点以下切り捨て
        }
        // sessionに保存する
        if (isTemp) {
            organizeSession.setTempHpArray(tempHpArray);
            organizeSession.setTempAtkArray(tempAtkArray);
        } else {
            organizeSession.setHpArray(tempHpArray);
            organizeSession.setAtkArray(tempAtkArray);
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
    private Map<String, BigDecimal> getBuddyBonusPower(String buddyEffect) {
        Map<String, BigDecimal> statusMap = new HashMap<>();
        BigDecimal hpPower = BigDecimal.ZERO;
        BigDecimal atkPower = BigDecimal.ZERO;
        BigDecimal small = BigDecimal.valueOf(0.2);// HP UP(小),ATK UP(小)
        BigDecimal hpMedium = BigDecimal.valueOf(0.3);// HP UP(中)
        BigDecimal atkMedium = BigDecimal.valueOf(0.35);// ATK UP(中)

        switch (buddyEffect) {
            case "HP UP(小)" -> hpPower = hpPower.add(small);
            case "HP UP(中)" -> hpPower = hpPower.add(hpMedium);
            case "ATK UP(小)" -> atkPower = atkPower.add(small);
            case "ATK UP(中)" -> atkPower = atkPower.add(atkMedium);
            case "HP&ATK UP(小)" -> {
                hpPower = hpPower.add(small);
                atkPower = atkPower.add(small);
            }
        }

        statusMap.put("hpPower", hpPower);
        statusMap.put("atkPower", atkPower);
        return statusMap;
    }

    /**
     * バディボーナス反映後のステータスを取得する.
     * @param card
     * @return card
     */
    private Card getReflectedStatus(Card card) {
        BigDecimal reflectedBonusHp = BigDecimal.ZERO;
        BigDecimal reflectedBonusAtk = BigDecimal.ZERO;

        Map<String, BigDecimal> statusMap = getBuddyBonusPower(card.getBuddy1Grouping().getEffect());
        reflectedBonusHp = reflectedBonusHp.add(statusMap.get("hpPower"));
        reflectedBonusAtk = reflectedBonusAtk.add(statusMap.get("atkPower"));

        statusMap = getBuddyBonusPower(card.getBuddy2Grouping().getEffect());
        reflectedBonusHp = reflectedBonusHp.add(statusMap.get("hpPower"));
        reflectedBonusAtk = reflectedBonusAtk.add(statusMap.get("atkPower"));

        statusMap = getBuddyBonusPower(card.getBuddy3Grouping().getEffect());
        reflectedBonusHp = reflectedBonusHp.add(statusMap.get("hpPower"));
        reflectedBonusAtk = reflectedBonusAtk.add(statusMap.get("atkPower"));

        card.setReflectedBonusHp(
                card.getMaxHp().multiply(BigDecimal.ONE.add(reflectedBonusHp)).setScale(0, RoundingMode.DOWN));
        card.setReflectedBonusAtk(
                card.getMaxAtk().multiply(BigDecimal.ONE.add(reflectedBonusAtk)).setScale(0, RoundingMode.DOWN));

        return card;
    }

    /**
     * 最大合計HPを取得.
     * 
     * @param cardArray
     * @return 最大合計HP
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
    public Card[] calculate(CalculateForm calculateForm, Card[] cardArray) throws CloneNotSupportedException {
        Card[] tempCardArray = new Card[5];
        for (int i = 0; i < cardArray.length; i++) {
            if (cardArray[i] == null) {
                continue;
            }
            tempCardArray[i] = cardArray[i].clone();
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
        BigDecimal[] levelArray = new BigDecimal[5];

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

        if (organizeSession.getLevelArray() != null) {
            levelArray = organizeSession.getLevelArray();
        } else {
            levelArray = setArray(levelArray);
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
        BigDecimal reflectedBonusHp = BigDecimal.ZERO;

        for (BigDecimal hp : hpArray) {
            if (hp == null) {
                continue;
            }
            reflectedBonusHp = reflectedBonusHp.add(hp);
        }

        return reflectedBonusHp;
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

    /**
     * 最大Lvを設定.
     * @param cardArray
     * @param array
     * @return array
     */
    public BigDecimal[] setLevelArray(Card[] cardArray, BigDecimal[] array) {
        for (int i = 0; i < array.length; i++) {
            if ((array[i] == BigDecimal.ZERO) || (array[i] == null)) {
                if (cardArray[i] == null) {
                    continue;
                }
                String rare = cardArray[i].getRare();
                BigDecimal maxLevel = BigDecimal.ZERO;

                // レア度から最大Lvを設定
                switch (rare) {
                    case "R" -> maxLevel = new BigDecimal(70);
                    case "SR" -> maxLevel = new BigDecimal(90);
                    case "SSR" -> maxLevel = new BigDecimal(110);
                }
                array[i] = maxLevel;
            }
        }
        return array;
    }

    /**
     * tempCardArrayを設定.
     * @param cardArray
     * @return tempCardArray
     */
    public Card[] setTempCardArray(Card[] cardArray) throws CloneNotSupportedException {
        Card[] tempCardArray = new Card[5];
        BigDecimal[] hpArray = organizeSession.getHpArray();
        BigDecimal[] atkArray = organizeSession.getAtkArray();

        for (int i = 0; i < tempCardArray.length; i++) {
            if (cardArray[i] == null) {
                continue;
            }
            Card card = new Card();
            tempCardArray[i] = card;
            tempCardArray[i] = cardArray[i].clone();
            tempCardArray[i].setMaxHp(hpArray[i]);
            tempCardArray[i].setMaxAtk(atkArray[i]);
        }
        return tempCardArray;
    }

    @Autowired
    public void setCardDao(@Qualifier("CardDaoJdbcImpl") CardDao dao) {
        this.dao = dao;
    }

    public CardService(OrganizeSession organizeSession) {
        this.organizeSession = organizeSession;
    }
}
