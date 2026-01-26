package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.CommentRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.CommentService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepositoryImpl commentRepository;
    private final Cache<String, User> userCache;

    public CommentServiceImpl(CommentRepositoryImpl commentRepository, Cache<String, User> userCache) {
        this.commentRepository = commentRepository;
        this.userCache = userCache;
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public boolean delete(String commentId) {
        return commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> getByPost(String postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getByUser(String userId) {
        return commentRepository.findByUserId(userId);
    }
}
