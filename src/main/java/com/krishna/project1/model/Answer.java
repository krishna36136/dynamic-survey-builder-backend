package com.krishna.project1.model;

public class Answer {
    private String questionId;
    private String response;

    public Answer() {}

    public Answer(String questionId, String response) {
        this.questionId = questionId;
        this.response = response;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
