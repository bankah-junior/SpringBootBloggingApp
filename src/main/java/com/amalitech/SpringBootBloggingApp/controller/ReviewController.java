package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.dto.DtoMapper;
import com.amalitech.SpringBootBloggingApp.model.dto.request.CreateReviewRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.request.UpdateReviewRequest;
import com.amalitech.SpringBootBloggingApp.model.dto.response.ApiResponse;
import com.amalitech.SpringBootBloggingApp.model.dto.response.ReviewResponse;
import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.service.ReviewService;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Create a new review
     * @param review the review to create
     * @return the created review
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new review", description = "Creates a new review for a post")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<ReviewResponse>> create(@Valid @RequestBody CreateReviewRequest request) {
        try {
            Review createdReview = reviewService.create(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Review created", DtoMapper.toReviewResponse(createdReview)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    /**
     * Delete a review by ID
     * @param reviewId the ID of the review to delete
     * @return true if the review was deleted, false otherwise
     */
    @DeleteMapping("/delete/{reviewId}")
    @Operation(summary = "Delete a review by ID", description = "Deletes a review by its ID")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String reviewId) {
        try {
            boolean isDeleted = reviewService.delete(reviewId);
            if (!isDeleted) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Review not found"));
            return ResponseEntity.ok(ApiResponse.success("Review deleted", null));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

     /**
     * Update a review
     * @param review the review to update
     * @return true if the review was updated, false otherwise
     */
    @PutMapping("/update")
    @Operation(summary = "Update a review", description = "Updates a review")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<ReviewResponse>> update(@Valid @RequestBody UpdateReviewRequest request) {
        try {
            Review existing = reviewService.getById(request.getId());
            if (existing == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error("Review not found"));
            existing.setRating(request.getRating());
            existing.setFeedback(request.getFeedback() != null ? request.getFeedback() : "");
            existing.setUpdatedAt(System.currentTimeMillis());
            reviewService.update(existing);
            return ResponseEntity.ok(ApiResponse.success("Review updated", DtoMapper.toReviewResponse(existing)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all reviews", description = "Retrieves a list of all reviews")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getAll() {
        try {
            List<Review> reviews = reviewService.getAll();
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toReviewResponses(reviews)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/post/{postId}")
    @Operation(summary = "Get reviews by post ID", description = "Retrieves a list of reviews for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<List<ReviewResponse>>> getByPost(@PathVariable String postId) {
        try {
            List<Review> reviews = reviewService.getByPost(postId);
            return ResponseEntity.ok(ApiResponse.success(DtoMapper.toReviewResponses(reviews)));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/post/{postId}/average-rating")
    @Operation(summary = "Get average rating for a post", description = "Retrieves the average rating for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<ApiResponse<Double>> getAverageRatingForPost(@PathVariable String postId) {
        try {
            double averageRating = reviewService.getAverageRatingForPost(postId);
            return ResponseEntity.ok(ApiResponse.success(averageRating));
        } catch (UserInputsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.error(e.getMessage()));
        }
    }
}
