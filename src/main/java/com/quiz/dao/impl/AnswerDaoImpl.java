package com.quiz.dao.impl;

import com.quiz.dao.AnswerDao;
import com.quiz.models.Answer;
import com.quiz.models.Question;
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
public class AnswerDaoImpl implements AnswerDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void saveAnswer(Answer answer) {
        getSession().save(answer);
    }

    @Override
    public List getAllAnswers() {
        Criteria criteria = getSession().createCriteria(Answer.class);
        return criteria.list();
    }

    @Override
    public List getAllAnswersInQuestion(Question question) {
        Criteria criteria = getSession().createCriteria(Answer.class);
        criteria.add(Restrictions.eq("question", question));
        return criteria.list();
    }

    @Override
    public Answer getRightAnswerInQuestion(Question question) {
        Criteria criteria = getSession().createCriteria(Answer.class);
        criteria.add(Restrictions.eq("question", question));
        List<Answer> answers = (List<Answer>) criteria.list();
        for (Answer answer : answers) {
            if (answer.isCorrect()) return answer;
        }
        return null;
    }

    @Override
    public Answer findAnswerById(Long id) {
        Criteria criteria = getSession().createCriteria(Answer.class);
        criteria.add(Restrictions.eq("id", id));
        return (Answer) criteria.list().get(0);
    }

}
