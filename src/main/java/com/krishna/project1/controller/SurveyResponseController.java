package com.krishna.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krishna.project1.model.SurveyResponse;
import com.krishna.project1.service.SurveyResponseService;

@RestController
@RequestMapping("/response")
@CrossOrigin(origins = "*")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseService service;

    // Save a survey response
    @PostMapping("/submit")
    public ResponseEntity<SurveyResponse> submitResponse(@RequestBody SurveyResponse response) {
        SurveyResponse saved = service.saveResponse(response);
        return ResponseEntity.ok(saved);
    }

    // Get all responses for a survey
    @GetMapping("/all/{surveyId}")
    public ResponseEntity<List<SurveyResponse>> getAllResponses(@PathVariable String surveyId) {
        List<SurveyResponse> responses = service.getResponsesBySurveyId(surveyId);
        return ResponseEntity.ok(responses);
    }
}
