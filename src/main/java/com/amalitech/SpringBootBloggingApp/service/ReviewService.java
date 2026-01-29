package com.amalitech.SpringBootBloggingApp.service;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    boolean delete(String reviewId);

    boolean update(Review review);

    List<Review> getByPost(String postId);
    List<Review> getByPost(com.amalitech.SpringBootBloggingApp.model.entity.Post post);
    
    List<Review> getByUser(String userId);
    List<Review> getByUser(com.amalitech.SpringBootBloggingApp.model.entity.User user);

    double getAverageRatingForPost(String postId);
    double getAverageRatingForPost(com.amalitech.SpringBootBloggingApp.model.entity.Post post);

    List<Review> getAll();
}
