package com.quiz.dao.impl;

import com.quiz.dao.CategoryDao;
import com.quiz.models.Category;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveCategory(Category category) {
        getSession().save(category);
    }

    @Override
    public List getAllCategories() {
        Criteria criteria = getSession().createCriteria(Category.class);
        return criteria.list();
    }

    @Override
    public Category getCategoryByTitle(String title) {
        Criteria criteria = getSession().createCriteria(Category.class);
        for (int i = 0; i < criteria.list().size(); i++) {
            Category category = (Category) criteria.list().get(i);
            if (category.getTitle().equals(title)) return category;
        }
        return null;
    }
}
