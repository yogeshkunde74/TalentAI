package com.talentai.backend.interview.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentai.backend.ai.client.GeminiClient;
import com.talentai.backend.ai.entity.ResumeAnalysis;
import com.talentai.backend.ai.repository.ResumeAnalysisRepository;
import com.talentai.backend.interview.dto.InterviewPreparationResponse;
import com.talentai.backend.interview.entity.InterviewPreparation;
import com.talentai.backend.interview.repository.InterviewPreparationRepository;
import com.talentai.backend.interview.service.InterviewService;
import org.springframework.stereotype.Service;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final InterviewPreparationRepository interviewPreparationRepository;
    private final GeminiClient geminiClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public InterviewServiceImpl(
            ResumeAnalysisRepository resumeAnalysisRepository,
            InterviewPreparationRepository interviewPreparationRepository,
            GeminiClient geminiClient) {

        this.resumeAnalysisRepository = resumeAnalysisRepository;
        this.interviewPreparationRepository = interviewPreparationRepository;
        this.geminiClient = geminiClient;
    }

    @Override
    public InterviewPreparationResponse prepareInterview(Long resumeId) {

        ResumeAnalysis analysis = resumeAnalysisRepository
                .findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume Analysis not found"));

        String prompt = """
                You are a Senior Technical Interviewer.

                Based on the following resume information,
                generate interview questions.

                Return ONLY valid JSON.

                Do NOT use markdown.

                Do NOT use ```json.

                Return exactly this structure:

                {
                  "technicalQuestions": [
                    {
                      "question": "",
                      "difficulty": ""
                    }
                  ],
                  "hrQuestions": [
                    {
                      "question": "",
                      "difficulty": ""
                    }
                  ],
                  "codingQuestions": [
                    {
                      "question": "",
                      "difficulty": ""
                    }
                  ],
                  "overallTips": ""
                }

                Resume Skills:
                """
                + analysis.getSkills()
                + """

                        Resume Strengths:
                        """
                + analysis.getStrengths()
                + """

                        Resume Weaknesses:
                        """
                + analysis.getWeaknesses();

        String aiAnswer = geminiClient.generateContent(prompt);

        aiAnswer = aiAnswer
                .replace("```json", "")
                .replace("```", "")
                .trim();

        System.out.println(aiAnswer);

        try {

            InterviewPreparationResponse response = objectMapper.readValue(
                    aiAnswer,
                    InterviewPreparationResponse.class);

            InterviewPreparation entity = InterviewPreparation.builder()
                    .resumeId(resumeId)

                    .technicalQuestions(
                            objectMapper.writeValueAsString(
                                    response.getTechnicalQuestions()))

                    .hrQuestions(
                            objectMapper.writeValueAsString(
                                    response.getHrQuestions()))

                    .codingQuestions(
                            objectMapper.writeValueAsString(
                                    response.getCodingQuestions()))

                    .overallTips(response.getOverallTips())

                    .build();

            interviewPreparationRepository.save(entity);

            return response;

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to parse AI response");

        }
    }
}