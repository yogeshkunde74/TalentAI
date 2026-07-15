package com.talentai.backend.ai.service.impl;

import java.util.List;
import com.talentai.backend.ai.entity.ResumeAnalysis;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.talentai.backend.ai.client.GeminiClient;
import com.talentai.backend.ai.dto.ResumeAnalysisResponse;
import com.talentai.backend.ai.service.AIService;
import org.springframework.stereotype.Service;
import com.talentai.backend.ai.repository.ResumeAnalysisRepository;
//import com.talentai.backend.ai.entity.ResumeAnalysis;

@Service
public class AIServiceImpl implements AIService {

  private final GeminiClient geminiClient;
  private final ResumeAnalysisRepository resumeAnalysisRepository;
  private final ObjectMapper objectMapper = new ObjectMapper();

  public AIServiceImpl(
      GeminiClient geminiClient,
      ResumeAnalysisRepository resumeAnalysisRepository) {

    this.geminiClient = geminiClient;
    this.resumeAnalysisRepository = resumeAnalysisRepository;
  }

  @Override
  public ResumeAnalysisResponse analyzeResume(String resumeText) {

    String prompt = """
        You are an ATS Resume Analyzer.

        Analyze the following resume.

        Return ONLY valid JSON.

        Do NOT use markdown.

        Do NOT use ```json.

        Do NOT explain anything.

        Return exactly this structure:

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

    String aiAnswer = geminiClient.generateContent(prompt);

    aiAnswer = aiAnswer
        .replace("```json", "")
        .replace("```", "")
        .trim();

    System.out.println(aiAnswer);

    try {

      ResumeAnalysisResponse response = objectMapper.readValue(
          aiAnswer,
          ResumeAnalysisResponse.class);

      ResumeAnalysis entity = ResumeAnalysis.builder()
          .overallScore(response.getResumeScore().getOverallScore())
          .technicalScore(response.getResumeScore().getTechnicalScore())
          .communicationScore(response.getResumeScore().getCommunicationScore())
          .experienceScore(response.getResumeScore().getExperienceScore())

          .skills(String.join(", ", response.getSkills()))
          .missingSkills(String.join(", ", response.getMissingSkills()))
          .strengths(String.join(", ", response.getStrengths()))
          .weaknesses(String.join(", ", response.getWeaknesses()))

          .interviewQuestions(
              objectMapper.writeValueAsString(
                  response.getInterviewQuestions()))

          .build();

      resumeAnalysisRepository.save(entity);
      return response;

    } catch (Exception e) {

      e.printStackTrace();

      throw new RuntimeException("Failed to parse AI response");

    }
  }

  @Override
  public List<ResumeAnalysis> getAllResumeAnalysis() {

    return resumeAnalysisRepository.findAll();

  }
}