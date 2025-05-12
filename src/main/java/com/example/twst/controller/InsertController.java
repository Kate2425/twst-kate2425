package com.example.twst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.twst.domain.model.BuddyGrouping;
import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.EnumUtils;
import com.example.twst.domain.model.MagicGrouping;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.service.BuddyGroupingService;
import com.example.twst.service.CardService;
import com.example.twst.service.MagicGroupingService;
import com.example.twst.session.EditSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("insert")
@Controller
@SessionAttributes(value = { "SearchForm" })
public class InsertController {

    @Autowired
    private CardService cardService;

    @Autowired
    private MagicGroupingService magicGroupingService;

    @Autowired
    private BuddyGroupingService buddyGroupingService;

    @Autowired
    private EditSession insertSession;

    @GetMapping
    public String input(@ModelAttribute SearchForm searchForm, @ModelAttribute CardForm cardForm, Model model) {
        // 対象テーブルの生成
        if (searchForm.getTableNameChecks() == null) {
            searchForm.setTableNameChecks(this.insertSession.getSearchForm().getTableNameChecks());
        }

        String[] tableNames = searchForm.getTableNameChecks();
        List<Card> cardList = cardService.selectMany(searchForm, tableNames[0]);
        TableEnum tableName = EnumUtils.getViewName(TableEnum.class, tableNames[0]);
        model.addAttribute("cardList", cardList);
        model.addAttribute("tableName", tableName);
        model.addAttribute("CardForm", cardForm);

        // マスタテーブルの生成
        List<MagicGrouping> magicGroupingList = magicGroupingService.selectMany();
        model.addAttribute("magicGroupingList", magicGroupingList);
        List<BuddyGrouping> buddyGroupingList = buddyGroupingService.selectMany();
        model.addAttribute("buddyGroupingList", buddyGroupingList);

        // セレクトボックス名をセット
        model.addAttribute("characterName", CharacterEnum.values());
        model.addAttribute("rare", CardEnum.getValue("rare"));
        model.addAttribute("type", CardEnum.getValue("type"));

        return "insert";
    }

    @PostMapping
    public String conform(@ModelAttribute CardForm cardForm, @ModelAttribute SearchForm searchForm, Model model) {
        // CardFormをセットする
        String[] tableName = new String[1];
        tableName[0] = cardForm.getTableName().getCharacterName();
        searchForm.setTableNameChecks(tableName);

        this.insertSession.setTableNameChecks(tableName);
        this.insertSession.setSearchForm(searchForm);

        cardService.insert(cardForm);
        model.addAttribute("CardForm", cardForm);

        return "redirect:insert";
    }

}
