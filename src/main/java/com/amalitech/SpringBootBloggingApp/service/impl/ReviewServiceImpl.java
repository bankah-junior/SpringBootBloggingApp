package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.ReviewRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.ReviewService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepositoryImpl reviewRepository;
    private final Cache<String, User> userCache;

    public ReviewServiceImpl(ReviewRepositoryImpl reviewRepository, Cache<String, User> userCache) {
        this.reviewRepository = reviewRepository;
        this.userCache = userCache;
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public boolean delete(String reviewId) {
        return reviewRepository.deleteById(reviewId);
    }

    @Override
    public List<Review> getByPost(String postId) {
        return reviewRepository.findByPostId(postId);
    }

    @Override
    public double getAverageRatingForPost(String postId) {
        return reviewRepository.calculateAverageRating(postId);
    }
}
