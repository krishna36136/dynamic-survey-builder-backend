package com.krishna.project1.model;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "responses")
public class SurveyResponse {
    @Id
    private String id;
    private String surveyId;
    private String userId;
    private List<Answer> answers;

    public SurveyResponse() {}

    public SurveyResponse(String surveyId, String userId, List<Answer> answers) {
        this.surveyId = surveyId;
        this.userId = userId;
        this.answers = answers;
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getSurveyId() { return surveyId; }
    public void setSurveyId(String surveyId) { this.surveyId = surveyId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}
