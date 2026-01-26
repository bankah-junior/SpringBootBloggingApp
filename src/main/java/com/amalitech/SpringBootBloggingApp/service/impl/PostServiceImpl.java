package com.amalitech.SpringBootBloggingApp.service.impl;

import com.amalitech.SpringBootBloggingApp.cache.Cache;
import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.repository.impl.PostRepositoryImpl;
import com.amalitech.SpringBootBloggingApp.service.PostService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepositoryImpl postRepository;
    private final Cache<String, User> userCache;

    public PostServiceImpl(PostRepositoryImpl postRepository, Cache<String, User> userCache) {
        this.postRepository = postRepository;
        this.userCache = userCache;
    }

    @Override
    public Post create(Post post) {
       return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        if (postRepository.update(post)) {
            return post;
        } else {
            return null;
        }
    }

    @Override
    public boolean delete(String postId) {
        return postRepository.deleteById(postId);
    }

    @Override
    public Post getById(String postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> searchByTitle(String keyword) {
        return postRepository.searchByTitle(keyword);
    }

    @Override
    public List<Post> getByAuthor(String authorId) {
        return postRepository.findByAuthorId(authorId);
    }

    @Override
    public List<Post> searchByTag(String tagName) {
        return postRepository.findByTagName(tagName);
    }

    @Override
    public List<Post> sortByDate(List<Post> posts, boolean ascending) {
        return posts.stream().sorted((p1, p2) -> {
                    if (ascending) {
                        return p1.getCreatedAt().compareTo(p2.getCreatedAt());
                    } else {
                        return p2.getCreatedAt().compareTo(p1.getCreatedAt());
                    }
                })
                .toList();
    }

    @Override
    public List<Post> sortByTitle(List<Post> posts, boolean ascending) {
        return posts.stream().sorted((p1, p2) -> {
                    if (ascending) {
                        return p1.getTitle().compareToIgnoreCase(p2.getTitle());
                    } else {
                        return p2.getTitle().compareToIgnoreCase(p1.getTitle());
                    }
                })
                .toList();
    }

    @Override
    public List<Post> getAllSorted(String sortBy, boolean ascending) {
        return switch (sortBy.toLowerCase()) {
            case "date" -> sortByDate(getAll(), ascending);
            case "title" -> sortByTitle(getAll(), ascending);
            default -> getAll();
        };
    }
}
