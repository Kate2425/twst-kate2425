package com.example.twst.controller;

import java.math.BigDecimal;
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
import com.example.twst.form.CalculateForm;
import com.example.twst.form.CardForm;
import com.example.twst.form.OrganizeForm;
import com.example.twst.service.CardService;
import com.example.twst.session.OrganizeSession;

@Controller
@RequestMapping("/organize")
@SessionAttributes(value = "OrganizeSession")
public class OrganizeController {

    @Autowired
    private final CardService cardService;

    @Autowired
    private final OrganizeSession organizeSession;

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

    /**
     * 編成画面の表示
     */
    @GetMapping
    public String input(OrganizeForm organizeForm, CalculateForm calculateForm,
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

        // 計算ボタン押下時
        BigDecimal[] tempHpArray = new BigDecimal[5];// 推定HP
        BigDecimal[] tempAtkArray = new BigDecimal[5];// 推定ATk
        Card[] tempCardArray = new Card[5];

        if (organizeSession.getTempCardArray() != null) {
            tempCardArray = organizeSession.getTempCardArray();
        }
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

        BigDecimal tempTotalHp = organizeSession.getTempTotalHp();
        BigDecimal tempReflectedHp = organizeSession.getTempReflectedHp();

        // modelに追加
        model.addAttribute("totalHp", totalHp); // 最大合計HP
        model.addAttribute("buddyCount", buddyCount);// バディ
        model.addAttribute("duoCount", duoCount);// デュオ
        model.addAttribute("reflectedHp", reflectedHp);// バディボーナス反映後合計HP
        model.addAttribute("tempTotalHp", tempTotalHp);// 推定合計HP
        model.addAttribute("tempReflectedHp", tempReflectedHp);// バディボーナス反映後推定合計HP
        model.addAttribute("arrayIndex", organizeForm.getArrayIndex());
        model.addAttribute("cardArray", cardArray);
        model.addAttribute("buddyMap", buddyMap);
        model.addAttribute("duoMap", duoMap);
        model.addAttribute("OrganizeSession", organizeSession);

        // enemy
        model.addAttribute("magic", CardEnum.getValue("magic"));

        return "organize.html";
    }

    /**
     * 編成画面にセット
     */
    @PostMapping
    public String conform(CardForm cardForm, OrganizeForm organizeForm,
            RedirectAttributes redirectAttributes,
            Model model) {

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
        card.setBuddy1Effect(cardForm.getBuddy1Effect());
        card.setBuddy2Effect(cardForm.getBuddy2Effect());
        card.setBuddy3Effect(cardForm.getBuddy3Effect());
        card.setMagic1Name(cardForm.getMagic1Name());
        card.setMagic2Name(cardForm.getMagic2Name());
        card.setMagic3Name(cardForm.getMagic3Name());
        card.setMagic1Effect(cardForm.getMagic1Effect());
        card.setMagic2Effect(cardForm.getMagic2Effect());
        card.setMagic3Effect(cardForm.getMagic3Effect());
        card.setMagic1Type(cardForm.getMagic1Type());
        card.setMagic2Type(cardForm.getMagic2Type());
        card.setMagic3Type(cardForm.getMagic3Type());

        // sessionに保存する
        cardArray[organizeForm.getArrayIndex()] = card;
        organizeSession.setCardArray(cardArray);
        redirectAttributes.addFlashAttribute("organizeSession", organizeSession);

        // modelに追加する
        model.addAttribute("cardArray", cardArray);
        model.addAttribute("organizeSession", organizeSession);

        return "redirect:organize";
    }

    public OrganizeController(CardService cardService) {
        this.organizeSession = new OrganizeSession();
        this.cardService = cardService;
    }
}
