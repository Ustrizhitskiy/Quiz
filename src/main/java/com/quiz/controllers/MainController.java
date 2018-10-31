package com.quiz.controllers;

import com.quiz.models.Category;
import com.quiz.services.impl.CategoryServiceImpl;
import com.quiz.services.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    TestServiceImpl testServiceImpl;

    @GetMapping("/categories")
    public String categoriesList(Model model) {
        List categories = categoryServiceImpl.getAllCategories();
        model.addAttribute("categories", categories);
        return "categoriesList";
    }

    @GetMapping("/categories/{category}")
    public String showTetList(@PathVariable String category, Model model) {
        Category categoryConstructor = categoryServiceImpl.getCategoryByTitle(category);
        List testsInCategory = testServiceImpl.getAllTestsInCategory(categoryConstructor);
        model.addAttribute("currentCategory", category);
        model.addAttribute("tests", testsInCategory);
        return "testsList";
    }

}
