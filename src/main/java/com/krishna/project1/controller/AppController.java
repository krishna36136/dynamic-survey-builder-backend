package com.krishna.project1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krishna.project1.model.Survey;
import com.krishna.project1.service.SurveyService;

@RestController()
@RequestMapping("survey")
@CrossOrigin(origins = "*")
public class AppController {
	
	
	@Autowired
	SurveyService service;
	
	
	@PostMapping("/setSurvey")
	public ResponseEntity<Survey> setSurvey(@RequestBody Survey survey) {
		return service.addSurvey(survey);
	}
}
