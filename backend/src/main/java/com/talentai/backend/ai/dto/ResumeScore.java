package com.talentai.backend.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeScore {

    private int overallScore;

    private int technicalScore;

    private int communicationScore;

    private int experienceScore;

}