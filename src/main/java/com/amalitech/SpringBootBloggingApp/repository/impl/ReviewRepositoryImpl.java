package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;

public class ReviewRepositoryImpl implements ReviewRepository {
    @Override
    public Review save(Review entity) {
        return null;
    }

    @Override
    public Optional<Review> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Review> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Review entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
