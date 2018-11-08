package com.quiz.services;

import com.quiz.models.Question;
import com.quiz.models.Test;

import java.util.List;

public interface QuestionService {

    void saveQuestion(Question question);

    List getAllQuestions();

    List getAllQuestionsInTest(Test test);

    Question findQuestionById(Long id);

}
