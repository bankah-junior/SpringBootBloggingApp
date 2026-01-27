package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.request.LoginRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.RegisterRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.UpdateUserDetailRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.UserResponse;
import com.amalitech.SpringBootBloggingApp.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl userServiceImpl;
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * User Login
     * @param loginRequest
     * @return ResponseEntity<UserResponse>
     */
    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs in a user and returns a JWT token")
    @Tag(name = "User")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = userServiceImpl.login(loginRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    /**
     * User Registration
     * @param registerRequest
     * @return ResponseEntity<UserResponse>
     */
    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "Registers a new user and returns the user details with a JWT token")
    @Tag(name = "User")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = userServiceImpl.create(registerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userResponse);
    }

    /**
     * Get User by ID
     * @param userId
     * @return ResponseEntity<UserResponse>
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get User by ID", description = "Retrieves a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String userId) {
        UserResponse userResponse = userServiceImpl.getById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    /**
     * Get User by Email
     * @param email
     * @return ResponseEntity<UserResponse>
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Get User by Email", description = "Retrieves a user by their email")
    @Tag(name = "User")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
        UserResponse userResponse = userServiceImpl.getByEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponse);
    }

    /**
     * Get All Users
     * @return ResponseEntity<List<UserResponse>>
     */
    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieves a list of all users")
    @Tag(name = "User")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> userResponses = userServiceImpl.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userResponses);
    }

    /**
     * Update User
     * @param userId
     * @param updateUserDetailRequest
     * @return ResponseEntity<UserResponse>
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Update User", description = "Updates user details")
    @Tag(name = "User")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId,
                                                   @RequestBody UpdateUserDetailRequest updateUserDetailRequest) {
        boolean isUpdated = userServiceImpl.updateUserDetails(userId, updateUserDetailRequest);
        if (!isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        UserResponse updatedUserResponse = userServiceImpl.getById(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUserResponse);
    }

    /**
     * Delete User by ID
     * @param userId
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User by ID", description = "Deletes a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userServiceImpl.delete(userId);
        if (!isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    /**
     * Change User Password
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return ResponseEntity<Void>
     */
    @PutMapping("/{userId}/change-password")
    @Operation(summary = "Change User Password", description = "Changes the password of a user")
    @Tag(name = "User")
    public ResponseEntity<Void> changePassword(@PathVariable String userId,
                                               @RequestParam String oldPassword,
                                               @RequestParam String newPassword) {
        boolean isChanged = userServiceImpl.changePassword(userId, oldPassword, newPassword);
        if (!isChanged) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
                }
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}