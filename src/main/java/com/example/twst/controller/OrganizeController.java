package com.example.twst.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.form.BattleForm;
import com.example.twst.form.CardForm;
import com.example.twst.form.OrganizeForm;
import com.example.twst.service.CardService;
import com.example.twst.session.OrganizeSession;

@Controller
@RequestMapping("/organize")
@SessionAttributes(value = "OrganizeSession")
public class OrganizeController {

    private CardService cardService;

    private final OrganizeSession organizeSession;

    /**
     * exam buttonの表示に使用するアイテム
     */
    static final Map<String, String> EXAM = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
        {
            put("BASIC", "basic");
            put("DEFENCE", "defence");
            put("ATTACK", "attack");
        }
    });

    /**
     * difficulty buttonの表示に使用するアイテム
     */
    static final Map<String, String> DIFFICULTY = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
        {
            put("EASY", "easy");
            put("NORMAL", "normal");
            put("HARD", "hard");
            put("EXTRA", "extra");
        }
    });

    /**
     * magicType buttonの表示に使用するアイテム
     */
    static final Map<String, String> MAGIC_TYPE = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
        {
            put("火", "FIRE");
            put("水", "WATER");
            put("木", "LEAF");
            put("無", "VOID");
            put("全", "ALL");
        }
    });

    @ModelAttribute
    public OrganizeForm setUpOrganizeForm() {
        OrganizeForm organizeForm = new OrganizeForm();
        return organizeForm;
    }

    @ModelAttribute
    public CardForm setUpCardForm() {
        CardForm cardForm = new CardForm();
        return cardForm;
    }

    @ModelAttribute
    public BattleForm setUpBattleForm() {
        BattleForm battleForm = new BattleForm();
        battleForm.setExam("basic");
        battleForm.setDifficulty("easy");
        battleForm.setEnemyMagicType("FIRE");

        return battleForm;
    }

    /**
     * 編成画面の表示
     */
    @GetMapping
    public String input(OrganizeForm organizeForm,
            @ModelAttribute("organizeSession") OrganizeSession organizeSession,
            Model model) throws Exception {

        Card[] cardArray = new Card[5];
        if (organizeSession.getCardArray() != null) {
            // sessionからcardArrayを取得
            cardArray = organizeSession.getCardArray();
        }

        // デュオをカウント
        int duoCount = 0;
        Map<String, List<String>> duoMap = cardService.duoCount(cardArray);
        if (duoMap.containsKey("duoCount")) {
            List<String> countList = duoMap.get("duoCount");
            duoCount = Integer.parseInt(countList.get(countList.size() - 1));
        }

        // バディボーナスを加算
        int buddyCount = 0;
        Map<String, List<String>> buddyMap = cardService.buddyCount(cardArray, false);
        if (buddyMap.containsKey("buddyCount")) {
            List<String> countList = buddyMap.get("buddyCount");
            buddyCount = Integer.parseInt(countList.get(countList.size() - 1));
        }

        BigDecimal[] levelArray = new BigDecimal[5];
        if (organizeSession.getLevelArray() != null) {
            // sessionからlevelArrayを取得
            levelArray = organizeSession.getLevelArray();
        }

        // sessionからステータスを取得
        BigDecimal[] hpArray = new BigDecimal[5];// バディボーナス反映後HP

        if (organizeSession.getHpArray() != null) {
            hpArray = organizeSession.getHpArray();
        } else {
            hpArray = cardService.setArray(hpArray);
        }

        // 合計値を取得
        BigDecimal totalHp = cardService.sum(cardArray);// 最大合計HP
        BigDecimal reflectedHp = cardService.tempSum(hpArray);// バディボーナス反映後合計HP

        BigDecimal[] tempHpArray = new BigDecimal[5];// 推定HP
        BigDecimal[] tempAtkArray = new BigDecimal[5];// 推定ATk
        Card[] tempCardArray = cardService.setTempCardArray(cardArray);

        // sessionからステータスを取得
        if (organizeSession.getTempHpArray() != null) {
            tempHpArray = organizeSession.getTempHpArray();
            tempAtkArray = organizeSession.getTempAtkArray();
        } else {
            tempHpArray = cardService.setArray(tempHpArray);
            tempAtkArray = cardService.setArray(tempAtkArray);
        }

        levelArray = cardService.setLevelArray(cardArray, levelArray);

        // sessionに保存
        organizeSession.setLevelArray(levelArray);
        organizeSession.setHpArray(hpArray);
        organizeSession.setTempHpArray(tempHpArray);
        organizeSession.setTempAtkArray(tempAtkArray);
        organizeSession.setTempCardArray(tempCardArray);
        organizeSession.setCardArray(cardArray);

        // modelに追加
        model.addAttribute("totalHp", totalHp); // 最大合計HP
        model.addAttribute("buddyCount", buddyCount);// バディ
        model.addAttribute("duoCount", duoCount);// デュオ
        model.addAttribute("reflectedHp", reflectedHp);// バディボーナス反映後合計HP
        model.addAttribute("arrayIndex", organizeForm.getArrayIndex());
        model.addAttribute("cardArray", cardArray);
        model.addAttribute("buddyMap", buddyMap);
        model.addAttribute("duoMap", duoMap);
        model.addAttribute("organizeSession", organizeSession);
        model.addAttribute("exam", EXAM);
        model.addAttribute("enemyMagicType", MAGIC_TYPE);
        model.addAttribute("difficulty", DIFFICULTY);

        // enemy
        model.addAttribute("magic", CardEnum.getValue("magic"));

        return "organize.html";
    }

    /**
     * 編成画面にセット
     */
    @PostMapping
    public String conform(CardForm cardForm, OrganizeForm organizeForm,
            RedirectAttributes redirectAttributes) {

        Card[] cardArray = new Card[5];
        if (organizeSession.getCardArray() != null) {
            // sessionからcardArrayを取得
            cardArray = organizeSession.getCardArray();
        }

        // CardFormをセットする
        Card card = new Card();
        card.setTableName(cardForm.getTableName());
        card.setId(cardForm.getId());
        card.setName(cardForm.getName());
        card.setClothingName(cardForm.getClothingName());
        card.setRare(cardForm.getRare());
        card.setType(cardForm.getType());
        card.setBuddy1(cardForm.getBuddy1());
        card.setBuddy2(cardForm.getBuddy2());
        card.setBuddy3(cardForm.getBuddy3());
        card.setDuo(cardForm.getDuo());
        card.setMinHp(cardForm.getMinHp());
        card.setMinAtk(cardForm.getMinAtk());
        card.setMaxHp(cardForm.getMaxHp());
        card.setMaxAtk(cardForm.getMaxAtk());
        card.setMagic1BuffdebuffGrouping(cardForm.getMagic1BuffdebuffGrouping());
        card.setMagic2BuffdebuffGrouping(cardForm.getMagic2BuffdebuffGrouping());
        card.setMagic3BuffdebuffGrouping(cardForm.getMagic3BuffdebuffGrouping());
        card.setBuddy1Effect(cardForm.getBuddy1Effect());
        card.setBuddy2Effect(cardForm.getBuddy2Effect());
        card.setBuddy3Effect(cardForm.getBuddy3Effect());
        card.setMagic1(cardForm.getMagic1());
        card.setMagic2(cardForm.getMagic2());
        card.setMagic3(cardForm.getMagic3());

        // sessionに保存する
        cardArray[organizeForm.getArrayIndex()] = card;
        organizeSession.setCardArray(cardArray);
        redirectAttributes.addFlashAttribute("organizeSession", organizeSession);

        return "redirect:organize";
    }

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public OrganizeController() {
        this.organizeSession = new OrganizeSession();
    }
}
