package com.quiz.dao;

import com.quiz.models.Question;
import com.quiz.models.Test;

import java.util.List;

public interface QuestionDao {

    void saveQuestion(Question question);

    List getAllQuestions();

    List getAllQuestionsInTest(Test test);

    Question findQuestionById(Long id);

}
