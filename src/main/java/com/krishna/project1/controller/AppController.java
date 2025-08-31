package com.krishna.project1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krishna.project1.model.Survey;
import com.krishna.project1.service.SurveyService;

@RestController
@RequestMapping("survey")
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    SurveyService service;

    // Save survey
    @PostMapping("/setSurvey")
    public ResponseEntity<Survey> setSurvey(@RequestBody Survey survey) {
        return service.addSurvey(survey);
    }

    // Get survey by ID
    @GetMapping("/{id}")
    public ResponseEntity<Survey> getSurveyById(@PathVariable String id) {
        return service.getSurveyById(id);
    }

    // Get all surveys of logged-in admin
    @GetMapping("/all")
    public List<Survey> getAllSurveys() {
        return service.getAllSurveys();
    }

    // Delete survey by ID (only if belongs to logged-in admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable String id) {
        return service.deleteSurvey(id);
    }
}
