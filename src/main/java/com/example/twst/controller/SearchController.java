package com.example.twst.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.twst.domain.model.BuffDebuffGroupingEnum;
import com.example.twst.domain.model.CardEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.OrganizeForm;
import com.example.twst.form.SearchForm;

@RequestMapping("search")
@Controller
public class SearchController {

    /**
     * include buttonの表示に使用するアイテム
     */
    static final Map<String, String> INCLUDE = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
        {
            put("含む", "include");
            put("含まない", "exclude");
        }
    });

    /** 
     * sort buttonの表示に使用するアイテム 
     */
    static final Map<String, String> SORT = Collections.unmodifiableMap(new LinkedHashMap<String, String>() {
        {
            put("ATK", "atk");
            put("HP", "hp");
            put("バディ最大ATK", "reflectedAtk");
            put("バディ最大HP", "reflectedHp");
        }
    });

    @ModelAttribute
    public SearchForm setUpSearchForm() {
        SearchForm searchForm = new SearchForm();

        // include set
        searchForm.setInclude1("include1");
        searchForm.setInclude2("include2");
        searchForm.setInclude3("include3");

        // sort set 
        searchForm.setSort("atk");

        return searchForm;
    }

    /**
     * 検索画面の表示
     */
    @GetMapping
    public String input(SearchForm searchForm, OrganizeForm organizeForm, Model model) {
        model.addAttribute("rare", CardEnum.getValueListOfFormName("rare"));
        model.addAttribute("type", CardEnum.getValueListOfFormName("type"));
        model.addAttribute("magic", CardEnum.getValueListOfFormName("magic"));
        model.addAttribute("buffDebuff", BuffDebuffGroupingEnum.getTypeList());
        model.addAttribute("heartslabyul", CharacterEnum.getValueListOfDormitoryName("Heartslabyul"));
        model.addAttribute("savanaclaw", CharacterEnum.getValueListOfDormitoryName("Savanaclaw"));
        model.addAttribute("octavinelle", CharacterEnum.getValueListOfDormitoryName("Octavinelle"));
        model.addAttribute("scarabia", CharacterEnum.getValueListOfDormitoryName("Scarabia"));
        model.addAttribute("pomefiore", CharacterEnum.getValueListOfDormitoryName("Pomefiore"));
        model.addAttribute("ignihyde", CharacterEnum.getValueListOfDormitoryName("Ignihyde"));
        model.addAttribute("diasomnia", CharacterEnum.getValueListOfDormitoryName("Diasomnia"));
        model.addAttribute("nrc", CharacterEnum.getValueListOfDormitoryName("Nrc"));
        model.addAttribute("special", CharacterEnum.getValueListOfDormitoryName("Special"));
        model.addAttribute("tableName", TableEnum.getViewNameList());
        model.addAttribute("include1", INCLUDE);
        model.addAttribute("include2", INCLUDE);
        model.addAttribute("include3", INCLUDE);
        model.addAttribute("sort", SORT);

        model.addAttribute("SearchForm", searchForm);
        model.addAttribute("arrayIndex", organizeForm.getArrayIndex());

        return "search.html";
    }
}
