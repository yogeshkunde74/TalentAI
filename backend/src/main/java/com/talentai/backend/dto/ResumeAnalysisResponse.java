package com.talentai.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeAnalysisResponse {

    private int score;

    private List<String> skills;

    private List<String> missingSkills;

    private List<String> strengths;

    private List<String> suggestions;
}