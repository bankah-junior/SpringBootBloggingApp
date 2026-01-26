package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
}