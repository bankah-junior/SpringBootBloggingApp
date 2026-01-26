package com.amalitech.SpringBootBloggingApp.service;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;

import java.util.List;

public interface ReviewService {

    Review create(Review review);

    boolean delete(String reviewId);

    List<Review> getByPost(String postId);

    double getAverageRatingForPost(String postId);
}
