package com.quiz.dao.impl;

import com.quiz.dao.QuestionDao;
import com.quiz.models.Question;
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
public class QuestionDaoImpl implements QuestionDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }


    @Override
    public void saveQuestion(Question question) {
        getSession().save(question);
    }

    @Override
    public List getAllQuestions() {
        Criteria criteria = getSession().createCriteria(Question.class);
        return criteria.list();
    }

    @Override
    public List getAllQuestionsInTest(Test test) {
        Criteria criteria = getSession().createCriteria(Question.class);
        criteria.add(Restrictions.eq("test", test));
        return criteria.list();
    }

    @Override
    public Question findQuestionById(Long id) {
        Criteria criteria = getSession().createCriteria(Question.class);
        criteria.add(Restrictions.eq("id", id));
        return (Question) criteria.list().get(0);
    }

}
