package com.talentai.backend.improve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeImproveResponse {

    private String professionalSummary;

    private String improvedSkills;

    private String improvedProjects;

    private String atsKeywords;

    private String finalSuggestions;

    private int improvedScore;

}