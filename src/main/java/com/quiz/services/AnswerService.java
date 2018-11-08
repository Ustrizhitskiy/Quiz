package com.quiz.services;

import com.quiz.models.Answer;
import com.quiz.models.Question;

import java.util.List;

public interface AnswerService {

    void saveAnswer(Answer answer);

    List getAllAnswers();

    List getAllAnswersInQuestion(Question question);

    Answer getRightAnswerInQuestion(Question question);

}
