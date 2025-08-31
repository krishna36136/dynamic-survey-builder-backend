package com.krishna.project1.model;

import java.util.List;
import java.util.UUID;

public class Question {
    private String id;
    private String type;
    private String question;
    private List<String> options;

    public Question(String type, String question, List<String> options) {
        this.id = UUID.randomUUID().toString(); // Auto-generate ID
        this.type = type;
        this.question = question;
        this.options = options;
    }

    // Default constructor (required for MongoDB deserialization)
    public Question() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    // No setter for id (to keep it immutable)
    // Or if you want to allow overriding, keep it.

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
