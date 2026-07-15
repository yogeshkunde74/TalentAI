package com.talentai.backend.job.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobMatchResponse {

    private int matchScore;

    private List<String> matchedSkills;

    private List<String> missingSkills;

    private String summary;

    private String recommendation;

}