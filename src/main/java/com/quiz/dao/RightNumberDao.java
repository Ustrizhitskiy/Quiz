package com.quiz.dao;

import com.quiz.models.RightNumber;
import com.quiz.models.Test;
import com.quiz.models.User;

public interface RightNumberDao {

    void saveNumber(RightNumber rightNumber);

    void updateNumber(RightNumber rightNumber);

    void deleteNumber(RightNumber rightNumber);

    RightNumber findRightNumber(User user, Test test);

}
