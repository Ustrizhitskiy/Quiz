package com.quiz.controllers;

import com.quiz.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/")
    public String welcome() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getCredentials());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        if (name.equals("anonymousUser")) return "intro";
        else return "redirect:/categories";
    }

}
