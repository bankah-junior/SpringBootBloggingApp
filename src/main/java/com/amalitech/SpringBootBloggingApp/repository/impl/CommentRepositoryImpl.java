package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

public class CommentRepositoryImpl implements CommentRepository {
    @Override
    public Comment save(Comment entity) {
        return null;
    }

    @Override
    public Optional<Comment> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Comment> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Comment entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
