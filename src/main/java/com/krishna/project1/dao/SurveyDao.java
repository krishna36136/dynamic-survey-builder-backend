package com.krishna.project1.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.krishna.project1.model.Survey;

@Repository
public interface SurveyDao extends MongoRepository<Survey, String>{
	List<Survey> findByAdminId(String adminId);
}
