package com.amalitech.SpringBootBloggingApp.model.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "post_tags")
public class PostTag {

    @Indexed
    private String postId;

    @Indexed
    private String tagId;

    public PostTag() {}

    public PostTag(String postId, String tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public String getPostId() { return postId; }
    public void setPostId(String postId) { this.postId = postId; }

    public String getTagId() { return tagId; }
    public void setTagId(String tagId) { this.tagId = tagId; }
}
