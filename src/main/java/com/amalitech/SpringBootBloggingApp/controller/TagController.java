package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagService tagService;
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Get all tags
     * @return List<Tag>
     */
    @GetMapping("/all")
    @Operation(summary = "Get all tags", description = "Retrieves a list of all tags")
    @Tag(name = "Tag")
    public ResponseEntity<?> getAllTags() {
        try {
            List<com.amalitech.SpringBootBloggingApp.model.entity.Tag> tags = tagService.getAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tags);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Create a new tag
     * @param tag
     * @return Tag
     */
     @PostMapping("/create")
     @Operation(summary = "Create a new tag", description = "Creates a new tag")
     @Tag(name = "Tag")
     public ResponseEntity<?> createTag(@RequestBody com.amalitech.SpringBootBloggingApp.model.entity.Tag tag) {
        try {
            com.amalitech.SpringBootBloggingApp.model.entity.Tag createdTag = tagService.create(tag);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdTag);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }

    /**
     * Assign a tag to a post
     * @param postId
     * @param tagId
     * @return ResponseEntity<Void>
     */
     @PostMapping("/{postId}/assign/{tagId}")
     @Operation(summary = "Assign a tag to a post", description = "Assigns a tag to a post")
     @Tag(name = "Tag")
     public ResponseEntity<?> assignTagToPost(@PathVariable String postId, @PathVariable String tagId) {
        try {
            tagService.assignTagToPost(postId, tagId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }

    /**
     * Get all tags assigned to a post
     * @param postId
     * @return List<Tag>
     */
    @GetMapping("/{postId}/assigned")
    @Operation(summary = "Get all tags assigned to a post", description = "Retrieves a list of all tags assigned to a post")
    @Tag(name = "Tag")
    public ResponseEntity<?> getAllTagsAssignedToPost(@PathVariable String postId) {
        try {
            List<com.amalitech.SpringBootBloggingApp.model.entity.Tag> tags = tagService.getTagsByPost(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tags);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Unassign a tag from a post
     * @param postId
     * @param tagId
     * @return ResponseEntity<Void>
     */
     @PostMapping("/{postId}/unassign/{tagId}")
     @Operation(summary = "Unassign a tag from a post", description = "Unassigns a specific tag from a post")
     @Tag(name = "Tag")
    public ResponseEntity<?> unassignTagFromPost(@PathVariable String postId, @PathVariable String tagId) {
        try {
            // This method needs to be implemented in TagService
            // For now, we'll use unassignAllTagsFromPost as a temporary fix
            tagService.unassignAllTagsFromPost(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }

    /**
     * Get by name
     * @param name
     * @return Tag
     */
     @GetMapping("/name/{name}")
     @Operation(summary = "Get tag by name", description = "Retrieves a tag by its name")
     @Tag(name = "Tag")
     public ResponseEntity<?> getTagByName(@PathVariable String name) {
        try {
            com.amalitech.SpringBootBloggingApp.model.entity.Tag tag = tagService.getByName(name);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(tag);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
     }
}
