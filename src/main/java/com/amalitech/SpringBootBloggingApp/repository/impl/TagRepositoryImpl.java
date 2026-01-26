package com.amalitech.SpringBootBloggingApp.repository.impl;

import com.amalitech.SpringBootBloggingApp.model.entity.Tag;
import com.amalitech.SpringBootBloggingApp.repository.TagRepository;

import java.util.List;
import java.util.Optional;

public class TagRepositoryImpl implements TagRepository {
    @Override
    public Tag save(Tag entity) {
        return null;
    }

    @Override
    public Optional<Tag> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Tag> findAll() {
        return List.of();
    }

    @Override
    public boolean update(Tag entity) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
