package com.amalitech.SpringBootBloggingApp.controller;

import com.amalitech.SpringBootBloggingApp.model.entity.Review;
import com.amalitech.SpringBootBloggingApp.service.impl.ReviewServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewServiceImpl reviewServiceImpl;
    public ReviewController(ReviewServiceImpl reviewServiceImpl) {
        this.reviewServiceImpl = reviewServiceImpl;
    }

    /**
     * Create a new review
     * @param review the review to create
     * @return the created review
     */
    @PostMapping("/create")
    @Operation(summary = "Create a new review", description = "Creates a new review for a post")
    @Tag(name = "Review")
    public ResponseEntity<Review> create(@RequestBody Review review) {
        Review createdReview = reviewServiceImpl.create(review);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdReview);
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
        boolean isDeleted = reviewServiceImpl.delete(reviewId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(isDeleted);
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
        boolean isUpdated = reviewServiceImpl.update(review);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(isUpdated);
    }

     /**
     * Get all reviews
     * @return a list of all reviews
     */
    @GetMapping
    @Operation(summary = "Get all reviews", description = "Retrieves a list of all reviews")
    @Tag(name = "Review")
    public ResponseEntity<List<Review>> getAll() {
        List<Review> reviews = reviewServiceImpl.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviews);
    }

    /**
     * Get reviews by post ID
     * @param postId the ID of the post to get reviews for
     * @return a list of reviews for the specified post
     */
     @GetMapping("/post/{postId}")
    @Operation(summary = "Get reviews by post ID", description = "Retrieves a list of reviews for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<List<Review>> getByPost(@PathVariable String postId) {
        List<Review> reviews = reviewServiceImpl.getByPost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reviews);
    }

     /**
     * Get average rating for a post
     * @param postId the ID of the post to get the average rating for
     * @return the average rating for the specified post
     */
     @GetMapping("/post/{postId}/average-rating")
    @Operation(summary = "Get average rating for a post", description = "Retrieves the average rating for a specific post")
    @Tag(name = "Review")
    public ResponseEntity<Double> getAverageRatingForPost(@PathVariable String postId) {
        double averageRating = reviewServiceImpl.getAverageRatingForPost(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(averageRating);
    }
}
