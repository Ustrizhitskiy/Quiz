package com.quiz.controllers;

import com.quiz.models.*;
import com.quiz.services.impl.AnswerServiceImpl;
import com.quiz.services.impl.QuestionServiceImpl;
import com.quiz.services.impl.TestServiceImpl;
import com.quiz.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (userAnswer.equals(rightAnswerInQuestion))
            System.out.println("right");
        else System.out.println("incorrect");
        return startTest(category, test, count, model);
    }

    @GetMapping("/{category}/{test}/results")
    public String showResults(@PathVariable String category,
                              @PathVariable String test, Model model) {
        return "results";
    }

}
