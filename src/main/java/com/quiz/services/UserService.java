package com.quiz.services;

import com.quiz.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    void updateUser(User user);

    List getAllUsers();

    List getUserByUsername(String username);

    void deleteUser(User user);

}
