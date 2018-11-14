package com.quiz.controllers;

import com.quiz.models.*;
import com.quiz.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/categories")
public class TestConroller {

    @Autowired
    TestServiceImpl testServiceImpl;

    @Autowired
    QuestionServiceImpl questionServiceImpl;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    AnswerServiceImpl answerServiceImpl;

    @Autowired
    CategoryServiceImpl categoryServiceImpl;

    @Autowired
    RightNumberServiceImpl rightNumberServiceImpl;

    private List getListOfQuestionsInTest(@PathVariable String category, @PathVariable String test) {
        Test testByCategoryTitleAndTestLevel = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, test);
        return questionServiceImpl.getAllQuestionsInTest(testByCategoryTitleAndTestLevel);
    }

    @GetMapping("/{category}/{test}")
    public String showTestQuestion(@PathVariable String category,
                                   @PathVariable String test, Model model) {
        List questions = getListOfQuestionsInTest(category, test);
        model.addAttribute("questions", questions);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = (User) userServiceImpl.getUserByUsername(name).get(0);
        if (userByUsername.getRoles().contains(Role.ADMIN)) {
            model.addAttribute("addAnswers", "Add answers");
        }
        return "questionsList";
    }

    @GetMapping("/{category}/{test}/{count}")
    public String startTest(@PathVariable String category,
                            @PathVariable String test,
                            @PathVariable int count, Model model) {
        if (count == 1) {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            User userByUsername = (User) userServiceImpl.getUserByUsername(name).get(0);
            Test addingTest = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, test);
            RightNumber rightNumberisEmpty = rightNumberServiceImpl.findRightNumber(userByUsername, addingTest);
            if(!(rightNumberisEmpty == null)) {
                rightNumberServiceImpl.deleteNumber(rightNumberisEmpty);
            }
            String str = userByUsername.getId().toString() + "," + addingTest.getId().toString();
            RightNumber rightNumber = new RightNumber(str, 0);
            rightNumberServiceImpl.saveNumber(rightNumber);
            System.out.println(rightNumber);
        }
        List questions = getListOfQuestionsInTest(category, test);
        if (questions.size() < count) {
            String str = "/categories/" + category + "/" + test +"/results";
            return "redirect:" + str;
        }
        Question question = (Question) questions.get(count - 1);
        List answers = answerServiceImpl.getAllAnswersInQuestion(question);
        model.addAttribute("currentCategory", category);
        model.addAttribute("level", test);
        model.addAttribute("count", count);
        model.addAttribute("question", question.getBody());
        model.addAttribute("answers", answers);
        return "question";
    }

    @PostMapping("/{category}/{test}/{count}")
    public String saveAnswer(@PathVariable String category,
                             @PathVariable String test,
                             @PathVariable int count,
                             @RequestParam Long answer, Model model) {
        List questions = getListOfQuestionsInTest(category, test);
        Question question = (Question) questions.get(count - 2);
        Answer rightAnswerInQuestion = answerServiceImpl.getRightAnswerInQuestion(question);
        Answer userAnswer = answerServiceImpl.findAnswerById(answer);

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = (User) userServiceImpl.getUserByUsername(name).get(0);
        Test addingTest = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, test);

        if (userAnswer.getBody().equals(rightAnswerInQuestion.getBody())) {
            RightNumber rightNumber = rightNumberServiceImpl.findRightNumber(userByUsername, addingTest);
            int num = rightNumber.getNumber();
            rightNumber.setNumber(num + 1);
            rightNumberServiceImpl.updateNumber(rightNumber);
            System.out.println(rightNumber.getNumber());
        }
        return startTest(category, test, count, model);
    }

    @GetMapping("/{category}/{test}/results")
    public String showResults(@PathVariable String category,
                              @PathVariable String test, Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByUsername = (User) userServiceImpl.getUserByUsername(name).get(0);
        Test addingTest = testServiceImpl.findTestByCategoryTitleAndTestLevel(category, test);
        RightNumber rightNumber = rightNumberServiceImpl.findRightNumber(userByUsername, addingTest);
        model.addAttribute("numberOfRightAnswers", rightNumber.getNumber());
        return "results";
    }

}
