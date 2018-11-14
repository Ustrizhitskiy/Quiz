package com.quiz.services;

import com.quiz.models.RightNumber;
import com.quiz.models.Test;
import com.quiz.models.User;

public interface RightNumberService {

    void saveNumber(RightNumber rightNumber);

    void updateNumber(RightNumber rightNumber);

    void deleteNumber(RightNumber rightNumber);

    RightNumber findRightNumber(User user, Test test);

}
