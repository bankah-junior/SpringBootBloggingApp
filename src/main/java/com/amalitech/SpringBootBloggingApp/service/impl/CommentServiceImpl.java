package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.CommentRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.CommentService;

import com.amalitech.SpringBootBloggingApp.util.ValidationUtil;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if(!ValidationUtil.isValidComment(comment.getContent())) {
            throw new UserInputsException("Invalid comment");
        }
        return commentRepository.save(comment);
    }

    @Override
    public boolean delete(String commentId) {
        if(!ValidationUtil.isValidObjectId(commentId)) {
            throw new UserInputsException("Invalid comment ID");
        }
        return commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> getByPost(String postId) {
        if(!ValidationUtil.isValidObjectId(postId)) {
            throw new UserInputsException("Invalid post ID");
        }
        return commentRepository.findByPostId(postId);
    }

    @Override
    public List<Comment> getByUser(String userId) {
        if(!ValidationUtil.isValidObjectId(userId)) {
            throw new UserInputsException("Invalid user ID");
        }
        return commentRepository.findByUserId(userId);
    }

    @Override
    public boolean update(Comment comment) {
        if(!ValidationUtil.isValidObjectId(comment.getId())) {
            throw new UserInputsException("Invalid comment ID");
        }
        if(!ValidationUtil.isValidComment(comment.getContent())) {
            throw new UserInputsException("Invalid comment");
        }
        return commentRepository.update(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

     @Override
    public Comment findById(String commentId) {
        if(!ValidationUtil.isValidObjectId(commentId)) {
            throw new UserInputsException("Invalid comment ID");
        }
         Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.orElse(null);
    }
}
