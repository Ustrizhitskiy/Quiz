package com.quiz.services;

import com.quiz.models.Category;
import com.quiz.models.Test;

import java.util.List;

public interface TestService {

    void saveTest(Test test);

    List getAllTests();

    List getAllTestsInCategory(Category category);

}