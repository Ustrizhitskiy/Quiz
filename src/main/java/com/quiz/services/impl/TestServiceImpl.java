package com.quiz.services.impl;

import com.quiz.dao.TestDao;
import com.quiz.models.Category;
import com.quiz.models.Test;
import com.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDao testDao;

    @Override
    public void saveTest(Test test) {
        testDao.saveTest(test);
    }

    @Override
    public List getAllTests() {
        return testDao.getAlltest();
    }

    @Override
    public List getAllTestsInCategory(Category category) {
        return testDao.getAllTestsInCategory(category);
    }

}
