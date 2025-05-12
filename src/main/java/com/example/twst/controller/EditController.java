package com.example.twst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.twst.domain.model.TableEnum;
import com.example.twst.form.SearchForm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@RequestMapping("edit")
@Controller
public class EditController {

    /**
     * 更新画面の表示
     */
    @GetMapping
    public String input(SearchForm searchForm, Model model) {
        model.addAttribute("SearchForm", searchForm);
        model.addAttribute("tableName", TableEnum.values());
        return "edit";
    }

}
