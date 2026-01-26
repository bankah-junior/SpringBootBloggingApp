package com.amalitech.SpringBootBloggingApp.repository;

import com.amalitech.SpringBootBloggingApp.model.entity.Post;

import java.util.List;

public interface PostRepository extends BaseRepository<Post> {

    List<Post> findByAuthorId(String authorId);

    List<Post> searchByTitle(String keyword);

    List<Post> findByTagName(String tagName);
}
