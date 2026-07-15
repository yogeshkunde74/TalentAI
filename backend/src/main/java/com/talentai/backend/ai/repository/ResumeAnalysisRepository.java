package com.talentai.backend.ai.repository;

import com.talentai.backend.ai.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository
        extends JpaRepository<ResumeAnalysis, Long> {

}