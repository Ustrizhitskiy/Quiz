package com.quiz.controllers;

import com.quiz.models.Category;
import com.quiz.models.Role;
import com.quiz.models.User;
import com.quiz.services.impl.CategoryServiceImpl;
import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @GetMapping
    public String adminPanel() {
        return "admin";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        List users = userServiceImpl.getAllUsers();
        model.addAttribute("users", users);
        User user = (User) users.get(1);
        System.out.println(user.getRoles());
        if (user.getRoles().contains(Role.USER)) System.out.println("You are user");
        return "usersList";
    }

    @GetMapping("/cat")
    public String categoriesList(Model model) {
        List categories = categoryServiceImpl.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/addCat")
    public String addCat() {
        return "addCategory";
    }

    @PostMapping("/addCat")
    public String saveNewCategory(Category category) {
        categoryServiceImpl.saveCategory(category);
        return "redirect:/cat";
    }

    @GetMapping("/addTest")
    public String addTest() {
        return "addTest";
    }

}
