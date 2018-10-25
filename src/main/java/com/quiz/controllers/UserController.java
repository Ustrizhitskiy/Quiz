package com.quiz.controllers;

import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public String welcome() {
        return "intro";
    }

}
