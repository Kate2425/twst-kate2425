package com.example.twst.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.twst.domain.model.CardEnum;
import com.example.twst.domain.model.CharacterEnum;
import com.example.twst.domain.model.TableEnum;
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
        }
    });

    /**
     * 検索画面の表示
     */
    @GetMapping
    public String input(SearchForm searchForm, Model model) {
        model.addAttribute("rare", CardEnum.getValue("rare"));
        model.addAttribute("type", CardEnum.getValue("type"));
        model.addAttribute("magic", CardEnum.getValue("magic"));
        model.addAttribute("heartslabyul", CharacterEnum.getValue("Heartslabyul"));
        model.addAttribute("savanaclaw", CharacterEnum.getValue("Savanaclaw"));
        model.addAttribute("octavinelle", CharacterEnum.getValue("Octavinelle"));
        model.addAttribute("scarabia", CharacterEnum.getValue("Scarabia"));
        model.addAttribute("pomefiore", CharacterEnum.getValue("Pomefiore"));
        model.addAttribute("ignihyde", CharacterEnum.getValue("Ignihyde"));
        model.addAttribute("diasomnia", CharacterEnum.getValue("Diasomnia"));
        model.addAttribute("nrc", CharacterEnum.getValue("Nrc"));
        model.addAttribute("special", CharacterEnum.getValue("Special"));
        model.addAttribute("tableName", TableEnum.values());
        model.addAttribute("include1", INCLUDE);
        model.addAttribute("include2", INCLUDE);
        model.addAttribute("include3", INCLUDE);
        model.addAttribute("sort", SORT);

        // include set
        searchForm.setInclude1("include1");
        searchForm.setInclude2("include2");
        searchForm.setInclude3("include3");

        // sort set
        searchForm.setSort("atk");

        model.addAttribute("SearchForm", searchForm);/* 必須 */

        return "search";
    }

}
