package com.talentai.backend.ai.service;

import com.talentai.backend.ai.dto.ResumeAnalysisResponse;

public interface AIService {

    ResumeAnalysisResponse analyzeResume(String resumeText);

}