package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.service.impl.TagServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {
    private final TagServiceImpl tagServiceImpl;
    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    /**
     * Get all tags
     * @return List<Tag>
     */
    @GetMapping("/all")
    @Operation(summary = "Get all tags", description = "Retrieves a list of all tags")
    @Tag(name = "Tag")
    public ResponseEntity<List<com.amalitech.SpringBootBloggingApp.model.entity.Tag>> getAllTags() {
        List<com.amalitech.SpringBootBloggingApp.model.entity.Tag> tags = tagServiceImpl.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tags);
    }

    /**
     * Create a new tag
     * @param tag
     * @return Tag
     */
     @PostMapping("/create")
     @Operation(summary = "Create a new tag", description = "Creates a new tag")
     @Tag(name = "Tag")
     public ResponseEntity<com.amalitech.SpringBootBloggingApp.model.entity.Tag> createTag(@RequestBody com.amalitech.SpringBootBloggingApp.model.entity.Tag tag) {
        com.amalitech.SpringBootBloggingApp.model.entity.Tag createdTag = tagServiceImpl.create(tag);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTag);
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
     public ResponseEntity<Void> assignTagToPost(@PathVariable String postId, @PathVariable String tagId) {
        tagServiceImpl.assignTagToPost(postId, tagId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
     }

    /**
     * Get all tags assigned to a post
     * @param postId
     * @return List<Tag>
     */
    @GetMapping("/{postId}/assigned")
    @Operation(summary = "Get all tags assigned to a post", description = "Retrieves a list of all tags assigned to a post")
    @Tag(name = "Tag")
    public ResponseEntity<List<com.amalitech.SpringBootBloggingApp.model.entity.Tag>> getAllTagsAssignedToPost(@PathVariable String postId) {
        List<com.amalitech.SpringBootBloggingApp.model.entity.Tag> tags = tagServiceImpl.getTagsByPost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tags);
    }

    /**
     * Unassign all tags from a post
     * @param postId
     * @return ResponseEntity<Void>
     */
     @PostMapping("/{postId}/unassign/{tagId}")
     @Operation(summary = "Unassign a tag from a post", description = "Unassigns a tag from a post")
     @Tag(name = "Tag")
     public ResponseEntity<Void> unassignAllTagsFromPost(@PathVariable String postId) {
        tagServiceImpl.unassignAllTagsFromPost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
     }

    /**
     * Get by name
     * @param name
     * @return Tag
     */
     @GetMapping("/name/{name}")
     @Operation(summary = "Get tag by name", description = "Retrieves a tag by its name")
     @Tag(name = "Tag")
     public ResponseEntity<com.amalitech.SpringBootBloggingApp.model.entity.Tag> getTagByName(@PathVariable String name) {
        com.amalitech.SpringBootBloggingApp.model.entity.Tag tag = tagServiceImpl.getByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tag);
     }
}
