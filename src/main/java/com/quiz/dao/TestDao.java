package com.quiz.dao;

import com.quiz.models.Category;
import com.quiz.models.Test;

import java.util.List;

public interface TestDao {

    void saveTest(Test test);

    List getAlltest();

    List getAllTestsInCategory(Category category);

}
