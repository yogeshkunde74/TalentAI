package com.talentai.backend.ai.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentai.backend.ai.client.GeminiClient;
import com.talentai.backend.ai.dto.ResumeAnalysisResponse;
import com.talentai.backend.ai.service.AIService;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {

    private final GeminiClient geminiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public AIServiceImpl(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    @Override
    public ResumeAnalysisResponse analyzeResume(String resumeText) {

        String prompt = """
                You are an ATS Resume Analyzer.

                Analyze the following resume.

                Return ONLY valid JSON.

                Use exactly this format.

                {
                  "resumeScore": {
                    "overallScore": 0,
                    "technicalScore": 0,
                    "communicationScore": 0,
                    "experienceScore": 0
                  },
                  "skills": [],
                  "missingSkills": [],
                  "strengths": [],
                  "weaknesses": [],
                  "interviewQuestions": [
                    {
                      "question": "",
                      "difficulty": ""
                    }
                  ]
                }

                Resume:

                """ + resumeText;

        try {

            String aiResponse = geminiClient.generateContent(prompt);

            return objectMapper.readValue(
                    aiResponse,
                    ResumeAnalysisResponse.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Gemini response", e);
        }
    }
}