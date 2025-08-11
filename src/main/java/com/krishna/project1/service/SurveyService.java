package com.krishna.project1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krishna.project1.dao.SurveyDao;
import com.krishna.project1.model.Survey;

@Service
public class SurveyService {

	@Autowired
	SurveyDao dao;
	
	public ResponseEntity<Survey> addSurvey(Survey survey) {
		// TODO Auto-generated method stub
		dao.save(survey);
		return ResponseEntity.ok(survey);
		
	}
	
}
