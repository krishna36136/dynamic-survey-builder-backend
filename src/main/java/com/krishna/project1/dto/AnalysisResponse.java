package com.krishna.project1.dto;

import java.util.List;

public class AnalysisResponse {
    private String surveyId;
    private String title;
    private List<QuestionAnalytics> analytics;

    public AnalysisResponse(String surveyId, String title, List<QuestionAnalytics> analytics) {
        this.surveyId = surveyId;
        this.title = title;
        this.analytics = analytics;
    }

    public String getSurveyId() { return surveyId; }
    public String getTitle() { return title; }
    public List<QuestionAnalytics> getAnalytics() { return analytics; }
}
