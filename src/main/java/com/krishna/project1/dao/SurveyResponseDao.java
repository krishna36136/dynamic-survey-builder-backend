package com.krishna.project1.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.krishna.project1.model.SurveyResponse;
import java.util.List;

@Repository
public interface SurveyResponseDao extends MongoRepository<SurveyResponse, String> {
    List<SurveyResponse> findBySurveyId(String surveyId);
}
