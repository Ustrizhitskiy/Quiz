package com.quiz.controllers;

import com.quiz.models.Role;
import com.quiz.models.User;
import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, ModelMap modelMap) {
        List userByUsername = userServiceImpl.getUserByUsername(user.getUsername());
        if (!userByUsername.isEmpty()) {
            modelMap.addAttribute("message", "User with this username already exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userServiceImpl.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
