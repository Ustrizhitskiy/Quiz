package com.quiz.controllers;

import com.quiz.models.Category;
import com.quiz.models.Role;
import com.quiz.models.Test;
import com.quiz.models.User;
import com.quiz.services.impl.CategoryServiceImpl;
import com.quiz.services.impl.TestServiceImpl;
import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    TestServiceImpl testServiceImpl;

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

    @GetMapping("/categories")
    public String categoriesList(Model model) {
        List categories = categoryServiceImpl.getAllCategories();
        model.addAttribute("categories", categories);
        return "categoriesList";
    }

    @GetMapping("/categories/addCat")
    public String addCat() {
        return "addCategory";
    }

    @PostMapping("/categories/addCat")
    public String saveNewCategory(Category category) {
        categoryServiceImpl.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{category}/addTest")
    public String addTestToCategory(@PathVariable String category, Model model) {
        model.addAttribute("message", category);
        return "addTest";
    }

    @GetMapping("/categories/{category}")
    public String showTetList(@PathVariable String category, Model model) {
        Category categoryConstructor = categoryServiceImpl.getCategoryByTitle(category);
        List testsInCategory = testServiceImpl.getAllTestsInCategory(categoryConstructor);
        model.addAttribute("currentCategory", category);
        model.addAttribute("tests", testsInCategory);
        return "testsList";
    }

    @PostMapping("/categories/{category}")
    public String saveNewTestLevel(@PathVariable String category,
                                   @RequestParam String level, Model model) {
        Category categoryConstructor = categoryServiceImpl.getCategoryByTitle(category);
        System.out.println(categoryConstructor);
        Test test = new Test(level, categoryConstructor);
        testServiceImpl.saveTest(test);
        System.out.println(test.getCategoryTitle());
        List testsInCategory = testServiceImpl.getAllTestsInCategory(categoryConstructor);
        System.out.println(testsInCategory);
        model.addAttribute("currentCategory", category);
        model.addAttribute("tests", testsInCategory);

        return "testsList";
    }

}
