package com.blank.epicfserver.controller;

import com.blank.epicfserver.model.User;
import org.junit.jupiter.api.Test;

public class AuthControllerTest {

    @Test
    void testCreateUser() {
        AuthController authController = new AuthController();
        authController.registerUser(new User("test@test.me","t_password"));
    }
}
