package com.example.twst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.twst.domain.model.Card;
import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.CardForm;
import com.example.twst.form.SearchForm;
import com.example.twst.service.CardService;

@RequestMapping("result")
@Controller
public class ResultController {
    @Autowired
    private CardService service;

    @GetMapping
    public String input(@ModelAttribute SearchForm searchForm, @ModelAttribute CardForm cardForm,
            Model model) {

        // テーブルの生成
        List<Card> list = service.selectAll(searchForm);
        model.addAttribute("list", list);
        model.addAttribute("tableName", TableEnum.values());

        return "result";
    }
}
