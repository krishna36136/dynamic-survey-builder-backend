package com.krishna.project1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.krishna.project1.dao.SurveyResponseDao;
import com.krishna.project1.model.SurveyResponse;

@Service
public class SurveyResponseService {

    @Autowired
    private SurveyResponseDao dao;

    public SurveyResponse saveResponse(SurveyResponse response) {
        return dao.save(response);
    }

    public List<SurveyResponse> getResponsesBySurveyId(String surveyId) {
        return dao.findBySurveyId(surveyId);
    }
}
