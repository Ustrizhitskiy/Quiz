package com.quiz.dao.impl;

import com.quiz.dao.UserDao;
import com.quiz.models.User;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveUser(User user) {
        getSession().save(user);
    }

    @Override
    public List getAllUsers() {
        Criteria criteria = getSession().createCriteria(User.class);
        //criteria.setFirstResult(2);     // начиная с какого элемента добавлять в список
        //criteria.setMaxResults(1);      // сколько элементов добавить в список
        return criteria.list();
    }

    @Override
    public List getUserByUsername(String username) {
        Criteria criteria = getSession().createCriteria(User.class)
                .add(Restrictions.like("username", username));
        return criteria.list();
    }

}
