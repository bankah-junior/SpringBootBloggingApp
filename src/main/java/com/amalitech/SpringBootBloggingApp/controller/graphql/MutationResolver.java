package com.amalitech.SpringBootBloggingApp.controller.graphql;

import com.amalitech.SpringBootBloggingApp.model.dto.request.CreatePostRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.UserResponse;
import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.model.entity.User;
import com.amalitech.SpringBootBloggingApp.service.PostService;
import com.amalitech.SpringBootBloggingApp.service.UserService;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MutationResolver {

    private final PostService postService;
    private final UserService userService;

    public MutationResolver(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @MutationMapping
    public Post createPost(CreatePostRequest input) {
        UserResponse author = userService.getById(input.getAuthorId());
        User user = new User(
                author.getId(),
                author.getUsername(),
                author.getEmail(),
                null,
                author.getCreatedAt(),
                author.getUpdatedAt()
        );

        Post post = new Post(
                null,
                user,
                input.getTitle(),
                input.getContent(),
                false,
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                null
        );

        return postService.create(post);
    }
}

