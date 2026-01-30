package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.request.LoginRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.RegisterRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.UpdateUserDetailRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.UserResponse;
import com.amalitech.SpringBootBloggingApp.service.UserService;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * User Login
     * @param loginRequest
     * @return ResponseEntity with JWT token if successful, or bad request with error message if not
     */
    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs in a user and returns a JWT token")
    @Tag(name = "User")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserResponse userResponse = userService.login(loginRequest);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * User Registration
     * @param registerRequest
     * @return ResponseEntity with JWT token if successful, or bad request with error message if not
     */
    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "Registers a new user and returns the user details with a JWT token")
    @Tag(name = "User")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserResponse userResponse = userService.create(registerRequest);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(userResponse);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get User by ID
     * @param userId
     * @return ResponseEntity with JWT token if successful, or bad request with error message if not
     */
    @GetMapping("/{userId}")
    @Operation(summary = "Get User by ID", description = "Retrieves a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        try {
            UserResponse userResponse = userService.getById(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get User by Email
     * @param email
     * @return ResponseEntity with JWT token if successful, or bad request with error message if not
     */
    @GetMapping("/email/{email}")
    @Operation(summary = "Get User by Email", description = "Retrieves a user by their email")
    @Tag(name = "User")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            UserResponse userResponse = userService.getByEmail(email);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponse);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get All Users
     * @return ResponseEntity with list of user responses if successful, or bad request with error message if not
     */
    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieves a list of all users")
    @Tag(name = "User")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserResponse> userResponses = userService.getAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userResponses);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Update User
     * @param userId
     * @param updateUserDetailRequest
     * @return ResponseEntity with updated user response if successful, or bad request with error message if not
     */
    @PutMapping("/{userId}")
    @Operation(summary = "Update User", description = "Updates user details")
    @Tag(name = "User")
    public ResponseEntity<?> updateUser(@PathVariable String userId,
                                                   @RequestBody UpdateUserDetailRequest updateUserDetailRequest) {
        try {
            boolean isUpdated = userService.updateUserDetails(userId, updateUserDetailRequest);
            if (!isUpdated) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            UserResponse updatedUserResponse = userService.getById(userId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(updatedUserResponse);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Delete User by ID
     * @param userId
     * @return ResponseEntity with no content if successful, or not found if user does not exist, or bad request with error message if not
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User by ID", description = "Deletes a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        try {
            boolean isDeleted = userService.delete(userId);
            if (!isDeleted) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .build();
            }
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    /**
     * Change User Password
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return ResponseEntity with no content if successful, or bad request with error message if not
     */
    @PutMapping("/{userId}/change-password")
    @Operation(summary = "Change User Password", description = "Changes the password of a user")
    @Tag(name = "User")
    public ResponseEntity<?> changePassword(@PathVariable String userId,
                                               @RequestParam String oldPassword,
                                               @RequestParam String newPassword) {
        try {
            boolean isChanged = userService.changePassword(userId, oldPassword, newPassword);
            if (!isChanged) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
            }
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

}