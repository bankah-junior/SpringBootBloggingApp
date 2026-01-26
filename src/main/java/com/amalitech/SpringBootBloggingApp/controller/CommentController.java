package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }
}
