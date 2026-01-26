package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Tag;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.TagRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.TagService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepositoryImpl tagRepository;
    private final Cache<String, User> userCache;

    public TagServiceImpl(TagRepositoryImpl tagRepository, Cache<String, User> userCache) {
        this.tagRepository = tagRepository;
        this.userCache = userCache;
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public void assignTagToPost(String postId, String tagId) {
        tagRepository.assignTagToPost(postId, tagId);
    }

    @Override
    public List<Tag> getTagsByPost(String postId) {
        return tagRepository.findTagsByPostId(postId);
    }

    @Override
    public void unassignAllTagsFromPost(String postId) {
        tagRepository.unassignAllTagsFromPost(postId);
    }
}
