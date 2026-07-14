package com.talentai.backend.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeAnalysisResponse {

    private ResumeScore resumeScore;

    private List<String> skills;

    private List<String> missingSkills;

    private List<String> strengths;

    private List<String> weaknesses;

    private List<InterviewQuestion> interviewQuestions;

}