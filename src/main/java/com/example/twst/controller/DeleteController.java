package com.example.twst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.EnumUtils;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.service.CardService;
import com.example.twst.session.EditSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("delete")
@Controller
@SessionAttributes(value = { "SearchForm" })
public class DeleteController {

    private CardService cardService;

    private final EditSession deleteSession;

    @ModelAttribute
    public SearchForm setUpSearchForm() {
        SearchForm searchForm = new SearchForm();
        searchForm.setNameChecks(new String[0]);
        searchForm.setRareChecks(new String[0]);
        searchForm.setTypeChecks(new String[0]);
        searchForm.setMagicChecks1(new String[0]);
        searchForm.setMagicChecks2(new String[0]);
        searchForm.setMagicChecks3(new String[0]);
        searchForm.setBuffDebuffChecks(new String[0]);
        searchForm.setBuddyChecks(new String[0]);
        searchForm.setDuoChecks(new String[0]);
        searchForm.setInclude1("include1");
        searchForm.setInclude2("include2");
        searchForm.setInclude3("include3");
        searchForm.setSort("atk");
        return searchForm;
    }

    @ModelAttribute
    public CardForm setUpCardForm() {
        CardForm cardForm = new CardForm();
        return cardForm;
    }

    @GetMapping
    public String input(SearchForm searchForm, CardForm cardForm, Model model) {
        // 対象テーブルの生成
        if (searchForm.getTableNameChecks() == null) {
            searchForm.setTableNameChecks(deleteSession.getSearchForm().getTableNameChecks());
        }

        String[] tableNames = searchForm.getTableNameChecks();
        List<Card> cardList = cardService.selectMany(searchForm, tableNames[0]);
        TableEnum tableName = EnumUtils.getViewName(TableEnum.class, tableNames[0]);
        model.addAttribute("cardList", cardList);
        model.addAttribute("tableName", tableName);

        return "delete.html";
    }

    @PostMapping
    public String conform(CardForm cardForm, SearchForm searchForm,
            RedirectAttributes redirectAttributes, Model model) {
        // CardFormをセットする
        String[] tableName = new String[1];
        tableName[0] = cardForm.getTableName().getCharacterName();
        searchForm.setTableNameChecks(tableName);

        deleteSession.setTableNameChecks(tableName);
        deleteSession.setSearchForm(searchForm);
        redirectAttributes.addFlashAttribute("deleteSession", deleteSession);

        cardService.deleteOne(cardForm);

        return "redirect:delete";
    }

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public DeleteController(EditSession deleteSession) {
        this.deleteSession = deleteSession;
    }
}
