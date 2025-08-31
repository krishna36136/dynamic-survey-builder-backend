package com.krishna.project1.controller;

import com.krishna.project1.dto
.QuestionAnalytics;
import com.krishna.project1.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@CrossOrigin(origins = "*")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/{surveyId}")
    public List<QuestionAnalytics> getSurveyAnalysis(@PathVariable String surveyId) {
        return analysisService.analyzeSurvey(surveyId);
    }
}
