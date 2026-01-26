package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}

