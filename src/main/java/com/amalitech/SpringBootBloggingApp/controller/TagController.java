package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.DtoMapper;
import com.amalitech.SpringBootBloggingApp.model.dto.request.CreateTagRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.ApiResponse;
import com.amalitech.SpringBootBloggingApp.model.dto.response.TagResponse;
import com.amalitech.SpringBootBloggingApp.model.entity.Tag;
import com.amalitech.SpringBootBloggingApp.service.TagService;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
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
     * @return List of tags
     */
    @GetMapping("/all")
    @Operation(summary = "Get all tags", description = "Retrieves a list of all tags")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<List<TagResponse>>> getAllTags() {
        try {
            List<Tag> tags = tagService.getAll();
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toTagResponses(tags)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new tag", description = "Creates a new tag")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<TagResponse>> createTag(@Valid @RequestBody CreateTagRequest request) {
        try {
            Tag createdTag = tagService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Tag created", DtoMapper.toTagResponse(createdTag)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Assign a tag to a post
     * @param postId the post ID
     * @param tagId the tag ID
     * @return ResponseEntity
     */
    @PostMapping("/{postId}/assign/{tagId}")
    @Operation(summary = "Assign a tag to a post", description = "Assigns a tag to a post")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<Void>> assignTagToPost(@PathVariable String postId, @PathVariable String tagId) {
        try {
            tagService.assignTagToPost(postId, tagId);
            return ResponseEntity.ok(ApiResponse.success("Tag assigned to post", null));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Get all tags assigned to a post
     * @param postId the post ID
     * @return list of tags assigned to the post
     */
    @GetMapping("/{postId}/assigned")
    @Operation(summary = "Get all tags assigned to a post", description = "Retrieves a list of all tags assigned to a post")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<List<TagResponse>>> getAllTagsAssignedToPost(@PathVariable String postId) {
        try {
            List<Tag> tags = tagService.getTagsByPost(postId);
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toTagResponses(tags)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Unassign a tag from a post
     * @param postId the post ID
     * @param tagId the tag ID
     * @return ResponseEntity
     */
    @PostMapping("/{postId}/unassign/{tagId}")
    @Operation(summary = "Unassign a tag from a post", description = "Unassigns a specific tag from a post")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<Void>> unassignTagFromPost(@PathVariable String postId, @PathVariable String tagId) {
        try {
            tagService.unassignTagFromPost(postId, tagId);
            return ResponseEntity.ok(ApiResponse.success("Tag unassigned from post", null));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Get tag by name
     * @param name the tag name
     * @return the tag
     */
    @GetMapping("/name/{name}")
    @Operation(summary = "Get tag by name", description = "Retrieves a tag by its name")
    @io.swagger.v3.oas.annotations.tags.Tag(name = "Tag")
    public ResponseEntity<ApiResponse<TagResponse>> getTagByName(@PathVariable String name) {
        try {
            Tag tag = tagService.getByName(name);
            if (tag == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Tag not found"));
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toTagResponse(tag)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }
}
