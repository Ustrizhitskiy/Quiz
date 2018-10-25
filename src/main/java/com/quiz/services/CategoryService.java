package com.quiz.services;

import com.quiz.models.Category;

import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);

    List getAllCategories();

    Category getCategoryByTitle(String title);

}
