package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.service.ReviewService;
import com.amalitech.SpringBootBloggingApp.util.exceptions.UserInputsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<?> create(@RequestBody Review review) {
        try {
            Review createdReview = reviewService.create(review);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdReview);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
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
    public ResponseEntity<Boolean> delete(@PathVariable String reviewId) {
        try {
            boolean isDeleted = reviewService.delete(reviewId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(isDeleted);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(false);
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
    public ResponseEntity<Boolean> update(@RequestBody Review review) {
        try {
            boolean isUpdated = reviewService.update(review);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(isUpdated);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
    }

     /**
     * Get all reviews
     * @return a list of all reviews
     */
    @GetMapping
    @Operation(summary = "Get all reviews", description = "Retrieves a list of all reviews")
    @Tag(name = "Review")
    public ResponseEntity<?> getAll() {
        try {
            List<Review> reviews = reviewService.getAll();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reviews);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    /**
     * Get reviews by post ID
     * @param postId the ID of the post to get reviews for
     * @return a list of reviews for the specified post
     */
     @GetMapping("/post/{postId}")
    @Operation(summary = "Get reviews by post ID", description = "Retrieves a list of reviews for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<?> getByPost(@PathVariable String postId) {
        try {
            List<Review> reviews = reviewService.getByPost(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(reviews);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

     /**
     * Get average rating for a post
     * @param postId the ID of the post to get the average rating for
     * @return the average rating for the specified post
     */
     @GetMapping("/post/{postId}/average-rating")
    @Operation(summary = "Get average rating for a post", description = "Retrieves the average rating for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<?> getAverageRatingForPost(@PathVariable String postId) {
        try {
            double averageRating = reviewService.getAverageRatingForPost(postId);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(averageRating);
        } catch (UserInputsException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
