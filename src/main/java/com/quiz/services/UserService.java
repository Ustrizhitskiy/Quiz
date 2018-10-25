package com.quiz.services;

import com.quiz.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List getAllUsers();

    List getUserByUsername(String username);

}
