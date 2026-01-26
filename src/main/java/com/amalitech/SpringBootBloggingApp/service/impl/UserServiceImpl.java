package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.UserRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.UserService;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final Cache<String, User> userCache;

    public UserServiceImpl(UserRepositoryImpl userRepository, Cache<String, User> userCache) {
        this.userRepository = userRepository;
        this.userCache = userCache;
    }

}

