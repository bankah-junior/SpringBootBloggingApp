package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.PostRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.PostService;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepositoryImpl postRepository;
    private final Cache<String, User> userCache;

    public PostServiceImpl(PostRepositoryImpl postRepository, Cache<String, User> userCache) {
        this.postRepository = postRepository;
        this.userCache = userCache;
    }
}
