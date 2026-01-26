package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.ReviewRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.ReviewService;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepositoryImpl reviewRepository;
    private final Cache<String, User> userCache;

    public ReviewServiceImpl(ReviewRepositoryImpl reviewRepository, Cache<String, User> userCache) {
        this.reviewRepository = reviewRepository;
        this.userCache = userCache;
    }
}
