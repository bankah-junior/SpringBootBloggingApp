package com.amalitech.SpringBootBloggingApp.service;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    boolean delete(String reviewId);

    boolean update(Review review);

    List<Review> getByPost(String postId);

    double getAverageRatingForPost(String postId);

    List<Review> getAll();
}
