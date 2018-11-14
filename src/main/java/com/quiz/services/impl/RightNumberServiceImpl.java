package com.quiz.services.impl;

import com.quiz.dao.RightNumberDao;
import com.quiz.models.RightNumber;
import com.quiz.models.Test;
import com.quiz.models.User;
import com.quiz.services.RightNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightNumberServiceImpl implements RightNumberService {

    @Autowired
    RightNumberDao rightNumberDao;

    @Override
    public void saveNumber(RightNumber rightNumber) {
        rightNumberDao.saveNumber(rightNumber);
    }

    @Override
    public void updateNumber(RightNumber rightNumber) {
        rightNumberDao.updateNumber(rightNumber);
    }

    @Override
    public void deleteNumber(RightNumber rightNumber) {
        rightNumberDao.deleteNumber(rightNumber);
    }

    @Override
    public RightNumber findRightNumber(User user, Test test) {
        return rightNumberDao.findRightNumber(user, test);
    }

}
