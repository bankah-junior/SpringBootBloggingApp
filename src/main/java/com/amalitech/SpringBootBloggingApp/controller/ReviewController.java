package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewServiceImpl reviewServiceImpl;
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }
}
