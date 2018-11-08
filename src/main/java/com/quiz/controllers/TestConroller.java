package com.quiz.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class TestConroller {

    @GetMapping("/{category}/{test}")
    public String showTestQuestion() {
        return "questionsList";
    }

}
