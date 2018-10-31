package com.quiz.models;

import com.quiz.services.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class UserTest {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Test
    public void setActive() {
        User user = new User(111L, "Serg", "Ustr", "su", "123", true, Collections.singleton(null));
        user.setActive(false);
        Assert.assertFalse(user.isActive());
    }

}
