package com.quiz.dao;

import com.quiz.models.Answer;
import com.quiz.models.Question;

import java.util.List;

public interface AnswerDao {

    void saveAnswer(Answer answer);

    List getAllAnswers();

    List getAllAnswersInQuestion(Question question);

    Answer getRightAnswerInQuestion(Question question);

}
