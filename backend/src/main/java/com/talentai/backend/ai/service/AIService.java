package com.talentai.backend.ai.service;

import java.util.List;

import com.talentai.backend.ai.dto.ResumeAnalysisResponse;
import com.talentai.backend.ai.entity.ResumeAnalysis;

public interface AIService {

    ResumeAnalysisResponse analyzeResume(String resumeText);

    List<ResumeAnalysis> getAllResumeAnalysis();
}