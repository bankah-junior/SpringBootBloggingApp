package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.DtoMapper;
import com.amalitech.SpringBootBloggingApp.model.dto.request.CreateCommentRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.UpdateCommentRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.ApiResponse;
import com.amalitech.SpringBootBloggingApp.model.dto.response.CommentResponse;
import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.service.CommentService;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * create a comment
     * @param comment the comment to create
     * @return the created comment if valid, otherwise a bad request response with an error message
     */
    @PostMapping("/create")
    @Operation(summary = "Create a comment", description = "Creates a new comment for a post")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<CommentResponse>> create(@Valid @RequestBody CreateCommentRequest request) {
        try {
            Comment createdComment = commentService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Comment created", DtoMapper.toCommentResponse(createdComment)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Find by id
     * @param commentId the comment id to find
     * @return the comment with the given id if found, otherwise null
     */
    @GetMapping("/{commentId}")
    @Operation(summary = "Find comment by id", description = "Retrieves a comment by its id")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<CommentResponse>> findById(@PathVariable String commentId) {
        try {
            Comment comment = commentService.findById(commentId);
            if (comment == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Comment not found"));
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toCommentResponse(comment)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Find all comments", description = "Retrieves all comments")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getAll() {
        try {
            List<Comment> comments = commentService.getAll();
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toCommentResponses(comments)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Find comments by user id", description = "Retrieves all comments for a user")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> findByUserId(@PathVariable String userId) {
        try {
            List<Comment> comments = commentService.getByUser(userId);
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toCommentResponses(comments)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Find comments by post id", description = "Retrieves all comments for a post")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> findByPostId(@PathVariable String postId) {
        try {
            List<Comment> comments = commentService.getByPost(postId);
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toCommentResponses(comments)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a comment", description = "Updates a comment")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<CommentResponse>> update(@Valid @RequestBody UpdateCommentRequest request) {
        try {
            Comment existing = commentService.findById(request.getId());
            if (existing == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Comment not found"));
            existing.setContent(request.getContent());
            existing.setUpdatedAt(System.currentTimeMillis());
            boolean updated = commentService.update(existing);
            if (!updated) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Comment not found"));
            return ResponseEntity.ok(ApiResponse.success("Comment updated", DtoMapper.toCommentResponse(existing)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{commentId}")
    @Operation(summary = "Delete a comment", description = "Deletes a comment by its id")
    @Tag(name = "Comment")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String commentId) {
        try {
            boolean deleted = commentService.delete(commentId);
            if (!deleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Comment not found"));
            return ResponseEntity.ok(ApiResponse.success("Comment deleted", null));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }
}
