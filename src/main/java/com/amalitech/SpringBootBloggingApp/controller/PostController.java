package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.PostServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostServiceImpl postServiceImpl;
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }
}
