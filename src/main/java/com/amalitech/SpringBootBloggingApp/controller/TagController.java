package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.TagServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagServiceImpl tagServiceImpl;
    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }
}
