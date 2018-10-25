package com.quiz.services.impl;

import com.quiz.dao.CategoryDao;
import com.quiz.models.Category;
import com.quiz.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void saveCategory(Category category) {
        categoryDao.saveCategory(category);
    }

    @Override
    public List getAllCategories() {
       return categoryDao.getAllCategories();
    }

    @Override
    public Category getCategoryByTitle(String title) {
        return categoryDao.getCategoryByTitle(title);
    }
}
