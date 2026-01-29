package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.ReviewRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.ReviewService;

import com.amalitech.SpringBootBloggingApp.util.ValidationUtil;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
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
        if (!ValidationUtil.isValidRating(review.getRating())) {
            throw new UserInputsException("Invalid rating");
        }
        if (!ValidationUtil.isValidContent(review.getFeedback())) {
            throw new UserInputsException("Invalid content");
        }
        return reviewRepository.save(review);
    }

    @Override
    public boolean delete(String reviewId) {
        if (!ValidationUtil.isValidObjectId(reviewId)) {
            throw new UserInputsException("Invalid review ID");
        }
        return reviewRepository.deleteById(reviewId);
    }

    @Override
    public boolean update(Review review) {
        if (!ValidationUtil.isValidObjectId(review.getId())) {
            throw new UserInputsException("Invalid review ID");
        }
        return reviewRepository.update(review);
    }

    @Override
    public List<Review> getByPost(String postId) {
        if (!ValidationUtil.isValidObjectId(postId)) {
            throw new UserInputsException("Invalid post ID");
        }
        return reviewRepository.findByPostId(postId);
    }

    @Override
    public List<Review> getByPost(Post post) {
        return List.of();
    }

    @Override
    public List<Review> getByUser(String userId) {
        return List.of();
    }

    @Override
    public List<Review> getByUser(User user) {
        return List.of();
    }

    @Override
    public double getAverageRatingForPost(String postId) {
        if (!ValidationUtil.isValidObjectId(postId)) {
            throw new UserInputsException("Invalid post ID");
        }
        return reviewRepository.calculateAverageRating(postId);
    }

    @Override
    public double getAverageRatingForPost(Post post) {
        return 0;
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }
}
