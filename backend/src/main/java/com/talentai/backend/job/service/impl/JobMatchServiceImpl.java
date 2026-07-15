package com.talentai.backend.job.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentai.backend.ai.client.GeminiClient;
import com.talentai.backend.ai.entity.ResumeAnalysis;
import com.talentai.backend.ai.repository.ResumeAnalysisRepository;
import com.talentai.backend.job.dto.JobMatchResponse;
import com.talentai.backend.job.repository.JobMatchRepository;
import com.talentai.backend.job.service.JobMatchService;
import org.springframework.stereotype.Service;
import com.talentai.backend.job.entity.JobMatch;

@Service
public class JobMatchServiceImpl implements JobMatchService {

    private final ResumeAnalysisRepository resumeAnalysisRepository;
    private final JobMatchRepository jobMatchRepository;
    private final GeminiClient geminiClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JobMatchServiceImpl(
            ResumeAnalysisRepository resumeAnalysisRepository,
            JobMatchRepository jobMatchRepository,
            GeminiClient geminiClient) {

        this.resumeAnalysisRepository = resumeAnalysisRepository;
        this.jobMatchRepository = jobMatchRepository;
        this.geminiClient = geminiClient;
    }

    @Override
    public JobMatchResponse matchResume(
            Long resumeId,
            String jobDescription) {

        ResumeAnalysis analysis = resumeAnalysisRepository
                .findById(resumeId)
                .orElseThrow(() -> new RuntimeException("Resume Analysis not found"));

        String prompt = """
                You are an ATS AI.

                Compare the following resume with the Job Description.

                Return ONLY valid JSON.

                Do NOT use markdown.

                Do NOT use ```json.

                Return exactly this structure:

                {
                  "matchScore": 0,
                  "matchedSkills": [],
                  "missingSkills": [],
                  "summary": "",
                  "recommendation": ""
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
                + analysis.getWeaknesses()
                + """

                        Job Description:
                        """
                + jobDescription;

        String aiAnswer = geminiClient.generateContent(prompt);

        aiAnswer = aiAnswer
                .replace("```json", "")
                .replace("```", "")
                .trim();

        System.out.println(aiAnswer);

        try {

            JobMatchResponse response = objectMapper.readValue(
                    aiAnswer,
                    JobMatchResponse.class);

            JobMatch entity = JobMatch.builder()
                    .resumeId(resumeId)
                    .jobDescription(jobDescription)
                    .matchScore(response.getMatchScore())
                    .matchedSkills(String.join(", ", response.getMatchedSkills()))
                    .missingSkills(String.join(", ", response.getMissingSkills()))
                    .summary(response.getSummary())
                    .recommendation(response.getRecommendation())
                    .build();

            jobMatchRepository.save(entity);

            return response;
        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException("Failed to parse AI response");

        }
    }
}