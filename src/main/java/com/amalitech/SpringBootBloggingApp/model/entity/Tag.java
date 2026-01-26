package com.amalitech.SpringBootBloggingApp.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tags")
public class Tag {

    @Id
    private String id;

    @Indexed
    private String name;

    public Tag() {}

    public Tag(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
