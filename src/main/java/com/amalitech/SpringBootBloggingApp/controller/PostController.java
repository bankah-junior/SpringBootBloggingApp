package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.entity.Post;
import com.amalitech.SpringBootBloggingApp.service.PostService;
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
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Create a new post
     *
     * @param post the post to create
     * @return the created post
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new post", description = "Creates a new post")
    @Tag(name = "Post")
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postService.create(post);
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
     *
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
    @GetMapping("/sort/date")
    @Operation(summary = "Get all posts sorted by date", description = "Retrieves all posts sorted by date")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSortedByDate(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postService.sortByDate(postService.getAll(), ascending);
        return ResponseEntity.ok(sortedPosts);
    }

    /**
     * Get all posts sorted by title
     *
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
    @GetMapping("/sort/title")
    @Operation(summary = "Get all posts sorted by title", description = "Retrieves all posts sorted by title")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSortedByTitle(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postService.sortByTitle(postService.getAll(), ascending);
        return ResponseEntity.ok(sortedPosts);
    }

    /**
     * Get all sorted posts
     *
     * @param ascending whether to sort in ascending order
     * @return the sorted posts
     */
    @GetMapping("/sort/all")
    @Operation(summary = "Get all sorted posts", description = "Retrieves all posts sorted by date and title")
    @Tag(name = "Post")
    public ResponseEntity<List<Post>> getAllSorted(@RequestParam boolean ascending) {
        List<Post> sortedPosts = postService.getAllSorted("date", ascending);
        return ResponseEntity.ok(sortedPosts);
    }

    /**
     * Update a post
     *
     * @param post the post to update
     * @return the updated post
     */
    @PutMapping("/update")
    @Operation(summary = "Update a post", description = "Updates a post")
    @Tag(name = "Post")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        Post updatedPost = postService.update(post);
        return ResponseEntity.ok(updatedPost);
    }

    /**
     * Delete a post by ID
     *
     * @param id the ID of the post to delete
     * @return ResponseEntity with no content if successful, or bad request with error message if not
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a post by ID", description = "Deletes a post by ID")
    @Tag(name = "Post")
    public ResponseEntity<?> deletePostById(@PathVariable String id) {
        try {
            postService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get all posts
     *
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
    @GetMapping("/all")
    @Operation(summary = "Get all posts", description = "Retrieves all posts")
    @Tag(name = "Post")
    public ResponseEntity<?> getAllPosts() {
        try {
            List<Post> posts = postService.getAll();
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get a post by ID
     *
     * @param id the ID of the post to retrieve
     * @return ResponseEntity with post if successful, or bad request with error message if not
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a post by ID", description = "Retrieves a post by ID")
    @Tag(name = "Post")
    public ResponseEntity<?> getPostById(@PathVariable String id) {
        try {
            Post post = postService.getById(id);
            return ResponseEntity.ok(post);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get posts by author ID
     *
     * @param authorId the ID of the author
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
    @GetMapping("/author/{authorId}")
    @Operation(summary = "Get posts by author ID", description = "Retrieves all posts by a specific author")
    @Tag(name = "Post")
    public ResponseEntity<?> getPostsByAuthorId(@PathVariable String authorId) {
        try {
            List<Post> posts = postService.getByAuthor(authorId);
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Search posts by title keyword
     *
     * @param keyword the keyword to search for
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
    @GetMapping("/search/title/{keyword}")
    @Operation(summary = "Search posts by title", description = "Searches posts by title keyword")
    @Tag(name = "Post")
    public ResponseEntity<?> searchPostsByTitle(@PathVariable String keyword) {
        try {
            List<Post> posts = postService.searchByTitle(keyword);
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Search posts by tag name
     *
     * @param tagName the tag name to search for
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
    @GetMapping("/search/tag/{tagName}")
    @Operation(summary = "Search posts by tag", description = "Searches posts by tag name")
    @Tag(name = "Post")
    public ResponseEntity<?> searchPostsByTag(@PathVariable String tagName) {
        try {
            List<Post> posts = postService.searchByTag(tagName);
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get posts by published status
     *
     * @param published the published status to filter by
     * @return ResponseEntity with list of posts if successful, or bad request with error message if not
     */
    @GetMapping("/published/{published}")
    @Operation(summary = "Get posts by published status", description = "Retrieves posts by published status")
    @Tag(name = "Post")
    public ResponseEntity<?> getPostsByPublished(@PathVariable boolean published) {
        try {
            List<Post> posts = postService.getByPublished(published);
            return ResponseEntity.ok(posts);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}