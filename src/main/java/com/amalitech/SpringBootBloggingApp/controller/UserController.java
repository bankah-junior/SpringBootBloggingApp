package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.request.LoginRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.RegisterRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.UpdateUserDetailRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.ApiResponse;
import com.amalitech.SpringBootBloggingApp.model.dto.response.UserResponse;
import com.amalitech.SpringBootBloggingApp.service.UserService;
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

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Logs in a user and returns a JWT token")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<UserResponse>> login(@RequestBody LoginRequest loginRequest) {
        UserResponse userResponse = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userResponse));
    }

    @PostMapping("/register")
    @Operation(summary = "User Registration", description = "Registers a new user and returns the user details with a JWT token")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody RegisterRequest registerRequest) {
        UserResponse userResponse = userService.create(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("User registered", userResponse));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get User by ID", description = "Retrieves a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String userId) {
        UserResponse userResponse = userService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userResponse));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get User by Email", description = "Retrieves a user by their email")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByEmail(@PathVariable String email) {
        UserResponse userResponse = userService.getByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userResponse));
    }

    @GetMapping
    @Operation(summary = "Get All Users", description = "Retrieves a list of all users")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        List<UserResponse> userResponses = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(userResponses));
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update User", description = "Updates user details")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String userId,
                                                               @RequestBody UpdateUserDetailRequest updateUserDetailRequest) {
        boolean isUpdated = userService.updateUserDetails(userId, updateUserDetailRequest);
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("User not found"));
        }
        UserResponse updatedUserResponse = userService.getById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("User updated", updatedUserResponse));
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete User by ID", description = "Deletes a user by their ID")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String userId) {
        boolean isDeleted = userService.delete(userId);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("User not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("User deleted", null));
    }

    @PutMapping("/{userId}/change-password")
    @Operation(summary = "Change User Password", description = "Changes the password of a user")
    @Tag(name = "User")
    public ResponseEntity<ApiResponse<Void>> changePassword(@PathVariable String userId,
                                                           @RequestParam String oldPassword,
                                                           @RequestParam String newPassword) {
        boolean isChanged = userService.changePassword(userId, oldPassword, newPassword);
        if (!isChanged) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error("Invalid current password or update failed"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success("Password updated", null));
    }
}
