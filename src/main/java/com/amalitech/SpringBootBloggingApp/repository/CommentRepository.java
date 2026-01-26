package com.amalitech.SpringBootBloggingApp.repository;

import com.amalitech.SpringBootBloggingApp.model.entity.Comment;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment> {
    List<Comment> findByPostId(String postId);
    List<Comment> findByUserId(String userId);
}
