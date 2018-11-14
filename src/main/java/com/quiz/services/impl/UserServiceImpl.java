package com.quiz.services.impl;

import com.quiz.dao.UserDao;
import com.quiz.models.User;
import com.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

}
