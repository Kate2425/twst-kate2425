package com.example.twst.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.twst.domain.model.Card;
import com.example.twst.form.CalculateForm;
import com.example.twst.service.CardService;
import com.example.twst.session.OrganizeSession;

@Controller
@SessionAttributes(value = "OrganizeSession")
@RequestMapping("/calculate")
public class CalculateController {
    @Autowired
    private final CardService cardService;

    @Autowired
    private final OrganizeSession organizeSession;

    public CalculateController(CardService cardService) {
        this.organizeSession = new OrganizeSession();
        this.cardService = cardService;
    }

    @ModelAttribute
    public CalculateForm setUpCalculateForm() {
        CalculateForm calculateForm = new CalculateForm();
        return calculateForm;
    }

    @GetMapping
    public String calculate(CalculateForm calculateForm,
            RedirectAttributes redirectAttributes, Model model) throws Exception {

        Card[] tempCardArray = new Card[5];
        if (organizeSession.getTempCardArray() != null) {
            tempCardArray = organizeSession.getCardArray();
        }

        BigDecimal[] tempHpArray = new BigDecimal[5];// 推定HP
        BigDecimal[] tempAtkArray = new BigDecimal[5];// 推定ATK
        BigDecimal[] levelArray = new BigDecimal[5];
        if (organizeSession.getLevelArray() != null) {
            // sessionからlevelArrayを取得
            levelArray = organizeSession.getLevelArray();
        } else {
            levelArray = cardService.setArray(levelArray);
        }

        tempCardArray = cardService.calculate(calculateForm, tempCardArray);
        cardService.buddyCount(tempCardArray, true);

        // sessionからステータスを取得
        if (organizeSession.getTempHpArray() != null) {
            tempHpArray = organizeSession.getTempHpArray();
            tempAtkArray = organizeSession.getTempAtkArray();
            levelArray = organizeSession.getLevelArray();
        } else {
            tempHpArray = cardService.setArray(tempHpArray);
            tempAtkArray = cardService.setArray(tempAtkArray);
        }
        // 合計値を取得
        BigDecimal tempTotalHp = cardService.sum(tempCardArray);
        BigDecimal tempReflectedHp = cardService.tempSum(tempHpArray);

        organizeSession.setLevelArray(levelArray);
        organizeSession.setTempHpArray(tempHpArray);
        organizeSession.setTempAtkArray(tempAtkArray);
        organizeSession.setTempCardArray(tempCardArray);
        organizeSession.setTempTotalHp(tempTotalHp);// 推定合計HP
        organizeSession.setTempReflectedHp(tempReflectedHp);// バディボーナス反映後推定合計HP

        redirectAttributes.addFlashAttribute("organizeSession", organizeSession);
        return "redirect:organize";
    }

}
