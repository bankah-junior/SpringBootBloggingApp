package com.amalitech.SpringBootBloggingApp.repository;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;

import java.util.List;

public interface ReviewRepository extends BaseRepository<Review> {

    List<Review> findByPostId(String postId);

    double calculateAverageRating(String postId);
}
