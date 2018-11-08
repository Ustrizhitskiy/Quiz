package com.quiz.services.impl;

import com.quiz.dao.QuestionDao;
import com.quiz.models.Question;
import com.quiz.models.Test;
import com.quiz.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionDao questionDao;

    @Override
    public void saveQuestion(Question question) {
        questionDao.saveQuestion(question);
    }

    @Override
    public List getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    @Override
    public List getAllQuestionsInTest(Test test) {
        return questionDao.getAllQuestionsInTest(test);
    }

    @Override
    public Question findQuestionById(Long id) {
        return questionDao.findQuestionById(id);
    }

}
