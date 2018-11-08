package com.quiz.dao.impl;

import com.quiz.dao.TestDao;
import com.quiz.models.Category;
import com.quiz.models.Test;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class TestDaoImpl implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveTest(Test test) {
        getSession().save(test);
    }

    @Override
    public List getAlltest() {
        Criteria criteria = getSession().createCriteria(Test.class);
        return criteria.list();
    }

    @Override
    public List getAllTestsInCategory(Category category) {
        Criteria criteria = getSession().createCriteria(Test.class);
        criteria.add(Restrictions.eq("category", category));
        return criteria.list();
    }

}
