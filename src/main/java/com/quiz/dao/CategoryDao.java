package com.quiz.dao;

import com.quiz.models.Category;

import java.util.List;

public interface CategoryDao {

    void saveCategory(Category category);

    List getAllCategories();

    Category getCategoryByTitle(String title);

}
