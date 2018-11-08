package com.quiz.controllers;

import com.quiz.models.*;
import com.quiz.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @Autowired
    AnswerServiceImpl answerServiceImpl;

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
        return getString(model, categoryServiceImpl, userServiceImpl);
    }

    static String getString(Model model, CategoryServiceImpl categoryServiceImpl, UserServiceImpl userServiceImpl) {
        List categories = categoryServiceImpl.getAllCategories();
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = (User) userServiceImpl.getUserByUsername(name).get(0);
        String userFirstName = userByUsername.getFirstName();
        model.addAttribute("categories", categories);
        model.addAttribute("userName", userFirstName);
        if (userByUsername.getUsername().equals("admin")) {
            model.addAttribute("addTestToCatHead", "Edit");
            model.addAttribute("addTestToCat", "Add new test to category");
            model.addAttribute("addNewCategory", "Add new category");
        }
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
        return getString(category, model, categoryServiceImpl, testServiceImpl);
    }
    // Возвращаемая строка (повторяющийся код в MainController):
    static String getString(String category, Model model, CategoryServiceImpl categoryServiceImpl, TestServiceImpl testServiceImpl) {
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
        Test test = new Test(level, categoryConstructor);
        testServiceImpl.saveTest(test);
        List testsInCategory = testServiceImpl.getAllTestsInCategory(categoryConstructor);
        model.addAttribute("currentCategory", category);
        model.addAttribute("tests", testsInCategory);

        return "testsList";
    }

    @GetMapping("/categories/{category}/{level}/addQuestion")
    public String showTestQuestion(@PathVariable String category,
                                   @PathVariable String level, Model model) {
        Test test = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, level);
        List questionsInTest = questionServiceImpl.getAllQuestionsInTest(test);
        model.addAttribute("questions", questionsInTest);
        return "questionsList";
    }

    @PostMapping("/categories/{category}/{level}/addQuestion")
    public String saveNewQuestionToTest(@PathVariable String category,
                                        @PathVariable String level,
                                        @RequestParam String body, Model model) {
        Test test = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, level);
        Question question = new Question(body, test);
        questionServiceImpl.saveQuestion(question);
        return showTestQuestion(category, level, model);
    }

    @GetMapping("/categories/{category}/{level}/{questionId}/addAnswers")
    public String showAnswersToTheQuestion(@PathVariable String category,
                                           @PathVariable String level,
                                           @PathVariable Long questionId, Model model) {
        Question question = questionServiceImpl.findQuestionById(questionId);
        List answersToTheQuestion = answerServiceImpl.getAllAnswersInQuestion(question);
        model.addAttribute("answers", answersToTheQuestion);
        return "answersList";
    }

    @PostMapping("/categories/{category}/{level}/{questionId}/addAnswers")
    public String saveNewAnswerToTheQuestion(@PathVariable String category,
                                             @PathVariable String level,
                                             @PathVariable Long questionId,
                                             @RequestParam String body,
                                             @RequestParam boolean correct, Model model) {
        Question question = questionServiceImpl.findQuestionById(questionId);
        Answer answer = new Answer(body, correct, question);
        answerServiceImpl.saveAnswer(answer);
        return showAnswersToTheQuestion(category, level, questionId, model);

    }

}
