package com.amalitech.SpringBootBloggingApp.service;

import com.amalitech.SpringBootBloggingApp.model.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment create(Comment comment);

    boolean delete(String commentId);

    List<Comment> getByPost(String postId);
    List<Comment> getByPost(com.amalitech.SpringBootBloggingApp.model.entity.Post post);
    
    List<Comment> getByUser(String userId);
    List<Comment> getByUser(com.amalitech.SpringBootBloggingApp.model.entity.User user);

    boolean update(Comment comment);

    List<Comment> getAll();

     Comment findById(String commentId);
}
