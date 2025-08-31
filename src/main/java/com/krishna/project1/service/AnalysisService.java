package com.krishna.project1.service;

import com.krishna.project1.model.Question;
import com.krishna.project1.model.Survey;
import com.krishna.project1.model.SurveyResponse;
import com.krishna.project1.dto.QuestionAnalytics;
import com.krishna.project1.dao.SurveyDao;
import com.krishna.project1.dao.SurveyResponseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalysisService {

    @Autowired
    private SurveyDao surveyRepository;

    @Autowired
    private SurveyResponseDao responseRepository;

    public List<QuestionAnalytics> analyzeSurvey(String surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        List<SurveyResponse> responses = responseRepository.findBySurveyId(surveyId);

        List<QuestionAnalytics> analytics = new ArrayList<>();

        for (Question question : survey.getQuestions()) {
            QuestionAnalytics qa = new QuestionAnalytics();
            qa.setQuestionId(question.getId());
            qa.setQuestion(question.getQuestion());
            qa.setType(question.getType());

            switch (question.getType()) {
                case "multiple":
                case "dropdown":
                    qa.setData(analyzeOptions(question.getId(), responses));
                    break;

                case "rating":
                    qa.setData(analyzeRating(question.getId(), responses));
                    break;

                case "text":
                    qa.setData(analyzeText(question.getId(), responses));
                    break;

                default:
                    qa.setData(Collections.emptyList());
                    break;
            }

            analytics.add(qa);
        }

        return analytics;
    }

    // ✅ For multiple-choice and dropdown questions
    private List<Map<String, Object>> analyzeOptions(String questionId, List<SurveyResponse> responses) {
        Map<String, Long> optionCount = responses.stream()
                .flatMap(r -> r.getAnswers().stream())
                .filter(a -> a.getQuestionId().equals(questionId))
                .collect(Collectors.groupingBy(a -> a.getResponse(), Collectors.counting()));

        return optionCount.entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("option", entry.getKey());
                    map.put("count", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }

    // ✅ For rating questions
    private List<Map<String, Object>> analyzeRating(String questionId, List<SurveyResponse> responses) {
        List<Integer> ratings = responses.stream()
                .flatMap(r -> r.getAnswers().stream())
                .filter(a -> a.getQuestionId().equals(questionId))
                .map(a -> {
                    try {
                        return Integer.parseInt(a.getResponse());
                    } catch (NumberFormatException e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        double average = ratings.isEmpty() ? 0 : ratings.stream().mapToInt(Integer::intValue).average().orElse(0);

        Map<String, Object> avgMap = new HashMap<>();
        avgMap.put("average", average);
        avgMap.put("totalResponses", ratings.size());

        return Collections.singletonList(avgMap);
    }

    // ✅ For text questions
    private List<Map<String, Object>> analyzeText(String questionId, List<SurveyResponse> responses) {
        List<Map<String, Object>> textAnswers = new ArrayList<>();

        responses.forEach(r -> r.getAnswers().stream()
                .filter(a -> a.getQuestionId().equals(questionId))
                .forEach(a -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("answer", a.getResponse());
                    textAnswers.add(map);
                }));

        return textAnswers;
    }
}
