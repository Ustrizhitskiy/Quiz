package com.quiz.services.impl;

import com.quiz.dao.TestDao;
import com.quiz.models.Category;
import com.quiz.models.Test;
import com.quiz.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Override
    public void saveTest(Test test) {
        testDao.saveTest(test);
    }

    @Override
    public List getAllTests() {
        return testDao.getAlltest();
    }

    @Override
    public List<Test> getAllTestsInCategory(Category category) {
        return testDao.getAllTestsInCategory(category);
    }

    @Override
    public Test findTestByCategoryTitleAndTestLevel(String title, String level) {
        Category category = categoryServiceImpl.getCategoryByTitle(title);
        List tests = getAllTestsInCategory(category);
        for (Object test1 : tests) {
            Test test = (Test) test1;
            if (test.getLevel().equals(level)) return test;
        }
        return null;
    }

}
