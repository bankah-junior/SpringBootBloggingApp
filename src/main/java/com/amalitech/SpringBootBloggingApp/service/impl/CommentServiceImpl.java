package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.CommentRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.CommentService;

import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryImpl commentRepository;
    private final Cache<String, User> userCache;

    public CommentServiceImpl(CommentRepositoryImpl commentRepository, Cache<String, User> userCache) {
        this.commentRepository = commentRepository;
        this.userCache = userCache;
    }
}
