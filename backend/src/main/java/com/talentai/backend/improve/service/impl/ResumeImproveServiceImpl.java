package com.talentai.backend.improve.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentai.backend.ai.client.GeminiClient;
import com.talentai.backend.ai.entity.ResumeAnalysis;
import com.talentai.backend.ai.repository.ResumeAnalysisRepository;
import com.talentai.backend.improve.dto.ResumeImproveResponse;
import com.talentai.backend.improve.entity.ResumeImprovement;
import com.talentai.backend.improve.repository.ResumeImprovementRepository;
import com.talentai.backend.improve.service.ResumeImproveService;
import org.springframework.stereotype.Service;

@Service
public class ResumeImproveServiceImpl implements ResumeImproveService {

    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final ResumeImprovementRepository resumeImprovementRepository;
    private final GeminiClient geminiClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResumeImproveServiceImpl(
            ResumeAnalysisRepository resumeAnalysisRepository,
            ResumeImprovementRepository resumeImprovementRepository,
            GeminiClient geminiClient) {

        this.resumeAnalysisRepository = resumeAnalysisRepository;
        this.resumeImprovementRepository = resumeImprovementRepository;
        this.geminiClient = geminiClient;
    }

    @Override
    public ResumeImproveResponse improveResume(Long resumeId) {

        ResumeAnalysis analysis = resumeAnalysisRepository
                .findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume Analysis not found"));

        String prompt = """
                You are an expert ATS Resume Writer.

                Improve this resume.

                Return ONLY valid JSON.

                Do NOT use markdown.

                Do NOT use ```json.

                Return exactly this structure:

                {
                  "professionalSummary": "",
                  "improvedSkills": "",
                  "improvedProjects": "",
                  "atsKeywords": "",
                  "finalSuggestions": "",
                  "improvedScore": 0
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

            ResumeImproveResponse response = objectMapper.readValue(
                    aiAnswer,
                    ResumeImproveResponse.class);

            ResumeImprovement entity = ResumeImprovement.builder()
                    .resumeId(resumeId)
                    .professionalSummary(response.getProfessionalSummary())
                    .improvedSkills(response.getImprovedSkills())
                    .improvedProjects(response.getImprovedProjects())
                    .atsKeywords(response.getAtsKeywords())
                    .finalSuggestions(response.getFinalSuggestions())
                    .improvedScore(response.getImprovedScore())
                    .build();

            resumeImprovementRepository.save(entity);

            return response;

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to parse AI response");

        }
    }
}