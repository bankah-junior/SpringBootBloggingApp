package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.repository.ReviewRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    private final MongoTemplate mongoTemplate;

    public ReviewRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

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
