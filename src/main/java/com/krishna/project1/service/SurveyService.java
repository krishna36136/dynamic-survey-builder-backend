package com.krishna.project1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.krishna.project1.dao.SurveyDao;
import com.krishna.project1.model.Survey;

@Service
public class SurveyService {

    @Autowired
    SurveyDao dao;

    // Save new survey (attach adminId automatically)
    public ResponseEntity<Survey> addSurvey(Survey survey) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName(); // username/email of logged-in admin

        survey.setAdminId(adminId);
        dao.save(survey);
        return ResponseEntity.ok(survey);
    }

    // Fetch survey by ID
    public ResponseEntity<Survey> getSurveyById(String id) {
        Optional<Survey> survey = dao.findById(id);
        return survey.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Fetch all surveys created by logged-in admin
    public List<Survey> getAllSurveys() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();
        return dao.findByAdminId(adminId);
    }

    // Delete a survey (only if it belongs to the logged-in admin)
    public ResponseEntity<String> deleteSurvey(String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String adminId = auth.getName();

        Optional<Survey> survey = dao.findById(id);

        if (survey.isPresent() && survey.get().getAdminId().equals(adminId)) {
            dao.deleteById(id);
            return ResponseEntity.ok("Survey deleted successfully");
        }

        return ResponseEntity.status(403).body("Not authorized to delete this survey");
    }
}
