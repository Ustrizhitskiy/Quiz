package com.quiz.dao;

import com.quiz.models.User;

import java.util.List;

public interface UserDao {

    void saveUser(User user);

    void updateUser(User user);

    List getAllUsers();

    List getUserByUsername(String username);

    void deleteUser(User user);

}
