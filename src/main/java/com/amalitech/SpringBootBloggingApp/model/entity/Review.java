package com.amalitech.SpringBootBloggingApp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public class Review {

    @Id
    private String id;

    @Indexed
    private String postId;

    @Indexed
    private String userId;

    private int rating;

    private String feedback;

    private Long createdAt;

    private Long updatedAt;

    public Review() {}

    public Review(String id, String postId, String userId,
                  int rating, String feedback, Long createdAt, Long updatedAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.rating = rating;
        this.feedback = feedback;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPostId() { return postId; }
    public void setPostId(String postId) { this.postId = postId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Long getCreatedAt() { return createdAt; }
    public void setCreatedAt(Long createdAt) { this.createdAt = createdAt; }

    public Long getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Long updatedAt) { this.updatedAt = updatedAt; }

    public String getReviewId() { return id; }
    public void setReviewId(String reviewId) {
        this.id = reviewId;
    }

}