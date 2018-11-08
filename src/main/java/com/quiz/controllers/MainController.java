package com.quiz.controllers;

import com.quiz.services.impl.CategoryServiceImpl;
import com.quiz.services.impl.TestServiceImpl;
import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.quiz.controllers.AdminController.getString;

@Controller
public class MainController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    TestServiceImpl testServiceImpl;

    @GetMapping("/categories")
    public String categoriesList(Model model) {
        return getString(model, categoryServiceImpl, userServiceImpl);
    }

    @GetMapping("/categories/{category}")
    public String showTetList(@PathVariable String category, Model model) {
        return getString(category, model, categoryServiceImpl, testServiceImpl);
    }

}
