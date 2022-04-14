package com.todo.service;

import com.todo.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,Object> generateToken(User user);
}
