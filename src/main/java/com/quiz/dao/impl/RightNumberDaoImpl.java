package com.quiz.dao.impl;

import com.quiz.dao.RightNumberDao;
import com.quiz.models.RightNumber;
import com.quiz.models.Test;
import com.quiz.models.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class RightNumberDaoImpl implements RightNumberDao {

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveNumber(RightNumber rightNumber) {
        getSession().save(rightNumber);
    }

    @Override
    public void updateNumber(RightNumber rightNumber) {
        getSession().update(rightNumber);
    }

    @Override
    public void deleteNumber(RightNumber rightNumber) {
        getSession().delete(rightNumber);
    }

    @Override
    public RightNumber findRightNumber(User user, Test test) {
        Criteria criteria = getSession().createCriteria(RightNumber.class);
        List listNumbers = criteria.list();
        String id1 = user.getId().toString();
        String id2 = test.getId().toString();
        String numberId = id1 + "," + id2;
        for (int i = 0; i < listNumbers.size(); i++) {
            RightNumber rightNumber = (RightNumber) listNumbers.get(0);
            if (rightNumber.getGenerated().equals(numberId)) {
                return rightNumber;
            }
        }
        return null;
    }

}
