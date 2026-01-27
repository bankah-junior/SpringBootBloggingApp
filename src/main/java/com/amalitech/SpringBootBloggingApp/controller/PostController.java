package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.service.impl.PostServiceImpl;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostServiceImpl postServiceImpl;
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
    }

    /**
     * Create a new post
     * @param post the post to create
     * @return the created post
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new post", description = "Creates a new post")
    @Tag(name = "Post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postServiceImpl.create(post);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdPost);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get all posts sorted by date
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
     @GetMapping("/sort/date")
    @Operation(summary = "Get all posts sorted by date", description = "Retrieves all posts sorted by date")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSortedByDate(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postServiceImpl.sortByDate(postServiceImpl.getAll(), ascending);
        return ResponseEntity.ok(sortedPosts);
    }

     /**
     * Get all posts sorted by title
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
     @GetMapping("/sort/title")
    @Operation(summary = "Get all posts sorted by title", description = "Retrieves all posts sorted by title")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSortedByTitle(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postServiceImpl.sortByTitle(postServiceImpl.getAll(), ascending);
        return ResponseEntity.ok(sortedPosts);
    }

    /**
     * Get all sorted posts
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
     @GetMapping("/sort/all")
    @Operation(summary = "Get all sorted posts", description = "Retrieves all posts sorted by date and title")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSorted(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postServiceImpl.getAllSorted("date", ascending);
        return ResponseEntity.ok(sortedPosts);
    }

    /**
     * Update a post
     * @param post the post to update
     * @return the updated post
     */
     @PutMapping("/update")
    @Operation(summary = "Update a post", description = "Updates a post")
    @Tag(name = "Post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postServiceImpl.update(post);
        return ResponseEntity.ok(updatedPost);
    }

     /**
     * Delete a post by ID
     * @param id the ID of the post to delete
     * @return ResponseEntity with no content if successful, or bad request with error message if not
     */
      @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a post by ID", description = "Deletes a post by ID")
    @Tag(name = "Post")
     public ResponseEntity<?> deletePostById(@PathVariable String id) {
        try {
            postServiceImpl.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get all posts
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
     @GetMapping("/all")
    @Operation(summary = "Get all posts", description = "Retrieves all posts")
    @Tag(name = "Post")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postServiceImpl.getAll();
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get a post by ID
     * @param id the ID of the post to retrieve
     * @return ResponseEntity with post if successful, or bad request with error message if not
     */
     @GetMapping("/{id}")
    @Operation(summary = "Get a post by ID", description = "Retrieves a post by ID")
    @Tag(name = "Post")
    public ResponseEntity<?> getPostById(@PathVariable String id) {
        try {
            Post post = postServiceImpl.getById(id);
            return ResponseEntity.ok(post);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

}
