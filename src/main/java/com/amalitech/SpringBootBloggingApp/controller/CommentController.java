package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.entity.Comment;
import com.amalitech.SpringBootBloggingApp.service.impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    /**
     * create a comment
     * @param comment the comment to create
     * @return the created comment
     */
     @PostMapping("/create")
    @Operation(summary = "Create a comment", description = "Creates a new comment for a post")
    @Tag(name = "Comment")
    public ResponseEntity<Comment> create(@RequestBody Comment comment) {
        Comment createdComment = commentServiceImpl.create(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }

    /**
     * Find by id
     * @param commentId the comment id to find
     * @return the comment with the given id
     */
     @GetMapping("/{commentId}")
    @Operation(summary = "Find comment by id", description = "Retrieves a comment by its id")
    @Tag(name = "Comment")
    public ResponseEntity<Comment> findById(@PathVariable String commentId) {
        Comment comment = commentServiceImpl.findById(commentId);
        if (comment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

    /**
     * Find all comments
     * @return all comments
     */
     @GetMapping("/all")
    @Operation(summary = "Find all comments", description = "Retrieves all comments")
    @Tag(name = "Comment")
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> comments = commentServiceImpl.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    /**
     * Find comments by user id
     * @param userId the user id to find comments for
     * @return all comments for the given user id
     */
     @GetMapping("/user/{userId}")
    @Operation(summary = "Find comments by user id", description = "Retrieves all comments for a user")
    @Tag(name = "Comment")
    public ResponseEntity<List<Comment>> findByUserId(@PathVariable String userId) {
        List<Comment> comments = commentServiceImpl.getByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

     /**
     * Find comments by post id
     * @param postId the post id to find comments for
     * @return all comments for the given post id
     */
     @GetMapping("/post/{postId}")
    @Operation(summary = "Find comments by post id", description = "Retrieves all comments for a post")
    @Tag(name = "Comment")
    public ResponseEntity<List<Comment>> findByPostId(@PathVariable String postId) {
        List<Comment> comments = commentServiceImpl.getByPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    /**
     * Update a comment
     * @param comment the comment to update
     * @return the updated comment
     */
     @PutMapping("/update")
    @Operation(summary = "Update a comment", description = "Updates a comment")
    @Tag(name = "Comment")
    public ResponseEntity<Comment> update(@RequestBody Comment comment) {
        boolean updated = commentServiceImpl.update(comment);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(comment);
    }

     /**
     * Delete a comment
     * @param commentId the comment id to delete
     * @return true if the comment was deleted, false otherwise
     */
     @DeleteMapping("/delete/{commentId}")
    @Operation(summary = "Delete a comment", description = "Deletes a comment by its id")
    @Tag(name = "Comment")
    public ResponseEntity<Boolean> delete(@PathVariable String commentId) {
        boolean deleted = commentServiceImpl.delete(commentId);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
