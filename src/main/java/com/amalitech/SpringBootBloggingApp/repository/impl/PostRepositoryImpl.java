package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public Post save(Post entity) {
        return null;
    }

    @Override
    public Optional<Post> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Post> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Post entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
