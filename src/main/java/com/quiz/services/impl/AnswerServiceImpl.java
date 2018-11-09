package com.quiz.services.impl;

import com.quiz.dao.AnswerDao;
import com.quiz.models.Answer;
import com.quiz.models.Question;
import com.quiz.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Override
    public void saveAnswer(Answer answer) {
        answerDao.saveAnswer(answer);
    }

    @Override
    public List getAllAnswers() {
        return answerDao.getAllAnswers();
    }

    @Override
    public List getAllAnswersInQuestion(Question question) {
        return answerDao.getAllAnswersInQuestion(question);
    }

    @Override
    public Answer getRightAnswerInQuestion(Question question) {
        return answerDao.getRightAnswerInQuestion(question);
    }

    @Override
    public Answer findAnswerById(Long id) {
        return answerDao.findAnswerById(id);
    }

}
