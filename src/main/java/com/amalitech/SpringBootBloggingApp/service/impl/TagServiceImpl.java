package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.TagRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.TagService;

import org.springframework.stereotype.Service;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepositoryImpl tagRepository;
    private final Cache<String, User> userCache;

    public TagServiceImpl(TagRepositoryImpl tagRepository, Cache<String, User> userCache) {
        this.tagRepository = tagRepository;
        this.userCache = userCache;
    }
}
