package com.quiz.controllers;

import com.quiz.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/cat")
    public String categoriesList(Model model) {
        List categories = categoryServiceImpl.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

}
